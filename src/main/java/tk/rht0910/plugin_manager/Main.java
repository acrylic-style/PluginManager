package tk.rht0910.plugin_manager;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
	public static Main instance = null;

	@Override
	public void onEnable() {
		try {
			Bukkit.getServer().getLogger().info("PluginManager is initializing...");
			instance = this;
			Bukkit.getServer().getLogger().info("PluginManager is initialized!");
		} catch(Exception e) {
			Bukkit.getServer().getLogger().severe("Plugin initialize error! Disabling plugin... and Please see errors.");
			e.printStackTrace();
			Manager.getPluginUtil().unloadPlugin(null, "PluginManager");
		}
	}

	@Override
	public void onLoad() {
		try {
			Bukkit.getServer().getLogger().info("Loading PluginManager v0.4.5...");
			Bukkit.getServer().getLogger().info("Loaded PluginManager v0.4.5");
		} catch(Exception e) {
			Bukkit.getServer().getLogger().info("Unknown error: " + e);
			e.printStackTrace();
		}
	}

	@Override
	public void onDisable() {
		try {
		Bukkit.getServer().getLogger().info("PluginManager is disabled!");
		} catch(Exception e) {
			Bukkit.getServer().getLogger().severe("Unknown error! Please see errors.");
			e.printStackTrace();
		}
	}

	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(command.getName().equalsIgnoreCase("pman")) {
			if(!sender.isOp()) {
				sender.sendMessage(ChatColor.RED + "You are not operator!");
			}
			if(args.length == 0 || args.equals(null)) {
				sender.sendMessage(ChatColor.AQUA + "PluginManager is running on " + ChatColor.GREEN + "version 0.4.5" + ChatColor.RED + "(dev)");
				sender.sendMessage(ChatColor.AQUA + "Available commands: '/pman help'");
				return true;
			}
			if(args[0] == "help") {
				Manager.getCommand().ShowHelp(sender);
				sender.sendMessage(ChatColor.AQUA + "PluginManager 0.4.5" + ChatColor.RED + "(dev)");
			} else if(args[0].equalsIgnoreCase("load")) {
				if(!sender.isPermissionSet("pluginmanager.admin")) {
					sender.sendMessage(ChatColor.DARK_RED + "No permission");
					return false;
				}
				if(args[1] == null) {
					sender.sendMessage(ChatColor.RED + "Not enough args");
					return false;
				}
				Manager.getPluginUtil().loadPlugin(sender, args[1]);
			} else if(args[0].equalsIgnoreCase("disable")) {
				if(!sender.isPermissionSet("pluginmanager.admin")) {
					Bukkit.getServer().getLogger().severe("No permission: /pman disable : " + sender.toString());
					sender.sendMessage(ChatColor.DARK_RED + "No permission");
					return false;
				}
				Manager.getPluginUtil().unloadPlugin(sender, args[1]);
			} else if(args[0].equalsIgnoreCase("unload")) {
				if(!sender.isPermissionSet("pluginmanager.admin")) {
					Bukkit.getServer().getLogger().severe("No permission: /pman unload : " + sender.toString());
					sender.sendMessage(ChatColor.DARK_RED + "No permission");
					return false;
				}
				Manager.getPluginUtil().unloadPlugin(sender, args[1]);
			} else if(args[0].equalsIgnoreCase("download")) {
				if(!sender.isOp()) {
					sender.sendMessage(ChatColor.DARK_RED + "You are not Operator!");
					return false;
				}
				if(!sender.isPermissionSet("pluginmanager.admin")) {
					sender.sendMessage(ChatColor.DARK_RED + "No permission");
					return false;
				}
				if(args[1] == null || args[2] == null) {
					sender.sendMessage(ChatColor.RED + "Not enough args");
					return false;
				}
				Manager.getPluginUtil().Download(sender, args[1], args[2]);
			} else if(args[0].equalsIgnoreCase("restore")) {
				if(!sender.isOp()) {
					sender.sendMessage(ChatColor.DARK_RED + "You are not Operator!");
					return false;
				}
				if(!sender.isPermissionSet("pluginmanager.admin")) {
					sender.sendMessage(ChatColor.DARK_RED + "No permission");
					return false;
				}
				if(!sender.isPermissionSet("pluginmanager.super-admin")) {
					sender.sendMessage(ChatColor.DARK_RED + "No permission");
					return false;
				}
				if(args[0] == null) {
					sender.sendMessage(ChatColor.RED + "Not enough args, cannot continue.");
					return false;
				}
				Manager.getPluginUtil().RestorePlugin(sender, args[1]);
			} else if(args[0].equalsIgnoreCase("delete")) {
				if(!sender.isOp()) {
					sender.sendMessage(ChatColor.DARK_RED + "You are not Operator!");
					return false;
				}
				if(!sender.isPermissionSet("pluginmanager.admin")) {
					sender.sendMessage(ChatColor.DARK_RED + "No permission");
					return false;
				}
				if(!sender.isPermissionSet("pluginmanager.super-admin")) {
					sender.sendMessage(ChatColor.DARK_RED + "No permission");
					return false;
				}
				if(args[1] == null || args[2] == null) {
					sender.sendMessage(ChatColor.RED + "Not enough args, cannot continue.");
					return false;
				}
				Manager.getPluginUtil().DeletePlugin(sender, args[1], args[2]);
			} else if(args[0].equalsIgnoreCase("viewer")) {
				Bukkit.getServer().getLogger().warning("Opening config viewer by " + sender.toString());
				//if(args[2] == null || args[2] == "") {
					Manager.getPluginUtil().ConfigViewer(sender, args[1], args[2]);
				//} else {
				//	Manager.getPluginUtil().ConfigViewer(sender, args[0], args[1], new Integer(args[2]));
				//}
			} else if(args[0].equalsIgnoreCase("editor")) {
				Manager.getPluginUtil().EditConfigFile(sender, args[1], args[2], args[3], args[4]);
			} else {
				sender.sendMessage(ChatColor.RED + "Invalid args");
				Manager.getCommand().ShowHelp(sender);
			}
		}
		return true;
	}
}
