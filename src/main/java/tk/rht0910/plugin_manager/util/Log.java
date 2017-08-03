package tk.rht0910.plugin_manager.util;

import org.bukkit.Bukkit;

public class Log {
	public static void info(String msg) {
		Bukkit.getLogger().info(msg);
	}

	public static void warning(String msg) {
		Bukkit.getLogger().warning(msg);
	}

	public static void warn(String msg) {
		Bukkit.getLogger().warning(msg);
	}

	public static void severe(String msg) {
		Bukkit.getLogger().severe(msg);
	}

	public static void error(String msg) {
		Bukkit.getLogger().severe(msg);
	}

	public static void config(String msg) {
		Bukkit.getLogger().config(msg);
	}

	public static void fine(String msg) {
		Bukkit.getLogger().fine(msg);
	}

	public static void finer(String msg) {
		Bukkit.getLogger().finer(msg);
	}

	public static void finest(String msg) {
		Bukkit.getLogger().finest(msg);
	}
}
