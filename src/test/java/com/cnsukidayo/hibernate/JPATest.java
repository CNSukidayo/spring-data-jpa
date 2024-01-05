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

}
