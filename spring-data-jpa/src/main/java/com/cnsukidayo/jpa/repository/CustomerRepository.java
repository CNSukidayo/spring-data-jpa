package com.cnsukidayo.jpa.repository;

import com.cnsukidayo.jpa.pojo.Customer;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author guyuanjie
 * @date 2024/1/6 13:26
 */
public interface CustomerRepository extends PagingAndSortingRepository<Customer, Long> {

    /**
     * 在JPA中如果要查询表中所有字段则可以省略select * 直接从from开始写
     * 这里from的后面是实体类,而不是表
     * where后面的条件customerName也是实体类的属性
     * 并且该方法的参数是通过顺序来指定的,即?1占位符表示方法的第一个参数
     */
    @Query("from Customer where customerName = ?1")
    Customer findCustomerByCustomerNameWithOrder(String customerName);

    /**
     * 这里如果想要使用参数名来指代占位符,不能直接使用形参的名称
     * 而必须指定@Param注解,在该注解中指定该占位符的名称与形参的对应情况
     * 并且必须要加冒号:
     */
    @Query("from Customer where customerName =: customerName")
    Customer findCustomerByCustomerNameWithName(@Param("customerName") String customerName);

    /**
     * 更新操作
     * 在spring-data-jpa中,增删改操作必须使用事务;
     *
     * @Transactional 注解可以标注在service层, 也可以标注在dao层;
     * 此外还必须标注@Modifying注解告知spring-data-jpa这是增删改的操作
     */
    @Modifying
    @Transactional
    @Query("update Customer c set c.customerName =: customerName where c.customerId =: customerId")
    int updateCustomer(@Param("customerName") String customerName, @Param("customerId") Long customerId);

    // 删除操作
    @Modifying
    @Transactional
    @Query("delete from Customer c where c.customerId = ?1")
    int deleteCustomer(Long id);

    /**
     * JPQL其实是不支持新增的
     * 但可以通过hibernate实现伪新增的方式
     */
    @Modifying
    @Transactional
    @Query("insert into Customer(customerName) select c.customer from Customer c where c.customerId = ?1")
    int insertCustomerBySelect(Long id);

    @Query(value = "select * from tb_customer where customer_name =: customerName",nativeQuery = true)
    Customer findCustomerByCustomerNameWithNative(@Param("customerName") String customerName);


}
