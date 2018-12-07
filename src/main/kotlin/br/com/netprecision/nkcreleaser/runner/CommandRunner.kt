package br.com.netprecision.nkcreleaser.runner

import br.com.netprecision.nkcreleaser.utils.StreamGobbler
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.messaging.core.MessageSendingOperations
import org.springframework.stereotype.Service
import java.util.concurrent.Executors

@Service
class CommandRunner {

    @Autowired
    lateinit var messageSendingOperations: MessageSendingOperations<String>

    fun run(command: String) {
        //val builder = ProcessBuilder()
        //builder.command("$command")
        //builder.directory(File(System.getProperty("user.home")))
        //val process = builder.start()
        //builder.command("sh", "-c", "\"$command\"")
        sendToBroker("Running $command...")
        val process = Runtime.getRuntime().exec(command)
        val gobbler = StreamGobbler(process.inputStream, ::socketConsumer)
        Executors.newSingleThreadExecutor().submit(gobbler)
        val exitCode = process.waitFor()
        println("Thread executed: $exitCode")
    }

    private fun socketConsumer(str: String) {
        sendToBroker(str)
    }

    private fun sendToBroker(str: String) : String {
        messageSendingOperations.convertAndSend("/topic/output", str)
        return str
    }

}