package dev.ngdangkietswe.swedebezium.configs;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author ngdangkietswe
 * @since 12/13/2024
 */

@ConfigurationProperties(prefix = "database")
@Getter
@Setter
public class DebeziumDBProperties {

    private String host;
    private Integer port;
    private String username;
    private String password;
}
