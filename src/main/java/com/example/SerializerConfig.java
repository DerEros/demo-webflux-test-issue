package com.example;

import com.example.model.ReverserResult;
import com.example.serializer.ReverserResultSerializer;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SerializerConfig {
    @Bean
    @Autowired public Module specificSerializers(ReverserResultSerializer reverserResultSerializer) {
        SimpleModule serializerModule = new SimpleModule();
        serializerModule.addSerializer(ReverserResult.class, reverserResultSerializer);

        return serializerModule;
    }
}
