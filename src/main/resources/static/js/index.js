window.addEventListener("load", () => {
    connect();
})

function connect() {
  const socket = new SockJS('/userMessages');
  stompClient = Stomp.over(socket);
  stompClient.connect({}, function (frame) {
      stompClient.subscribe('/topic/userMessages', function (notification) {
          $('#textArea').val(notification);
       });
  });
}