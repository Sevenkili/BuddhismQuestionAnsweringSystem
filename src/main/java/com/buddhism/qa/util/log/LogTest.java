package com.buddhism.qa.util.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by TT. Wu on 2017/4/19.
 */
public class LogTest {
    private static final Logger LOG = LoggerFactory.getLogger(LogTest.class);

    public static void main(String[] args) {
        // 记录debug级别的信息
        LOG.debug("this is a debug message");
        // 记录info级别的信息
        LOG.info("this is a info message");
        // 记录error级别的信息
        LOG.error("this is a error message");
    }
}
