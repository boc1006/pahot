package com.boc.common.core.test.mybatis;

import java.sql.Connection;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Properties;

import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.type.TypeHandlerRegistry;

import com.boc.common.utils.ReflectHelper;

@Intercepts({@Signature(type=StatementHandler.class,method="prepare",args={Connection.class, Integer.class })})
public class MyBatisSQLInterceptor implements Interceptor {

    private Properties properties;

    public Object intercept(Invocation invocation) throws Throwable {
    	Boolean disable = Boolean.valueOf(System.getProperty("disable.junit.dubboProviderMethodFacade", "true"));
    	if(disable) {
    		return invocation.proceed();
    	}
        StatementHandler statementHandler = (StatementHandler) invocation.getTarget();
        BoundSql boundSql = statementHandler.getBoundSql();
        ParameterHandler parameterHandler = statementHandler.getParameterHandler();
        MappedStatement mappedStatement = (MappedStatement) ReflectHelper.getValueByFieldName(parameterHandler, "mappedStatement");
        String sql = showSql(mappedStatement.getConfiguration(),boundSql);
        List<String> list = SqlThreadLocal.get();
        if (list == null) {
            list = new ArrayList<String>(5);
            SqlThreadLocal.set(list);
        }
        list.add(sql);
        return invocation.proceed();
    }

    public static String getSql(Configuration configuration, BoundSql boundSql, String sqlId, long time) {
        String sql = showSql(configuration, boundSql);
        StringBuilder str = new StringBuilder(100);
        str.append(sqlId);
        str.append(":");
        str.append(sql);
        str.append(":");
        str.append(time);
        str.append("ms");
        return str.toString();
    }

    private static String getParameterValue(Object obj) {
        String value = null;
        if (obj instanceof String) {
            value = "'" + obj.toString() + "'";
        } else if (obj instanceof Date) {
            DateFormat formatter = DateFormat.getDateTimeInstance(DateFormat.DEFAULT, DateFormat.DEFAULT, Locale.CHINA);
            value = "'" + formatter.format(new Date()) + "'";
        } else {
            if (obj != null) {
                value = obj.toString();
            } else {
                value = "";
            }

        }
        return value;
    }

    public static String showSql(Configuration configuration, BoundSql boundSql) {
        Object parameterObject = boundSql.getParameterObject();
        List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
        String sql = boundSql.getSql().replaceAll("[\\s]+", " ");
        if (parameterMappings.size() > 0 && parameterObject != null) {
            TypeHandlerRegistry typeHandlerRegistry = configuration.getTypeHandlerRegistry();
            if (typeHandlerRegistry.hasTypeHandler(parameterObject.getClass())) {
                sql = sql.replaceFirst("\\?", getParameterValue(parameterObject));

            } else {
                MetaObject metaObject = configuration.newMetaObject(parameterObject);
                for (ParameterMapping parameterMapping : parameterMappings) {
                    String propertyName = parameterMapping.getProperty();
                    if (metaObject.hasGetter(propertyName)) {
                        Object obj = metaObject.getValue(propertyName);
                        sql = sql.replaceFirst("\\?", getParameterValue(obj));
                    } else if (boundSql.hasAdditionalParameter(propertyName)) {
                        Object obj = boundSql.getAdditionalParameter(propertyName);
                        sql = sql.replaceFirst("\\?", getParameterValue(obj));
                    }
                }
            }
        }
        return sql;
    }

    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    public void setProperties(Properties properties0) {
        this.properties = properties0;
    }
}
