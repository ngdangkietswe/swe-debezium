package dev.ngdangkietswe.swedebezium.debezium;

import dev.ngdangkietswe.swedebezium.components.ApplicationContextProvider;
import dev.ngdangkietswe.swedebezium.configs.DebeziumDBProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;

/**
 * @author ngdangkietswe
 * @since 12/13/2024
 */

@Getter
@Setter
public class DebeziumDatabaseConfig {

    private String host;
    private Integer port;
    private String username;
    private String password;
    private String databaseName;
    private String schemaIncludeList;
    private String tableIncludeList;
    private String columnExcludeList;

    public DebeziumDatabaseConfig(String databaseName, String schema, String[] tables, String[] excludeColumns) {
        var debeziumDbProperties = ApplicationContextProvider.getBean(DebeziumDBProperties.class);
        this.host = debeziumDbProperties.getHost();
        this.port = debeziumDbProperties.getPort();
        this.username = debeziumDbProperties.getUsername();
        this.password = debeziumDbProperties.getPassword();
        this.databaseName = databaseName;
        this.schemaIncludeList = schema;
        this.tableIncludeList = String.join(",", tables);
        this.columnExcludeList = String.join(",", excludeColumns);
    }

    @Getter
    @AllArgsConstructor
    public enum Service {

        AUTH(
                "auth",
                "SweAuth",
                "sweauth",
                new String[]{
                        "sweauth.users",
                },
                new String[]{}
        );

        private final String service;
        private final String database;
        private final String schema;
        private final String[] tables;
        private final String[] columns;

        public static Service of(String service) {
            return Arrays.stream(Service.values())
                    .filter(s -> s.getService().equals(service))
                    .findFirst()
                    .orElseThrow(() -> new IllegalArgumentException("Invalid service: " + service));
        }
    }

    public static DebeziumDatabaseConfig getDebeziumDatabaseConfig(String service) {
        Service eService = Service.of(service);
        return new DebeziumDatabaseConfig(
                eService.database,
                eService.schema,
                eService.tables,
                eService.columns);
    }
}
