package br.com.netprecision.nkcreleaser.config

import br.com.netprecision.nkcreleaser.client.TodoClient
import feign.Feign
import feign.Logger
import feign.gson.GsonDecoder
import feign.gson.GsonEncoder
import feign.okhttp.OkHttpClient
import feign.slf4j.Slf4jLogger
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class FeignConfiguration {

    @Bean
    fun todoClient() : TodoClient = Feign.builder()
            .client(OkHttpClient())
            .encoder(GsonEncoder())
            .decoder(GsonDecoder())
            .logger(Slf4jLogger(TodoClient::class.java))
            .logLevel(Logger.Level.FULL)
            .target(TodoClient::class.java, "https://jsonplaceholder.typicode.com/todos")
}