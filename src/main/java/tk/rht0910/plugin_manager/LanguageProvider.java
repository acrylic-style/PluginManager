package tk.rht0910.plugin_manager;

import java.io.File;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class LanguageProvider {
	public static String load(String path, String def) {
		FileConfiguration config = null;
		/*InputStreamReader isr = null;
		InputStream is = Main.getInputStream();
		// Manager.getMain().getConfig().load(new File("language_" + Manager.getMain().getConfig().getString("language") + ".yml"));
		try {
			isr = new InputStreamReader(is, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.getCause().printStackTrace();
			Bukkit.getLogger().severe("This error is unrecoverble error, please create ticket");
			Bukkit.getLogger().severe("https://github.com/rht0910/PluginManager/issues/");
		} catch (ExceptionInInitializerError | NullPointerException e) {
			// e.getCause().printStackTrace();
			e.printStackTrace();
			Bukkit.getLogger().severe("This error is unrecoverble error, please create ticket");
			Bukkit.getLogger().severe("https://github.com/rht0910/PluginManager/issues/");
		}*/
		config = YamlConfiguration.loadConfiguration(new File(Main.getPlugin(Main.class).getDataFolder(), "language_" + Main.getLanguageCode() + ".yml"));
		return (String) config.get(path, def);
	}
}
