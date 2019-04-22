package xin.tianhui.cloud.netty;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;



/**
 * 停止服务器停止netty服务
 */
@Slf4j
public class NettyApplicationListener implements ApplicationListener<ContextClosedEvent> {

    @Override
    public void onApplicationEvent(ContextClosedEvent event) {
        log.debug("closed :{}",event);
    }
}
