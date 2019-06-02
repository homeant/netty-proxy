package fun.vyse.cloud.netty;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * netty 配置
 */
@Data
@ConfigurationProperties(prefix = "netty")
public class NettyProperties {

    private boolean enabled = false;

    private int port=1080;
    /**
     * 当设置该选项以后，如果在两小时内没有数据的通信时，TCP会自动发送一个活动探测数据报文
     */
    private boolean soKeepAlive = true;

    private int soBacklog;

    private int bossThreadCount = 2;

    private int workerThreadCount = 2;
}
