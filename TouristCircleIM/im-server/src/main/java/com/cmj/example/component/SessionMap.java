package com.cmj.example.component;

import com.cmj.example.vo.UserVo;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * @author mengjie_chen
 * @description session管理器
 * @date 2020/10/7
 */
public enum SessionMap {
    SESSIONMAP;

    public static SessionMap instance() {
        return SESSIONMAP;
    }

    //会话集合
    private ConcurrentHashMap<String, ServerSession> map = new ConcurrentHashMap<>();

    /**
     * 添加session
     *
     * @param sessionId
     * @param serverSession
     * @return void
     * @author mengjie_chen
     * @date 2020/10/7
     */
    public void addSession(String sessionId, ServerSession serverSession) {
        map.put(sessionId, serverSession);
        System.out.println("用户登录:id= " + serverSession.getUserVo().getUserId() + "   在线总数: " + map.size());
    }

    /**
     * 获取session
     *
     * @param sessionId
     * @return void
     * @author mengjie_chen
     * @date 2020/10/7
     */
    public ServerSession getSession(String sessionId) {
        return map.get(sessionId);
    }

    /**
     * 删除session
     *
     * @param sessionId
     * @return void
     * @author mengjie_chen
     * @date 2020/10/7
     */
    public void removeSession(String sessionId) {
        if (!map.containsKey(sessionId)) {
            return;
        }
        ServerSession serverSession = map.get(sessionId);
        map.remove(sessionId);
        System.out.println("用户登录:id= " + serverSession.getUserVo().getUserId() + "   在线总数: " + map.size());
    }

    /**
     * 根据用户ID获取session
     *
     * @param userId
     * @return java.util.List<com.cmj.example.server.component.ServerSession>
     * @author mengjie_chen
     * @date 2020/10/7
     */
    public List<ServerSession> getSessionListByUserId(String userId) {
        return map.values()
                .stream()
                .filter(serverSession -> serverSession.getUserVo().getUserId().equals(userId))
                .collect(Collectors.toList());
    }

    /**
     * 判断用户是否登录
     *
     * @param userVo
     * @return boolean
     * @author mengjie_chen
     * @date 2020/10/7
     */
    public boolean hasLogin(UserVo userVo) {
        for (Map.Entry<String, ServerSession> serverSessionEntry : map.entrySet()) {
            UserVo user = serverSessionEntry.getValue().getUserVo();
            if (user.getUserId().equals(userVo.getDeviceId())
                    && user.getPlatform().equals(userVo.getPlatform())) {
                return true;
            }
        }
        return false;
    }
}
