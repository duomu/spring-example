package com.yll.springdata.jpa.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.beans.PropertyVetoException;

/**
 * Mysql数据库
 * @author：linlin.yang
 * @date：2017/10/16 10:44
 */
@Configuration
@PropertySource(JpaMysqlConfig.DB_LOCATION)//导入属性文件
@EnableJpaRepositories(basePackages = "com.yll.springdata.jpa.repository")
public class JpaMysqlConfig {
    static final String DB_LOCATION = "classpath:db.properties";

    @Value("${db.url}")
    private String url;

    @Value("${db.username}")
    private String user;

    @Value("${db.password}")
    private String password;

    @Value("${db.driver}")
    private String driverClass;

    @Bean(name = "dataSource")
    public DataSource dataSource() throws PropertyVetoException {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setDriverClass(driverClass);
        dataSource.setJdbcUrl(url);
        dataSource.setUser(user);
        dataSource.setPassword(password);
        return dataSource;
    }

    @Bean(name = "jpaVendorAdapter")
    public JpaVendorAdapter jpaVendorAdapter() {
        HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        adapter.setDatabase(Database.MYSQL);
        adapter.setShowSql(true);
        adapter.setGenerateDdl(true);
        adapter.setDatabasePlatform("org.hibernate.dialect.MySQLDialect");
        return adapter;
    }

    @Bean(name = "entityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(@Qualifier("dataSource") DataSource dataSource,
                                                                           @Qualifier("jpaVendorAdapter") JpaVendorAdapter jpaVendorAdapter) {
        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setDataSource(dataSource);
        factory.setJpaVendorAdapter(jpaVendorAdapter);
        factory.setPackagesToScan("com.yll.springdata.jpa.entity");
        return factory;
    }

    @Bean
    public JpaTransactionManager transactionManager(@Qualifier("entityManagerFactory") EntityManagerFactory emf) {
        return new JpaTransactionManager(emf);
    }
}
