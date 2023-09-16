package com.learn.learnzookeeper;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;

/**
 * 〈修改节点数据〉
 *
 * @author 商玉
 * @create 2022/1/6
 * @since 1.0.0
 */
public class UpdateNote implements Watcher {

    @Override
    public void process(WatchedEvent watchedEvent) {
        //当连接创建了，服务端发送给客户端SyncConnected事件
        try {
            updateNodeSync();
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void updateNodeSync() throws KeeperException, InterruptedException {
        /**
         * path: 路径
         * data: 要修改的内容 byte[]
         * version： 为-1，表示对最新版本的数据进行修改
         * zookeeper.setData(path,data,version)
         */
        byte[] data = zooKeeper.getData("/lg_persistent", false, null);
        System.out.println("修改前的直："+new String(data));
        //修改 stat：状态信息对象 -1：最新版本
        Stat stat = zooKeeper.setData("/lg_persistent", "客户端修改内容".getBytes(), -1);

        byte[] data1 = zooKeeper.getData("/lg_persistent", false, null);
        System.out.println("修改后的值："+new String(data1));
    }

    private static ZooKeeper zooKeeper;

    public static void main(String[] args) throws IOException, InterruptedException {
        zooKeeper = new ZooKeeper("192.168.102.129:2181", 5000, new UpdateNote());
        Thread.sleep(Integer.MAX_VALUE);
    }
}
