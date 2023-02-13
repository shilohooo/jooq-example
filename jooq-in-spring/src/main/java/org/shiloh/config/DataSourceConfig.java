package org.shiloh.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.TransactionAwareDataSourceProxy;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * 数据源、事务管理配置
 *
 * @author shiloh
 * @date 2023/2/13 17:41
 */
@Configuration
@EnableTransactionManagement
@PropertySource("classpath:jdbc.properties")
public class DataSourceConfig {
    /**
     * JDBC 连接地址
     */
    @Value("${jdbc.url}")
    private String jdbcUrl;

    /**
     * JDBC 连接用户名
     */
    @Value("${jdbc.username}")
    private String jdbcUsername;

    /**
     * JDBC 连接密码
     */
    @Value("${jdbc.password}")
    private String jdbcPassword;

    /**
     * 事务管理器配置
     *
     * @param dataSource 数据源对象
     * @return {@link DataSourceTransactionManager}
     * @author shiloh
     * @date 2023/2/13 17:43
     */
    @Bean
    public PlatformTransactionManager transactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    /**
     * 数据源配置
     *
     * @return {@link DataSource}
     * @author shiloh
     * @date 2023/2/13 17:44
     */
    @Bean
    public DataSource dataSource() {
        final HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl(this.jdbcUrl);
        hikariConfig.setUsername(this.jdbcUsername);
        hikariConfig.setPassword(this.jdbcPassword);
        return new TransactionAwareDataSourceProxy(
                new HikariDataSource(hikariConfig)
        );
    }
}
