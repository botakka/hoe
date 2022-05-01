package hu.oe.hoe.bards;

import hu.oe.hoe.base.OpenApiApplication;
import hu.oe.hoe.model.Hero;
import hu.oe.hoe.model.Hybrid;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableAutoConfiguration
@EnableAspectJAutoProxy
@EnableCaching
@SpringBootApplication
@EntityScan(basePackageClasses = {Hero.class, Hybrid.class})
@EnableJpaRepositories(basePackageClasses = {BardRepository.class, HybridRepository.class})
@ComponentScan(basePackageClasses = {BardResource.class})
@OpenAPIDefinition(
    security = {
      @SecurityRequirement(
          name = "jwt-token",
          scopes = {"admin", "user"})
    },
    info =
        @io.swagger.v3.oas.annotations.info.Info(
            title = "Bards of Empires - Bard",
            version = "v1",
            description = "OE Microservice Project",
            license =
                @io.swagger.v3.oas.annotations.info.License(
                    name = "Apache 2.0",
                    url = "http://foo.bar"),
            contact =
                @Contact(
                    url = "http://valami.com",
                    name = "Karóczkai (K)risztián",
                    email = "krisztian@mozilla.hu")))
public class Application extends OpenApiApplication {

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }
}