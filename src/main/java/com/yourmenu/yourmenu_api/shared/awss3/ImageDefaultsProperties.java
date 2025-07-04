package com.yourmenu.yourmenu_api.shared.awss3;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Data
@ConfigurationProperties(prefix = "yourmenu.images")
public class ImageDefaultsProperties {

    private String defaultCapaRestaurant;
    private String defaultIconRestaurant;
    private String defaultVisualDish;
}

