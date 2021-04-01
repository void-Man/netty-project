package com.cmj.example.component;

import com.alibaba.fastjson.JSONObject;
import com.cmj.example.constants.CommonConstants;
import com.cmj.example.utils.common.IOUtil;
import com.cmj.example.utils.zk.ZookeeperClient;
import com.cmj.example.utils.zk.ZookeeperConstants;
import org.apache.zookeeper.CreateMode;

import java.util.Objects;

/**
 * @author mengjie_chen
 * @description 节点的Zk协调客户端（单例）
 * @date 2020/11/5
 */
public enum ImWorker {

    /**
     * host和port可以从配置文件中读取
     */
    INstance();

    /**
     * zk客户端
     */
    private final ZookeeperClient client = ZookeeperClient.getInstance();
    /**
     * zk里面注册的当前节点路径
     */
    private final String pathRegistered;
    /**
     * 当前节点
     */
    private final ImNode currentNode;

    ImWorker() {
        createParentIfNotExists(ZookeeperConstants.IM_PATH_PRIX);
        this.currentNode = new ImNode(IOUtil.getHostAddress(), CommonConstants.LOCAL_PORT);
        this.pathRegistered = getPathRegistered(ZookeeperConstants.IM_NODE_PATH_PRIX, JSONObject.toJSONBytes(currentNode));
        currentNode.setNodeId(getIdByPath(pathRegistered));
    }

    /**
     * 检测父节点是否存在
     *
     * @param path
     * @return void
     * @author mengjie_chen
     * @date 2020/11/7
     */
    private void createParentIfNotExists(String path) {
        try {
            client.createIfNotExists(path);
        } catch (Exception e) {
            throw new RuntimeException("创建节点失败，请稍后再试\n" + e);
        }
    }

    /**
     * 创建自身节点
     *
     * @param path
     * @return java.lang.String
     * @author mengjie_chen
     * @date 2020/11/7
     */
    private String getPathRegistered(String path, byte[] data) {
        try {
            return client.createIfNotExistsWithMode(path, CreateMode.EPHEMERAL_SEQUENTIAL, data);
        } catch (Exception e) {
            throw new RuntimeException("创建节点失败，请稍后再试\n" + e);
        }
    }

    /**
     * 获取节点ID
     *
     * @param
     * @return long
     * @author mengjie_chen
     * @date 2020/11/7
     */
    public long getIdByPath(String path) {
        String sid = null;
        if (null == path) {
            throw new RuntimeException("节点路径有误");
        }
        int index = path.lastIndexOf(ZookeeperConstants.IM_NODE_PATH_PRIX);
        if (index >= 0) {
            index += ZookeeperConstants.IM_NODE_PATH_PRIX.length();
            sid = index <= path.length() ? path.substring(index) : null;
        }

        if (null == sid) {
            throw new RuntimeException("节点ID获取失败");
        }

        return Long.parseLong(sid);
    }

    /**
     * 增加负载，表示当前用户登录成功
     *
     * @param
     * @return boolean
     * @author mengjie_chen
     * @date 2020/11/7
     */
    public void incrBalance() {
        if (Objects.isNull(currentNode)) {
            throw new RuntimeException("节点还未初始化成功");
        }
        currentNode.incrBalance();
    }

    /**
     * 减少负载，表示当前用户下线
     *
     * @param
     * @return boolean
     * @author mengjie_chen
     * @date 2020/11/7
     */
    public void decrBalance() {
        if (Objects.isNull(currentNode)) {
            throw new RuntimeException("节点还未初始化成功");
        }
        currentNode.decrBalance();
    }

    public String getPathRegistered() {
        return pathRegistered;
    }

    public ImNode getCurrentNode() {
        return currentNode;
    }
}
