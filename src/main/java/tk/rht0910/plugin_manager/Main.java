package tk.rht0910.plugin_manager;

import java.util.Collection;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import tk.rht0910.plugin_manager.util.PluginUtils;

public final class Main extends JavaPlugin {
	@Override
	public void onEnable() {
		try {
			Bukkit.getServer().getLogger().info("PluginManager is initializing...");
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
			Bukkit.getServer().getLogger().info("Loading PluginManager v0.6...");
			Bukkit.getServer().getLogger().info("Loaded PluginManager v0.6");
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
			Bukkit.getServer().getLogger().severe(ChatColor.DARK_RED + "Unknown error! Please see errors.");
			e.printStackTrace();
		}
	}

	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		try {
		if(command.getName().equalsIgnoreCase("pman")) {
			if(sender instanceof Player) {
				if(!sender.isOp()) {
					sender.sendMessage(ChatColor.RED + "You're not operator!");
				}
			}
			if(args.length == 0 || args.equals(null)) {
				sender.sendMessage(ChatColor.AQUA + "PluginManager is running on " + ChatColor.GREEN + "version 0.6");
				sender.sendMessage(ChatColor.AQUA + "Available commands: '/pman help'");
				return true;
			}
			if(args[0] == "help") {
				Manager.getCommand().ShowHelp(sender);
				sender.sendMessage(ChatColor.AQUA + "PluginManager 0.6" + ChatColor.AQUA + "(BETA)");
			} else if(args[0].equalsIgnoreCase("load")) {
				if(sender instanceof Player) {
					if(!sender.isPermissionSet("pluginmanager.admin")) {
						sender.sendMessage(ChatColor.DARK_RED + "No permission");
						return false;
					}
				}
				if(args[1] == null) {
					sender.sendMessage(ChatColor.RED + "Not enough args");
					return false;
				}
				//Manager.getPluginUtil().loadPlugin(sender, args[1]);
				sender.sendMessage(ChatColor.GREEN + "----- Plugin Manager Help -----");
				sender.sendMessage(ChatColor.RED + "----- <Requirement args> [Permission] - Information");
				sender.sendMessage(ChatColor.GRAY + "----- <[Options]> -----");
				sender.sendMessage(ChatColor.AQUA + " - /pman <[help]> [pluginmanager.help(Defaults granted permission for all operators.)] - Displays this. '/ピーマン'? No...");
				sender.sendMessage(ChatColor.DARK_BLUE + " --- Requires permission [pluginmanager.admin] ---");
				sender.sendMessage(ChatColor.AQUA + " - /load(/pman load) <Plugin name or Plugin File> [pluginmanager.load] - Load or Enable a plugin");
				sender.sendMessage(ChatColor.AQUA + " - /unload(/pman disable) <Plugin name> [pluginmanager.unload] - Disable plugin");
				sender.sendMessage(ChatColor.AQUA + " - /download(/pman download) <FileName> <URL> [pluginmanager.download] - Download plugin");
				sender.sendMessage(ChatColor.DARK_BLUE + " --- Requires permission [pluginmanager.admin] and [pluginmanager.super-admin] ---");
				sender.sendMessage(ChatColor.AQUA + " - /delete <FileName> <PluginName(DummyPluginName is allowed)> [pluginmanager.delete] - ");
				sender.sendMessage(ChatColor.AQUA + " - /restore <FileName> [pluginmanager.restore]");
			} else if(args[0].equalsIgnoreCase("disable")) {
				if(sender instanceof Player) {
					if(!sender.isPermissionSet("pluginmanager.admin")) {
						Bukkit.getServer().getLogger().severe("No permission: /pman disable : " + sender.toString());
						sender.sendMessage(ChatColor.DARK_RED + "No permission");
						return false;
					}
				}
				Manager.getPluginUtil().unloadPlugin(sender, args[1]);
			} else if(args[0].equalsIgnoreCase("unload")) {
				if(sender instanceof Player) {
					if(!sender.isPermissionSet("pluginmanager.admin")) {
						Bukkit.getServer().getLogger().severe("No permission: /pman unload : " + sender.toString());
						sender.sendMessage(ChatColor.DARK_RED + "No permission");
						return false;
					}
				}
				Manager.getPluginUtil().unloadPlugin(sender, args[1]);
			} else if(args[0].equalsIgnoreCase("download")) {
				if(sender instanceof Player) {
					if(!sender.isOp()) {
						sender.sendMessage(ChatColor.DARK_RED + "You are not Operator!");
						return false;
					}
					if(!sender.isPermissionSet("pluginmanager.admin")) {
						sender.sendMessage(ChatColor.DARK_RED + "No permission");
						return false;
					}
				}
				if(args[1] == null || args[2] == null) {
					sender.sendMessage(ChatColor.RED + "Not enough args");
					return false;
				}
				PluginUtils.Download(sender, args[1], args[2]);
			} else if(args[0].equalsIgnoreCase("restore")) {
				if(sender instanceof Player) {
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
				}
				if(args[0] == null) {
					sender.sendMessage(ChatColor.RED + "Not enough args, cannot continue.");
					return false;
				}
				Manager.getPluginUtil().RestorePlugin(sender, args[1]);
			} else if(args[0].equalsIgnoreCase("delete")) {
				if(sender instanceof Player) {
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
				PluginUtils.EditConfigFile(sender, args[1], args[2], args[3], args[4]);
			} else if(args[0].equalsIgnoreCase("update")) {
				if(sender instanceof Player) {
					if(!sender.isOp()) {
						sender.sendMessage(ChatColor.DARK_RED + "You are not Operator!");
						return false;
					}
					if(!sender.isPermissionSet("pluginmanager.admin")) {
						sender.sendMessage(ChatColor.DARK_RED + "No permission");
						return false;
					}
				}
				sender.sendMessage(ChatColor.AQUA + "Updating plugin...(Downloading from stable build)");
				try {
					PluginUtils.Download(sender, "PluginManager", "http://point.rht0910.tk:8080/job/PluginManager/lastSuccessfulBuild/artifact/target/PluginManager.jar");
				} catch(Exception e) {
					sender.sendMessage(ChatColor.RED + "Failed to update. (Is Download server down?)");
					return false;
				}
				final Collection<? extends Player> onplayers = Bukkit.getServer().getOnlinePlayers();
				final Player[] players = (Player[]) onplayers.toArray();
				for(int i=0;i<=players.length;i++) {
					if(players[i].isOp()) {
						players[i].sendMessage(ChatColor.GREEN + "PluginManager is updated by " + sender.toString() + ". Please restart server.");
					}
				}
			} else if(args[0].equalsIgnoreCase("update-local")) {
				if(sender instanceof Player) {
					if(!sender.isOp()) {
						sender.sendMessage(ChatColor.DARK_RED + "You are not Operator!");
						return false;
					}
					if(!sender.isPermissionSet("pluginmanager.admin")) {
						sender.sendMessage(ChatColor.DARK_RED + "No permission");
						return false;
					}
				}
				sender.sendMessage(ChatColor.AQUA + "Updating plugin...(Downloading from stable build)");
				try {
					PluginUtils.Download(sender, "PluginManager", "http://local4.point.rht0910.tk:8080/job/PluginManager/lastSuccessfulBuild/artifact/target/PluginManager.jar");
				} catch(Exception e) {
					sender.sendMessage(ChatColor.RED + "Failed to update. (Is Download server down?)");
					return false;
				}
				final Collection<? extends Player> onplayers = Bukkit.getServer().getOnlinePlayers();
				final Player[] players = (Player[]) onplayers.toArray();
				for(int i=0;i<=players.length;i++) {
					if(players[i].isOp()) {
						players[i].sendMessage(ChatColor.GREEN + "PluginManager is updated by " + sender.toString() + ". Please restart server.");
					}
				}
			} else {
				sender.sendMessage(ChatColor.RED + "Invalid args");
				//Manager.getCommand().ShowHelp(sender);
				sender.sendMessage(ChatColor.GREEN + "----- Plugin Manager Help -----");
				sender.sendMessage(ChatColor.RED + "----- <Requirement args> [Permission] - Information");
				sender.sendMessage(ChatColor.GRAY + "----- <[Options]> -----");
				sender.sendMessage(ChatColor.AQUA + " - /pman <[help]> [pluginmanager.help(Defaults granted permission for all operators.)] - Displays this. '/ピーマン'? No...");
				sender.sendMessage(ChatColor.DARK_BLUE + " --- Requires permission [pluginmanager.admin] ---");
				sender.sendMessage(ChatColor.AQUA + " - /load(/pman load) <Plugin name or Plugin File> [pluginmanager.load] - Load or Enable a plugin");
				sender.sendMessage(ChatColor.AQUA + " - /unload(/pman disable) <Plugin name> [pluginmanager.unload] - Disable plugin");
				sender.sendMessage(ChatColor.AQUA + " - /download(/pman download) <FileName> <URL> [pluginmanager.download] - Download plugin");
				sender.sendMessage(ChatColor.DARK_BLUE + " --- Requires permission [pluginmanager.admin] and [pluginmanager.super-admin] ---");
				sender.sendMessage(ChatColor.AQUA + " - /delete <FileName> <PluginName(DummyPluginName is allowed)> [pluginmanager.delete] - ");
				sender.sendMessage(ChatColor.AQUA + " - /restore <FileName> [pluginmanager.restore]");
			}
		}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return true;
	}
}
