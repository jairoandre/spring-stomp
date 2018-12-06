var stompClient = null;

var initialBufferSize = 15;

var app = new Vue({
    el: '#app',
    data: {
        bufferSize: initialBufferSize,
        output: Array(initialBufferSize).fill('')
    }
});

function connect() {
    var socket = new SockJS('/nkc-releaser');
    appendData('Establishing connection...');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        appendData('Connected!')
        stompClient.subscribe('/topic/output', function (data) {
            appendData(data.body);
        });
    });
}

connect();

function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    console.log("Disconnected");
}

function appendData(message) {
    if (app.output.length > app.bufferSize) {
        app.output.shift();
        app.output.push(message);
    } else {
        app.output.push(message);
    }

}

