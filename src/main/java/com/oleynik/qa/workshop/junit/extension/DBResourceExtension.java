package com.oleynik.qa.workshop.junit.extension;

import com.oleynik.qa.workshop.junit.model.MyServer;
import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.BeforeTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

public class DBResourceExtension implements BeforeTestExecutionCallback, AfterTestExecutionCallback {
    MyServer myServer = new MyServer();

    @Override
    public void beforeTestExecution(ExtensionContext context){
        myServer.connect();
    }

    @Override
    public void afterTestExecution(ExtensionContext context) {
        myServer.disconnect();
    }
}
