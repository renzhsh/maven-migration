package com.liangma.migration;

import com.liangma.migration.config.MigrationConfig;
import com.liangma.migration.exception.MigrationException;
import com.liangma.migration.logs.ILogger;
import org.yaml.snakeyaml.Yaml;

import java.net.URL;

public class PluginContext {

    private final ILogger logger;

    public PluginContext() {
        this(new ConsoleLogger());
    }

    public PluginContext(ILogger logger) {
        this.logger = logger;
    }

    public void execute() {


        try {
            PluginConfig config = getConfig();
            System.out.println(config);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e);
        }
    }

    protected PluginConfig getConfig() throws NotFoundYmlException {
        ClassLoader loader = this.getClass().getClassLoader();

        URL url = loader.getResource("migration.yml");
        if (url == null) {
            throw new NotFoundYmlException("migration.yml");
        }

        Yaml yaml = new Yaml();
        return yaml.loadAs(loader.getResourceAsStream("migration.yml"), PluginConfig.class);

    }
}
