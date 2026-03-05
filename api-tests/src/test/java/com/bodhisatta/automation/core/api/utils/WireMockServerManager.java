package com.bodhisatta.automation.core.api.utils;

import com.github.tomakehurst.wiremock.WireMockServer;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;

public class WireMockServerManager {

    private static WireMockServer server;

    public static void startServer()
    {
        server=new WireMockServer(
                options().port(8089).usingFilesUnderClasspath("wiremock")
        );

        server.start();

        System.out.println("WireMock started at: http://localhost:8089");
    }

    public static void stopServer()
    {
        if (server!=null)
        {
            server.stop();
        }
    }
}
