package fun.vyse.cloud.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.net.InetSocketAddress;

@Slf4j
public class NettyServerApplication {

    @Autowired
    private ServerBootstrap serverBootstrap;

    @Autowired
    private InetSocketAddress inetSocketAddress;

    private Channel serverChannel;

    @PostConstruct
    public void start() throws Exception{
        log.debug("netty start ....");
        serverChannel = serverBootstrap.bind(inetSocketAddress).sync().channel().closeFuture().sync().channel();
    }

    @PreDestroy
    public void destroy() {
        log.debug("netty destroy ....");
        serverChannel.close();
        serverChannel.parent().close();
    }
}
