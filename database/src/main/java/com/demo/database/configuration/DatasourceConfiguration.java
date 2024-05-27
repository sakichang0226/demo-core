package com.demo.database.configuration;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@MapperScan(
        basePackages = "com.demo.database.mapper"
)
public class DatasourceConfiguration {

    @Bean
    public DataSourceTransactionManager databaseTransactionManager(DataSource datasource) {
        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager();

        transactionManager.setDataSource(datasource);
        transactionManager.setRollbackOnCommitFailure(true);

        return transactionManager;
    }

    @Bean
    public SqlSessionFactory databaseSqlSessionfactory(DataSource dataSource) throws  Exception {
        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);

        return sessionFactory.getObject();
    }

    @Bean
    @ConfigurationProperties("spring.datasource")
    public DataSourceProperties databaseDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    public DataSource databaseDataSource() {
        return databaseDataSourceProperties().initializeDataSourceBuilder().build();
    }

}
