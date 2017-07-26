package cn.cie.event;

import cn.cie.event.handler.EventHandler;
import cn.cie.utils.RedisUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Created by RojerAlone on 2017/6/25.
 * 事件消费者，不断从事件队列中获取事件，根据事件类型处理不同的事件
 */
@Service
public class EventConsumer implements InitializingBean, ApplicationContextAware {

    private final Logger logger = Logger.getLogger(this.getClass());

    private ApplicationContext applicationContext;

    @Autowired
    private RedisUtil<EventModel> redisUtil;

    /**
     * 事件类型以及执行这些事件的handler
     */
    private Map<EventType, List<EventHandler>> handlers = new HashMap<EventType, List<EventHandler>>();

    public void afterPropertiesSet() throws Exception {
        // 从上下文中获取所有的handler
        Map<String, EventHandler> beans = applicationContext.getBeansOfType(EventHandler.class);
        if (beans != null) {
            for (Map.Entry<String, EventHandler> entry : beans.entrySet()) {
                // 遍历所有的 hander ，将 event-handler 的映射加入 handlers 中
                List<EventType> types = entry.getValue().getSupportEvent();
                for (EventType type : types) {
                    if (!handlers.containsKey(type)) {
                        handlers.put(type, new ArrayList<EventHandler>());
                    }
                    handlers.get(type).add(entry.getValue());
                }
            }
        }
        class EventSonsumerThread implements Runnable {
            public void run() {
                while (true) {
                    EventModel event = redisUtil.blpopObject(0, EventModel.EVENT_KEY, EventModel.class);
                    if (event == null) {
                        continue;
                    }
                    if (!handlers.containsKey(event.getEventType())) {
                        logger.error("error event type");
                        continue;
                    }
                    logger.info("当前线程为：" + Thread.currentThread().getName());
                    for (EventHandler handler : handlers.get(event.getEventType())) {
                        handler.doHandler(event);
                    }
                }
            }
        }
        // 创建一个新线程处理事件
//        Thread eventHandlerThread = new Thread(new Runnable() {
//            public void run() {
//                while (true) {
//                    EventModel event = redisUtil.blpopObject(0, EventModel.EVENT_KEY, EventModel.class);
//                    if (event == null) {
//                        continue;
//                    }
//                    if (!handlers.containsKey(event.getEventType())) {
//                        logger.error("error event type");
//                        continue;
//                    }
//                    for (EventHandler handler : handlers.get(event.getEventType())) {
//                        handler.doHandler(event);
//                    }
//                }
//            }
//        }, "eventHandlerThread");
//        eventHandlerThread.start();
        ThreadPoolExecutor threadPool = (ThreadPoolExecutor) Executors.newFixedThreadPool(3);
        threadPool.execute(new EventSonsumerThread());
        threadPool.execute(new EventSonsumerThread());
        threadPool.execute(new EventSonsumerThread());
    }

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
