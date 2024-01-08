package com.cnsukidayo.jpa.scanner;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.core.type.filter.AssignableTypeFilter;
import org.springframework.data.repository.Repository;
import org.springframework.stereotype.Component;

/**
 * @author guyuanjie
 * @date 2024/1/8 11:32
 */
@Component
public class JPABeanDefinitionRegistryPostProcessor implements BeanDefinitionRegistryPostProcessor {
    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        // 动态注册扫描器
        JPAClassPathBeanDefinitionScanner jpaClassPathBeanDefinitionScanner = new JPAClassPathBeanDefinitionScanner(registry);
        // 必须实现repository接口
        jpaClassPathBeanDefinitionScanner.addIncludeFilter(new AssignableTypeFilter(Repository.class));
        // 扫描路径
        jpaClassPathBeanDefinitionScanner.scan("com.cnsukidayo.jpa.repository");
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

    }
}
