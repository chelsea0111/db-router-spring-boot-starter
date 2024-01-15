package com.xxy.middleware.db.router.annotation;

import java.lang.annotation.*;

/**w
 * 路由策略，是否分表
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface DBRouterStrategy {
    boolean splitTable() default false;
}
