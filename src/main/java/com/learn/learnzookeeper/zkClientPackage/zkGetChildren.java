package com.learn.learnzookeeper.zkClientPackage;

import org.I0Itec.zkclient.IZkChildListener;
import org.I0Itec.zkclient.ZkClient;

import java.util.List;

/**
 * 〈获取子节点〉
 *
 * @author 商玉
 * @create 2022/1/6
 * @since 1.0.0
 */
public class zkGetChildren {

    /**
     * 客户端可以对⼀个不存在的节点进⾏⼦节点变更的监听。
     * ⼀旦客户端对⼀个节点注册了⼦节点列表变更监听之后，那么当该节点的⼦节点列表发⽣变更时，服务
     * 端都会通知客户端，并将最新的⼦节点列表发送给客户端
     * 　该节点本身的创建或删除也会通知到客户端。
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        ZkClient zkClient = new ZkClient("192.168.102.129:2181");
        //List<String> children = zkClient.getChildren("/lg-zkClient");
        //System.out.println(children);
        String path="/lg-zkClient";
    //    注册监听事件
        zkClient.subscribeChildChanges(path, new IZkChildListener() {
            @Override
            public void handleChildChange(String s, List<String> list) throws Exception {
                System.out.println(s+"s 的子类"+list);
            }
        });

        zkClient.createPersistent(path);
        Thread.sleep(1000);
        System.out.println(zkClient.getChildren(path));
        zkClient.createPersistent("/lg-zkClient/c1");
        Thread.sleep(1000);
        zkClient.delete("/lg-zkClient/c1");
        Thread.sleep(1000);
        zkClient.delete(path);
        Thread.sleep(Integer.MAX_VALUE);
    }
}
