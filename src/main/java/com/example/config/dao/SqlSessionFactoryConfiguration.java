package com.example.config.dao;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;
import java.io.IOException;

/**
 * 
 * @author xuhewei
   @date 2018/03/20
 *
 */
@SpringBootConfiguration
public class SqlSessionFactoryConfiguration {

    @Value("${mybatis.config-location}")
    private String myBatisConfigPath;
    @Value("${mapper.xml.config.path}")
    private String mapperXMLConfigPath;
    @Value("${mapper.package.path}")
    private String mapperPackagePath;
    @Autowired
    private DataSource dataSource;

    @Bean
    public SqlSessionFactoryBean createSqlSessionFactory() throws IOException {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        String packageXMLConfigPath = PathMatchingResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX + mapperXMLConfigPath;
        
        // 设置MyBatis 配置文件的路径
        sqlSessionFactoryBean.setConfigLocation(new ClassPathResource(myBatisConfigPath));
        // 设置mapper 对应的XML 文件的路径
        sqlSessionFactoryBean.setMapperLocations(resolver.getResources(packageXMLConfigPath));
        // 设置数据源
        sqlSessionFactoryBean.setDataSource(dataSource);
        // 设置mapper 接口所在的包
        sqlSessionFactoryBean.setTypeAliasesPackage(mapperPackagePath);

        return sqlSessionFactoryBean;
    }
}
