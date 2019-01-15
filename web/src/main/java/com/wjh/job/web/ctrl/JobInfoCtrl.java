package com.wjh.job.web.ctrl;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class JobInfoCtrl {

    @RequestMapping(value="/demo/jobinfo/detail",method = RequestMethod.POST,produces = "application/json")
    @ResponseBody
    String getInfo(@RequestBody JSONObject jsonParam) {
        return jsonParam.toJSONString();
    }
}
