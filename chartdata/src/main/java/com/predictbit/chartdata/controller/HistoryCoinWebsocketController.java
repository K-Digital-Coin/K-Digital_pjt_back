package com.predictbit.chartdata.controller;

import com.predictbit.chartdata.handler.HistoryCoinWebSocketHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.util.HashMap;
import java.util.Map;

@RestController
public class HistoryCoinWebsocketController {
    @Autowired
    private HistoryCoinWebSocketHandler webSocketHandler;

    @GetMapping("/")
    public String home(Model model) {
        return "home";
    }

    @GetMapping("/connect")
    public Map<String, String> connect() {
        Map<String, String> result = new HashMap<>();
        result.put("url", "/websocket");
        return result;
    }

//    @RequestMapping(value = "/send", method = RequestMethod.GET)
//    @ResponseBody
//    public String send(@RequestParam("message") String message) {
//        for (WebSocketSession session : webSocketHandler.getSessions()) {
//            try {
//                session.sendMessage(new TextMessage(message));
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//        return "OK";
//    }
}
