package me.piggypiglet.botchecker.core.storage.file;

import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import me.piggypiglet.botchecker.BotCheckerBootstrap;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

// ------------------------------
// Copyright (c) PiggyPiglet 2018
// https://www.piggypiglet.me
// ------------------------------
@Singleton
public final class GFile {
    @Inject private Gson gson;

    private final Map<String, Map<String, Object>> itemMaps = new ConcurrentHashMap<>();
    private final Logger logger = LoggerFactory.getLogger("GFile");

    public void make(String name, String externalPath, String internalPath) {
        File file = new File(externalPath);

        try {
            if (!file.exists()) {
                //noinspection ResultOfMethodCallIgnored
                file.getParentFile().mkdirs();

                if (file.createNewFile()) {
                    if (me.piggypiglet.botchecker.core.utils.file.FileUtils.exportResource(BotCheckerBootstrap.class.getResourceAsStream(internalPath), externalPath)) {
                        insertIntoMap(name, file);

                        logger.info(name + " successfully created & loaded.");
                    } else {
                        logger.error(name + " creation failed.");
                    }
                } else {
                    logger.error(name + " creation failed.");
                }
            } else {
                insertIntoMap(name, file);

                logger.info(name + " successfully loaded.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean isJson(String path) {
        return path.toLowerCase().endsWith(".json");
    }

    @SuppressWarnings("unchecked")
    public void insertIntoMap(String name, File file) {
        Map<String, Object> populatorMap = new HashMap<>();

        try {
            String fileContent = FileUtils.readFileToString(file, "UTF-8");

            if (isJson(file.getPath())) {
                populatorMap.put("file-configuration", new FileConfiguration(gson.fromJson(fileContent, LinkedTreeMap.class)));
            } else {
                populatorMap.put("file-content", fileContent);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        populatorMap.put("file", file);
        itemMaps.put(name, populatorMap);
    }

    public FileConfiguration getFileConfiguration(String name) {
        Object item = itemMaps.get(name).get("file-configuration");

        if (item instanceof FileConfiguration) {
            return (FileConfiguration) item;
        }

        return new FileConfiguration(new HashMap<>());
    }

    public void clearMap() {
        itemMaps.clear();
    }
}