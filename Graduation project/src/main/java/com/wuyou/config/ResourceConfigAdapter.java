package com.wuyou.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Description
 * @Author 无忧
 * @Date 2022/4/14 13:35
 */
@Configuration
public class ResourceConfigAdapter implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        WebMvcConfigurer.super.addResourceHandlers(registry);
        String path = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\";
        String os = System.getProperty("os.name");
        if (os.toLowerCase().startsWith("win")) {
            System.out.println("windows");
            System.out.println(path);
            registry.addResourceHandler("/**").
                    addResourceLocations("file:" + path);
        } else {//linux和mac系统 可以根据逻辑再做处理
            registry.addResourceHandler("/static/**").
                    addResourceLocations("file:" + path);
        }
    }
}
