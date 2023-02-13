package org.shiloh.config;

import org.jooq.DSLContext;
import org.jooq.impl.DSL;
import org.jooq.impl.DefaultConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import javax.sql.DataSource;

import static org.jooq.SQLDialect.MYSQL;

/**
 * jOOQ 配置
 *
 * @author shiloh
 * @date 2023/2/13 17:52
 */
@Configuration
@Import(DataSourceConfig.class)
public class JooqConfig {
    /**
     * jOOQ {@link DSLContext} 配置
     *
     * @param configuration jOOQ Configuration
     * @return {@link DSLContext}
     * @author shiloh
     * @date 2023/2/13 17:53
     */
    @Bean
    public DSLContext dslContext(org.jooq.Configuration configuration) {
        return DSL.using(configuration);
    }

    /**
     * jOOQ {@link org.jooq.Configuration} 配置
     *
     * @param dataSource 数据源对象
     * @return {@link org.jooq.Configuration}
     * @author shiloh
     * @date 2023/2/13 17:55
     */
    @Bean
    public org.jooq.Configuration configuration(DataSource dataSource) {
        final DefaultConfiguration configuration = new DefaultConfiguration();
        configuration.setDataSource(dataSource);
        configuration.set(MYSQL);
        return configuration;
    }
}
