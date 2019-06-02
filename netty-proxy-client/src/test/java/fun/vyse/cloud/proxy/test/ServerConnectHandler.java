package fun.vyse.cloud.proxy.test;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.socks.SocksCmdRequest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ChannelHandler.Sharable
public class ServerConnectHandler extends SimpleChannelInboundHandler<SocksCmdRequest> {
    @Override
    public void channelRead0(ChannelHandlerContext ctx, SocksCmdRequest socksCmdRequest) {
        log.debug("host:{}", socksCmdRequest.host());
    }
}
