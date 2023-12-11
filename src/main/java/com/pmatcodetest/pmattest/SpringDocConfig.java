package com.pmatcodetest.pmattest;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@OpenAPIDefinition
@Configuration
public class SpringDocConfig {

  @Bean
  public OpenAPI customizeOpenAPI() {
    return new OpenAPI()
      .info(new Info()
        .title("My App API")
        .version("1.0")
        .description("App API documentation")
      );
  }

}
