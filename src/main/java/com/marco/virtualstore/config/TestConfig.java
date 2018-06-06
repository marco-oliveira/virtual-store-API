package com.marco.virtualstore.config;

import com.marco.virtualstore.services.DBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.text.ParseException;

/**
 * Created by Marco Antônio on 06/06/2018
 */

@Configuration
@Profile("test")
public class TestConfig {

    @Autowired
    private DBService dbService;

    @Bean
    public boolean instantiateDataBase() throws ParseException {
        this.dbService.instantiateTestDataBase();
        return true;
    }
}
