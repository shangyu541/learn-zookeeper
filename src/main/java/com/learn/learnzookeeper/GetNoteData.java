package com.learn.learnzookeeper;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * 〈获取节点数据〉
 *
 * @author 商玉
 * @create 2022/1/5
 * @since 1.0.0
 */
public class GetNoteData implements Watcher {

    private static CountDownLatch countDownLatch = new CountDownLatch(1);
    private static ZooKeeper zooKeeper;

    public static void main(String[] args) throws IOException, InterruptedException {
        zooKeeper = new ZooKeeper("192.168.102.129:2181", 10000, new GetNoteData());
        Thread.sleep(Integer.MAX_VALUE);
    }

    @Override
    public void process(WatchedEvent watchedEvent) {

        //子节点列表发生变化时，服务器会发出nodeChildrenChanged通知，但不会把变化情况告诉给客户端
        //需要客户端自行获取，且通知是一次性的，需反复注册监听
        if (watchedEvent.getType() == Event.EventType.NodeChildrenChanged) {
            List<String> children = null;
            //再次获取节点数据
            try {
                children = zooKeeper.getChildren(watchedEvent.getPath(), true);
            } catch (KeeperException e) {

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(children);
        }

        //当连接创建了，服务端发送给客户端syncConnected事件
        if (watchedEvent.getState()==Event.KeeperState.SyncConnected){
            //调用获取单个节点数据方法
            try {
                getNoteDate();
                getChildrens();
            } catch (KeeperException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
    }

    private void getChildrens() throws KeeperException, InterruptedException {
        /**
         * path 路径
         * watch  是否要启动监听，当子节点列表发生变化，会触发监听
         * zookeeper.getChildren(path,watch);
         */
        List<String> children = zooKeeper.getChildren("/lg_persistent", true);
        System.out.println(children);
    }

    private void getNoteDate() throws KeeperException, InterruptedException, UnsupportedEncodingException {
        /**
         * path  获取数据的路径
         * watch  是否开启监听
         * stat  节点状态信息
         * null  表示获取最新版本的数据
         */
        //byte[] data = zooKeeper.getData("/lg_persistent/lg-children", true, null);
        byte[] data = zooKeeper.getData("/lg_persistent", true, null);
        System.out.println(new String(data,"utf-8"));
    }
}
