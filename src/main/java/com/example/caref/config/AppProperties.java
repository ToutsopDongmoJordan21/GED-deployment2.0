package com.example.caref.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

@Component
@ConfigurationProperties(prefix = "app")
@Data
public class AppProperties {


    private final Storage storage = new Storage();
    private final Ftp ftp = new Ftp();
    @Data
    public static class Storage {

        private String path;
        private String directory;
    }
    @Data
    public static class Ftp {

        private String ip;
        private String user;
        private String pwd;
    }

    @NotNull
    private String appUrl;

    public String getAppUrl() {
        return appUrl;
    }

}
