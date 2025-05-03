package turbotechmods.comfortplus.mixin;

import net.minecraft.client.option.GameOptions;
import net.minecraft.util.Util;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import java.io.File;

@Mixin(GameOptions.class)
public abstract class GlobalOptions {

	@ModifyVariable(method = "<init>", at = @At("HEAD"), argsOnly = true)
	private static File modifyGameOptionsDirectory(File originalFile) {
		String defaultMinecraftPath;

		if (Util.getOperatingSystem() == Util.OperatingSystem.OSX) {
			defaultMinecraftPath = System.getProperty("user.home") + "/Library/Application Support/minecraft";
		} else if (Util.getOperatingSystem() == Util.OperatingSystem.LINUX) {
			defaultMinecraftPath = System.getProperty("user.home") + "/.minecraft";
		} else {
			defaultMinecraftPath = System.getenv("APPDATA") + "/.minecraft";
		}

		File optionsFile = new File(defaultMinecraftPath);
		if (!optionsFile.exists()) {
			optionsFile.mkdirs();
		}

		return optionsFile;
	}
}