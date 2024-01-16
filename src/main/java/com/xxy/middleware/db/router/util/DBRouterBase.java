package com.xxy.middleware.db.router.util;

import com.xxy.middleware.db.router.DBContextHolder;

/**
 * 数据源基础配置,使用方的实体类继承该对象，通过tbIdx字段获取分表情况
 */
public class DBRouterBase {
    private String tbIdx;

    public String getTbIdx() {
        return DBContextHolder.getTBKey();
    }
}
