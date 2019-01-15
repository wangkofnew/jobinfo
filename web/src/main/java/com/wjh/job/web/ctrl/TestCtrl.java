package com.wjh.job.web.ctrl;

import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class TestCtrl {

    @RequestMapping(value="/demo/test",method = RequestMethod.GET)
    @ResponseBody
    String getInfo(HttpServletRequest request) {
        return "OK";
    }
}
