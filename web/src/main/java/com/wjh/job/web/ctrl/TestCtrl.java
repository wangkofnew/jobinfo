package com.wjh.job.web.ctrl;

import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.*;

@RestController
public class TestCtrl {

    @RequestMapping(value="/test",method = RequestMethod.GET,produces = "application/json")
    @ResponseBody
    String getInfo(@RequestBody JSONObject jsonParam) {
        return "OK";
    }
}
