package com.ai.room.service;

import com.ai.room.enums.HomeEnum;
import com.ai.room.enums.PredictEnum;
import com.ai.room.utils.Constants;
import com.ai.room.utils.HttpUtils;

/**
 * @author zhaoyan
 * @date 2021/11/16
 */
public class SmartHome {



    public static void main(String[] args) {
        for (PredictEnum predictEnum : PredictEnum.values()) {
            Constants.predictResMap.put(predictEnum.getCode().toString(),predictEnum.getMessage());
        }

        Integer homeNumber = HomeEnum.BEDROOM.getRoomCode();
        Integer hourNumber = 2;
        String url = "http://localhost:8080/smart_home/" + homeNumber + "/" + hourNumber;

        String result = HttpUtils.doGet(url);
        String message = Constants.predictResMap.get(result);
        System.out.println(message);
    }

}
