package com.devjr.app_empleo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class webConfig implements WebMvcConfigurer {

    @Value("${empleosapp.ruta.imagenes}")
    public String rutaImagen;

    @Value("${empleosapp.ruta.cv}")
    public String rutaCV;

    public void addResourceHandlers(ResourceHandlerRegistry registry){
        registry.addResourceHandler("/logos/**").addResourceLocations("file:"+rutaImagen);

        registry.addResourceHandler("/cv/**").addResourceLocations("file:"+rutaCV);
    }

}
