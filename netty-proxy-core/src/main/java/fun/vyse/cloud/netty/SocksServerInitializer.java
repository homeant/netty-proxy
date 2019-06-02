package fun.vyse.cloud.netty;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.socks.SocksInitRequestDecoder;
import io.netty.handler.codec.socks.SocksMessageEncoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Data
@Slf4j
@EqualsAndHashCode(callSuper = false)
public class SocksServerInitializer extends ChannelInitializer<SocketChannel> {

    private final List<ChannelHandler> channelHandlers;

    @Override
    public void initChannel(SocketChannel socketChannel) {
        ChannelPipeline pipeline = socketChannel.pipeline()
                .addLast(new SocksInitRequestDecoder())
                .addLast(new SocksMessageEncoder());
        channelHandlers.forEach(r -> pipeline.addLast(r));
    }


}
