package com.liangma.migration;

import com.liangma.migration.annotation.Table;
import com.liangma.migration.convert.IDescriptorConverter;
import com.liangma.migration.descriptor.ClassDescriptor;
import com.liangma.migration.descriptor.FieldDescriptor;
import com.liangma.migration.descriptor.TableDescriptor;
import com.liangma.migration.exception.InvalidCharacterException;
import com.liangma.migration.exception.InvalidOperateException;
import com.liangma.migration.exception.MigrationException;
import com.liangma.migration.exception.NotFoundExpressionException;
import com.liangma.migration.generator.ISQLGenerator;
import com.liangma.migration.loaders.IAnnotatedClassLoader;
import com.liangma.migration.logs.ILogger;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


/**
 *
 */
@Component
public class MigrationContext {

    private final IAnnotatedClassLoader annotatedClassLoader;

    private final ILogger log;

//    //数据源组件
//    @Autowired
//    private DataSource dataSource;

    //用于访问数据库的组件
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public IDescriptorConverter converter;

    @Autowired
    private ISQLGenerator generator;

    /**
     * @param annotatedClassLoader
     * @param log
     */
    public MigrationContext(IAnnotatedClassLoader annotatedClassLoader, ILogger log) {
        this.annotatedClassLoader = annotatedClassLoader;
        this.log = log;
    }

    /**
     * step1 : 获取声明的Table
     */
    public List<TableDescriptor> getDeclaredTables() throws MigrationException {

        List<ClassDescriptor> clazzes = annotatedClassLoader.getTypesAnnotatedWith(Table.class)
                .stream().map(ClassDescriptor::new)
                .collect(Collectors.toList());

        List<TableDescriptor> list = new ArrayList<>();

        for (ClassDescriptor clazz : clazzes) {
            list.add(converter.TableConvert(clazz));
        }

        return list;
    }

    /**
     * step2: 从数据库从获取现有的Table
     */
    public void getDbTables() {
//        try {
//            System.out.println("默认数据源为：" + dataSource.getClass());
//            System.out.println("数据库连接实例：" + dataSource.getConnection());
//            //访问数据库
//            Integer i = jdbcTemplate.queryForObject("SELECT count(*) from `clients`", Integer.class);
//            System.out.println("user 表中共有" + i + "条数据。");
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
    }

    /**
     * step3: 对比二者差异，获取最新变更
     */
    public void diff() {

    }

    /**
     * step4： 根据变更，生成sql
     */
    public List<String> generateSql(@NotNull List<TableDescriptor> list) {
        List<String> result = new ArrayList<>();

        for (TableDescriptor table : list) {
            String sql = generator.generateTable(table);
            result.add(sql);
        }

        return result;
    }

    /**
     * step5: 根据变更执行到数据库
     */
    @Transactional
    public void update2db(String sql) {
        jdbcTemplate.execute(sql);
    }


    public void execute() {
        try {
            List<TableDescriptor> list = getDeclaredTables();

            getDbTables();

            diff();

            List<String> sqls = generateSql(list);

            for (String sql : sqls) {
                System.out.println(sql);
//                update2db(sql);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
