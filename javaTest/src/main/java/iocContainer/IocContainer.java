package iocContainer;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class IocContainer {

    // 存储bean的集合
    private Map<String ,Object> beans = new ConcurrentHashMap<>();

    public Object getBean(String id){
        return beans.get(id);
    }

    // bean注册方法,需要传入bean的id，bean的类，多个参数的id们
    public void setBean(String id, Class<?> clazz, String... refIds){
        Object[] params = new Object[refIds.length];
        for (int i = 0; i < params.length; i++ ) {
            params[i] = beans.get(refIds[i]);
        }
        Constructor<?>[] constructors = clazz.getConstructors();
        Object bean = null;
        for (Constructor constructor: constructors ) {
            try {
                if (constructor.getParameterCount() == refIds.length)
                bean = constructor.newInstance(params);
            } catch (InstantiationException e) {
            } catch (IllegalAccessException e) {
            } catch (InvocationTargetException e) {
            }
        }
        if(bean == null){
            throw new RuntimeException("找不到对应的构造");
        }
        beans.put(id, bean);
    }

}
