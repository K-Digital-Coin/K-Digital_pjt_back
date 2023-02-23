package com.predictbit;

import jakarta.websocket.*;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URI;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WebSocketServerTest {

    @Test
    public void testWebSocketServer() throws Exception {
        WebSocketContainer container = ContainerProvider.getWebSocketContainer();
        CountDownLatch latch = new CountDownLatch(1);
        TestEndpoint endpoint = new TestEndpoint(latch);

        Session session = container.connectToServer(endpoint, new URI("ws://localhost:8080/echo"));
        session.getBasicRemote().sendText("Hello, World!");
        latch.await(10, TimeUnit.SECONDS);

        assertEquals("Echo: Hello, World!", endpoint.getMessage());
    }

    private static class TestEndpoint extends Endpoint {
        private CountDownLatch latch;
        private String message;

        public TestEndpoint(CountDownLatch latch) {
            this.latch = latch;
        }

        public String getMessage() {
            return message;
        }

        @Override
        public void onOpen(Session session, EndpointConfig config) {
            try {
                session.getBasicRemote().sendText("Hello, Server!");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void onMessage(Session session, String message) {
            this.message = message;
            latch.countDown();
        }
    }
}
