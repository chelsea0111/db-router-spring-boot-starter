package com.xxy.middleware.db.router;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 数据路由配置
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DBRouterConfig {
    private int dbCount;

    private int tbCount;

    private String routerKey;
}
