package com.liangma.migration.convert;

import com.liangma.migration.annotation.*;
import com.liangma.migration.config.NamingConvention;
import com.liangma.migration.descriptor.ClassDescriptor;
import com.liangma.migration.descriptor.ColumnDescriptor;
import com.liangma.migration.descriptor.FieldDescriptor;
import com.liangma.migration.descriptor.TableDescriptor;
import com.liangma.migration.exception.*;
import com.liangma.migration.util.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class DescriptorConverter implements IDescriptorConverter {

    /**
     * Table 命名规则
     */
    @Autowired
    @Qualifier("tableNaming")
    protected NamingConvention tableNaming;

    /**
     * Column 命名规则
     */
    @Autowired
    @Qualifier("columnNaming")
    protected NamingConvention columnNaming;

    @Autowired
    @Qualifier("mapper")
    protected Map<String, MapperExpression> mapper;


    /**
     * @param clazz
     * @return
     */
    @Override
    public TableDescriptor TableConvert(ClassDescriptor clazz) throws MigrationException {
        TableDescriptor result = new TableDescriptor();

        Table tableAnno = clazz.getAnnotation(Table.class);
        if (!StringUtils.isEmpty(tableAnno.value())) {
            result.setName(tableAnno.value());
        } else {
            result.setName(tableNaming.caseTransform(clazz.getName()));
        }

        if (!StringUtils.isEmpty(clazz.getComment())) {
            result.setComment(clazz.getComment());
        }

        List<ColumnDescriptor> list = new ArrayList<>();

        for (FieldDescriptor field : clazz.getFields()) {
            list.add(ColumnConvert(field));
        }

        if (list.stream().noneMatch(ColumnDescriptor::isPrimaryKey)) {
            throw new NotFoundKeyException("Class:" + clazz);
        }

        result.setColumns(list.toArray(new ColumnDescriptor[]{}));


        return result;
    }

    /**
     * @param field
     * @return
     */
    @Override
    public ColumnDescriptor ColumnConvert(FieldDescriptor field) throws MigrationException {
        ColumnDescriptor result = new ColumnDescriptor();

        //name
        result.setName(columnNaming.caseTransform(field.getName()));

        // comment
        if (!StringUtils.isEmpty(field.getComment())) {
            result.setComment(field.getComment());
        }

        mapDbType(field.getType(), result);

        // 主键
        Key keyAnno = field.getAnnotation(Key.class);
        if (keyAnno != null) {
            result.setPrimaryKey(true);
            result.setAllowNull(false);
            result.setAutoIncrement(keyAnno.autoIncrement());
        } else {
            if (field.getName().toLowerCase() == "id") { // 主键默认名称
                result.setPrimaryKey(true);
                result.setAllowNull(false);
            }
        }

        Required requiredAnno = field.getAnnotation(Required.class);
        if (requiredAnno != null) {
            result.setAllowNull(false);
        }

        MaxLength maxLengthAnno = field.getAnnotation(MaxLength.class);
        if (maxLengthAnno != null) {
            result.setMaxLength(maxLengthAnno.value());
            result.setPrecise(maxLengthAnno.precise());
        }

        // 自定义
        Column colAnno = field.getAnnotation(Column.class);

        // name
        if (colAnno != null) {
            if (!StringUtils.isEmpty(colAnno.value())) {
                result.setName(colAnno.value());
            }

            // bigint(10,2)
            if (!StringUtils.isEmpty(colAnno.dbType())) {
                String dbType = colAnno.dbType().replace(" ", "");

                String pattern = "[^0-9a-zA-Z(),]";
                // 创建 Pattern 对象
                Pattern r = Pattern.compile(pattern);

                // 现在创建 matcher 对象
                Matcher m = r.matcher(dbType);
                final String InvalidCharacterMessage = "Field:" + field.toString() + " Column:" + colAnno.toString();
                if (m.find()) {
                    // 禁止出现 0-9a-zA-Z(), 以外的字符
                    throw new InvalidCharacterException(InvalidCharacterMessage);
                }

                String[] items = dbType.replace("(", ".").replace(")", ",").split(",");

                if (items.length > 0) {
                    // 只能是字符
                    if (!Pattern.matches("^[a-zA-Z]+$", items[0])) {
                        throw new InvalidCharacterException(InvalidCharacterMessage);
                    }
                    result.setDbType(items[0]);
                }
                if (items.length > 1) {
                    // 只能是数字
                    if (!Pattern.matches("^[0-9]+$", items[1])) {
                        throw new InvalidCharacterException(InvalidCharacterMessage);
                    }
                    result.setMaxLength(Integer.parseInt(items[1]));
                }
                if (items.length > 2) {
                    // 只能是数字
                    if (!Pattern.matches("^[0-9]+$", items[2])) {
                        throw new InvalidCharacterException(InvalidCharacterMessage);
                    }
                    result.setPrecise(Integer.parseInt(items[2]));
                }
            }
        }

        return result;
    }

    /**
     * 根据Java类型自动映射到DbType,并附加默认值
     *
     * @param fieldType
     * @param column
     */
    protected void mapDbType(@NotNull Class<?> fieldType, @NotNull ColumnDescriptor column) throws NotFoundExpressionException, InvalidOperateException {
//        int（4字节）	Integer
//        byte（1字节）	Byte
//        short（2字节）	Short
//        long（8字节）	Long
//        float（4字节）	Float
//        double（8字节）	Double
//        char（2字节）	Character
//        boolean（未定）	Boolean
//        enum
        switch (fieldType.getName()) {
            case "int":
            case "java.lang.Integer":
                setTypeMapping("int", column);
                break;
            case "byte":
            case "java.lang.Byte":
                setTypeMapping("byte", column);
                break;
            case "short":
            case "java.lang.Short":
                setTypeMapping("short", column);
                break;
            case "long":
            case "java.lang.Long":
                setTypeMapping("long", column);
                break;
            case "float":
            case "java.lang.Float":
                setTypeMapping("float", column);
                break;
            case "double":
            case "java.lang.Double":
                setTypeMapping("double", column);
                break;
            case "char":
            case "java.lang.Character":
                setTypeMapping("char", column);
                break;
            case "boolean":
            case "java.lang.Boolean":
                setTypeMapping("boolean", column);
                break;
            case "java.util.Date":
                setTypeMapping("date", column);
                break;
            case "java.lang.String":
                setTypeMapping("string", column);
                break;
            default:
                if (fieldType.isEnum()) {
                    setTypeMapping("enum", column);
                } else {
                    throw new InvalidOperateException("不支持的类型：" + fieldType);
                }
                break;

        }
    }

    private void setTypeMapping(@NotNull String type, @NotNull ColumnDescriptor column) throws NotFoundExpressionException {
        if (!mapper.containsKey(type)) {
            throw new NotFoundExpressionException("不存在" + type + "类型的映射");
        }
        MapperExpression expression = mapper.get(type);

        column.setMapperExpression(expression);
    }

    @Override
    public String toString() {
        return "DescriptorConverter{" +
                "tableNaming=" + tableNaming +
                ", columnNaming=" + columnNaming +
                '}';
    }
}
