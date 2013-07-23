package com.minecraftwars.incinerator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.bukkit.configuration.file.YamlConfiguration;

public class ConfigManager {
    private static ConfigManager instance;
    private YamlConfiguration config;

    public ConfigManager() {
        loadFile();
        instance = this;
    }

    public static ConfigManager getInstance() {
        return instance;
    }

    private void loadFile() {
        File folder = Incinerator.getInstance().getDataFolder();
        if (!folder.isDirectory()) {
            folder.mkdirs();
        }
        File configFile = new File(Incinerator.getInstance().getDataFolder(), "config.yml");
        if (!configFile.exists()) {
            Incinerator.LOG.info("Creating config File...");
            createFile(configFile);
        }
        else {
            Incinerator.LOG.info("Loading config File...");
        }

        config = YamlConfiguration.loadConfiguration(configFile);
    }

    protected void createFile(File configFile) {
        configFile.getParentFile().mkdirs();

        InputStream inputStream = Incinerator.getInstance().getResource("config.yml");

        if (inputStream == null) {
            Incinerator.LOG.severe("Missing resource file: 'config'");
            return;
        }

        OutputStream outputStream = null;

        try {
            outputStream = new FileOutputStream(configFile);

            int read;
            byte[] bytes = new byte[1024];

            while ((read = inputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, read);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public String getSomething() {
        return config.getString("Incinerator.Something", "something");
    }
}
