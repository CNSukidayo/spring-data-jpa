package com.cnsukidayo.jpa.test;

import com.cnsukidayo.jpa.config.SpringDataJPAConfig;
import com.cnsukidayo.jpa.proxy.MyJPAProxy;
import com.cnsukidayo.jpa.pojo.Customer;
import com.cnsukidayo.jpa.repository.CustomerRepository;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import javax.persistence.EntityManager;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Proxy;
import java.lang.reflect.Type;
import java.util.Optional;

/**
 * @author sukidayo
 * @date 2024/1/7 19:14
 */
public class JPAProxyTest {

    @Test
    public void testP() throws ClassNotFoundException {
        // 创建Spring容器
        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(SpringDataJPAConfig.class);
        // 从容器中获取获取LocalContainerEntityManagerFactoryBean然后得到EntityManager
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = annotationConfigApplicationContext.getBean(LocalContainerEntityManagerFactoryBean.class);
        EntityManager entityManager = entityManagerFactoryBean.createNativeEntityManager(null);
        // 通过反射获取当前接口泛型中指定的泛型类型
        ParameterizedType parameterizedType = (ParameterizedType) CustomerRepository.class.getGenericInterfaces()[0];
        Type type = parameterizedType.getActualTypeArguments()[0];
        Class<?> aClass = Class.forName(type.getTypeName());
        CustomerRepository customerRepository = (CustomerRepository) Proxy.newProxyInstance(
                CustomerRepository.class.getClassLoader(),
                new Class[]{CustomerRepository.class},
                new MyJPAProxy(entityManager, aClass)
        );
        Optional<Customer> customer = customerRepository.findById(10L);
        System.out.println("输出结果:" + customer);
    }

    @Test
    public void testS() throws ClassNotFoundException {
        // 创建Spring容器
        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(SpringDataJPAConfig.class);
        CustomerRepository customerRepository = annotationConfigApplicationContext.getBean(CustomerRepository.class);
        Optional<Customer> customer = customerRepository.findById(1L);
        System.out.println(customer);
    }



}
