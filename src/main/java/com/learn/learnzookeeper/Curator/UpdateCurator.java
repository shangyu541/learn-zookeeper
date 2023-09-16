package com.learn.learnzookeeper.Curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.data.Stat;

/**
 * 〈更新节点信息〉
 *
 * @author 商玉
 * @create 2022/1/7
 * @since 1.0.0
 */
public class UpdateCurator {

    public static void main(String[] args) throws Exception {
        CuratorFramework build = CuratorFrameworkFactory.builder()
                .connectString("192.168.102.129:2181")
                .connectionTimeoutMs(10000)
                .sessionTimeoutMs(10000)
                .retryPolicy(new ExponentialBackoffRetry(1000, 5))
                .build();
        build.start();
        String path="/lg-curator";
        Stat stat = new Stat();
        byte[] bytes = build.getData().storingStatIn(stat).forPath(path);
        System.out.println("更新前的数据："+new String(bytes));
        build.setData().forPath(path, "新内容22222".getBytes());
    }

}
