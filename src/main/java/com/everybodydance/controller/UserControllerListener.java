package com.everybodydance.controller;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@WebListener
public class UserControllerListener implements HttpSessionListener {
    private static final Map<String, HttpSession> map = new ConcurrentHashMap<>();
    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
           HttpSession hs = httpSessionEvent.getSession();
           map.put(hs.getId(),hs);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {

    }
    public static HttpSession getSessionById(String sessionid){
        return map.get(sessionid);
    }
}
