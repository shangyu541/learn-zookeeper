package com.learn.learnzookeeper;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * 〈建立会话〉
 *
 * @author 商玉
 * @create 2022/1/4
 * @since 1.0.0
 */
public class CreateSession implements Watcher {

    private static CountDownLatch countDownLatch=new CountDownLatch(1);

    /**
     * 当前类实现了watcher接口，重写了process方法，该方法负责处理来自zookeeper服务端的watcher通知，在收到服务端发送过来的SyncConnected事件之后，
     * 解除主程序在countdownlatch上的等待阻塞，至此，会话创建完毕
     * @param watchedEvent
     */
    @Override
    public void process(WatchedEvent watchedEvent) {
        if (watchedEvent.getState()==Event.KeeperState.SyncConnected){
            countDownLatch.countDown();
        }
    }

    public static void main(String[] args) throws InterruptedException, IOException {
        ZooKeeper zooKeeper = new ZooKeeper("192.168.102.129:2181", 5000, new CreateSession());
        System.out.println(zooKeeper.getState());
        countDownLatch.await();
        //表示会话真正建立
        System.out.println("====================client connected to zookeeper=========");
    }

}
