package com.wenzi.yuyin.Controller;

import com.wenzi.yuyin.com.baidu.speech.restapi.common.DemoException;
import com.wenzi.yuyin.com.baidu.speech.restapi.ttsdemo.TtsMain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

@RestController
public class HelloController {
    @Autowired
    TtsMain ttsMain;
    @RequestMapping("/hello")

    public ModelAndView hello(){
        ModelAndView mv = new ModelAndView();
//        try {
////            ttsMain.run();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (DemoException e) {
//            e.printStackTrace();
//        }
        mv.setViewName("/index.html");
        return mv;
    }
}
