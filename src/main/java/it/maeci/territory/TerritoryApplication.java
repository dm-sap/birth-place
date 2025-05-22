package it.maeci.territory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootConfiguration
@EnableJpaRepositories(basePackages = "it.maeci.territory")
@EntityScan(basePackages = "it.maeci.territory")
@ComponentScan(basePackages = "it.maeci.territory")
@ConfigurationPropertiesScan(value = "it.maeci.territory")
@PropertySource(value = {"classpath:territorio-services.properties",
        "file:${catalina.home}/conf/territorio-services.properties"}, ignoreResourceNotFound = true)
@EnableAutoConfiguration
public class TerritoryApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(TerritoryApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(TerritoryApplication.class);
    }
}
