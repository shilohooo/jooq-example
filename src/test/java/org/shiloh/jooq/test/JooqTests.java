package org.shiloh.jooq.test;

import org.jooq.DSLContext;
import org.jooq.impl.DSL;
import org.junit.Before;

import java.io.InputStream;
import java.sql.DriverManager;
import java.util.Properties;

import static org.jooq.SQLDialect.MYSQL;

/**
 * @author shiloh
 * @date 2023/2/11 15:51
 */
public abstract class JooqTests {
    protected DSLContext dslContext;

    @Before
    public void setup() {
        try (
                final InputStream inputStream = JooqSimpleTests.class
                        .getClassLoader()
                        .getResourceAsStream("jdbc.properties")
        ) {
            final Properties properties = new Properties();
            properties.load(inputStream);
            final String jdbcUrl = properties.getProperty("jdbc.url");
            final String jdbcUsername = properties.getProperty("jdbc.username");
            final String jdbcPassword = properties.getProperty("jdbc.password");
            dslContext = DSL.using(DriverManager.getConnection(jdbcUrl, jdbcUsername, jdbcPassword), MYSQL);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
