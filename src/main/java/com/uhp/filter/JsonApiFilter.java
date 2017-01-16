package com.uhp.filter;

import io.katharsis.invoker.KatharsisInvokerBuilder;
import io.katharsis.locator.JsonServiceLocator;
import io.katharsis.servlet.AbstractKatharsisFilter;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.stereotype.Component;

/**
 * @author Bogdan Kovalev
 *         Created on 1/16/17.
 */
@Component
public class JsonApiFilter extends AbstractKatharsisFilter implements BeanFactoryAware {

    private static final String DEFAULT_RESOURCE_SEARCH_PACKAGE = "com.uhp";
    private static final String RESOURCE_DEFAULT_DOMAIN = "http://localhost:8080";

    private BeanFactory beanFactory;

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }

    @Override
    protected KatharsisInvokerBuilder createKatharsisInvokerBuilder() {
        KatharsisInvokerBuilder builder = new KatharsisInvokerBuilder();

        builder.resourceSearchPackage(DEFAULT_RESOURCE_SEARCH_PACKAGE).
                resourceDefaultDomain(RESOURCE_DEFAULT_DOMAIN).
                jsonServiceLocator(new JsonServiceLocator() {
                    @Override
                    public <T> T getInstance(Class<T> clazz) {
                        return beanFactory.getBean(clazz);
                    }
                });

        return builder;
    }
}
