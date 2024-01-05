package com.cnsukidayo.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 * @author guyuanjie
 * @date 2024/1/5 17:27
 */
public class HibernateTest {
    // session工厂,类似mybatis中的sqlSession;是数据库的一次会话
    private SessionFactory sessionFactory;

    @Before
    public void init() {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure("/hibernate.cfg.xml").build();
        // 根据服务注册类创建一个元数据资源集,同时构建元数据并生成应用
        sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
    }

    @Test
    public void testC() {
        // 通过Session进行持久化操作
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Customer customer = new Customer();
        customer.setCustomerName("蔡徐坤");
        session.save(customer);
        transaction.commit();
        session.close();
    }

    @Test
    public void testR() {
        // 通过Session进行持久化操作
        Session session = sessionFactory.openSession();
        Customer customer = session.find(Customer.class, 1L);
        System.out.println(customer);
        session.close();
    }

    @Test
    public void testR_lazy() {
        // 通过Session进行持久化操作
        Session session = sessionFactory.openSession();
        Customer customer = session.load(Customer.class, 1L);
        System.out.println("=======================");
        System.out.println(customer);
        session.close();
    }

    @Test
    public void testU() {
        Session session = sessionFactory.openSession();
        Customer customer = new Customer();
        //customer.setCustomerId(1L);
        customer.setCustomerName("鸡哥");
        // 如果设置了id则会更新,如果没有设置id则会插入数据
        session.saveOrUpdate(customer);
        session.close();
    }

    @Test
    public void testJPQL() {
        Session session = sessionFactory.openSession();
        // JPQL可以省略 select * 步骤;而且这里from的不是一张表,而是一个对象
        // 这里customerId使用的不是数据库的字段,而是对象的字段,并且后面的:id是占位符的意思,在setParameter中指定查询的具体参数即可
        String jpql = "from Customer where customerId=:id";
        List<Customer> resultList = session.createQuery(jpql, Customer.class)
                .setParameter("id",1L)
                .getResultList();
        System.out.println(resultList);
        session.close();
    }


}