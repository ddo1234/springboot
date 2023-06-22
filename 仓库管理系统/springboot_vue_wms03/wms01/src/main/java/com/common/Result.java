package com.common;

import lombok.Data;

import java.util.List;

@Data
public class Result {
    //编码：200/400
    private int code;
    //成功/失败
    private String msg;
    //总记录数
    private Long total;
    //数据
    private Object data;

    //返回成功或者失败的情况
    public static Result fail() {
        return result(400, "失败", 0L, null);
    }

    //返回不带参数
    public static Result successful() {
        return result(200, "成功", 0L, null);
    }

    //返回带object参数
    public static Result successful(Object data) {
        return result(200, "成功", 0L, data);
    }

    //返回带List参数
    public static Result successful(List data) {
        return result(200, "成功", 0L, data);
    }

    //返回带两个参数
    public static Result successful(Object data, Long total) {
        return result(200, "成功", total, data);
    }

    //返回全部数据
    public static Result result(int code, String msg, Long total, Object data) {
        Result results = new Result();
        results.setCode(code);
        results.setData(data);
        results.setMsg(msg);
        results.setTotal(total);
        return results;
    }
}
