package com.cmj.example.constants;

public interface CommonConstants {
    /**
     * redis地址
     */
    public static final String REDIS_HOST = "192.168.198.153";
    /**
     * 最大空闲数
     */
    public static final int MAX_IDEL = 100;
    /**
     * 最大连接数
     */
    public static final int MAX_Total = 100;
    /**
     * 预热连接数
     */
    public static final int HOT_QUANTITY = 10;
    /**
     * 节点通信占用端口
     */
    public static final int LOCAL_PORT = 9000;

    /**
     * 魔数，可以通过配置获取
     */
    public static final short MAGIC_CODE = 0x86;
    /**
     * 版本号
     */
    public static final short VERSION_CODE = 0x01;

    /**
     * 返回枚举类
     */
    enum ResultEnum {
        SUCCESS(0, "Success", true),
        AUTH_FAILED(1, "登录失败", false),
        NO_TOKEN(2, "没有授权码", false),
        UNKNOW_ERROR(3, "未知错误", false),
        USER_UNKNOW(4, "用户不在线", false),
        ;

        private Integer code;
        private String desc;
        private boolean isSuccess;

        ResultEnum(Integer code, String desc, boolean isSuccess) {
            this.code = code;
            this.desc = desc;
            this.isSuccess = isSuccess;
        }

        public Integer getCode() {
            return code;
        }

        public String getDesc() {
            return desc;
        }

        public boolean isSuccess() {
            return isSuccess;
        }
    }

    /**
     * 平台 1：windows；2：mac；3：android；4：ios；5：web
     */
    public enum Platform {
        WINDOWS(1, "windows"),
        MAC(2, "mac"),
        ANDROID(3, "android"),
        IOS(4, "ios"),
        WEB(5, "web");

        private Integer code;
        private String desc;

        Platform(Integer code, String desc) {
            this.code = code;
            this.desc = desc;
        }

        public Integer getCode() {
            return code;
        }

        public String getDesc() {
            return desc;
        }
    }

    /**
     * 平台 1：windows；2：mac；3：android；4：ios；5：web
     */
    public enum MessageType {
        TEXT(1, "普通消息"),
        URL(2, "URL"),
        IMAGE(3, "图片");

        private Integer code;
        private String desc;

        MessageType(Integer code, String desc) {
            this.code = code;
            this.desc = desc;
        }

        public Integer getCode() {
            return code;
        }

        public String getDesc() {
            return desc;
        }
    }
}
