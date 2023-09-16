package com.learn.learnzookeeper.Curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;

/**
 * 〈创建节点〉
 *
 * @author 商玉
 * @create 2022/1/6
 * @since 1.0.0
 */
public class CuratorCreateNote {

    public static void main(String[] args) throws Exception {
        CuratorFramework build = CuratorFrameworkFactory.builder()
                .connectString("192.168.102.129:2181")
                .sessionTimeoutMs(5000)
                .connectionTimeoutMs(3000)
                .retryPolicy(new ExponentialBackoffRetry(1000, 5))
                .build();
        build.start();
        System.out.println("会话连接成功");

        //    添加节点
        String path = "/lg-curator/c1";
        build.create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT).forPath(path, "init".getBytes());
        Thread.sleep(1000);
        System.out.println("创建成功：" + path);
    }

}
