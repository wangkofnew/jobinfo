package com.wjh.job.web.spider;

import com.wjh.job.web.spider.bank.Citic;
import com.wjh.job.web.spider.bank.Psbc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Init implements CommandLineRunner {
    @Autowired
    private Psbc psbc;
    @Autowired
    private Citic citic;
    @Override
    public void run(String... args) throws Exception {
        psbc.letsGO();
        citic.letsGO();
    }
}
