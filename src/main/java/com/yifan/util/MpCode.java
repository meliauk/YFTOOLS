package com.yifan.util;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.util.ArrayList;

public class MpCode {

    public static void main(String[] args) {
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();

        //  1  * 全局配置
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");
        gc.setOutputDir(projectPath + "/src/main/java");
        gc.setAuthor("弋凡");
        gc.setOpen(false);
        gc.setFileOverride(false);// 是否覆盖原来生成的
        gc.setServiceName("%sService");   // 去service的前缀
        gc.setIdType(IdType.ID_WORKER);
        gc.setDateType(DateType.ONLY_DATE);
        gc.setSwagger2(true);
        mpg.setGlobalConfig(gc);
        //  2 * 设置数据源
        DataSourceConfig config = new DataSourceConfig();
        config.setUrl("jdbc:mysql://106.75.104.48:3306/yftools?useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone=GMT%2B8");
        config.setUsername("root");
        config.setPassword("root");
        config.setDriverName("com.mysql.cj.jdbc.Driver");
        config.setDbType(DbType.MYSQL);
        mpg.setDataSource(config);
        // 3 * 包的配置
        PackageConfig packageConfig = new PackageConfig();
//        packageConfig.setModuleName("blog");
        packageConfig.setParent("com.yifan");
        packageConfig.setEntity("entity");
        packageConfig.setMapper("mapper");
        packageConfig.setService("service");
        packageConfig.setController("controller");
        mpg.setPackageInfo(packageConfig);

        //  4 * 配置策略
        StrategyConfig strategy = new StrategyConfig();
        strategy.setInclude("plan");// 设置要映射的表 &&&&&&&&&&&  不传参数是映射所有的表~
        strategy.setNaming(NamingStrategy.underline_to_camel);  // 设置下划线 转 驼峰
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setEntityLombokModel(true);
        strategy.setLogicDeleteFieldName("deleted"); // 逻辑删除字段

        //  自动填充配置
        TableFill gmtCreate = new TableFill("gmt_create", FieldFill.INSERT);
        TableFill gmtModified = new TableFill("gmt_modified", FieldFill.INSERT_UPDATE);
        ArrayList<TableFill> tableFills = new ArrayList<>();
        tableFills.add(gmtCreate);
        tableFills.add(gmtModified);
        strategy.setTableFillList(tableFills);
        strategy.setVersionFieldName("version");// 乐观锁
        strategy.setRestControllerStyle(true);
        strategy.setControllerMappingHyphenStyle(true); // localhost:8080/hello_id__2
        mpg.setStrategy(strategy);
        // 执行
        mpg.execute();
    }



}
