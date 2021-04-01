package com.cmj.example.vo;

/**
 * @author mengjie_chen
 * @description date 2020/11/30
 */
public class OrderResultVo {

    /**
     * 返回码
     */
    private int code = 200;
    /**
     * 成功标识
     */
    private boolean isSuccess = true;
    /**
     * 是否需要创建处理器
     */
    private boolean needHandle = false;
    /**
     * 错误码描述
     */
    private String msg = "";

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    public boolean isNeedHandle() {
        return needHandle;
    }

    public void setNeedHandle(boolean needHandle) {
        this.needHandle = needHandle;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
