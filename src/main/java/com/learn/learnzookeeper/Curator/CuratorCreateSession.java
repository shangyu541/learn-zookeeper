package com.learn.learnzookeeper.Curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;

/**
 * 〈创建会话〉
 *
 * @author 商玉
 * @create 2022/1/6
 * @since 1.0.0
 */
public class CuratorCreateSession {

    public static void main(String[] args) {
        ExponentialBackoffRetry retry = new ExponentialBackoffRetry(1000,3);

        CuratorFramework client = CuratorFrameworkFactory.newClient("192.168.102.129:2181", 5000, 3000, retry);
        client.start();
        System.out.println("zookeeper session1 established ");

        CuratorFramework base = CuratorFrameworkFactory.builder()
                //server地址
                .connectString("192.168.102.129:2181")
                //会话超时时间
                .sessionTimeoutMs(5000)
                //连接超时时间
                .connectionTimeoutMs(3000)
                //重试策略
                .retryPolicy(retry)
                //独立命名空间/base
                .namespace("base")
                .build();
        base.start();
        System.out.println("zookeeper session2 established ");
    }

}
