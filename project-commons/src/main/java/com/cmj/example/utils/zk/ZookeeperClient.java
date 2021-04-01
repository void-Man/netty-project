package com.cmj.example.utils.zk;

import com.alibaba.fastjson.JSONObject;
import org.apache.curator.framework.CuratorFramework;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author mengjie_chen
 * @description
 * @date 2020/11/7
 */
public enum ZookeeperClient {
    INSTANCE;

    private static final CuratorFramework CURATORFRAMEWORK;

    static {
        CURATORFRAMEWORK = ZookeeperClientFactory.createSimple(ZookeeperConstants.ZOOKEEPER_ADDRESS);
    }

    /**
     * 获取实例
     *
     * @param
     * @return com.cmj.example.utils.zk.ZookeeperClient
     * @author mengjie_chen
     * @date 2020/11/7
     */
    public static ZookeeperClient getInstance() {
        return INSTANCE;
    }

    /**
     * 创建节点
     *
     * @param path 节点路径
     * @return void
     * @author mengjie_chen
     * @date 2020/11/7
     */
    public String createIfNotExists(String path) throws Exception {
        return createIfNotExistsWithMode(path, CreateMode.PERSISTENT, null);
    }

    /**
     * 创建节点
     *
     * @param path
     * @param mode
     * @return void
     * @author mengjie_chen
     * @date 2020/11/7
     */
    public String createIfNotExistsWithMode(String path, CreateMode mode, byte[] data) throws Exception {
        Stat stat = CURATORFRAMEWORK.checkExists().forPath(path);
        if (Objects.isNull(stat)) {
            return CURATORFRAMEWORK.create()
                    .creatingParentsIfNeeded()
                    .withProtection()
                    .withMode(mode)
                    .forPath(path, data);
        }
        return path;
    }

    /**
     * 功能描述
     *
     * @param path
     * @param rClass
     * @return java.util.List<R>
     * @author mengjie_chen
     * @date 2020/11/10
     */
    public <R> List<R> getChildDataByPath(String path, Class<R> rClass) {
        List<R> resultList = new ArrayList<>(10);
        try {
            Stat stat = CURATORFRAMEWORK.checkExists().forPath(path);
            if (Objects.isNull(stat)) {
                return new ArrayList<>(10);
            }
            List<String> list = CURATORFRAMEWORK.getChildren().forPath(path);
            for (String s : list) {
                byte[] bytes = CURATORFRAMEWORK.getData().forPath(path + "/" + s);
                R result = JSONObject.parseObject(bytes, rClass);
                resultList.add(result);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return resultList;
    }

}
