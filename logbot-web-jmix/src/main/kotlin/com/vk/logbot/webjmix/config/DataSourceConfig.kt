package com.vk.logbot.webjmix.config

import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import javax.sql.DataSource

@Configuration
class DataSourceConfig {

    @Bean
    @Primary
    @ConfigurationProperties("main.datasource")
    fun dataSourceProperties(): DataSourceProperties {
        return DataSourceProperties()
    }

    @Bean
    @Primary
    @ConfigurationProperties("main.datasource.hikari")
    fun dataSource(dataSourceProperties: DataSourceProperties): DataSource {
        return dataSourceProperties.initializeDataSourceBuilder().build()
    }
}