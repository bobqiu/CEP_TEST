package com.qiu.kafka.test.demo;

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;

import java.util.Properties;

/**
 * Title :
 * <p>
 * Description :
 * <p>
 * CopyRight : CopyRight (c) 2014
 * <p>
 * <p>
 * JDK Version Used : JDK 5.0 +
 * <p>
 * Modification History	:
 * <p>
 * <pre>NO.    Date    Modified By    Why & What is modified</pre>
 * <pre>1    15.10.22    bob        Created</pre>
 * <p>
 *http://blog.csdn.net/ganglia/article/details/12651891
 * @author bob
 */
public class ProducerTest {
    public static void main(String[] args) {

        Properties props = new Properties();
        props.put("zk.connect", "10.1.251.122:12181,10.1.251.123:12181,10.1.251.124:12181/kafka");
        props.put("serializer.class", "kafka.serializer.StringEncoder");
        props.put("metadata.broker.list", "10.1.251.122:9092,10.1.251.123:9092,10.1.251.124:9092");
        ProducerConfig config = new ProducerConfig(props);
        Producer<String, String> producer = new Producer<String, String>(config);
        for (int i = 0; i < 1000000; i++) {
            try {
                producer.send(new KeyedMessage<String, String>("testTopic", System.currentTimeMillis()+"::test"+i));

                System.out.println(System.currentTimeMillis()+"::test"+i);
            } catch (Exception e) {
                e.printStackTrace();
            }

           // System.out.println("OK");
        }
        producer.close();
    }
}
