package br.com.netprecision.nkcreleaser

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class NkcReleaserApplication

fun main(args: Array<String>) {
    runApplication<NkcReleaserApplication>(*args)
}
