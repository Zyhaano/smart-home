package com.ai.room.enums;

/**
 * @author zhaoyan
 * @date 2021/11/16
 */
public enum HomeEnum {
    PARLOUR(0,"客厅"),
    KITCHEN(1,"厨房"),
    BATHROOM(2,"卫生间"),
    BEDROOM(3,"卧室");
    private Integer roomCode;
    private String roomName;

    HomeEnum(Integer roomCode, String roomName) {
        this.roomCode = roomCode;
        this.roomName = roomName;
    }

    public Integer getRoomCode() {
        return roomCode;
    }

    public String getRoomName() {
        return roomName;
    }
}
