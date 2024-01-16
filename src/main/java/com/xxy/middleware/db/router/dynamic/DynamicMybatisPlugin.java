package com.xxy.middleware.db.router.dynamic;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Invocation;

import java.util.regex.Pattern;

/**
 * mybatis拦截器，对sql语句进行拦截，修改分表信息
 */
public class DynamicMybatisPlugin implements Interceptor {
    private static Pattern pattern = Pattern.compile("(from|into|update)[\\s]{1,}(\\w{1,})", Pattern.CASE_INSENSITIVE);

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        StatementHandler statementHandler = (StatementHandler) invocation.getTarget();
    }
}
