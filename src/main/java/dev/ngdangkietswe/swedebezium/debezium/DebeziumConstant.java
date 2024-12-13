package dev.ngdangkietswe.swedebezium.debezium;

/**
 * @author ngdangkietswe
 * @since 12/13/2024
 */

public class DebeziumConstant {

    // Snapshot Mode
    public static final String SNAPSHOT_MODE_ALWAYS = "always";
    public static final String SNAPSHOT_MODE_INITIAL = "initial";
    public static final String SNAPSHOT_MODE_INITIAL_ONLY = "initial_only";

    // Failure Handling Mode
    public static final String FAILURE_HANDLING_MODE_WARN = "warn";
    public static final String FAILURE_HANDLING_MODE_FAIL = "fail";
    public static final String FAILURE_HANDLING_MODE_SKIP = "skip";

    // Operation Type
    public static final String OPERATION_TYPE_CREATE = "c";
    public static final String OPERATION_TYPE_UPDATE = "u";
    public static final String OPERATION_TYPE_DELETE = "d";
    public static final String OPERATION_TYPE_TRUNCATE = "t";

    // DEFAULT
    public static final String DEFAULT_CONNECTOR_CLASS = "io.debezium.connector.postgresql.PostgresConnector";
    public static final String DEFAULT_PLUGIN_NAME = "pgoutput";
    public static final String DEFAULT_SLOT_NAME = "debezium";
    public static final Boolean DEFAULT_SLOT_DROP_ON_STOP = false;
    public static final String DEFAULT_PUBLICATION_NAME = "dbz_publication";
    public static final String DEFAULT_PUBLICATION_AUTO_CREATE_MODE = "filtered";
    public static final Integer DEFAULT_HEARTBEAT_INTERVAL_MS = 5000;
    public static final Boolean DEFAULT_FLUSH_LSN_SOURCE = true;
    public static final String DEFAULT_TOPIC_HEARTBEAT_PREFIX = "debezium-heartbeat";
    public static final String DEFAULT_SNAPSHOT_MODE = SNAPSHOT_MODE_ALWAYS;
    public static final String DEFAULT_FAILURE_HANDLING_MODE = FAILURE_HANDLING_MODE_WARN;
    public static final String DEFAULT_SKIPPED_OPERATIONS = OPERATION_TYPE_TRUNCATE;
    public static final String DEFAULT_TOPIC_TRANSACTION_PREFIX = "debezium-transaction";
    public static final Boolean DEFAULT_KEY_CONVERTER_SCHEMAS_ENABLE = false;
    public static final Boolean DEFAULT_VALUE_CONVERTER_SCHEMAS_ENABLE = false;
    public static final Boolean DEFAULT_INCLUDE_SCHEMA_CHANGES = false;
    public static final String DEFAULT_KEY_CONVERTER = "org.apache.kafka.connect.json.JsonConverter";
    public static final String DEFAULT_VALUE_CONVERTER = "org.apache.kafka.connect.json.JsonConverter";
}