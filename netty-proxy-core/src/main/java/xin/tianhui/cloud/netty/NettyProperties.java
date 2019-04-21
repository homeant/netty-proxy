package xin.tianhui.cloud.netty;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * netty 配置
 */
@Data
@ConfigurationProperties(prefix = "netty")
public class NettyProperties {

    private int prop;

    private boolean keepAlive = true;

    private int backlog;
}
