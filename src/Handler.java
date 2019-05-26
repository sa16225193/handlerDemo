public class Handler {

    /**
     * 消息队列
     */
    MessageQueue mQueue;

    /**
     * 不断从消息队列中取消息
     */
    Looper mLooper;


    public Handler() {
        //这样获取Looper与MessageQueue实例，用来保证不同线程共享Looper和MessageQueue实例
        mLooper = Looper.myLooper();
        mQueue = mLooper.mQueue;
    }

    /**
     * 发送消息
     * @param msg
     */
    public void sendMessage(Message msg) {
        enqueueMessage(msg);
    }

    /**
     * 消息添加到消息队列
     * @param msg
     */
    private void enqueueMessage(Message msg) {
        //注意,Handler也会作为Message的一部分进入消息队列
        msg.target = this;
        mQueue.enqueueMessage(msg);
    }

    /**
     * 处理消息
     * @param msg
     */
    public  void dispatchMessage(Message msg) {
        handleMessage(msg);
    }

    public void handleMessage(Message msg) {

    }


}
