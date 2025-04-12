package org.med.Utils;

import org.testcontainers.containers.PostgreSQLContainer;

public class TestContainerSetup {
    private static final PostgreSQLContainer<?> postgresContainer = new PostgreSQLContainer<>("postgres:16-alpine")
            .withPassword("root")
            .withUsername("root");
    public static void startTestContainer(){
        postgresContainer.start();
    }
    public static void stopTestContainer(){
        postgresContainer.close();
    }
}
