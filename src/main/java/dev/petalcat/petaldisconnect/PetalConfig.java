package dev.petalcat.petaldisconnect;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;

public class PetalConfig {

    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final File FILE = new File("config/petaldisconnect.json");

    public boolean enableQuickDisconnect = true;
    public boolean enableFocusCheck = false;
    public boolean enableAutoReconnect = true;

    public static PetalConfig load() {
        try {
            if (!FILE.exists()) {
                PetalConfig cfg = new PetalConfig();
                cfg.save();
                return cfg;
            }

            try (InputStreamReader reader = new InputStreamReader(
                    new FileInputStream(FILE),
                    "UTF-8"
            )) {
                return GSON.fromJson(reader, PetalConfig.class);
            }

        } catch (Exception e) {
            e.printStackTrace();
            return new PetalConfig();
        }
    }

    public void save() {
        try {
            File parent = FILE.getParentFile();
            if (parent != null) parent.mkdirs();

            try (OutputStreamWriter writer = new OutputStreamWriter(
                    new FileOutputStream(FILE),
                    "UTF-8"
            )) {
                GSON.toJson(this, writer);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}