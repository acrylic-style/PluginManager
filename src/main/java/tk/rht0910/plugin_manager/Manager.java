package tk.rht0910.plugin_manager;

import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import tk.rht0910.plugin_manager.util.Command;
import tk.rht0910.plugin_manager.util.PluginUtils;

public abstract class Manager {
	private static PluginUtils pluginUtils;
	private static Command command;
	private static Main instance;
	private static Main main;
	private static CommandSender sender;

	static {
		pluginUtils = new PluginUtils();
		command = new Command();
		new Main();
		instance = Main.instance;
		main = new Main();
	}

	public static PluginUtils getPluginUtil() {
		return pluginUtils;
	}

	public static Command getCommand() {
		return command;
	}

	public static Main getInstance() {
		return instance;
	}

	public static Main getMain() {
		return main;
	}

	public static CommandSender getSender(CommandSender sender) {
		Manager.sender = sender;
		return Manager.sender;
	}

	public static boolean hasPermission(CommandSender sender, String permission) {
		if(Manager.getSender(sender).hasPermission(permission)) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean isSenderPlayer(CommandSender sender) {
		return Manager.getSender(sender) instanceof Player;
	}

	public static boolean isSenderConsole(CommandSender sender) {
		return Manager.getSender(sender) instanceof ConsoleCommandSender;
	}
}
