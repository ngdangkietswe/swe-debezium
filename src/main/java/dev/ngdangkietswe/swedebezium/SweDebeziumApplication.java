package dev.ngdangkietswe.swedebezium;

import dev.ngdangkietswe.swedebezium.configs.DebeziumDBProperties;
import dev.ngdangkietswe.swedebezium.debezium.DebeziumConnector;
import dev.ngdangkietswe.swedebezium.debezium.DebeziumDatabaseConfig;
import dev.ngdangkietswe.swedebezium.debezium.DebeziumService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@RequiredArgsConstructor
@EnableConfigurationProperties(value = DebeziumDBProperties.class)
public class SweDebeziumApplication implements CommandLineRunner {

    private final DebeziumService debeziumService;

    public static void main(String[] args) {
        SpringApplication.run(SweDebeziumApplication.class, args);
    }

    @Override
    public void run(String... args) {
        System.out.println("Registering Debezium connector...");

        var connector = new DebeziumConnector(
                "auth",
                DebeziumDatabaseConfig.getDebeziumDatabaseConfig((DebeziumDatabaseConfig.Service.AUTH.getService()))
        );

        debeziumService.registerConnector(connector.getName(), connector);
    }
}
