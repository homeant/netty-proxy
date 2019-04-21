package xin.tianhui.cloud.netty;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import lombok.Data;

import java.util.List;

@Data
public class NettyServerHandlerInitializer extends ChannelInitializer<SocketChannel> {

    private final StringDecoder stringDecoder;

    private final StringEncoder stringEncoder;

    private final List<ChannelHandler> channelHandlers;

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline pipeline = socketChannel.pipeline();
        pipeline.addLast("decoder", stringDecoder);
        channelHandlers.forEach(r -> pipeline.addLast(r));
        pipeline.addLast("encoder", stringEncoder);
    }
}
