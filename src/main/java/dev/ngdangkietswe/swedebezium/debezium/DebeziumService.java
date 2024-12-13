package dev.ngdangkietswe.swedebezium.debezium;

import dev.ngdangkietswe.swejavacommonshared.json.JsonConverter;
import dev.ngdangkietswe.swejavacommonshared.utils.RestTemplateUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ngdangkietswe
 * @since 12/13/2024
 */

@Component
@RequiredArgsConstructor
@SuppressWarnings("all")
@Log4j2
public class DebeziumService {

    private final RestTemplateUtil restTemplateUtil;

    @Value("${debezium.address}")
    private String debeziumAddress;

    public void registerConnector(String service, DebeziumConnector connector) {
        if (existsConnector(service)) {
            update(connector);
        } else {
            register(connector);
        }
    }

    public void register(DebeziumConnector connector) {
        String apiUrl = String.format("http://%s/connectors", debeziumAddress);

        Map<String, Object> map = new HashMap<>();
        map.put("name", connector.getName());
        map.put("config", connector);

        try {
            var response = restTemplateUtil.post(apiUrl, JsonConverter.toJson(map), String.class, null);
            if (response.getStatusCode().is2xxSuccessful()) {
                log.info("Register connector [{}] successfully", connector.getName());
            }
        } catch (HttpClientErrorException e) {
            if (e.getStatusCode() == HttpStatus.CONFLICT) {
                log.info("Register connector [{}] failed. Error: {}. Trying to update connector", connector.getName(), e.getMessage());
            }
        }
    }

    public void update(DebeziumConnector connector) {
        String apiUrl = String.format("http://%s/connectors/%s/config", debeziumAddress, connector.getName());

        try {
            var response = restTemplateUtil.put(apiUrl, JsonConverter.toJson(connector), String.class);
            if (response.getStatusCode().is2xxSuccessful()) {
                log.info("Update connector [{}] successfully", connector.getName());
            } else {
                log.info("Update connector [{}] failed", connector.getName());
            }
        } catch (HttpClientErrorException e) {
            log.info("Update connector [{}] failed. Error: {}", connector.getName(), e.getMessage());
        }
    }

    public boolean existsConnector(String connectorName) {
        String apiUrl = String.format("http://%s/connectors/%s", debeziumAddress, connectorName);

        try {
            var response = restTemplateUtil.get(apiUrl, String.class);
            if (response.getStatusCode().is2xxSuccessful()) {
                log.info("Get connector [{}] successfully", connectorName);
                return true;
            } else {
                return false;
            }
        } catch (HttpClientErrorException e) {
            log.info("Error when get connector [{}]. Error: {}", connectorName, e.getMessage());
            return false;
        }
    }
}
