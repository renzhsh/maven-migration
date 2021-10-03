package com.liangma.migration.mysql.generator;

import com.liangma.migration.descriptor.ColumnDescriptor;
import com.liangma.migration.descriptor.TableDescriptor;
import com.liangma.migration.generator.ISQLGenerator;
import com.liangma.migration.mysql.descriptor.MysqlTableDescriptor;
import com.liangma.migration.util.StringUtils;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class MysqlSQLGenerator implements ISQLGenerator {

    @Override
    public String generateTable(TableDescriptor descriptor) {
        MysqlTableDescriptor mysqlTable = (MysqlTableDescriptor) descriptor;


        StringBuilder result = new StringBuilder();

        result.append("CREATE TABLE `" + mysqlTable.getName() + "` (\r\n");

        ColumnDescriptor[] columns = mysqlTable.getColumns();
        for (ColumnDescriptor col : columns) {
            result.append(" ").append(generateColumn(col)).append(",\r\n");
        }

        ColumnDescriptor key = Arrays.stream(columns).filter(ColumnDescriptor::isPrimaryKey).findFirst().get();

        result.append(" " + generatePrimaryKey(key) + "\r\n");

        result.append(") ENGINE=" + mysqlTable.getEngine().name());

        if (!StringUtils.isEmpty(mysqlTable.getComment())) {
            result.append(" COMMENT='" + mysqlTable.getComment() + "'");
        }

        result.append(";");
        return result.toString();
    }

    public String generateColumn(ColumnDescriptor descriptor) {
        StringBuilder result = new StringBuilder();

        // `Enabled` tinyint(1) NOT NULL,
        result.append("`" + descriptor.getName() + "`");

        // dbType
        result.append(" " + descriptor.getDbType());
        if (descriptor.getMaxLength() > 0) {
            result.append("(" + descriptor.getMaxLength());

            if (descriptor.getPrecise() > 0) {
                result.append("," + descriptor.getPrecise());
            }
            result.append(")");
        }

        // not null
        if (descriptor.isPrimaryKey() || !descriptor.isAllowNull()) {
            result.append(" NOT NULL");
        } else {
            result.append(" DEFAULT NULL");
        }

        // 主键递增
        if (descriptor.isPrimaryKey() && descriptor.isAutoIncrement()) {
            result.append(" AUTO_INCREMENT");
        }

        // 注释
        if (!StringUtils.isEmpty(descriptor.getComment())) {
            result.append(" COMMENT '" + descriptor.getComment() + "'");
        }


        return result.toString();
    }

    protected String generatePrimaryKey(ColumnDescriptor descriptor) {
        return "PRIMARY KEY (`" + descriptor.getName() + "`)";
    }
}
