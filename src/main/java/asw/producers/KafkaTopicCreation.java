package asw.producers;

import java.util.Properties;

import kafka.admin.AdminUtils;
import kafka.utils.ZKStringSerializer$;
import kafka.utils.ZkUtils;
import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.ZkConnection;

public class KafkaTopicCreation {
	static {
		createTopic("newVote", 1, 1);
	}
	
	// Returns true if the topic has been created
    public static boolean createTopic(String topicName, int partitions, int replication) {
        ZkClient zkClient = null;
        ZkUtils zkUtils;
        try {
            String zookeeperHosts = "localhost:2181";
            int sessionTimeOutInMs = 15 * 1000;
            int connectionTimeOutInMs = 10 * 1000;

            zkClient = new ZkClient(zookeeperHosts, sessionTimeOutInMs, connectionTimeOutInMs, ZKStringSerializer$.MODULE$);
            zkUtils = new ZkUtils(zkClient, new ZkConnection(zookeeperHosts), false);

            Properties topicConfig = new Properties();

            if (AdminUtils.topicExists(zkUtils, topicName)) {
            	return false;
            } else {
            	AdminUtils.createTopic(zkUtils, topicName, partitions, replication, topicConfig);
            	return true;
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        } finally {
            if (zkClient != null)
                zkClient.close();
        }
    }
}