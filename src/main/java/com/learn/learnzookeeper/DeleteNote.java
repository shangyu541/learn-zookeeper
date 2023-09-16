package com.learn.learnzookeeper;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;

/**
 * 〈删除节点〉
 *
 * @author 商玉
 * @create 2022/1/6
 * @since 1.0.0
 */
public class DeleteNote implements Watcher {

    @Override
    public void process(WatchedEvent watchedEvent) {
        try {
            deleteNodeSync();
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void deleteNodeSync() throws KeeperException, InterruptedException {
        Stat exists = zooKeeper.exists("/lg_persistent", false);
        System.out.println(exists==null?"该节点不存在":"该节点存在");
        zooKeeper.delete("/lg_persistent",-1);
        Stat exists1 = zooKeeper.exists("/lg_persistent", false);
        System.out.println(exists1==null?"该节点不存在":"该节点存在");
    }

    private static ZooKeeper zooKeeper;


    public static void main(String[] args) throws IOException, InterruptedException {
        zooKeeper = new ZooKeeper("192.168.102.129:2181", 5000, new DeleteNote());
        Thread.sleep(Integer.MAX_VALUE);
    }

}
