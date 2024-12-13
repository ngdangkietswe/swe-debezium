package dev.ngdangkietswe.swedebezium.configs;

import dev.ngdangkietswe.swejavacommonshared.utils.RestTemplateUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author ngdangkietswe
 * @since 12/13/2024
 */

@Configuration
public class RestTemplateConfig {

    @Bean
    public RestTemplateUtil restTemplateUtil() {
        RestTemplate restTemplate = new RestTemplate();
        return new RestTemplateUtil(restTemplate);
    }
}
