package com.kylinteam.mq.demo;

/**
 * 消息生产客户端示例
 *
 * @author troyer.wang
 * @date 2019/7/27
 */
public class ProduceClient {

    public static void main(String[] args) throws Exception{
        MqClient.produce("Hello World");
        MqClient.produce("ABC");
        MqClient.produce("周杰伦");
        MqClient.produce("蔡徐坤");
    }

}
