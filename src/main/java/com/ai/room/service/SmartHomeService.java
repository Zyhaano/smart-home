package com.ai.room.service;

import com.ai.room.utils.Constants;
import com.ai.room.utils.HttpUtils;
import org.springframework.stereotype.Service;

/**
 * @author zhaoyan
 * @date 2021/11/16
 */
@Service
public class SmartHomeService {


    public String predict(Integer homeNumber ,Integer hourNumber){

        String url = Constants.predicUrl + homeNumber + "/" + hourNumber;
        String code = HttpUtils.doGet(url);
        String message = Constants.predictResMap.get(code);

        return message;
    }


}
