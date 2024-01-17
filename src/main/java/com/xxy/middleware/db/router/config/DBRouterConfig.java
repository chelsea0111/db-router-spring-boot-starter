package com.xxy.middleware.db.router.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@ConfigurationProperties("router.jdbc.datasource")
public class DBRouterConfig {
    private int dbCount;
    private int tbCount;
    private String list;
    private Map<String, DataSourceConfig> dataSources = new HashMap<>();

    public DBRouterConfig() {
    }

    public DBRouterConfig(int dbCount, int tbCount, String list, Map<String, DataSourceConfig> dataSources) {
        this.dbCount = dbCount;
        this.tbCount = tbCount;
        this.list = list;
        this.dataSources = dataSources;
    }

    public int getDbCount() {
        return dbCount;
    }

    public void setDbCount(int dbCount) {
        this.dbCount = dbCount;
    }

    public int getTbCount() {
        return tbCount;
    }

    public void setTbCount(int tbCount) {
        this.tbCount = tbCount;
    }

    public String getList() {
        return list;
    }

    public void setList(String list) {
        this.list = list;
    }

    public Map<String, DataSourceConfig> getDataSources() {
        return dataSources;
    }

    public void setDataSources(Map<String, DataSourceConfig> dataSources) {
        this.dataSources = dataSources;
    }

}
