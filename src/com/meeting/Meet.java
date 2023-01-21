package com.meeting;

import java.util.concurrent.CountDownLatch;

public class Meet implements Runnable {
    private CountDownLatch register;
    public void arrive(String name) {
        System.out.printf("%s已经到会.\n", name);
        register.countDown();
        System.out.printf("智能会议系统:等待%d名还未参会人员.\n", register.getCount());
    }

    public Meet(Integer count) {
        register = new CountDownLatch(count);
    }
    @Override
    public void run() {
        System.out.printf("智能会议系统:总共有%d名参会人员.\n", register.getCount());
        try {
            // 等待与会人员
            register.await();
            //开始会议
            System.out.printf("智能会议系统:所有人已经到齐\n");
            System.out.printf("智能会议系统:让我们一起开会吧\n");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // 创建一个智能会议系统对象，它有20个参与者
        Meet meeting = new Meet(20);
        Thread threadConference = new Thread(meeting);
        threadConference.start();

        // 创建20个参会人员，每个参会人员都是并行运行
        for (int i = 1; i < 21; i++) {
            People p = new People(meeting, "参会人员" + i);
            Thread t = new Thread(p);
            t.start();
        }
    }
}
