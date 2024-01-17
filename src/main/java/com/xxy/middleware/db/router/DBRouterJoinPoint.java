package com.xxy.middleware.db.router;

import com.xxy.middleware.db.router.annotation.DBRouter;
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

    @Pointcut("@annotation(com.xxy.middleware.db.router.annotation.DBRouter)")
    public void aopPoint() {
    }

    @Around("aopPoint() && @annotation(dbRouter)")
    public Object doRouter(ProceedingJoinPoint pjp, DBRouter dbRouter) {
        String key = dbRouter.key();
        // 根据属性key获取value
        String value = getValueByKey(key, pjp.getArgs());
        return value;
    }

    private String getValueByKey(String key, Object[] args) {
        for (Object arg : args) {
            System.out.println("arg = " + arg);
        }
        return null;
    }

}
