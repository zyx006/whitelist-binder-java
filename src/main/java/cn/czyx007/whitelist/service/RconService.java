package cn.czyx007.whitelist.service;

import cn.czyx007.whitelist.config.RconConfig;
import lombok.extern.slf4j.Slf4j;
import org.glavo.rcon.Rcon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Slf4j
@Service
public class RconService {
    @Autowired
    private RconConfig rconConfig;

    public void addWhitelist(String username) throws Exception {
        log.info("开始为玩家 [{}] 添加白名单", username);

        try (Rcon rcon = new Rcon(rconConfig.getHost(), rconConfig.getPort(), rconConfig.getPassword())) {
            log.debug("已成功连接到服务器 {}:{}", rconConfig.getHost(), rconConfig.getPort());
            try {
                log.info("发送白名单添加命令");
                rcon.command("whitelist add " + username);
                log.info("白名单命令执行成功，玩家 [{}] 已添加到白名单", username);
            } catch (IOException e) {
                log.error("发送/接收RCON命令时发生错误: {}", e.getMessage());
                throw new IOException("执行白名单命令失败", e);
            }
        } catch (IOException e) {
            log.error("连接到RCON服务器失败 ({}:{}): {}",
                    rconConfig.getHost(),
                    rconConfig.getPort(),
                    e.getMessage()
            );
            throw new IOException("无法连接到RCON服务器");
        }
    }
}