package com.yifan.controller;

import com.yifan.entity.SocketMessage;
import com.yifan.service.SocketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("yifan")
public class ChatController {

    @Autowired
    private SocketService socketService;

    /* 接收消息     @SendToUser 谁发送的返回给谁  */
    @MessageMapping("/chat")
    public void broadCast(SocketMessage message){
        socketService.sendToMsg(message);
    }

    /* 发送给个人*/
    @MessageMapping("/chatTo")
    public void serverSendUesrMsg(SocketMessage message){
        socketService.sendToUserMsg(message);
    }

}