package com.learn.learnzookeeper.Curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;

/**
 * 〈删除节点〉
 *
 * @author 商玉
 * @create 2022/1/6
 * @since 1.0.0
 */
public class DeleteCurator {
    public static void main(String[] args) throws Exception {
        CuratorFramework build = CuratorFrameworkFactory.builder()
                .connectString("192.168.102.129:2183")
                .sessionTimeoutMs(10000)
                .connectionTimeoutMs(10000)
                .retryPolicy(new ExponentialBackoffRetry(1000, 3))
                .build();
        build.start();
        String path = "/lg-curator";
        ////删除一个子节点
        //build.delete().forPath(path);
        ////删除节点并递归删除其子节点
        //build.delete().deletingChildrenIfNeeded().forPath(path);
        ////指定版本进行删除
        //build.delete().withVersion(1).forPath(path);
        ////强制保证删除一个节点
        //build.delete().guaranteed().forPath(path);

        build.delete().deletingChildrenIfNeeded().withVersion(-1).forPath(path);
        System.out.println("删除成功");
    }
}
