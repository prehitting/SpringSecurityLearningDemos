package com.prehitting.db.generator;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.TemplateType;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.prehitting.db.utils.YamlUtil;

import java.nio.file.Paths;
import java.util.Collections;


/**
 * @ClassName MybatisPlusGenerator
 * @Description TODO
 * @Author 24809
 * @Date 2022/12/7 9:05
 * @Version 1.0
 */
public class MybatisPlusGenerator {

    private static final String databaseUrl;

    private static final String username;

    private static final String password;

    private static final String PROJECT_HOME;

    private static final String MODULE_NAME;

    private static final String JAVA_DIR;

    private static final String PARENT_PACKAGE;

    private static final String MODULE_PACKAGE;

    private static final String MAPPER_XML_DIR;


    static {
        databaseUrl = (String) YamlUtil.getValFromYaml("spring.datasource.url", "application.yml");
        username = (String) YamlUtil.getValFromYaml("spring.datasource.username", "application.yml");
        password = (String) YamlUtil.getValFromYaml("spring.datasource.password", "application.yml");
        MODULE_NAME = "db-module";
        PROJECT_HOME = Paths.get("./"+MODULE_NAME).toAbsolutePath().normalize() +"/";
        JAVA_DIR = "src/main/java/";
        PARENT_PACKAGE = "com.prehitting";
        MODULE_PACKAGE = "db";
        MAPPER_XML_DIR = PROJECT_HOME + "src/main/resources/mapper/";
    }

    public static void main(String[] args) {
        FastAutoGenerator.create(databaseUrl,username,password)
                .globalConfig(builder -> {
                    builder.author("YiMing")
                            .enableSwagger()
                            .dateType(DateType.TIME_PACK)
                            .disableOpenDir()
                            .outputDir(PROJECT_HOME+JAVA_DIR);
                })
                .packageConfig(builder -> {
                    builder.parent(PARENT_PACKAGE)
                            .moduleName(MODULE_PACKAGE)
                            .pathInfo(Collections.singletonMap(OutputFile.xml,MAPPER_XML_DIR))
                            .entity("model")
                            .service("service")
                            .serviceImpl("service.impl")
                            .controller("controller");
                })
                .strategyConfig(builder -> {
                    builder
                            .entityBuilder()
                            .enableFileOverride()
                            .enableLombok()
                            .enableChainModel()
                            .enableTableFieldAnnotation();
                    builder
                            .serviceBuilder()
                            .enableFileOverride()
                            .formatServiceFileName("%sService")
                            .formatServiceImplFileName("%sServiceImpl");
                    builder
                            .mapperBuilder()
                            .enableFileOverride()
                            .enableBaseColumnList()
                            .enableBaseResultMap();
                    builder
                            .controllerBuilder()
                            .enableFileOverride()
                            .enableRestStyle();
                })
                .templateConfig(builder -> {
                    builder
                            .disable(TemplateType.CONTROLLER)
                            .disable(TemplateType.SERVICE_IMPL);
                })
                .templateEngine(new FreemarkerTemplateEngine())
                .execute();
    }
}
