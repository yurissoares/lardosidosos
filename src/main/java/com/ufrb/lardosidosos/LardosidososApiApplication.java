package com.ufrb.lardosidosos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LardosidososApiApplication {
	public static void main(String[] args) {
		SpringApplication.run(LardosidososApiApplication.class, args);
	}

//	@Bean
//    public WebMvcConfigurer corsConfigurer() 
//    {
//        return new WebMvcConfigurer() {
//            @Override
//            public void addCorsMappings(CorsRegistry registry) {
//                registry.addMapping("/**")
//                	.allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS", "HEAD", "TRACE", "CONNECT");
//            }
//        };
//    }

}
