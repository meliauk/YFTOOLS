package com.yifan.service.impl;

import com.yifan.entity.SocketMessage;
import com.yifan.service.SocketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class SocketServiceimpl   implements SocketService {

    @Autowired
    private SimpMessagingTemplate template;


    @Override
    public void sendToMsg(SocketMessage socketMessage) {
        //  这里是  stompClient.subscribe('/topic/message'
        template.convertAndSend("/topic/message",socketMessage);

    }

    @Override
    public void sendToUserMsg(SocketMessage msg) {
        System.err.println(msg.getToname());
        template.convertAndSendToUser(msg.getToname(),"/ptp",msg);
    }
}
