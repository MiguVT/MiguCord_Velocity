package com.miguvt.migucord;

import com.velocitypowered.api.plugin.Plugin;
import com.velocitypowered.api.plugin.annotation.DataDirectory;
import com.velocitypowered.api.proxy.ProxyServer;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent;
import com.velocitypowered.api.event.proxy.ProxyShutdownEvent;
import org.slf4j.Logger;
import javax.inject.Inject;
import java.nio.file.Path;

@Plugin(id = "migucord", name = "MiguCord Velocity", version = "1.0.0", description = "A Velocity plugin for Minecraft server integration with Discord", authors = {"MiguVT"})
public class MiguCordPlugin {
    private final ProxyServer server;
    private final Logger logger;
    private final Path dataDirectory;

    @Inject
    public MiguCordPlugin(ProxyServer server, Logger logger, @DataDirectory Path dataDirectory) {
        this.server = server;
        this.logger = logger;
        this.dataDirectory = dataDirectory;
    }

    @Subscribe
    public void onProxyInitialization(ProxyInitializeEvent event) {
        // Cargar configuración
        // Iniciar bot de Discord
        // Registrar comandos y eventos
        logger.info("MiguCord plugin has been initialized!");
    }

    @Subscribe
    public void onProxyShutdown(ProxyShutdownEvent event) {
        // Código para manejar la detención del plugin
        logger.info("MiguCord plugin is shutting down.");
    }
}
