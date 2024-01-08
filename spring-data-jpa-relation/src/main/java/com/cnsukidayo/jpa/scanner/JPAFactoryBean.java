package com.cnsukidayo.jpa.scanner;

import com.cnsukidayo.jpa.proxy.MyJPAProxy;
import com.cnsukidayo.jpa.repository.CustomerRepository;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import javax.persistence.EntityManager;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Proxy;
import java.lang.reflect.Type;

/**
 * @author guyuanjie
 * @date 2024/1/8 13:28
 */
public class JPAFactoryBean implements FactoryBean {

    @Autowired
    private LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean;

    private Class<?> repositoryInterface;

    public JPAFactoryBean(Class<?> repositoryInterface) {
        this.repositoryInterface = repositoryInterface;
    }

    /**
     * 随意控制实例化过程
     */
    @Override
    public Object getObject() throws Exception {
        // 这段代码就是之前动态代理的代码
        EntityManager entityManager = localContainerEntityManagerFactoryBean.createNativeEntityManager(null);
        ParameterizedType parameterizedType = (ParameterizedType) CustomerRepository.class.getGenericInterfaces()[0];
        Type type = parameterizedType.getActualTypeArguments()[0];
        Class<?> aClass = Class.forName(type.getTypeName());
        return Proxy.newProxyInstance(
                CustomerRepository.class.getClassLoader(),
                new Class[]{CustomerRepository.class},
                new MyJPAProxy(entityManager, aClass)
        );
    }

    /**
     * 该方法的返回值,也就是Spring在实例化一个Bean之前,会先判断该Bean
     * 的类型与该方法返回的类型是否匹配,如果匹配的话就调用getObject()方法
     */
    @Override
    public Class<?> getObjectType() {
        return repositoryInterface;
    }
}
