public class Looper {

    /**
     * 用来使不同线程共享Looper
     */
    static final ThreadLocal<Looper> sThreadLocal = new ThreadLocal<>();

    /**
     * 消息队列，存储Handler发送消息
     */
    static MessageQueue mQueue;

    private Looper() {
        mQueue = new MessageQueue();
    }

    /**
     * 初始化Looper
     */
    public static void prepare() {
        if (sThreadLocal.get() != null) {
            throw new RuntimeException("Only one Looper may be created per thread");
        }
        sThreadLocal.set(new Looper());
    }

    /**
     * 不断地从消息队列中获取消息
     */
    public static void loop() {
        //定义final避免被Hook
        final Looper looper = myLooper();
        final MessageQueue queue = looper.mQueue;
        for (; ; ) {
            //从队列中取消息
            Message message = queue.next();
            //获取sendMessage()的handler,并使之处理消息
            message.target.dispatchMessage(message);
        }
    }


    /**
     * 不通过return this来获取Looper对象
     * 通过ThreadLocal来保证一个线程只有唯一地Looper
     *
     * @return
     */
    public static Looper myLooper() {
        return sThreadLocal.get();
    }
}
