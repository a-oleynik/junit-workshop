package com.oleynik.qa.workshop.junit.rules;

import com.oleynik.qa.workshop.junit.model.MyServer;
import org.junit.rules.ExternalResource;

public class DBResourceRule extends ExternalResource {
    MyServer myServer = new MyServer();

    @Override
    protected void before() {
        myServer.connect();
    }

    @Override
    protected void after() {
        myServer.disconnect();
    }
}
