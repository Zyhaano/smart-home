package com.ai.room.contrller;

import com.ai.room.enums.HomeEnum;
import com.ai.room.service.SmartHomeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author zhaoyan
 * @date 2021/11/17
 */
@Slf4j
@RestController
@RequestMapping("/smart")
public class SmartHomeController {
    @Autowired
    SmartHomeService smartHomeService;

    @RequestMapping(value = "/predict", method = {RequestMethod.POST, RequestMethod.GET})
    public String predict(HttpServletRequest request,Integer roomNumber ,Integer hourNumber) throws Exception {
        return smartHomeService.predict(roomNumber,hourNumber);
    }
}
