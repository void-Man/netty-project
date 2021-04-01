package com.cmj.example.vo;

/**
 * @author mengjie_chen
 * @description 用户vo
 * @date 2020/10/7
 */
public class UserVo {

    /**
     * 用户ID
     */
    private String userId;
    /**
     * 设备ID
     */
    private String deviceId;
    /**
     * token
     */
    private String token;
    /**
     * 平台
     */
    private Integer platform;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Integer getPlatform() {
        return platform;
    }

    public void setPlatform(Integer platform) {
        this.platform = platform;
    }

    @Override
    public String toString() {
        return "UserVo{" +
                "userId='" + userId + '\'' +
                ", deviceId='" + deviceId + '\'' +
                ", token='" + token + '\'' +
                ", platform=" + platform +
                '}';
    }

    public static UserVo proto2UserVo(ProtoMsg.LoginRequest loginRequest) {
        UserVo userVo = new UserVo();
        userVo.setUserId(loginRequest.getUserId());
        userVo.setDeviceId(loginRequest.getDeviceId());
        userVo.setToken(loginRequest.getToken());
        userVo.setPlatform(loginRequest.getPlatform());
        System.out.println("登录中: " + userVo.toString());
        return userVo;
    }
}
