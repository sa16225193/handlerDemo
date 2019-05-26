import java.util.UUID;

public class TestHandler {

    public static void main(String[] args) {
        //初始化Looper
        Looper.prepare();

        //创建Handler实例
        Handler handler = new Handler() {

            /**
             * 处理消息
             * @param msg
             */
            public void handleMessage(Message msg) {
                System.out.println("Thread Id : " + Thread.currentThread().getName() + " received message = " + msg);
            }
        };

        //开启10个线程,不断发送消息
        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        Message msg = new Message(UUID.randomUUID().toString());
                        System.out.println(Thread.currentThread().toString() + " send message = " + msg);
                        handler.sendMessage(msg);
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }).start();
        }

        //不断从消息队列中取消息
        Looper.loop();
    }

}
