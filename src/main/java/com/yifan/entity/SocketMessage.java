package com.yifan.entity;

import lombok.Data;

@Data
public class SocketMessage {

    private String toname; //发送给的人

    private String name; // 发送者

    private String message;

    private String date;

}