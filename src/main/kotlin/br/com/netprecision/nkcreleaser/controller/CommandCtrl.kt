package br.com.netprecision.nkcreleaser.controller

import br.com.netprecision.nkcreleaser.model.HelloMessage
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

    @RequestMapping("/run")
    fun getRun(@RequestParam("cmd") cmd: String) : ResponseEntity<String> {
        println("Running command: $cmd")
        runner.run(cmd)
        return ResponseEntity.ok("Success!")
    }

    @MessageMapping("/hello")
    @SendTo("/topic/output")
    fun hello(message: HelloMessage) : String {
        return "Hello there ${message.name}"

    }

}