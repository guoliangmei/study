package csp;

import org.hswebframework.web.organizational.authorization.PersonnelAuthenticationHolder;
import org.hswebframework.web.organizational.authorization.PersonnelAuthenticationSupplier;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomerAutoConfig implements BeanPostProcessor {

    @Bean
    @ConditionalOnMissingBean(RoleOrgScopeDataAccessHandler.class)
    public RoleOrgScopeDataAccessHandler roleOrgScopeDataAccessHandler() {
        return new RoleOrgScopeDataAccessHandler();
    }

    @Override
    public Object postProcessBeforeInitialization(Object o, String s) throws BeansException {
        return o;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String s) throws BeansException {
        if (bean instanceof PersonnelAuthenticationSupplier) {
            PersonnelAuthenticationHolder.addSupplier(((PersonnelAuthenticationSupplier) bean));
        }
        return bean;
    }
}
