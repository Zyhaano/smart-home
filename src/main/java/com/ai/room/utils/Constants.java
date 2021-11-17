package com.ai.room.utils;

import com.ai.room.enums.PredictEnum;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhaoyan7
 * @date 2021/11/16
 */
public class Constants {

    public static Map<String,String> predictResMap = new HashMap<String, String>();


    static {
        for (PredictEnum predictEnum : PredictEnum.values()) {
            Constants.predictResMap.put(predictEnum.getCode().toString(),predictEnum.getMessage());
        }
    }


    public static String predicUrl = "http://localhost:8080/smart_home/";

}
