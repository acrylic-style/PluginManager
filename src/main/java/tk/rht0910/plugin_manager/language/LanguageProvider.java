package tk.rht0910.plugin_manager.language;

import java.io.File;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import tk.rht0910.plugin_manager.PluginManager;

public class LanguageProvider {
	public static String load(String path, String def) {
		FileConfiguration config = YamlConfiguration.loadConfiguration(new File(PluginManager.getPlugin(PluginManager.class).getDataFolder(), "language_" + PluginManager.getLanguageCode() + ".yml"));
		return (String) config.get(path, def);
	}
}
