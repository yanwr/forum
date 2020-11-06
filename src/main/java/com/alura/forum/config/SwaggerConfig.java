package com.alura.forum.config;

import com.alura.forum.models.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import java.util.Collections;

@Configuration
public class SwaggerConfig {

  @Bean
  public Docket docket() {
    return new Docket(DocumentationType.SWAGGER_2)
      .select()
      .apis(RequestHandlerSelectors.basePackage("com.alura.forum"))
      .paths(PathSelectors.ant("/**"))
      .build()
      .ignoredParameterTypes(User.class)
      .globalOperationParameters(
        Collections.singletonList(
          new ParameterBuilder()
            .name("Authorization")
            .description("Header to put token")
            .modelRef(new ModelRef("string"))
            .parameterType("header")
            .required(false)
            .build()
        )
      );
  }
}
