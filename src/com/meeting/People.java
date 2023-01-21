package com.meeting;

public class People implements Runnable {
    private String name;
    private Meet meet;
    public People(Meet meet, String name) {
        this.meet = meet;
        this.name = name;
    }
    public String getName() {
        return name;
    }

    @Override
    public void run() {
        try {
            meet.arrive(this.name);
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
