package dev.ngdangkietswe.swedebezium.debezium;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @author ngdangkietswe
 * @since 12/13/2024
 */

@Getter
@Setter
public class DebeziumConnector {

    @JsonProperty("name")
    private String name;
    @JsonProperty("connector.class")
    private String connectorClass;
    @JsonProperty("plugin.name")
    private String pluginName;
    @JsonProperty("slot.name")
    private String slotName;
    @JsonProperty("slot.drop.on.stop")
    private Boolean slotDropOnStop;
    @JsonProperty("publication.name")
    private String publicationName;
    @JsonProperty("database.hostname")
    private String databaseHostName;
    @JsonProperty("database.port")
    private Integer databasePort;
    @JsonProperty("database.user")
    private String databaseUser;
    @JsonProperty("database.password")
    private String databasePassword;
    @JsonProperty("database.dbname")
    private String databaseDbName;
    @JsonProperty("topic.prefix")
    private String topicPrefix;
    @JsonProperty("schema.include.list")
    private String schemaIncludeList;
    @JsonProperty("table.include.list")
    private String tableIncludeList;
    @JsonProperty("column.exclude.list")
    private String columnExcludeList;
    @JsonProperty("publication.auto.create.mode")
    private String publicationAutoCreateMode;
    @JsonProperty("snapshot.mode")
    private String snapshotMode;
    @JsonProperty("event.processing.failure.handling.mode")
    private String eventProcessingFailureHandlingMode;
    @JsonProperty("heartbeat.interval.ms")
    private Integer heartbeatIntervalMs;
    @JsonProperty("heartbeat.action.query")
    private String heartbeatActionQuery;
    @JsonProperty("flush.lsn.source")
    private Boolean flushLsnSource;
    @JsonProperty("skipped.operations")
    private String skippedOperations;
    @JsonProperty("topic.heartbeat.prefix")
    private String topicHeartbeatPrefix;
    @JsonProperty("topic.transaction.prefix")
    private String topicTransactionPrefix;
    @JsonProperty("key.converter.schemas.enable")
    private Boolean keyConverterSchemasEnable;
    @JsonProperty("value.converter.schemas.enable")
    private Boolean valueConverterSchemasEnable;
    @JsonProperty("key.converter")
    private String keyConverter;
    @JsonProperty("value.converter")
    private String valueConverter;

    public DebeziumConnector(String connectorName, DebeziumDatabaseConfig databaseConfig) {
        this.name = connectorName;
        this.connectorClass = DebeziumConstant.DEFAULT_CONNECTOR_CLASS;
        this.pluginName = DebeziumConstant.DEFAULT_PLUGIN_NAME;
        this.slotName = String.format("%s_%s", DebeziumConstant.DEFAULT_SLOT_NAME, connectorName);
        this.slotDropOnStop = DebeziumConstant.DEFAULT_SLOT_DROP_ON_STOP;
        this.publicationName = DebeziumConstant.DEFAULT_PUBLICATION_NAME;
        this.databaseHostName = databaseConfig.getHost();
        this.databasePort = databaseConfig.getPort();
        this.databaseUser = databaseConfig.getUsername();
        this.databasePassword = databaseConfig.getPassword();
        this.databaseDbName = databaseConfig.getDatabaseName();
        this.topicPrefix = connectorName;
        this.schemaIncludeList = databaseConfig.getSchemaIncludeList();
        this.tableIncludeList = databaseConfig.getTableIncludeList();
        this.columnExcludeList = databaseConfig.getColumnExcludeList();
        this.publicationAutoCreateMode = DebeziumConstant.DEFAULT_PUBLICATION_AUTO_CREATE_MODE;
        this.snapshotMode = DebeziumConstant.DEFAULT_SNAPSHOT_MODE;
        this.eventProcessingFailureHandlingMode = DebeziumConstant.DEFAULT_FAILURE_HANDLING_MODE;
        this.heartbeatIntervalMs = DebeziumConstant.DEFAULT_HEARTBEAT_INTERVAL_MS;
        this.heartbeatActionQuery = getHeartbeatActionQuery(connectorName);
        this.flushLsnSource = DebeziumConstant.DEFAULT_FLUSH_LSN_SOURCE;
        this.skippedOperations = DebeziumConstant.DEFAULT_SKIPPED_OPERATIONS;
        this.topicHeartbeatPrefix = DebeziumConstant.DEFAULT_TOPIC_HEARTBEAT_PREFIX;
        this.topicTransactionPrefix = DebeziumConstant.DEFAULT_TOPIC_TRANSACTION_PREFIX;
        this.keyConverterSchemasEnable = DebeziumConstant.DEFAULT_KEY_CONVERTER_SCHEMAS_ENABLE;
        this.valueConverterSchemasEnable = DebeziumConstant.DEFAULT_VALUE_CONVERTER_SCHEMAS_ENABLE;
        this.keyConverter = DebeziumConstant.DEFAULT_KEY_CONVERTER;
        this.valueConverter = DebeziumConstant.DEFAULT_VALUE_CONVERTER;
    }

    public String getHeartbeatActionQuery(String connectorName) {
        String query = "set search_path to swe%s; " +
                "insert into cdc_heartbeat (connector, created_date) " +
                "values ('%s', now()) " +
                "on conflict (connector) " +
                "do update set created_date = now()";
        return String.format(query, connectorName, connectorName);
    }
}