package org.med;

import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.QuarkusApplication;
import io.quarkus.runtime.annotations.QuarkusMain;

@QuarkusMain
public class Main {

    public static void main(String... args) {
        Quarkus.run(AppRunner.class, args);
    }

    public static class AppRunner implements QuarkusApplication {


        @Override
        public int run(String... args) throws Exception {
            System.out.println(">> Quarkus CLI app started");

            Quarkus.waitForExit();
            return 0;
        }
    }
}