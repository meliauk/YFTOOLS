package com.yifan.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    // 基于STOMP协议的WebSocket

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // 注册一个websocket端点，客户端将使用它连接到我们的websocket服务器。
        registry.addEndpoint("/socket").setAllowedOrigins("*").withSockJS();
    }

    // 注册相关服务
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        //定义了服务端接收地址的前缀，也即客户端给服务端发消息的地址前缀
        registry.setApplicationDestinationPrefixes("/yifan");
        //定义了一个（或多个）客户端订阅地址的前缀信息，也就是客户端接收服务端发送消息的前缀信息
        registry.enableSimpleBroker("/topic","/user");
        // 点对点使用的订阅前缀（客户端订阅路径上会体现出来），不设置的话，默认也是/user/
        registry.setUserDestinationPrefix("/user/");
    }
}
