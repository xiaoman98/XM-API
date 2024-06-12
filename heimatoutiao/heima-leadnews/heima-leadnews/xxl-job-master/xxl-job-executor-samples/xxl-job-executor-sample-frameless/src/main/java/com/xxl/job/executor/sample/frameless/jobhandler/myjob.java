package com.xxl.job.executor.sample.frameless.jobhandler;

import com.xxl.job.core.handler.IJobHandler;



public class myjob extends IJobHandler {
    @Override
    public void execute() throws Exception {
        System.out.println("我们不能失去信仰");
    }
}
