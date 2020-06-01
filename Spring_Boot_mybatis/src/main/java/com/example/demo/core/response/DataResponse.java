package com.example.demo.core.response;


/**
 * @Author:GQM
 * @Date:created in 23:43 2020/5/27
 * @Description:
 * @Modifyed_By:
 */
public class DataResponse<T> {

    private int code;
    private String msg;
    private T data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
