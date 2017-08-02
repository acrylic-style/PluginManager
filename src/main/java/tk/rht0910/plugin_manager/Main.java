package tk.rht0910.plugin_manager;

import java.io.InputStream;
import java.util.Collection;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import tk.rht0910.plugin_manager.util.PluginUtils;

public final class Main extends JavaPlugin {
	public static InputStream getInputStream() {
		String language = Main.getPlugin(Main.class).getConfig().getString("language");
		return Main.class.getResourceAsStream("language_" + language + ".yml");
	}

	@Override
	public void onEnable() {
		try {
			Bukkit.getServer().getLogger().info("PluginManager is initializing...");
			if(!this.getConfig().isSet("language")) {
				this.getConfig().set("language", "en_US");
				this.getConfig().options().copyDefaults(true);
				//this.getConfig().options().copyDefaults(!this.getConfig().contains("language"));
			}
			Bukkit.getServer().getLogger().info("PluginManager is initialized!");
		} catch(Exception e) {
			Bukkit.getServer().getLogger().severe("Plugin initialize error! Disabling plugin... and Please see errors.");
			e.printStackTrace();
			Manager.getPluginUtil();
			PluginUtils.unloadPlugin(null, "PluginManager");
		}
	}

	@Override
	public void onLoad() {
		try {
			Bukkit.getServer().getLogger().info("Loading PluginManager v0.8.3...");
			Bukkit.getServer().getLogger().info("Loaded PluginManager v0.8.3");
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

	public Main getInstance() {
		return this;
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
				sender.sendMessage(ChatColor.AQUA + "PluginManager is running on " + ChatColor.GREEN + "version 0.8.3");
				sender.sendMessage(ChatColor.AQUA + "Available commands: '/pman help'");
				return true;
			}
			if(args[0].equalsIgnoreCase("help")) {
				//Manager.getCommand().ShowHelp(sender);
				sender.sendMessage(ChatColor.GREEN + " ----- Plugin Manager[v0.8.3] " + Lang.help + Lang.alpha + " -----");
				sender.sendMessage(ChatColor.RED + " ----- <" + Lang.required + "> [" + Lang.optional + "] - " + Lang.information);
				sender.sendMessage(ChatColor.AQUA + " - /pman help - " + Lang.pman_help_desc);
				sender.sendMessage(ChatColor.AQUA + " - /pman load <Plugin name or Plugin File> - " + Lang.pman_load_desc);
				sender.sendMessage(ChatColor.AQUA + " - /pman unload(or /pman disable) <Plugin name> - " + Lang.pman_unload_desc);
				sender.sendMessage(ChatColor.AQUA + " - /pman download <FileName> <URL> - " + Lang.pman_download_desc);
				sender.sendMessage(ChatColor.AQUA + " - /pman delete <(Current)FileName> <PluginName(or Backup file name)> - " + Lang.pman_delete_desc);
				sender.sendMessage(ChatColor.AQUA + " - /pman restore <FileName> - " + Lang.pman_restore_desc);
				sender.sendMessage(ChatColor.AQUA + " - /pman editor <Dir> <File> <Line(Count from 0)> <value> - " + Lang.pman_editor_desc);
				sender.sendMessage(ChatColor.AQUA + " - /pman viewer <Dir> <File> - " + Lang.pman_viewer_desc);
				sender.sendMessage(ChatColor.AQUA + " - /pman update - " + Lang.pman_update_desc);
				sender.sendMessage(ChatColor.AQUA + " - /pman usage <Command> - " + Lang.pman_usage_desc);
				sender.sendMessage(ChatColor.AQUA + " - /pman config language <en_US, ja_JP, ...> - Change language.");
				sender.sendMessage(ChatColor.AQUA + " - /pman config reload - Reload config.");
				sender.sendMessage(ChatColor.AQUA + " - BukkitDev(Project page): https://dev.bukkit.org/projects/pluginmanagement/");
				sender.sendMessage(ChatColor.AQUA + " - Jenkins(Developer version): http://point.rht0910.tk:8080/job/PluginManager/");
				sender.sendMessage(ChatColor.AQUA + " - Source code: https://github.com/rht0910/PluginManager/");
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
				PluginUtils.loadPlugin(sender, args[1]);
			} else if(args[0].equalsIgnoreCase("disable")) {
				if(sender instanceof Player) {
					if(!sender.isPermissionSet("pluginmanager.admin")) {
						Bukkit.getServer().getLogger().severe("No permission: /pman disable : " + sender.toString());
						sender.sendMessage(ChatColor.DARK_RED + "No permission");
						return false;
					}
				}
				PluginUtils.unloadPlugin(sender, args[1]);
			} else if(args[0].equalsIgnoreCase("unload")) {
				if(sender instanceof Player) {
					if(!sender.isPermissionSet("pluginmanager.admin")) {
						Bukkit.getServer().getLogger().severe("No permission: /pman unload : " + sender.toString());
						sender.sendMessage(ChatColor.DARK_RED + "No permission");
						return false;
					}
				}
				PluginUtils.unloadPlugin(sender, args[1]);
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
				PluginUtils.RestorePlugin(sender, args[1]);
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
				PluginUtils.DeletePlugin(sender, args[1], args[2]);
			} else if(args[0].equalsIgnoreCase("viewer")) {
				Bukkit.getServer().getLogger().warning("Opening config viewer by " + sender.toString());
				//if(args[2] == null || args[2] == "") {
					PluginUtils.ConfigViewer(sender, args[1], args[2]);
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
			} else if(args[0].equalsIgnoreCase("update-dev")) {
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
				sender.sendMessage(ChatColor.AQUA + "Updating plugin...(Downloading from DEVELOPER UNSTABLE build)");
				try {
					PluginUtils.Download(sender, "PluginManager", "http://point.rht0910.tk:8080/job/PluginManager-branch%20of%20dev/lastSuccessfulBuild/artifact/target/PluginManager.jar");
				} catch(Exception e) {
					sender.sendMessage(ChatColor.RED + "Failed to update. (Is Download server down?)");
					return false;
				}
				final Collection<? extends Player> onplayers = Bukkit.getServer().getOnlinePlayers();
				final Player[] players = (Player[]) onplayers.toArray();
				for(int i=0;i<=players.length;i++) {
					if(players[i].isOp()) {
						players[i].sendMessage(ChatColor.GREEN + "PluginManager" + ChatColor.RED + "(UNSTABLE)" + ChatColor.GREEN + " is updated by " + sender.toString() + ". Please restart server.");
					}
				}
			} else if(args[0].equalsIgnoreCase("update-dev-local")) {
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
				sender.sendMessage(ChatColor.AQUA + "Updating plugin...(Downloading from DEVELOPER UNSTABLE build)");
				try {
					PluginUtils.Download(sender, "PluginManager", "http://local4.point.rht0910.tk:8080/job/PluginManager-branch%20of%20dev/lastSuccessfulBuild/artifact/target/PluginManager.jar");
				} catch(Exception e) {
					sender.sendMessage(ChatColor.RED + "Failed to update. (Is Download server down?)");
					return false;
				}
				final Collection<? extends Player> onplayers = Bukkit.getServer().getOnlinePlayers();
				final Player[] players = (Player[]) onplayers.toArray();
				for(int i=0;i<=players.length;i++) {
					if(players[i].isOp()) {
						players[i].sendMessage(ChatColor.GREEN + "PluginManager" + ChatColor.RED + "(UNSTABLE)" + ChatColor.GREEN + " is updated by " + sender.toString() + ". Please restart server.");
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
			} else if(args[0].equalsIgnoreCase("usage")) {
				tk.rht0910.plugin_manager.util.Command.getUsageOfCmd(sender, args[1]);
			} else if(args[0].equalsIgnoreCase("config")) {
				if(args[1].equalsIgnoreCase("language")) {
					this.getConfig().set("language", args[2]);
					this.saveConfig();
					this.reloadConfig();
					sender.sendMessage(ChatColor.GREEN + "Success! Set language to " + args[2]);
				} else if(args[1].equalsIgnoreCase("reload")) {
					this.reloadConfig();
					sender.sendMessage(ChatColor.GREEN + "[PMan] Config reloaded");
				} else {
					sender.sendMessage("language以外の設定なんてありません。");
				}
			} else {
				sender.sendMessage(ChatColor.RED + Lang.invalid_args);
				//Manager.getCommand().ShowHelp(sender);
				sender.sendMessage(ChatColor.GREEN + " ----- Plugin Manager[v0.8.3] " + Lang.help + Lang.alpha + " -----");
				sender.sendMessage(ChatColor.RED + " ----- <" + Lang.required + "> [" + Lang.optional + "] - " + Lang.information);
				sender.sendMessage(ChatColor.AQUA + " - /pman help - " + Lang.pman_help_desc);
				sender.sendMessage(ChatColor.AQUA + " - /pman load <Plugin name or Plugin File> - " + Lang.pman_load_desc);
				sender.sendMessage(ChatColor.AQUA + " - /pman unload(or /pman disable) <Plugin name> - " + Lang.pman_unload_desc);
				sender.sendMessage(ChatColor.AQUA + " - /pman download <FileName> <URL> - " + Lang.pman_download_desc);
				sender.sendMessage(ChatColor.AQUA + " - /pman delete <(Current)FileName> <PluginName(or Backup file name)> - " + Lang.pman_delete_desc);
				sender.sendMessage(ChatColor.AQUA + " - /pman restore <FileName> - " + Lang.pman_restore_desc);
				sender.sendMessage(ChatColor.AQUA + " - /pman editor <Dir> <File> <Line(Count from 0)> <value> - " + Lang.pman_editor_desc);
				sender.sendMessage(ChatColor.AQUA + " - /pman viewer <Dir> <File> - " + Lang.pman_viewer_desc);
				sender.sendMessage(ChatColor.AQUA + " - /pman update - " + Lang.pman_update_desc);
				sender.sendMessage(ChatColor.AQUA + " - /pman update-dev - Update to UNSTABLE and DEVELOPER version.");
				sender.sendMessage(ChatColor.AQUA + " - /pman usage <Command> - " + Lang.pman_usage_desc);
				sender.sendMessage(ChatColor.AQUA + " - /pman config language <en_US, ja_JP, ...> - Change language.");
				sender.sendMessage(ChatColor.AQUA + " - /pman config reload - Reload config.");
				sender.sendMessage(ChatColor.AQUA + " - BukkitDev(Project page): https://dev.bukkit.org/projects/pluginmanagement/");
				sender.sendMessage(ChatColor.AQUA + " - Jenkins(Developer version): http://point.rht0910.tk:8080/job/PluginManager/");
				sender.sendMessage(ChatColor.AQUA + " - Source code: https://github.com/rht0910/PluginManager/");
			}
		}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return true;
	}
}
