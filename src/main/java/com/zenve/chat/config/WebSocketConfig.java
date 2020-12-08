package com.zenve.chat.config;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

@Component
@EnableWebSocket
public class WebSocketConfig {
     @Bean
    public ServerEndpointExporter serverEndpoint() {
        return new ServerEndpointExporter();
    }
}
