package com.zhuye.huochebangsiji.wxapi;

public class PayEvent {
    public String message;
    public int code;

    public PayEvent() {
    }

    public PayEvent(String message, int code) {
        this.message = message;
        this.code = code;
    }
}
