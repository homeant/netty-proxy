package fun.vyse.cloud.proxy.test;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelHandler;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit4.SpringRunner;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class NettyTest {

    @Autowired
    private ServerBootstrap serverBootstrap;

    @Test
    public void test(){
        log.debug("serverBootstrap:{}", serverBootstrap);
    }

    @Configuration
    @EnableAutoConfiguration
    public static class Config {
        @Bean
        public ChannelHandler serverHandler() {
            return new SocksServerHandler();
        }
    }
}
