package com.kylinteam.mq.demo;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * 消息处理中心类
 *
 * @author troyer.wang
 * @date 2019/7/27
 */
public class Broker {

    /**
     * 定义队列存储消息的最大数量
     */
    private final static int MAX_SIZE = 3;

    /**
     * 保存消息数据的容器
     */
    private static ArrayBlockingQueue<String> messageQueue = new ArrayBlockingQueue<String>(MAX_SIZE);

    /**
     * 生产消息
     * @param msg 可用消息
     */
    public static void produce(String msg) {
        if (messageQueue.offer(msg)) {
            System.out.println("成功向消息中心投递消息：" + msg + "，当前暂存的消息数量是：" + messageQueue.size());
        } else {
            System.out.println("消息处理中心内暂存的消息达到最大值，不能再放入消息！");
        }
        printSeparator();
    }

    /**
     * 消费消息
     * @return msg 可用消息
     */
    public static String consume() {
        String msg = messageQueue.poll();
        if (msg != null) {
            // 如果消息不为空，则从消息容器中取出一条消息
            System.out.println("已消费的信息是：" + msg + "，当前暂存的消息数量是：" + messageQueue.size());
        } else {
            System.out.println("消息处理中心内没有可用消息");
        }
        printSeparator();
        return msg;
    }

    /**
     * 打印分隔符
     */
    private static void printSeparator() {
        System.out.println("===========================");
    }

}
