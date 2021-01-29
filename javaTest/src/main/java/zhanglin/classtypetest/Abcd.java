package zhanglin.classtypetest;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;

public abstract class Abcd<T, T1> {

    
    
    public void log() {
    	Type[] types = ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments();
    	System.out.println(Arrays.toString(types));
    	Class<T> clazz = (Class<T>) types[0];
    	System.out.println(clazz);
    }
    
    
}
