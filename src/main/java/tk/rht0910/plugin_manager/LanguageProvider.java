package tk.rht0910.plugin_manager;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class LanguageProvider {
	public static String load(String path, String def) {
		FileConfiguration config = null;
		InputStreamReader isr = null;
		InputStream is = Main.getInputStream();
		// Manager.getMain().getConfig().load(new File("language_" + Manager.getMain().getConfig().getString("language") + ".yml"));
		try {
			isr = new InputStreamReader(is, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			Bukkit.getLogger().severe("This error is unrecoverble error, please create ticket");
			Bukkit.getLogger().severe("https://github.com/rht0910/PluginManager/issues/");
		} catch (ExceptionInInitializerError | NullPointerException e) {
			e.getCause().printStackTrace();
		}
		config = YamlConfiguration.loadConfiguration(isr);
		return (String) config.get(path, def);
	}
}
