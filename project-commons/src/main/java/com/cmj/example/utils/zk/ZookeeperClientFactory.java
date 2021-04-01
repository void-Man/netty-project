package com.cmj.example.utils.zk;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;

/**
 * @author mengjie_chen
 * @description date 2020/10/21
 */
public class ZookeeperClientFactory {

    /**
     * 指定重试次数，创建客户端
     *
     * @param connect
     * @return org.apache.curator.framework.CuratorFramework
     * @author mengjie_chen
     * @date 2020/10/21
     */
    public static CuratorFramework createSimple(String connect) {
        // 重试策略：第一次1倍的基础时间。第二次2倍基础时间，第三次3倍基础时间
        // baseSleepTimeMs：基础实践单位（毫秒）
        // maxRetries：最大重试次数
        ExponentialBackoffRetry retry = new ExponentialBackoffRetry(1000, 3);
        return CuratorFrameworkFactory.newClient(connect, retry);
    }

    /**
     * 指定参数，创建客户端
     *
     * @param connect
     * @return org.apache.curator.framework.CuratorFramework
     * @author mengjie_chen
     * @date 2020/10/21
     */
    public static CuratorFramework createWithOptions(String connect, ExponentialBackoffRetry retry, int connnectTimeoutMs, int sessionTimeoutMs) {
        return CuratorFrameworkFactory.builder()
                .connectString(connect)
                .retryPolicy(retry)
                .connectionTimeoutMs(connnectTimeoutMs)
                .sessionTimeoutMs(sessionTimeoutMs)
                .build();
    }

}
