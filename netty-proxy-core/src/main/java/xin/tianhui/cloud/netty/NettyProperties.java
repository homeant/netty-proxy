package xin.tianhui.cloud.netty;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * netty 配置
 */
@Data
@ConfigurationProperties(prefix = "netty")
public class NettyProperties {

    private int port=1080;

    private boolean soKeepAlive = true;

    private int soBacklog;

    private int bossThreadCount = 2;

    private int workerThreadCount = 2;
}
