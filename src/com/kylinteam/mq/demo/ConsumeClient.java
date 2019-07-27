package com.kylinteam.mq.demo;

/**
 * 消息消费客户端示例
 *
 * @author troyer.wang
 * @date 2019/7/27
 */
public class ConsumeClient {

    public static void main(String[] args) throws Exception {
        String message1 = MqClient.consume();
        String message2 = MqClient.consume();
        String message3 = MqClient.consume();
        String message4 = MqClient.consume();
        System.out.println("获取的消息为：" + message1);
        System.out.println("获取的消息为：" + message2);
        System.out.println("获取的消息为：" + message3);
        System.out.println("获取的消息为：" + message4);
    }

}
