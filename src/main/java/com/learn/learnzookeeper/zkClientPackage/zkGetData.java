package com.learn.learnzookeeper.zkClientPackage;

import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;

/**
 * 〈获取数据〉
 *
 * @author 商玉
 * @create 2022/1/6
 * @since 1.0.0
 */
public class zkGetData {


    public static void main(String[] args) throws InterruptedException {
        String path = "/lg-zkClient-Ep";

        ZkClient zkClient = new ZkClient("192.168.102.129:2181");
        boolean exists = zkClient.exists(path);
        if (!exists) {
            zkClient.createEphemeral(path, "123");
        }


        //注册监听
        zkClient.subscribeDataChanges(path, new IZkDataListener() {
            @Override
            public void handleDataChange(String path, Object data) throws Exception {
                System.out.println(path+"该节点内容被更新，更新后的内容"+data);
            }

            @Override
            public void handleDataDeleted(String s) throws Exception {
                System.out.println(s+"该节点被删除");
            }
        });

        //获取节点内容
        Object o = zkClient.readData(path);
        System.out.println(o);

        //更新
        zkClient.writeData(path,"4567");
        Thread.sleep(1000);

        //删除
        zkClient.delete(path);
        Thread.sleep(1000);

    }
}
