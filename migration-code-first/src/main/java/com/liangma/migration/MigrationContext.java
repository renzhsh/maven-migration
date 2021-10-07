package com.liangma.migration;

import com.liangma.migration.annotation.Table;
import com.liangma.migration.mapper.IDescriptorMapper;
import com.liangma.migration.descriptor.ClassDescriptor;
import com.liangma.migration.descriptor.ColumnDescriptor;
import com.liangma.migration.descriptor.TableDescriptor;
import com.liangma.migration.exception.MigrationException;
import com.liangma.migration.generator.ISQLGenerator;
import com.liangma.migration.jdbc.IJdbcExecutor;
import com.liangma.migration.loaders.IAnnotatedClassLoader;
import com.liangma.migration.logs.ILogger;
import com.liangma.migration.policy.IMigration;
import com.liangma.migration.policy.IMigrationFactory;
import com.liangma.migration.policy.MigrationPolicy;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

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

    @Autowired
    private IJdbcExecutor jdbcExecutor;

    @Autowired
    private IDescriptorMapper converter;

    @Autowired
    private ISQLGenerator generator;

//    @Autowired
//    private MigrationPolicy migrationPolicy;
//
//    @Autowired
//    private IMigrationFactory migrationFactory;

    @Autowired
    private IMigration migration;

    /**
     * @param annotatedClassLoader
     * @param log
     */
    public MigrationContext(IAnnotatedClassLoader annotatedClassLoader, ILogger log) {
        this.annotatedClassLoader = annotatedClassLoader;
        this.log = log;

        System.out.println("Migration ---[风险提示: 本工具仅可用于本地开发环境。如果用于生产环境，对数据库造成的任何损失，本工具概不负责！]");
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
            list.add(converter.mapTable(clazz));
        }

        return list;
    }

    /**
     * step2: 从数据库从获取现有的Table
     */
    public List<TableDescriptor> getDbTables() {
        List<TableDescriptor> list = jdbcExecutor.getTables();

        for (TableDescriptor table : list) {
            ColumnDescriptor[] columns = table.getColumns();

            table.setColumns(columns);
        }

        return list;
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
        jdbcExecutor.execute(sql);
    }


    public void execute() {
        try {
            migration.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
