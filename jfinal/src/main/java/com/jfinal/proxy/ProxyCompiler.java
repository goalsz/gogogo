/**
 * Copyright (c) 2011-2019, James Zhan 詹波 (jfinal@126.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.jfinal.proxy;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import javax.tools.DiagnosticCollector;
import javax.tools.FileObject;
import javax.tools.ForwardingJavaFileManager;
import javax.tools.JavaCompiler;
import javax.tools.JavaFileManager;
import javax.tools.JavaFileObject;
import javax.tools.SimpleJavaFileObject;
import javax.tools.ToolProvider;
import com.jfinal.log.Log;

/**
 * ProxyCompiler
 * 
 * https://www.programcreek.com/java-api-examples/?api=javax.tools.JavaCompiler
 */
public class ProxyCompiler {
	
	protected static final Log log = Log.getLog(ProxyCompiler.class);
	
	protected List<String> options = Arrays.asList("-target", "1.8" /*, "-parameters"*/);
	
	public void compile(ProxyClass proxyClass) {
		JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
		if (compiler == null) {
			throw new RuntimeException("Can not get javax.tools.JavaCompiler, check whether \"tools.jar\" is in the environment variable CLASSPATH");
		}
		
		DiagnosticCollector<JavaFileObject> collector = new DiagnosticCollector<>();
		try (MyJavaFileManager javaFileManager = new MyJavaFileManager(compiler.getStandardFileManager(collector, null, null))) {
			
			MyJavaFileObject javaFileObject = new MyJavaFileObject(proxyClass.getName(), proxyClass.getSourceCode());
			Boolean result = compiler.getTask(null, javaFileManager, collector, options, null, Arrays.asList(javaFileObject)).call();
			if (! result) {
				collector.getDiagnostics().forEach(item -> log.error(item.toString()));
			}
			
			Map<String, byte[]> ret = new HashMap<>();
			for (Entry<String, MyJavaFileObject> e : javaFileManager.fileObjects.entrySet()) {
				ret.put(e.getKey(), e.getValue().getByteCode());
			}
			
			proxyClass.setByteCode(ret);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	public ProxyCompiler setCompileOptions(List<String> options) {
		Objects.requireNonNull(options, "options can not be null");
		this.options = options;
		return this;
	}
	
	public ProxyCompiler addCompileOption(String option) {
		Objects.requireNonNull(option, "option can not be null");
		options.add(option);
		return this;
	}
	
	public static class MyJavaFileObject extends SimpleJavaFileObject {
		
		private String source;
		private ByteArrayOutputStream outPutStream;
		
		public MyJavaFileObject(String name, String source) {
			super(URI.create("String:///" + name + Kind.SOURCE.extension), Kind.SOURCE);
			this.source = source;
		}
		
		public MyJavaFileObject(String name, Kind kind) {
			super(URI.create("String:///" + name + kind.extension), kind);
			source = null;
		}
		
		@Override
		public CharSequence getCharContent(boolean ignoreEncodingErrors) {
			if (source == null) {
				throw new IllegalStateException("source field can not be null");
			}
			return source;
		}
		
		@Override
		public OutputStream openOutputStream() throws IOException {
			outPutStream = new ByteArrayOutputStream();
			return outPutStream;
		}
		
		public byte[] getByteCode() {
			return outPutStream.toByteArray();
		}
	}
	
	public static class MyJavaFileManager extends ForwardingJavaFileManager<JavaFileManager> {
		
		public Map<String, MyJavaFileObject> fileObjects = new HashMap<>();
		
		protected MyJavaFileManager(JavaFileManager fileManager) {
			super(fileManager);
		}
		
		@Override
		public JavaFileObject getJavaFileForOutput(Location location, String qualifiedClassName, JavaFileObject.Kind kind, FileObject sibling) throws IOException {
			MyJavaFileObject javaFileObject = new MyJavaFileObject(qualifiedClassName, kind);
			fileObjects.put(qualifiedClassName, javaFileObject);
			return javaFileObject;
		}
		
		// 是否在编译时依赖另一个类的情况下用到本方法 ?
		@Override
		public JavaFileObject getJavaFileForInput(Location location, String className, JavaFileObject.Kind kind) throws IOException {
			JavaFileObject javaFileObject = fileObjects.get(className);
			if (javaFileObject == null) {
				javaFileObject = super.getJavaFileForInput(location, className, kind);
			}
			return javaFileObject;
		}
	}
}








