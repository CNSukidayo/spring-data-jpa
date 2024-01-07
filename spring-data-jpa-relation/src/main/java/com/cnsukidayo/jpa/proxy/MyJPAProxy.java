package com.cnsukidayo.jpa.proxy;

import javax.persistence.EntityManager;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author sukidayo
 * @date 2024/1/7 19:15
 */
public class MyJPAProxy implements InvocationHandler {

    private EntityManager entityManager;
    private Class aClass;

    public MyJPAProxy(EntityManager entityManager, Class aClass) {
        this.entityManager = entityManager;
        this.aClass = aClass;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        MyJPARepository myJPAProxy = new MyJPARepository(entityManager, aClass);
        Method proxyMethod = myJPAProxy.getClass().getMethod(method.getName(), method.getParameterTypes());
        return proxyMethod.invoke(myJPAProxy, args);
    }
}
