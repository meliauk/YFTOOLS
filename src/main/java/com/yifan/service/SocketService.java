package com.yifan.service;

import com.yifan.entity.SocketMessage;

public interface SocketService   {

    public void sendToMsg(SocketMessage socketMessage);
    public void sendToUserMsg(SocketMessage msg);

}
