package ru.romanow.restful;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

/**
 * Created by romanow on 19.10.16
 */
@Configuration
public class WebConfiguration {

    @Bean
    public Validator validator() {
        return new LocalValidatorFactoryBean();
    }
}
