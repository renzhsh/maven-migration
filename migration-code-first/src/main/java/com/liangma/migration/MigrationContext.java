package com.liangma.migration;

import com.liangma.migration.annotations.Table;
import com.liangma.migration.loaders.IAnnotatedClassLoader;
import com.liangma.migration.logs.ILogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.lang.annotation.Annotation;
import java.sql.SQLException;
import java.util.List;


/**
 *
 */
@Component
public class MigrationContext {

    private final IAnnotatedClassLoader annotatedClassLoader;

    private final ILogger log;

    //数据源组件
    @Autowired
    private DataSource dataSource;

    //用于访问数据库的组件
    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * @param annotatedClassLoader
     * @param log
     */
    public MigrationContext(IAnnotatedClassLoader annotatedClassLoader, ILogger log) {
        this.annotatedClassLoader = annotatedClassLoader;
        this.log = log;
    }

    public void execute() {

        try {
            System.out.println("默认数据源为：" + dataSource.getClass());
            System.out.println("数据库连接实例：" + dataSource.getConnection());
            //访问数据库
            Integer i = jdbcTemplate.queryForObject("SELECT count(*) from `clients`", Integer.class);
            System.out.println("user 表中共有" + i + "条数据。");
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }
}
