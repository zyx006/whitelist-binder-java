package cn.czyx007.whitelist.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "minecraft.rcon")
public class RconConfig {
    private String host;
    private int port;
    private String password;
}