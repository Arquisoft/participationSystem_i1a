package asw.producers;

import java.util.Properties;

import kafka.admin.AdminUtils;
import kafka.utils.ZKStringSerializer$;
import kafka.utils.ZkUtils;
import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.ZkConnection;

public class KafkaTopicCreation {
    public static void createTopic(String topicName, int partitions, int replication) throws Exception {
        ZkClient zkClient = null;
        ZkUtils zkUtils;
        try {
            String zookeeperHosts = "localhost:2181";
            int sessionTimeOutInMs = 15 * 1000;
            int connectionTimeOutInMs = 10 * 1000;

            zkClient = new ZkClient(zookeeperHosts, sessionTimeOutInMs, connectionTimeOutInMs, ZKStringSerializer$.MODULE$);
            zkUtils = new ZkUtils(zkClient, new ZkConnection(zookeeperHosts), false);

            Properties topicConfig = new Properties();

            AdminUtils.createTopic(zkUtils, topicName, partitions, replication, topicConfig);

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (zkClient != null)
                zkClient.close();
        }
    }
}