package com.learn.learnzookeeper.Curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;

/**
 * 〈〉
 *
 * @author 商玉
 * @create 2022/1/6
 * @since 1.0.0
 */
public class CuratorGet {
    public static void main(String[] args) throws Exception {
        CuratorFramework build = CuratorFrameworkFactory.builder()
                .connectString("192.168.102.129:2183")
                .sessionTimeoutMs(10000)
                .connectionTimeoutMs(10000)
                .retryPolicy(new ExponentialBackoffRetry(1000, 3))
                .build();
        build.start();
        String path="/lg-curator";
        System.out.println("会话连接问题");
        //build.create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT).forPath(path,"init".getBytes());

        //普通查询
        build.getData().forPath(path);

        Stat stat = new Stat();
        byte[] bytes = build.getData().storingStatIn(stat).forPath(path);
        System.out.println(new String(bytes));


    }
}
