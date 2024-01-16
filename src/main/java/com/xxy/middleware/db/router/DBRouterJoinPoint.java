package com.xxy.middleware.db.router;

import com.xxy.middleware.db.router.annotation.DBRouter;
import com.xxy.middleware.db.router.strategy.IDBRouterStrategy;
import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;

@Aspect
public class DBRouterJoinPoint {
    Logger logger = LoggerFactory.getLogger(DBRouterJoinPoint.class);

    private DBRouterConfig dbRouterConfig;

    private IDBRouterStrategy dbRouterStrategy;

    public DBRouterJoinPoint(DBRouterConfig dbRouterConfig, IDBRouterStrategy dbRouterStrategy) {
        this.dbRouterConfig = dbRouterConfig;
        this.dbRouterStrategy = dbRouterStrategy;
    }

    /**
     * 切点
     */
    @Pointcut("@annotation(com.xxy.middleware.db.router.annotation.DBRouter)")
    public void aopPoint() {
    }

    @Around("aopPoint() && @annotation(dbRouter)")
    public Object doRouter(ProceedingJoinPoint pjp, DBRouter dbRouter) throws Throwable {
        String dbKey = dbRouter.key();
        if (StringUtils.isBlank(dbKey) && StringUtils.isBlank(dbRouterConfig.getRouterKey())) {
            throw new RuntimeException("annotation DBRouter key is null！");
        }
        dbKey = StringUtils.isNotBlank(dbKey) ? dbKey : dbRouterConfig.getRouterKey();
        // 获取dbKey的属性值
        String dbValue = getAttrValue(dbKey, pjp.getArgs());
        dbRouterStrategy.doRouter(dbValue);
        // 返回结果
        try {
            return pjp.proceed();
        } finally {
            dbRouterStrategy.clear();
        }
    }

    private String getAttrValue(String dbKey, Object[] args) {
        if (1 == args.length) {
            Object arg = args[0];
            if (arg instanceof String) {
                return arg.toString();
            }
        }

        String filedValue = null;
        for (Object arg : args) {
            try {
                if (StringUtils.isNotBlank(filedValue)) {
                    break;
                }
                Object value = getValueByName(dbKey, arg);
                filedValue = String.valueOf(value);
            } catch (Exception e) {
                logger.error("获取路由属性值失败 attr：{}", dbKey, e);
            }
        }

        return filedValue;
    }

    /**
     * 根据名称获取对象的属性值
     *
     * @param name   属性名称
     * @param object 对象
     * @return 属性值
     */
    private Object getValueByName(String name, Object object) {
        try {
            Field field = getFieldByName(name, object);
            if (field == null) {
                return null;
            }
            field.setAccessible(true);
            Object o = field.get(object);
            field.setAccessible(false);
            return o;
        } catch (IllegalAccessException e) {
            return null;
        }
    }

    /**
     * 根据名称获取对象的方法，该方法同时兼顾继承类获取父类的属性
     *
     * @param name
     * @param object
     * @return
     */
    private Field getFieldByName(String name, Object object) {
        Field field;
        try {
            Class<?> clazz = object.getClass();
            field = clazz.getDeclaredField(name);
        } catch (NoSuchFieldException e) {
            return null;
        }
        return field;
    }

}
