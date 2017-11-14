package cn.cie.event;

import cn.cie.event.handler.EventHandler;
import cn.cie.utils.RedisUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
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
public class EventConsumer implements InitializingBean, ApplicationContextAware, DisposableBean {

    private final Logger logger = Logger.getLogger(this.getClass());

    private ApplicationContext applicationContext;

    @Autowired
    private RedisUtil<EventModel> redisUtil;

    private ThreadPoolExecutor threadPool;

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
        // 设置线程池的大小为 CPU 的核数 * 2
        threadPool = (ThreadPoolExecutor) Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() + 2);
        new Thread(new Runnable() {
            public void run() {
                while (true) {
                    EventModel event = redisUtil.lpopObject(EventModel.EVENT_KEY, EventModel.class);
                    if (event == null) {
                        continue;
                    }
                    if (!handlers.containsKey(event.getEventType())) {
                        logger.error("error event type");
                        continue;
                    }
                    for (EventHandler handler : handlers.get(event.getEventType())) {
                        threadPool.execute(new EventConsumerThread(handler, event));
                    }
                }
            }
        }).start();
    }

    class EventConsumerThread implements Runnable {

        private EventHandler handler;

        private EventModel event;

        public EventConsumerThread(EventHandler handler, EventModel event) {
            this.handler = handler;
            this.event = event;
        }

        public void run() {
            handler.doHandler(event);
        }
    }

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public void destroy() throws Exception {
        if (threadPool != null) {
            while (threadPool.getQueue().size() != 0 || threadPool.getActiveCount() != 0) {
                // 等待所有任务执行完毕
            }
            threadPool.shutdownNow();
        }
    }
}
