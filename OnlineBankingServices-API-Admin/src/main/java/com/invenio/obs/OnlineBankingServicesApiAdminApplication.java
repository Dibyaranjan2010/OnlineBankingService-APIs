package com.invenio.obs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import com.invenio.obs.property.FileStorageProperties;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;



@EnableSwagger2
@SpringBootApplication
@EnableConfigurationProperties({
    FileStorageProperties.class
})

public class OnlineBankingServicesApiAdminApplication {

	public static void main(String[] args) {
		SpringApplication.run(OnlineBankingServicesApiAdminApplication.class, args);
	}

	@Bean
    public Docket obsApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()                
                .apis(RequestHandlerSelectors.basePackage("com.invenio.obs"))
               // .paths(regex("/*.*"))
                .build();
    }
	
}
