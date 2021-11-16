package com.ai.room.enums;

/**
 * @author zhaoyan
 * @date 2021/11/16
 */
public enum PredictEnum {
    LessParlour(0,"Living too little in the living room, it is recommended to increase entertainment activities"),
    NormalParlour(1,"Living room activity time is normal, continue to maintain"),
    MoreParlour(2,"Living time in the living room is too long, pay attention to rest"),
    LessKitchen(10,"Kitchen time is too little, please pay attention to healthy eating"),
    NormalKitchen(11,"Kitchen time is normal, keep it up"),
    MoreKitchen(12,"Kitchen time is too long, be careful to eat fat"),
    LessBathRoom(20,"Too little restroom time, please pay attention to gastrointestinal health"),
    NormalBathRoom(21,"Restroom hours are normal, continue to maintain"),
    MoreBathRoom(22,"Please pay attention to your gastrointestinal health if you spend too long in the bathroom"),
    LessBedRoom(30,"There is too little time in the bedroom, please pay attention to sleep"),
    NormalBedRoom(31,"The bedroom time is normal, keep it up"),
    MoreBedRoom(32,"Too much time in the bedroom, please pay attention to sleep")
    ;
    private Integer code;
    private String message;

    PredictEnum(Integer code, String message){
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
