package turbotechmods.comfortplus;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Comfort implements ModInitializer {
	public static final String MOD_ID = "comfortplus";
	public static final Logger LOGGER = LoggerFactory.getLogger("Comfort+");
	public static final String DEFAULT_MINECRAFT_PATH = defaultMinecraftPath();

	@Override
	public void onInitialize() {
        LOGGER.info("Default Minecraft Path: {}", DEFAULT_MINECRAFT_PATH);
	}

	private static String defaultMinecraftPath() {
		String path;
		if (System.getProperty("os.name").toLowerCase().contains("windows")) {
			path = System.getenv("APPDATA") + "/.minecraft";
			LOGGER.info("Detected OS: Windows");
		} else if (System.getProperty("os.name").toLowerCase().contains("mac")) {
			path = System.getProperty("user.home") + "/Library/Application Support/minecraft";
			LOGGER.info("Detected OS: macOS");
		} else {
			path = System.getProperty("user.home") + "/.minecraft";
			LOGGER.info("Detected OS: Linux/Unknown");
		}
		return path;
	}
}
