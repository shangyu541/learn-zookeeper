package com.learn.learnzookeeper.zkClientPackage;

import org.I0Itec.zkclient.ZkClient;

/**
 * 〈创建会话〉
 *
 * @author 商玉
 * @create 2022/1/6
 * @since 1.0.0
 */
public class zkClientCreateSession {

    public static void main(String[] args) {
        ZkClient zkClient = new ZkClient("192.168.102.129:2181");
        System.out.println("zookeeper session established");
    }
}
