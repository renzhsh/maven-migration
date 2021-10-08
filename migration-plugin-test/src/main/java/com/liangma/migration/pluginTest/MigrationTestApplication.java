package com.liangma.migration.pluginTest;

import com.liangma.migration.PluginContext;

import java.io.IOException;
import java.net.URL;

public class MigrationTestApplication {
    public static void main(String[] args) throws IOException {
        PluginContext context = new PluginContext();

        context.execute();
    }
}
