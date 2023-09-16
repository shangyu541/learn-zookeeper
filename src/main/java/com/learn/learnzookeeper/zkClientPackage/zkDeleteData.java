package com.learn.learnzookeeper.zkClientPackage;

import org.I0Itec.zkclient.ZkClient;

/**
 * 〈删除节点〉
 *
 * @author 商玉
 * @create 2022/1/6
 * @since 1.0.0
 */
public class zkDeleteData {

    public static void main(String[] args) {
        ZkClient zkClient = new ZkClient("192.168.102.129:2181");
        //递归删除
        //zkClient.deleteRecursive("/lg-zkClient/lg-c1");
        zkClient.deleteRecursive("/lg-zkClient");
        System.out.println("success");
    }
}
