package xin.tianhui.cloud.netty;

import io.netty.channel.ChannelHandler;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import lombok.Data;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import java.util.List;

@Data
@EnableConfigurationProperties(NettyProperties.class)
public class NettyConfiguration {

    private NettyProperties properties;

    @Bean
    public StringEncoder stringEncoder() {
        return new StringEncoder();
    }

    @Bean
    public StringDecoder stringDecoder() {
        return new StringDecoder();
    }

    @Bean
    public NettyServerHandlerInitializer protocolInitalizer(List<ChannelHandler> channelHandlers) {
        return new NettyServerHandlerInitializer(stringDecoder(), stringEncoder(), channelHandlers);
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

}
