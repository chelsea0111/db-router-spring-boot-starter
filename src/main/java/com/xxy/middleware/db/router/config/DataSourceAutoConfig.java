package com.xxy.middleware.db.router.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

@Configuration
public class DataSourceAutoConfig {
    Logger logger = LoggerFactory.getLogger(DataSourceAutoConfig.class);

    @Resource
    private DBRouterConfig dbRouterConfig;

    private final static String DATASOURCE_PREFIX = "router.jdbc.datasource.";


}
