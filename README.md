# handlerDemo
手写Handler框架示例

##  Handler作用
Handler是Android SDK来处理异步消息的核心类。 
子线程与主线程通过Handler来进行通信。子线程可以通过Handler来通知主线程进行UI更新。

## Handler工作流程
![](http://ww3.sinaimg.cn/large/006tNc79gy1g3er2olzk4j31bo0p4ahf.jpg)

## Handler源码分析
### 关键点
#### 如何实现线程间跨越的？
共享内存。
#### Handler是如何共享内存的
通过ThreadLocal共享MessageQueue和Looper。
#### 架构师从中可以学到什么样的设计思维
生产者-消费者设计模式  
生产 -> 消息入队列  
消费 -> 消息出队列  

### Handler主要方法
![](http://ww4.sinaimg.cn/large/006tNc79gy1g3er3rx9ekj31080iy127.jpg)

### Looper的构造函数为什么是private
#### 对应为什么一个线程只有一个Looper？
一个线程 -> 唯一ThreadLocal -> 唯一Looper -> 唯一MessageQueue  
因为Looper是通过Looper.prepare()创建的，而在prepare()方法中，会把Looper存放在ThreadLocal中。
ThreadLocal是一个HashMap，key是Thread(Thread.currentThread()),value是Looper对象。
同时，获取Looper对象也通过ThreadLocal.get()方法来获取，保证一个线程只有一个Looper。

### ThreadLocal介绍
ThreadLocal用来提供线程的上下文Context（比如线程的局部变量等），每个线程都具有唯一一个ThreadId和ThreadLocal.Value。


### MessageQueue介绍
根据时间进行排序的优先级队列。
![](http://ww3.sinaimg.cn/large/006tNc79gy1g3er4f43phj30j40l0gp8.jpg)









