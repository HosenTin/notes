package com.djy.notes.bean;

public class Msg {
    /**
     * 表示是否成功
     */
    private boolean success;
    /**
     * 提示消息
     */
    private String message;

    private Msg() {
        //添加成功不需要提示信息
    }

    // 方法重载
    public static Msg buildSuccess() {
        Msg msg = new Msg();
        msg.success = true;
        return msg;
    }

    //成功
    public static Msg buildSuccess(String message) {
        Msg msg = new Msg();
        msg.success = true;
        msg.message = message;
        return msg;
    }

    //失败
    public static Msg buildError(String message) {
        Msg msg = new Msg();
        msg.success = false;
        msg.message = message;
        return msg;
    }

    //判断是否成功
    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }
}
