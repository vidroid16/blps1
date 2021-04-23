package com.example.demo.DataBase;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.sql.DataSource;

@Profile("default")
@Configuration
public class LocalDB{
    @Bean
    public DataSource getDataSource() {
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName("org.postgresql.Driver");
        dataSourceBuilder.url("jdbc:postgresql://pg/studs");
        dataSourceBuilder.username("s264444");
        dataSourceBuilder.password("hqz388");
        return dataSourceBuilder.build();
    }
}