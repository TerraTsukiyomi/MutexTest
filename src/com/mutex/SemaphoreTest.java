package com.mutex;

import java.util.concurrent.Semaphore;

public class SemaphoreTest {

    // max 1 people
    static Semaphore semaphore = new Semaphore(1);

    static class MutexAuctionThread extends Thread {

        String name = "";

        MutexAuctionThread(String name) {
            this.name = name;
        }

        public void run() {

            try {

                System.out.println(name + " : фиксация блокировки...");
                System.out.println(name + " : сейчас свободных доступов: "
                        + semaphore.availablePermits());

                semaphore.acquire();
                System.out.println(name + " : получил разрешение!!");

                try {

                    for (int i = 1; i <= 5; i++) {

                        System.out.println(name + " : выполняет операцию " + i
                                + ", свободных доступов : "
                                + semaphore.availablePermits());

                        // sleep 3 second
                        Thread.sleep(3000);

                    }

                } finally {

                    System.out.println(name + " : освободил доступ...");
                    semaphore.release();
                    System.out.println(name + " : сейчас свободных доступов: "
                            + semaphore.availablePermits());

                }

            } catch (InterruptedException e) {

                e.printStackTrace();

            }

        }

    }

    public static void main(String[] args) {

        System.out.println("Всего свободных доступов : "
                + semaphore.availablePermits());

        MutexAuctionThread t1 = new MutexAuctionThread("Гость 1");
        t1.start();

        MutexAuctionThread t2 = new MutexAuctionThread("Гость 2");
        t2.start();

        MutexAuctionThread t3 = new MutexAuctionThread("Гость 3");
        t3.start();

        MutexAuctionThread t4 = new MutexAuctionThread("Гость 4");
        t4.start();

        MutexAuctionThread t5 = new MutexAuctionThread("Гость 5");
        t5.start();

        MutexAuctionThread t6 = new MutexAuctionThread("Гость 6");
        t6.start();

    }
}