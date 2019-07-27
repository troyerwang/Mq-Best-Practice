package com.kylinteam.mq.demo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 消息处理中心服务端
 *
 * @author troyer.wang
 * @date 2019/7/27
 */
public class BrokerServer implements Runnable {

    /**
     * 定义服务端端口
     */
    public static int SERVER_PORT = 9000;

    /**
     * 定义通信对象
     */
    private final Socket socket;

    public BrokerServer(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {

        try {
            // 定义输入输出对象
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream());

            // 保持持续监测，为什么不用while(true)，这里是一种装逼写法，因为while(true)最终会编译为for(;;)
            for(;;) {
                // 睡一毫秒，防治程序完全抢占CPU
                Thread.sleep(1);
                String str = in.readLine();
                if (str == null) {
                    continue;
                }
                System.out.println("接收到原始数据：" + str);

                if ("CONSUME".equals(str)) {
                    // 如果发送过来的是CONSUME指令，则从消息队列中获取一条消息
                    String message = Broker.consume();
                    out.println(message);
                    out.flush();
                } else {
                    // 如果不是这个指令，都表示生产消息放到消息队列中
                    Broker.produce(str);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(SERVER_PORT);
        while (true) {
            BrokerServer brokerServer = new BrokerServer(serverSocket.accept());
            new Thread(brokerServer).start();
        }
    }

}
