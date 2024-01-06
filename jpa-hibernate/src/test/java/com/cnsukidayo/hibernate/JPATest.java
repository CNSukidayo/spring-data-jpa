package com.cnsukidayo.hibernate;

import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 * @author guyuanjie
 * @date 2024/1/5 18:22
 */
public class JPATest {

    private EntityManagerFactory entityManagerFactory;

    @Before
    public void init() {
        // args0:指定持久化单元的名称,和persistence.xml配置文件中的name对应
        entityManagerFactory = Persistence.createEntityManagerFactory("hibernateJPA");
    }

    @Test
    public void testC() { 
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        Customer customer = new Customer();
        customer.setCustomerName("鸡哥");
        entityManager.persist(customer);

        transaction.commit();
    }

    @Test
    public void testR() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        // 懒加载查询
        Customer customer = entityManager.getReference(Customer.class, 1L);

        transaction.commit();
    }

    @Test
    public void testD() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        /*
        无法删除处于游离状态的实例
        Customer customer = new Customer();
        customer.setCustomerId(1L);
        */
        // 只有从数据库中查出的实例才可以被删除
        Customer customer = entityManager.find(Customer.class, 1L);
        entityManager.remove(customer);

        transaction.commit();
    }

    @Test
    public void testCache() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        Customer customer0 = entityManager.find(Customer.class, 1L);
        Customer customer1 = entityManager.find(Customer.class, 1L);

        transaction.commit();
    }


}
