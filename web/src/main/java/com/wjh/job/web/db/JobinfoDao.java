package com.wjh.job.web.db;

import com.wjh.job.web.db.mapper.JobinfoDO;
import com.wjh.job.web.db.mapper.JobinfoRepository;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.PageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component
public class JobinfoDao {
    @Autowired
    private JobinfoRepository jobinfoRepository;

    /**
     * 新增或编辑
     */
    public Object save(JobinfoDO jobinfo){
        return jobinfoRepository.save(jobinfo);
    }
    /**
     * 查询
     */
    public List<JobinfoDO> findByCompanyId(String company_id){
        return jobinfoRepository.findByCompanyId(company_id);
    }
    /**
     * 分页查询
     */
    public Object list(JobinfoDO jobinfo,
                       @RequestParam(required = false, defaultValue = "0") int pageNumber,
                       @RequestParam(required = false, defaultValue = "10") int pageSize) {

        //创建匹配器，需要查询条件请修改此处代码
        ExampleMatcher matcher = ExampleMatcher.matchingAll();

        //创建实例
        Example<JobinfoDO> example = Example.of(jobinfo, matcher);
        //分页构造
        Pageable pageable = PageRequest.of(pageNumber,pageSize);

        return jobinfoRepository.findAll(example, pageable);
    }
}
