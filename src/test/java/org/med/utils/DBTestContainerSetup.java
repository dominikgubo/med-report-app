package org.med.utils;

import io.quarkus.test.common.QuarkusTestResourceLifecycleManager;
import org.testcontainers.containers.PostgreSQLContainer;

import java.util.HashMap;
import java.util.Map;
public class DBTestContainerSetup implements QuarkusTestResourceLifecycleManager {
    PostgreSQLContainer postgreSQLContainer;

    @Override
    public Map<String, String> start() {
        this.postgreSQLContainer = new PostgreSQLContainer<>("postgres:16-alpine");
        postgreSQLContainer.start();

        Map<String, String> config = new HashMap<>();
        config.put("quarkus.datasource.jdbc.url", postgreSQLContainer.getJdbcUrl());
        config.put("quarkus.datasource.username", postgreSQLContainer.getUsername());
        config.put("quarkus.datasource.password", postgreSQLContainer.getPassword());
        return config;
    }

    @Override
    public void stop() {
        if(this.postgreSQLContainer.isRunning()){
            this.postgreSQLContainer.stop();
        }
    }
}
