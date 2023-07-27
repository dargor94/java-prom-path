package org.dargor.customer.app.client.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Setter
@Getter
@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "routing.product-ms")
public class ProductClientProperties {

    private String id;
    private String url;
    private String port;
    private String host;
    private String wishlistUrl;
    private String createUrl;

}
