package com.stackroute.swisit.crawler.kafka;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import javax.swing.event.SwingPropertyChangeSupport;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.stackroute.swisit.crawler.domain.SwisitBean;

@Service
public class KafkaConfig implements MessageService{
	public void publishingMessage(String topicName,Object message) throws JsonProcessingException{

        Properties configProperties = new Properties();
        configProperties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,
                "172.23.239.165:9092");
        //configProperties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"localhost:9092");
        configProperties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.ByteArraySerializer");
        configProperties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.StringSerializer");
        Producer producer = new KafkaProducer(configProperties);
//        for (int i = 0; i < 100; i++) {
//                String msg = "Message " + i;
//                producer.send(new ProducerRecord<String, String>(topicName, msg));
//                System.out.println("Sent:" + msg);
//        }
        //TODO: Make sure to use the ProducerRecord constructor that does not take parition Id
        //Movie m=new Movie();
        //byte b[]=m.serialize("hi", message);
        ObjectMapper om=new ObjectMapper();
        String s=om.writeValueAsString(message);
        ProducerRecord<String, String> rec = new ProducerRecord<String, String>(topicName,s);
        producer.send(rec);
        producer.send(rec);
        producer.close();
    }
	public List<SwisitBean> receivingMessage(String string) {
	       System.out.println("inside receiving mesage");
	       // TODO Auto-generated method stub
	       Properties props = new Properties();
	       //props.put("bootstrap.servers", "172.23.239.180:9095");
	       //props.put("bootstrap.servers", "localhost:9092");
	       props.put("group.id", "group-1");
	       
	       props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"172.23.239.165:9092");
	       props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
	       props.put("value.deserializer", "com.stackroute.swisit.crawler.domain.SwisitBean");
	       
	       List<SwisitBean> final_kafka=new ArrayList<SwisitBean>();
	       
	       //props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.ByteArraySerializer");
	       //props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.StringSerializer");
	       //SwisitBean[] cb =new Array
	       
	       KafkaConsumer<String, SwisitBean> kafkaConsumer = new KafkaConsumer<>(props);
	       kafkaConsumer.subscribe(Arrays.asList(string));
	       int i=0;
	       while (true) {
	         ConsumerRecords<String, SwisitBean> records = kafkaConsumer.poll(100000);
	         //System.out.println("my records are"+records);
	         for (ConsumerRecord<String, SwisitBean> record : records) {
	            final_kafka.add(record.value());
	         
	         }
	         
	         return final_kafka;
	    }

	}}