package xin.tianhui.cloud.proxy.client.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class IndexController {
    @GetMapping
    public Mono<String> index(){
        return Mono.create(r  -> r.success("hello world!"));
    }
}
