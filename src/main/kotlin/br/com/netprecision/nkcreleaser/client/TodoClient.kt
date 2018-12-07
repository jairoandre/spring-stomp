package br.com.netprecision.nkcreleaser.client

import br.com.netprecision.nkcreleaser.model.Todo
import feign.Param
import feign.RequestLine

interface TodoClient {

    @RequestLine("GET /")
    fun findAll() : List<Todo>

    @RequestLine("GET /{id}")
    fun findById(@Param("id") id: String) : Todo
}