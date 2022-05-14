package hu.oe.hoe.base;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.OAuthFlow;
import io.swagger.v3.oas.models.security.OAuthFlows;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

public class OpenApiApplication {

  @Value("${keycloak.auth-server-url}")
  private String authUrl;

  @Bean
  public OpenAPI customOpenAPI(@Value("${springdoc.version}") String appVersion) {
    return new OpenAPI()
        .components(
            new Components()
                .addSecuritySchemes(
                    "jwt-token",
                    new SecurityScheme()
                        .type(SecurityScheme.Type.OAUTH2)
                        .bearerFormat("Bearer ")
                        .name("authorization")
                        .description("Keycloak OAUTH2, JWT Token")
                        .flows(
                            new OAuthFlows()
                                .implicit(
                                    new OAuthFlow()
                                        .authorizationUrl(
                                            authUrl + "/realms/hoe/protocol/openid-connect/auth")))
                        .in(SecurityScheme.In.HEADER)))
        .info(
            new Info()
                .title("Heroes of Empires - Spacies API")
                .version(appVersion)
                .license(new License().name("Apache 2.0").url("http://springdoc.org")));
  }
}
