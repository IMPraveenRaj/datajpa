package com.springheaven.config;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class DBconfig {


    //let setup a connection in spring for that we need to use spring ORM dependecies
    //In production we should never use the DriverManager Datasource

    @Bean
    DriverManagerDataSource dataSource() {
        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
        driverManagerDataSource.setUrl("jdbc:mysql://localhost:3306/hibernate_demo");
        driverManagerDataSource.setUsername("root");
        driverManagerDataSource.setPassword("root");
        return driverManagerDataSource;
    }


    //we also need to create an entity manager bean for that we need to create LocalContainerEntityManagerFactoryBean

    @Bean
    LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(){
        LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        localContainerEntityManagerFactoryBean.setDataSource(dataSource());
        localContainerEntityManagerFactoryBean.setPackagesToScan("com.springheaven.entity");
        //though we have provided an entity manage but we need to provide an vendor who is the vendor implements this
        //otherwise you will get an exceptions
//        Error creating bean with name 'entityManagerFactoryBean' defined in class path resource [com/springheaven/config/DBconfig.class]: No PersistenceProvider specified in EntityManagerFactory configuration, and chosen PersistenceUnitInfo does not specify a provider class name either
        localContainerEntityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        localContainerEntityManagerFactoryBean.setPersistenceUnitName("mysqldb");
        return localContainerEntityManagerFactoryBean;
    }



    @Bean
    PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory){

        //whenever you are trying to connect to one database use JPATransaction Manager when
        //connecting to multiple txn manage use JTATransaction Manager
        JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
        jpaTransactionManager.setEntityManagerFactory(entityManagerFactory);
        return jpaTransactionManager;
    }

}
