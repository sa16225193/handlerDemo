import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * 根据时间进行排序的优先级队列
 */
public class MessageQueue {

    /**
     * 线程阻塞队列,AyncTask里面使用的就是BlockingQueue
     */
    BlockingQueue<Message> queue = new ArrayBlockingQueue<>(10);

    /**
     * 消息入队列
     * @param msg
     */
    public void enqueueMessage(Message msg) {
        try {
            queue.put(msg);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 取队列中的消息
     * @return
     */
    public Message next() {
        Message message = null;
        try {
            message = queue.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return message;
    }
}
