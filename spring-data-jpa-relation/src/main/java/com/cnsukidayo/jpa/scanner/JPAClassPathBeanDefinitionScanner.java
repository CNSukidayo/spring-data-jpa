package com.cnsukidayo.jpa.scanner;

import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.context.annotation.ScannedGenericBeanDefinition;
import org.springframework.core.type.AnnotationMetadata;

import java.util.Set;

/**
 * @author guyuanjie
 * @date 2024/1/8 11:25
 */
public class JPAClassPathBeanDefinitionScanner extends ClassPathBeanDefinitionScanner {
    public JPAClassPathBeanDefinitionScanner(BeanDefinitionRegistry registry) {
        super(registry);
    }

    @Override
    protected boolean isCandidateComponent(AnnotatedBeanDefinition beanDefinition) {
        AnnotationMetadata metadata = beanDefinition.getMetadata();
        return metadata.isInterface();
    }

    @Override
    protected Set<BeanDefinitionHolder> doScan(String... basePackages) {
        /*
        这一步是由JPABeanDefinitionRegistryPostProcessor指示的
        beanDefinitionHolders就是所有扫描到的repository
        */
        Set<BeanDefinitionHolder> beanDefinitionHolders = super.doScan(basePackages);
        for (BeanDefinitionHolder beanDefinitionHolder : beanDefinitionHolders) {
            ScannedGenericBeanDefinition beanDefinition = (ScannedGenericBeanDefinition) beanDefinitionHolder.getBeanDefinition();
            // 得到当前repository的真正类型
            String beanClassName = beanDefinition.getBeanClassName();
            // 因为JPAFactoryBean不可能自动注入要动态代理的对象的类型,所以通过构造函数的方式传入
            beanDefinition.getConstructorArgumentValues().addGenericArgumentValue(beanClassName);
            // 偷天换日,将实例化repository的工厂改掉 mybatis也是这么做的
            beanDefinition.setBeanClass(JPAFactoryBean.class);
        }
        return beanDefinitionHolders;
    }
}
