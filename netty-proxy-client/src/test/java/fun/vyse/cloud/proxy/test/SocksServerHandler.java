package fun.vyse.cloud.proxy.test;

import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.handler.codec.socks.*;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ChannelHandler.Sharable
public class SocksServerHandler extends SimpleChannelInboundHandler<SocksRequest> {

    @Override
    public void channelRead0(ChannelHandlerContext ctx, SocksRequest socksRequest) throws Exception {
        log.debug("type:{}", socksRequest.requestType());
        switch (socksRequest.requestType()) {
            case INIT:
                log.info("local server init");
                ctx.pipeline().addFirst(new SocksCmdRequestDecoder());
                ctx.write(new SocksInitResponse(SocksAuthScheme.NO_AUTH));
                break;
            case AUTH:
                log.info("local server auth");
                ctx.pipeline().addFirst(new SocksCmdRequestDecoder());
                ctx.write(new SocksAuthResponse(SocksAuthStatus.SUCCESS));
                break;
            case CMD:
                SocksCmdRequest req = (SocksCmdRequest) socksRequest;
                if (req.cmdType() == SocksCmdType.CONNECT) {
                    log.info("local server connect");
                    ctx.pipeline().addLast(new ServerConnectHandler());
                    ctx.pipeline().remove(this);
                    ctx.fireChannelRead(socksRequest);
                } else {
                    ctx.close();
                }
                break;
            case UNKNOWN:
                ctx.close();
                break;
        }
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable throwable) {
        throwable.printStackTrace();
        final Channel channel = ctx.channel();
        if (channel.isActive()) {
            channel.writeAndFlush(Unpooled.EMPTY_BUFFER).addListener(
                    ChannelFutureListener.CLOSE);
        }
    }
}
