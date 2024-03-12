package com.hwagae.market.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    private String resourcePath="/upload/**"; //view에서 접근할 경로
    //스프링 프레임워크에서는 servlet에서 지정 하지만 ,boot는 클래스에서 지정 가능
    private String savePath="file:///C:/image/";//실제 파일 저장 경로
    //savePath에  resourcePath로 접근을 하게따~~~
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry){
        //
        registry.addResourceHandler(resourcePath) //view에서 이런경로로 접근하게되면
                .addResourceLocations(savePath); //spring이 요 경로에서 파일을 찾아준다 이 말이래
    }
}
