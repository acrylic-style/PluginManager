package tk.rht0910.plugin_manager;

import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import tk.rht0910.plugin_manager.exception.ThrowUncaughtException;
import tk.rht0910.plugin_manager.exception.UncaughtException;
import tk.rht0910.plugin_manager.util.Command;
import tk.rht0910.plugin_manager.util.PluginUtils;

public final class Manager {
	private static PluginUtils pluginUtils;
	private static Command command;
	private static CommandSender sender;
	private static Main main;
	public static Boolean is_available_new_version = false;
	public static String current = Lang.version;
	public static String newv = "";

	static {
		pluginUtils = new PluginUtils();
		command = new Command();
		main = new Main();
	}

	public static PluginUtils getPluginUtil() {
		try {
			return pluginUtils;
		} catch(Exception e) {e.printStackTrace();return pluginUtils;}
	}

	public static Command getCommand() {
		try{
			return command;
		} catch(Exception e) {e.printStackTrace();return command;}
	}


	public static CommandSender getSender(CommandSender sender) {
		try {
			Manager.sender = sender;
			return Manager.sender;
		} catch(Exception e) {e.printStackTrace();return Manager.sender;}
	}

	public static boolean hasPermission(CommandSender sender, String permission) {
		try {
			if(Manager.getSender(sender).hasPermission(permission)) {
				return true;
			} else {
				return false;
			}
		} catch(Exception e) {e.printStackTrace();return false;}
	}

	public static boolean isSenderPlayer(CommandSender sender) {
		try {
			return Manager.getSender(sender) instanceof Player;
		} catch(Exception e) {e.printStackTrace();return Manager.getSender(sender) instanceof Player;}
	}

	public static boolean isSenderConsole(CommandSender sender) {
		try {
			return Manager.getSender(sender) instanceof ConsoleCommandSender;
		} catch(Exception e) {e.printStackTrace();return Manager.getSender(sender) instanceof ConsoleCommandSender;}
	}

	public static Main getMain() {
		try {
			return main;
		} catch(Exception e) {e.printStackTrace();return main;}
	}

	public static boolean getConfig(String path, Object def) {
		try {
			ThrowUncaughtException.callException("@ Debug call");
		} catch (UncaughtException e) {
			e.printStackTrace();
		}
		return true;
	}
}
