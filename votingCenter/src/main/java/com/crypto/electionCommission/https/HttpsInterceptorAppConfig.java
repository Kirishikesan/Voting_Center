package com.crypto.electionCommission.https;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class HttpsInterceptorAppConfig implements WebMvcConfigurer{

		@Autowired
		HttpsInterceptor httpsInterceptor;
		
		@Override
		public void addInterceptors(InterceptorRegistry registry) {
			registry.addInterceptor(httpsInterceptor);
		}
}
