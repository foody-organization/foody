package com.project.foody.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.spring6.view.ThymeleafViewResolver;

// 예: WebConfig
@Configuration
public class WebConfig {

    @Bean
    public ThymeleafViewResolver thymeleafViewResolver(SpringTemplateEngine templateEngine) {
        ThymeleafViewResolver r = new ThymeleafViewResolver();
        r.setTemplateEngine(templateEngine); // 여기서 SpringTemplateEngine 타입 사용
        r.setOrder(1);
        r.setViewNames(new String[] {"map", "html/*", "*"});
        return r;
    }

    @Bean
    public InternalResourceViewResolver jspViewResolver() {
        InternalResourceViewResolver r = new InternalResourceViewResolver();
        r.setPrefix("/WEB-INF/jsp/");
        r.setSuffix(".jsp");
        r.setOrder(2);                 // 나중에
        r.setViewNames("jsp/*");       // jsp/ 로 시작하는 이름만 JSP로
        return r;
    }
}
