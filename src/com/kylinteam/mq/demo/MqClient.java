package com.kylinteam.mq.demo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

/**
 * 消息处理中心客户端
 *
 * @author troyer.wang
 * @date 2019/7/27
 */
public class MqClient {

    /**
     * 生产消息
     * @param message 可用消息
     */
    public static void produce(String message) throws Exception {
        Socket socket = new Socket(InetAddress.getLocalHost(), BrokerServer.SERVER_PORT);
        PrintWriter out = new PrintWriter(socket.getOutputStream());

        // 写入消息
        out.println(message);
        out.flush();
    }

    /**
     * 消费消息
     * @return message 可用消息
     */
    public static String consume() throws Exception {
        Socket socket = new Socket(InetAddress.getLocalHost(), BrokerServer.SERVER_PORT);
        BufferedReader in =  new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter out = new PrintWriter(socket.getOutputStream());

        // 先向消息队列中发送指令"CONSUME"，表示准备要消息
        out.println("CONSUME");
        out.flush();

        // 获取消息
        String message = in.readLine();
        return message;
    }

}
