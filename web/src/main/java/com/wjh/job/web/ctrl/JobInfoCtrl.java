package com.wjh.job.web.ctrl;

import com.alibaba.fastjson.JSONObject;
import com.wjh.job.web.db.JobinfoDao;
import com.wjh.job.web.db.mapper.JobinfoDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class JobInfoCtrl {

    @Autowired
    private JobinfoDao jobinfoDao;
    @RequestMapping(value="/demo/jobinfo/detail",method = RequestMethod.POST,produces = "application/json")
    @ResponseBody
    String getInfo(@RequestBody JSONObject jsonParam) {


        Page<JobinfoDO> jobList =  (Page<JobinfoDO>)jobinfoDao.list(new JobinfoDO(),0,10000);
        jobList.getContent();

        return JSONObject.toJSONString(jobList.getContent());
    }
}


