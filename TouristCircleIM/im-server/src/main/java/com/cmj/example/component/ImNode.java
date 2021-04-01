package com.cmj.example.component;

import com.google.common.base.Objects;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author mengjie_chen
 * @description 分布式节点类
 * @date 2020/11/5
 */
public class ImNode implements Comparable<ImNode> {
    /**
     * id
     */
    private long nodeId;
    /**
     * netty服务连接数（用于描述负载，值越大表示负载越高）
     * 这里使用 AtomicInteger 的原因是，新的用户登录当前节点，需要增加负载，有并发的可能
     */
    private AtomicInteger balance;
    /**
     * netty服务地址
     */
    private String host;
    /**
     * netty服务端口
     */
    private Integer port;

    public ImNode(String host, Integer port) {
        this.host = host;
        this.port = port;
    }

    public long getNodeId() {
        return nodeId;
    }

    public void setNodeId(long nodeId) {
        this.nodeId = nodeId;
    }

    public AtomicInteger getBalance() {
        return balance;
    }

    public void setBalance(AtomicInteger balance) {
        this.balance = balance;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public void incrBalance(){
        balance.incrementAndGet();
    }

    public void decrBalance(){
        balance.decrementAndGet();
    }

    @Override
    public int compareTo(ImNode o) {
        if (this.balance.get() > o.getBalance().get()) {
            return 1;
        } else if (this.balance.get()  < o.getBalance().get() ) {
            return -1;
        }
        return 0;
    }

    @Override
    public String toString() {
        return "ImNode{" +
                "nodeId=" + nodeId +
                ", balance=" + balance +
                ", host='" + host + '\'' +
                ", port=" + port +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ImNode imNode = (ImNode) o;
        return nodeId == imNode.nodeId &&
                Objects.equal(balance, imNode.balance) &&
                Objects.equal(host, imNode.host) &&
                Objects.equal(port, imNode.port);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(nodeId, balance, host, port);
    }
}
