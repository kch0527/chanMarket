package com.example.market.config;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.HttpServletRequest;

public class RequestListener implements ServletRequestListener {
    @Override
    public void requestDestroyed(ServletRequestEvent sre)
    {
    }

    @Override
    public void requestInitialized(ServletRequestEvent sre)
    {
        ((HttpServletRequest) sre.getServletRequest()).getSession();
    }
}
