package com.marco.virtualstore.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.marco.virtualstore.domains.PagamentoComBoleto;
import com.marco.virtualstore.domains.PagamentoComCartao;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

@Configuration
public class jacksonConfig {

    @Bean
    public Jackson2ObjectMapperBuilder objectMapperBuilder(){
        Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder(){
          public void configure(ObjectMapper objectMapper){
              objectMapper.registerSubtypes(PagamentoComCartao.class);
              objectMapper.registerSubtypes(PagamentoComBoleto.class);
              super.configure(objectMapper);
          }
        };
        return builder;
    }
}
