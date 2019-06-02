package fun.vyse.cloud.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import java.net.InetSocketAddress;
import java.util.List;

@Slf4j
@ConditionalOnProperty(name = "netty.enabled", havingValue = "true")
@EnableConfigurationProperties(NettyProperties.class)
public class NettyAutoConfiguration {

    private final NettyProperties properties;

    public NettyAutoConfiguration(NettyProperties nettyProperties) {
        this.properties = nettyProperties;
    }

    @Bean
    public StringEncoder stringEncoder() {
        return new StringEncoder();
    }

    @Bean
    public StringDecoder stringDecoder() {
        return new StringDecoder();
    }

    @Bean
    public ChannelInitializer channelInitializer(List<ChannelHandler> channelHandlers) {
        return new SocksServerInitializer(channelHandlers);
    }

    @Bean
    public ServerBootstrap serverBootstrap(ChannelInitializer channelInitializer) {
        ServerBootstrap b = new ServerBootstrap();
        b.group(bossGroup(), workerGroup())
                .channel(NioServerSocketChannel.class)
                .handler(new LoggingHandler(LogLevel.DEBUG))
                .childHandler(channelInitializer)
                .option(ChannelOption.SO_BACKLOG, properties.getSoBacklog())
                .childOption(ChannelOption.SO_KEEPALIVE, properties.isSoKeepAlive());
        return b;
    }

    @Bean(name = "bossGroup", destroyMethod = "shutdownGracefully")
    public NioEventLoopGroup bossGroup() {
        return new NioEventLoopGroup(properties.getBossThreadCount());
    }

    @Bean(name = "workerGroup", destroyMethod = "shutdownGracefully")
    public NioEventLoopGroup workerGroup() {
        return new NioEventLoopGroup(properties.getWorkerThreadCount());
    }

    @Bean(name = "tcpSocketAddress")
    public InetSocketAddress tcpPort() {
        return new InetSocketAddress(properties.getPort());
    }

    @Bean
    public NettyServerApplication serverApplication(){
        return new NettyServerApplication();
    }

}
