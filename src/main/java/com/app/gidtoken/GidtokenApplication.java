package com.app.gidtoken;

import com.app.gidtoken.service.IdTokenService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class GidtokenApplication {
  private static final String HEADER_AUTHORIZATION = "-H \"Authorization:Bearer %s\"";

  @Autowired
  IdTokenService idTokenService;

  public static void main(String[] args) {
    try (ConfigurableApplicationContext ctx = SpringApplication.run(GidtokenApplication.class, args)) {
      GidtokenApplication app = ctx.getBean(GidtokenApplication.class);
      app.run(args);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void run(String... args) throws Exception {
    if (args.length < 1) {
      throw new Exception("require args1 (service name)");
    }
    com.google.auth.oauth2.IdToken token = idTokenService.getIdToken(args[0], args[1]);
    System.out.println(String.format(HEADER_AUTHORIZATION, token.getTokenValue()));
  }
}
