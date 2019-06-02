package fun.vyse.cloud.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.net.InetSocketAddress;

@Slf4j
public class NettyServerApplication {

    @Autowired
    ServerBootstrap serverBootstrap;

    @Autowired
    InetSocketAddress inetSocketAddress;

    private ChannelFuture channelFuture;

    @PostConstruct
    public void start() throws Exception{
        log.debug("netty start");
        channelFuture = serverBootstrap.bind(inetSocketAddress).sync();
    }

    @PreDestroy
    public void destroy() throws Exception{
        log.debug("netty destroy");
        channelFuture.channel().closeFuture().sync();
    }
}
