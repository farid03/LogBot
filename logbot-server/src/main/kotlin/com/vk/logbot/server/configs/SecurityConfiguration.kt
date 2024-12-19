package com.vk.logbot.server.configs

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.SecurityFilterChain
import org.springframework.web.cors.CorsConfiguration

@Configuration
@EnableWebSecurity
class SecurityConfiguration {

    @Bean
    fun springSecurity(
        http: HttpSecurity,
    ): SecurityFilterChain {

        http
            .csrf { csrf -> csrf.disable() }
            .formLogin { form -> form.disable() }
            .cors { cors ->
                cors.configurationSource { request ->
                    val configuration = CorsConfiguration()
                    configuration.allowedOrigins = mutableListOf("*")
                    configuration.allowedMethods = mutableListOf("*")
                    configuration.allowedHeaders = mutableListOf("*")
                    configuration
                }
            }
            .authorizeHttpRequests{ r -> r.anyRequest().permitAll() }
            .sessionManagement { manager -> manager.sessionCreationPolicy(SessionCreationPolicy.STATELESS) }
        return http.build()
    }
}