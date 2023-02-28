package com.backend.web.historyCoin.websocket;

import com.backend.web.historyCoin.dto.HistoryCoinDTO;
import com.backend.web.historyCoin.repository.HistoryCoinRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.websocket.OnClose;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Slf4j
@Service
@ServerEndpoint(value = "coin")
@RequiredArgsConstructor
public class HistoryCoinWebSocketService {

    private final HistoryCoinRepository historyCoinRepository;

    private static Set<Session> clients = Collections.synchronizedSet(new HashSet<Session>());

    @Transactional
    public HistoryCoinDTO.Basic findByIdx(long idx) {
        return historyCoinRepository.findById(idx).orElseThrow(() -> new IllegalArgumentException("Invalid idx")).toHistoryCoinBasicDTO();
    }

    @OnOpen
    public void onOpen(Session session) {
        log.info("Session open : " + session.toString());
        if (!clients.contains(session)) {
            clients.add(session);
            log.info("Session open : " + session);
        } else {
            log.info("Already connected session");
        }
    }

    @OnClose
    public void onClose(Session session) {
        log.info("Session close : " + session);
        clients.remove(session);
    }
}
