package ru.leti.wise.task.task.service.plugin;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.leti.wise.task.plugin.PluginServiceGrpc;

import javax.annotation.PostConstruct;

import static io.grpc.netty.shaded.io.grpc.netty.NettyChannelBuilder.forAddress;
import static ru.leti.wise.task.plugin.PluginServiceGrpc.newBlockingStub;

@Component
@RequiredArgsConstructor
public class PluginStubHolder {

    @Value("${grpc.service.plugin.port}")
    private int port;

    private PluginServiceGrpc.PluginServiceBlockingStub pluginServiceStub;


    @PostConstruct
    void init() {
        pluginServiceStub = newBlockingStub(forAddress("localhost", port).usePlaintext().build());
    }

    PluginServiceGrpc.PluginServiceBlockingStub get() {
        return pluginServiceStub;
    }
}
