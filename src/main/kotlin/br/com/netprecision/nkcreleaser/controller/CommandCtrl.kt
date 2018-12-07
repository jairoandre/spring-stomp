package br.com.netprecision.nkcreleaser.controller

import br.com.netprecision.nkcreleaser.client.TodoClient
import br.com.netprecision.nkcreleaser.model.HelloMessage
import br.com.netprecision.nkcreleaser.model.Todo
import br.com.netprecision.nkcreleaser.runner.CommandRunner
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.handler.annotation.SendTo
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam

@Controller
@CrossOrigin
class CommandCtrl {

    @Autowired
    lateinit var runner: CommandRunner

    @Autowired
    lateinit var todoClient: TodoClient

    @RequestMapping("/run")
    fun getRun(@RequestParam("cmd") cmd: String) : ResponseEntity<String> {
        println("Running command: $cmd")
        runner.run(cmd)
        return ResponseEntity.ok("Success!")
    }

    @MessageMapping("/run")
    fun run(cmd: String) {
        println(cmd)
        runner.run(cmd)
    }

    @RequestMapping("/todos")
    fun findTodos() : ResponseEntity<List<Todo>> {
        return ResponseEntity.ok(todoClient.findAll())
    }

}