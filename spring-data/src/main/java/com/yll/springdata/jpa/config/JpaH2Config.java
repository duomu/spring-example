package com.yll.springdata.jpa.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

/**
 * 内置数据库H2
 * @author：linlin.yang
 * @date：2017/10/16 15:23
 */
@Configuration
//@ComponentScan("com.yll.springdata")
@EnableJpaRepositories(basePackages = "com.yll.springdata.jpa.repository")
public class JpaH2Config {
    @Bean(name = "dataSource")
    public DataSource dataSource() {
        return new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2).build();
    }

    @Bean(name = "jpaVendorAdapter")
    public JpaVendorAdapter jpaVendorAdapter() {
        HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
        jpaVendorAdapter.setDatabase(Database.H2);
        jpaVendorAdapter.setShowSql(true);
        jpaVendorAdapter.setGenerateDdl(true);
        jpaVendorAdapter.setDatabasePlatform("org.hibernate.dialect.H2Dialect");
        return jpaVendorAdapter;
    }

    @Bean(name = "entityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(@Qualifier("dataSource") DataSource dataSource,
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
