package com.learn.learnzookeeper.zkClientPackage;

import org.I0Itec.zkclient.ZkClient;

/**
 * 〈创建节点〉
 *
 * @author 商玉
 * @create 2022/1/6
 * @since 1.0.0
 */
public class ZkCreateNode {
    public static void main(String[] args) {
        ZkClient zkClient = new ZkClient("192.168.102.129:2181");
        System.out.println("ZooKeeper session established.");

        //createPersistent 的值设置为true，可以递归创建节点
        zkClient.createPersistent("/lg-zkClient/lg-cl",true);
        System.out.println("success");
    }
}
