package tk.rht0910.plugin_manager.util;

import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public final class Manager {
	private static PluginUtils pluginUtils = null;
	private static CommandSender sender;

	static {
		pluginUtils = new PluginUtils();
	}

	public static PluginUtils getPluginUtil() {
		try {
			return pluginUtils;
		} catch(Exception e) {e.printStackTrace();return pluginUtils;}
	}

	public static CommandSender getSender(CommandSender sender) {
			Manager.sender = sender;
			return Manager.sender;
	}

	public static boolean hasPermission(CommandSender sender, String permission) {
			return Manager.getSender(sender).hasPermission(permission);
	}

	public static boolean isPlayer(CommandSender sender) {
		try {
			return Manager.getSender(sender) instanceof Player;
		} catch(Exception e) {e.printStackTrace();return Manager.getSender(sender) instanceof Player;}
	}

	public static boolean isConsole(CommandSender sender) {
		try {
			return Manager.getSender(sender) instanceof ConsoleCommandSender;
		} catch(Exception e) {e.printStackTrace();return Manager.getSender(sender) instanceof ConsoleCommandSender;}
	}
}
