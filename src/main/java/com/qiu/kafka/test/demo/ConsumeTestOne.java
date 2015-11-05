package com.qiu.kafka.test.demo;

import kafka.consumer.Consumer;
import kafka.consumer.ConsumerConfig;
import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ConsumerConnector;
import kafka.message.Message;
import kafka.message.MessageAndMetadata;

import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Title :
 * <p/>
 * Description :
 * <p/>
 * CopyRight : CopyRight (c) 2014
 * <p/>
 * <p/>
 * JDK Version Used : JDK 5.0 +
 * <p/>
 * Modification History	:
 * <p/>
 * <pre>NO.    Date    Modified By    Why & What is modified</pre>
 * <pre>1    15.10.22    bob        Created</pre>
 * <p/>
 *未运行通过
 * http://www.aboutyun.com/thread-9313-1-1.html
 * @author bob
 */
public class ConsumeTestOne {
    private static ConsumerConfig createConsumerConfig() {
        Properties properties = new Properties();
        properties.put("zookeeper.connect", "10.1.251.122:12181,10.1.251.123:12181,10.1.251.124:12181/kafka");
        properties.put("group.id", "test_group");
        properties.put("zookeeper.session.timeout.ms", "400000");
        properties.put("zookeeper.sync.time.ms", "200");
        properties.put("auto.commit.interval.ms", "1000");

        return new ConsumerConfig(properties);
    }

    public static void main(String[] args) {
        ConsumerConnector connector = Consumer.createJavaConsumerConnector(createConsumerConfig());

        Map<String, Integer> topics = new HashMap<String, Integer>();
        topics.put("testTopic", 2);
        Map<String, List<KafkaStream<byte[], byte[]>>> topicMessageStreams = connector.createMessageStreams(topics);
        List<KafkaStream<byte[], byte[]>> streams = topicMessageStreams.get("testTopic");
        ExecutorService threadPool = Executors.newFixedThreadPool(2);
        for (final KafkaStream<byte[], byte[]> stream : streams) {
            threadPool.submit(new Runnable() {
                @Override
                public void run() {
                    for (MessageAndMetadata msgAndMetadata : stream) {
                        System.out.println("topic:" + msgAndMetadata.topic());
                        Message message = (Message) msgAndMetadata.message();
                        ByteBuffer buffer = message.payload();
                        byte[] bytes = new byte[message.payloadSize()];
                        buffer.get(bytes);
                        String tmp = new String(bytes);
                        System.out.println("message content:" + tmp);
                    }
                }
            });
        }
    }
}
