package com.gavin.itnews.event;

/**
 * Created by Gavin
 * on 2019/4/15 20:30
 */
public enum EventType {
    LIKE(0),
    DISLIKE(1),
    COMMENT(2),
    PERSONAL_MESSAGE(3);
    private int type;

    EventType(int type) {
        this.type = type;
    }
}
