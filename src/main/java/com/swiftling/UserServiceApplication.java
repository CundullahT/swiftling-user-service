package com.swiftling;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
public class UserServiceApplication {

    public static void main(String[] args) {

        SpringApplication app = new SpringApplication(UserServiceApplication.class);
        Map<String, Object> defaultProperties = new HashMap<>();

        String env = System.getenv().getOrDefault("ENV", "local");
        String domain = System.getenv().get("SWIFTLING_HOSTNAME");

        //TODO Use real domain when it is available
        String hostOrIp = env.equalsIgnoreCase("local") ? "localhost" : env.equalsIgnoreCase("prod") ? domain : env.equalsIgnoreCase("dev") ? "cundi.onthewifi.com" : getPublicIP();

        defaultProperties.put("host.name-or-ip", hostOrIp);

        boolean notUseIp = env.equalsIgnoreCase("local") || env.equalsIgnoreCase("prod");

        if (!notUseIp) {
            defaultProperties.put("eureka.instance.preferIpAddress", true);
            defaultProperties.put("eureka.instance.ipAddress", hostOrIp);
        } else {
            defaultProperties.put("eureka.instance.hostname", hostOrIp);
        }

        String managementContextPath = "http://" + hostOrIp + ":${server.port}/actuator";

        defaultProperties.put("management.info.env.enabled", true);
        defaultProperties.put("management.endpoint.health.show-details", "ALWAYS");
        defaultProperties.put("management.endpoints.web.exposure.include", "*");
        defaultProperties.put("management.context-path", managementContextPath);

        String eurekaInstanceStatusPageUrlPath = managementContextPath + "/info";
        String eurekaInstanceHealthCheckUrlPath = managementContextPath + "/health";

        defaultProperties.put("eureka.instance.status-page-url-path", eurekaInstanceStatusPageUrlPath);
        defaultProperties.put("eureka.instance.health-check-url-path", eurekaInstanceHealthCheckUrlPath);
        defaultProperties.put("eureka.client.serviceUrl.defaultZone", "${SWIFTLING_DISCOVERY_SERVICE}/eureka/");

        defaultProperties.put("info.application.name", "swiftling-user-service");
        defaultProperties.put("info.application.description", "Swiftling App's User Service");

        String frontendUrl = env.equalsIgnoreCase("prod")
                ? "https://" + hostOrIp
                : "http://" + hostOrIp + ":5000";
        defaultProperties.put("app.frontend.url", frontendUrl);

        app.setDefaultProperties(defaultProperties);

        app.run(args);

    }

    private static String getPublicIP() {

        String IP = "127.0.0.1";

        try {
            URL checkIpURL = new URL("http://checkip.amazonaws.com/");
            HttpURLConnection conn = (HttpURLConnection) checkIpURL.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(5000);
            conn.setReadTimeout(5000);

            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            IP = in.readLine().trim();
            in.close();
            return IP;

        } catch (Exception e) {
            log.error(e.getMessage());
        }

        return IP;

    }

}
