package cn.cie.event;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by RojerAlone on 2017/6/25.
 * 事件实体
 */
public class EventModel {

    /**
     * 在缓存中事件队列的key
     */
    public static final String EVENT_KEY = "event";

    /**
     * 事件类型
     */
    private EventType eventType;
    /**
     * 事件发出者id
     */
    private int fromId;
    /**
     * 事件接受者id
     */
    private int toId;
    /**
     * 触发事件的实体，比如评论点赞
     */
    private int entityId;
    /**
     * 实体拥有者
     */
    private int entityOwnerId;
    /**
     * 可能会有的额外信息
     */
    private Map<String ,String> exts = new HashMap<String, String>();

    public EventModel() {

    }

    public EventModel(EventType eventType) {
        this.eventType = eventType;
    }

    public EventType getEventType() {
        return eventType;
    }

    public EventModel setEventType(EventType eventType) {
        this.eventType = eventType;
        return this;
    }

    public int getFromId() {
        return fromId;
    }

    public EventModel setFromId(int fromId) {
        this.fromId = fromId;
        return this;
    }

    public int getToId() {
        return toId;
    }

    public EventModel setToId(int toId) {
        this.toId = toId;
        return this;
    }

    public int getEntityId() {
        return entityId;
    }

    public EventModel setEntityId(int entityId) {
        this.entityId = entityId;
        return this;
    }

    public int getEntityOwnerId() {
        return entityOwnerId;
    }

    public EventModel setEntityOwnerId(int entityOwnerId) {
        this.entityOwnerId = entityOwnerId;
        return this;
    }

    public String getExts(String key) {
        return exts.get(key);
    }

    public EventModel setExts(String key, String value) {
        this.exts.put(key, value);
        return this;
    }
}
