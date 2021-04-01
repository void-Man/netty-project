package com.cmj.example.vo;

/**
 * @author mengjie_chen
 * @description 返回类
 * date 2020/11/19
 */
public class ResVo {

    /**
     * 返回码
     */
    private int code = 200;
    /**
     * 成功标识
     */
    private boolean isSuccess = true;
    /**
     * 错误码描述
     */
    private String msg = "";
    /**
     * 附带remark信息
     */
    private String msgDetail;

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

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getMsgDetail() {
        return msgDetail;
    }

    public void setMsgDetail(String msgDetail) {
        this.msgDetail = msgDetail;
    }


    public static final class ResVoBuilder {
        private int code = 200;
        private boolean isSuccess = true;
        private String msg = "";
        private String msgDetail;

        private ResVoBuilder() {
        }

        public static ResVoBuilder aResVo() {
            return new ResVoBuilder();
        }

        public ResVoBuilder code(int code) {
            this.code = code;
            return this;
        }

        public ResVoBuilder isSuccess(boolean isSuccess) {
            this.isSuccess = isSuccess;
            return this;
        }

        public ResVoBuilder msg(String msg) {
            this.msg = msg;
            return this;
        }

        public ResVoBuilder msgDetail(String msgDetail) {
            this.msgDetail = msgDetail;
            return this;
        }

        public ResVo build() {
            ResVo resVo = new ResVo();
            resVo.setCode(code);
            resVo.setMsg(msg);
            resVo.setMsgDetail(msgDetail);
            resVo.isSuccess = this.isSuccess;
            return resVo;
        }
    }
}
