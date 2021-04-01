package com.cmj.example.utils.zk;

public enum ZookeeperConstants {
    ;

    /**
     * zk集群地址
     */
    public static final String ZOOKEEPER_ADDRESS = "192.168.198.150:2181,192.168.198.151:2181,192.168.198.152:2181";
    /**
     * zk地址前缀
     */
    public static final String ZOOKEEPER_PATH_PRIX = "/ZookeeperTest";
    /**
     * im父节点
     */
    public static final String IM_PATH_PRIX = "/im/node";
    /**
     * im节点前缀
     */
    public static final String IM_NODE_PATH_PRIX = IM_PATH_PRIX + "/seq-";
}
