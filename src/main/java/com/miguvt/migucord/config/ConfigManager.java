package com.miguvt.migucord.config;


import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.representer.Representer;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

public class ConfigManager {
    private static final Path CONFIG_PATH = Paths.get("plugins/MiguVerse/config.yml");
    private static final String DEFAULT_CONFIG_PATH = "/config.yml";
    private Map<String, Object> config;
    private Yaml yaml;

    public ConfigManager() {
        setupYaml();
        loadConfig();
    }

    private void setupYaml() {
        Constructor constructor = new Constructor();
        Representer representer = new Representer();
        DumperOptions options = new DumperOptions();
        options.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
        yaml = new Yaml(constructor, representer, options);
    }

    private void loadConfig() {
        try {
            if (!Files.exists(CONFIG_PATH)) {
                Files.createDirectories(CONFIG_PATH.getParent());
                try (InputStream is = getClass().getResourceAsStream(DEFAULT_CONFIG_PATH)) {
                    Files.copy(is, CONFIG_PATH);
                }
            }
            try (FileInputStream fis = new FileInputStream(CONFIG_PATH.toFile())) {
                config = yaml.load(fis);
            }
            updateConfigWithDefaults();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void updateConfigWithDefaults() throws IOException {
        boolean modified = false;
        try (InputStream is = getClass().getResourceAsStream(DEFAULT_CONFIG_PATH)) {
            Map<String, Object> defaults = yaml.load(is);
            for (String key : defaults.keySet()) {
                if (!config.containsKey(key)) {
                    config.put(key, defaults.get(key));
                    modified = true;
                }
            }
        }
        if (modified) {
            saveConfig();
        }
    }

    private void saveConfig() throws IOException {
        try (FileWriter writer = new FileWriter(CONFIG_PATH.toFile())) {
            yaml.dump(config, writer);
        }
    }

    public Map<String, Object> getConfig() {
        return config;
    }
}
