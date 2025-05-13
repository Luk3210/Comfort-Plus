package turbotechmods.comfortplus;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class NoFog {
    private static final File CONFIG_FILE = new File("config/sodium-extra-options.json");

    public static void loadConfig() {
        try {
            if (!CONFIG_FILE.exists()) {
                createDefaultConfig();
            }

            FileReader reader = new FileReader(CONFIG_FILE);
            JsonObject config = JsonParser.parseReader(reader).getAsJsonObject();
            reader.close();

            JsonObject renderSettings = config.getAsJsonObject("render_settings");
            if (renderSettings != null) {
                renderSettings.add("multi_dimension_fog_control", new JsonPrimitive(true));

                JsonObject dimensionFogDistance = renderSettings.getAsJsonObject("dimensionFogDistance");
                if (dimensionFogDistance != null) {
                    dimensionFogDistance.add("minecraft:overworld", new JsonPrimitive(0));
                    dimensionFogDistance.add("minecraft:the_nether", new JsonPrimitive(33));
                    dimensionFogDistance.add("minecraft:the_end", new JsonPrimitive(33));
                } else {
                    JsonObject newDimensionFogDistance = new JsonObject();
                    newDimensionFogDistance.add("minecraft:overworld", new JsonPrimitive(0));
                    newDimensionFogDistance.add("minecraft:the_nether", new JsonPrimitive(33));
                    newDimensionFogDistance.add("minecraft:the_end", new JsonPrimitive(33));
                    renderSettings.add("dimensionFogDistance", newDimensionFogDistance);
                }
            }

            saveConfig(config);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void createDefaultConfig() throws IOException {
        JsonObject defaultConfig = new JsonObject();
        JsonObject renderSettings = new JsonObject();

        renderSettings.add("multi_dimension_fog_control", new JsonPrimitive(true));
        JsonObject dimensionFogDistance = new JsonObject();

        dimensionFogDistance.add("minecraft:overworld", new JsonPrimitive(0));
        dimensionFogDistance.add("minecraft:the_nether", new JsonPrimitive(33));
        dimensionFogDistance.add("minecraft:the_end", new JsonPrimitive(33));
        renderSettings.add("dimensionFogDistance", dimensionFogDistance);
        defaultConfig.add("render_settings", renderSettings);
        saveConfig(defaultConfig);
    }

    private static void saveConfig(JsonObject config) throws IOException {
        FileWriter writer = new FileWriter(CONFIG_FILE);
        writer.write(config.toString());
        writer.close();
    }
}