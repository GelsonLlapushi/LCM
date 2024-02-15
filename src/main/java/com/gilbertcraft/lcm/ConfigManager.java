package com.gilbertcraft.lcm;// ConfigManager.java

import org.bukkit.configuration.file.FileConfiguration;

public class ConfigManager {

    private final LC plugin;
    private final FileConfiguration config;

    public ConfigManager(LC plugin) {
        this.plugin = plugin;
        this.config = plugin.getConfig();
    }

    public void loadConfig() {
        plugin.saveDefaultConfig(); // Salva il file di configurazione predefinito se non esiste
    }

    public String getParticleType() {
        return config.getString("particle-type", "END_ROD");
    }

    public void setParticleType(String particleType) {
        config.set("particle-type", particleType);
        plugin.saveConfig();
    }
}
