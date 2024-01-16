package com.xxy.middleware.db.router.annotation;

import java.lang.annotation.*;

/**
 * 路由注解。
 * 路由注解的目的就是为了切面提供切点，同时获取方法中入参属性的某个字段，这个字段会作为路由字段存在
 */

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface DBRouter {
    /**
     * 分库分表字段
     */
    String key() default "";
}
