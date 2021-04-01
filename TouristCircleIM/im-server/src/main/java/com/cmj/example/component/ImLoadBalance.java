package com.cmj.example.component;

import com.cmj.example.utils.common.CommonUtils;
import com.cmj.example.utils.zk.ZookeeperClient;
import com.cmj.example.utils.zk.ZookeeperConstants;

import java.util.List;

/**
 * @author mengjie_chen
 * @description IM负载均衡器
 * @date 2020/11/7
 */
public class ImLoadBalance {

    /**
     * zk客户端
     */
    private final ZookeeperClient client;
    /**
     * 节点父路径（持久化节点）
     */
    private final String nodeParentPath;

    public ImLoadBalance() {
        nodeParentPath = ZookeeperConstants.IM_PATH_PRIX;
        client = ZookeeperClient.getInstance();
    }

    /**
     * 获取负载最小的节点
     *
     * @param
     * @return com.cmj.example.component.ImNode
     * @author mengjie_chen
     * @date 2020/11/9
     */
    public ImNode getBestNode() {
        List<ImNode> nodeList = getAllNodeInPath();
        if (CommonUtils.isNullOrEmpty(nodeList)) {
            throw new RuntimeException("节点未初始化，请稍后再试");
        }
        return getMinBalanceNode(nodeList);
    }

    /**
     * 获取负载最小的node
     *
     * @param nodeList
     * @return com.cmj.example.component.ImNode
     * @author mengjie_chen
     * @date 2020/11/10
     */
    private ImNode getMinBalanceNode(List<ImNode> nodeList) {
        return nodeList.stream().sorted(ImNode::compareTo).findFirst().orElse(null);
    }

    /**
     * 从zk中获取所有的节点
     *
     * @param
     * @return java.util.List<com.cmj.example.component.ImNode>
     * @author mengjie_chen
     * @date 2020/11/10
     */
    private List<ImNode> getAllNodeInPath() {
        return client.getChildDataByPath(nodeParentPath, ImNode.class);
    }
}
