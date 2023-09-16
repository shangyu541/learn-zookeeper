package com.learn.learnzookeeper;

import org.apache.zookeeper.*;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.concurrent.CountDownLatch;

/**
 * 〈创建节点〉
 *
 * @author 商玉
 * @create 2022/1/4
 * @since 1.0.0
 */
public class CreateNote implements Watcher {


    private static ZooKeeper zooKeeper;

    @Override
    public void process(WatchedEvent watchedEvent) {
        if (watchedEvent.getState() == Event.KeeperState.SyncConnected) {
            try {
                createNoteSync();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (KeeperException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * path  节点创建的路径
     * data[]  节点创建要保存的数据，是个byte类型的
     * acl   节点创建的权限信息（4种类型）
     *                  anyone_id_unsafe  表示任何人
     *                  auth_ids  此id仅可用于设置acl，他将被客户机验证的id替换
     *                  open_acl_unsafe  这是一个完全开放的acl（常用） world:anyone
     *                  creator_all_acl :此acl授予创建者身份验证id的所有权限
     * createnote  创建节点的类型（4种类型）
     *                    persistent  持久节点
     *                    persistent_sequential  持久顺序节点
     *                    ephemeral  临时节点
     *                    ephemeral_sequential   临时顺序节点
     *
     *
     *
     *
     * 创建节点方法
     */
    private void createNoteSync() throws UnsupportedEncodingException, KeeperException, InterruptedException {
        String node_PERSISTENT = zooKeeper.create("/lg_persistent", "持久节点内容".getBytes("utf-8"), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        String node_PERSISTENT_SEQUENTIAL = zooKeeper.create("/lg_persistent_sequential", "持久节点内容".getBytes("utf-8"), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT_SEQUENTIAL);
        String node_EPERSISTENT = zooKeeper.create("/lg_ephemeral", "临时节点内容".getBytes("utf-8"), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);

        System.out.println("创建的持久节点是:" + node_PERSISTENT);
        System.out.println("创建的持久顺序节点是:" + node_PERSISTENT_SEQUENTIAL);
        System.out.println("创建的临时节点是:" + node_EPERSISTENT);

    }

    public static void main(String[] args) throws InterruptedException, IOException {
        zooKeeper = new ZooKeeper("192.168.102.129:2181", 5000, new CreateNote());
        System.out.println(zooKeeper.getState());
        Thread.sleep(Integer.MAX_VALUE);
    }
}
