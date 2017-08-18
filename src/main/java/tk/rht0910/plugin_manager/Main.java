package tk.rht0910.plugin_manager;

import java.util.Collection;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
//import org.bukkit.util.StringUtil;

import tk.rht0910.plugin_manager.util.Log;
import tk.rht0910.plugin_manager.util.PluginUtils;

public final class Main extends JavaPlugin implements TabCompleter {
	//private static final String[] COMMANDS = {""};
	public char altColorChar = '&';
	public static String getLanguageCode() {
		String getty = Main.getPlugin(Main.class).getConfig().getString("language");
		return getty;
	}



	@Override
	public void onEnable() {
		try {
			Main.this.getConfig().options().copyDefaults(true);
			Main.this.saveConfig();
			Lang.initialize();
			Bukkit.getServer().getLogger().info("[PluginManager] " + Lang.init_complete);
		} catch(Exception | Error e) {
			Bukkit.getServer().getLogger().severe("[PluginManager] " + Lang.init_error);
			e.printStackTrace();
			Manager.getPluginUtil();
			PluginUtils.unloadPlugin(null, "PluginManager");
		}
	}

	@Override
	public void onLoad() {
		try {
			Bukkit.getServer().getLogger().info("[PluginManager] Loading PluginManager v1.0...");
			Bukkit.getServer().getLogger().info("[PluginManager] Loaded PluginManager v1.0");
		} catch(Exception e) {
			Bukkit.getServer().getLogger().info("[PluginManager] Unknown error: " + e);
			e.printStackTrace();
		}
	}

	@Override
	public void onDisable() {
		try {
		Bukkit.getServer().getLogger().info("[PluginManager] PluginManager is disabled!");
		} catch(Exception e) {
			Bukkit.getServer().getLogger().severe(ChatColor.DARK_RED + "[PluginManager] Unknown error! Please see errors.");
			e.printStackTrace();
		}
	}

	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		try {
			Lang.use(); // Initialize variables
		if(command.getName().equalsIgnoreCase("pman")) {
			if(sender instanceof Player) {
				if(!sender.isOp()) {
					sender.sendMessage(ChatColor.translateAlternateColorCodes(altColorChar, Lang.you_are_not_operator));
				}
			}
			if(args.length == 0 || args.equals(null)) {
				sender.sendMessage(ChatColor.translateAlternateColorCodes('&', String.format(Lang.running_on, Lang.version)));
				sender.sendMessage(ChatColor.translateAlternateColorCodes('&', Lang.available_commands));
				return true;
			}
			if(args[0].equalsIgnoreCase("help")) {
				try {
					//Manager.getCommand().ShowHelp(sender);
					sender.sendMessage(ChatColor.GREEN + " ----- Plugin Manager[" + Lang.version + "] " + Lang.help + " -----");
					sender.sendMessage(ChatColor.RED + " ----- <" + Lang.required + "> [" + Lang.optional + "] - " + Lang.information);
					sender.sendMessage(ChatColor.AQUA + " - /pman help - " + Lang.pman_help_desc);
					sender.sendMessage(ChatColor.AQUA + " - /pman load <Plugin name or Plugin File> - " + Lang.pman_load_desc);
					sender.sendMessage(ChatColor.AQUA + " - /pman unload(or /pman disable) <Plugin name> - " + Lang.pman_unload_desc);
					sender.sendMessage(ChatColor.AQUA + " - /pman download <FileName> <URL> - " + Lang.pman_download_desc);
					sender.sendMessage(ChatColor.AQUA + " - /pman delete <PluginFileName> <PluginName(or Backup file name)> - " + Lang.pman_delete_desc);
					sender.sendMessage(ChatColor.AQUA + " - /pman restore <FileName> - " + Lang.pman_restore_desc);
					sender.sendMessage(ChatColor.AQUA + " - /pman editor <Dir> <File> <Line(Count from 0)> <value> - " + Lang.pman_editor_desc);
					sender.sendMessage(ChatColor.AQUA + " - /pman viewer <Dir> <File> - " + Lang.pman_viewer_desc);
					sender.sendMessage(ChatColor.AQUA + " - /pman update - " + Lang.pman_update_desc);
					sender.sendMessage(ChatColor.AQUA + " - /pman update-dev - Update to UNSTABLE and DEVELOPER version.");
					sender.sendMessage(ChatColor.AQUA + " - /pman usage <Command> - " + Lang.pman_usage_desc);
					sender.sendMessage(ChatColor.AQUA + " - /pman config language <en_US, ja_JP, ...> - " + Lang.pman_config_language);
					sender.sendMessage(ChatColor.AQUA + " - /pman config reload - " + Lang.pman_config_reload);
					sender.sendMessage(ChatColor.translateAlternateColorCodes(altColorChar, String.format(Lang.project_page, "https://dev.bukkit.org/projects/pluginmanagement/")));
					sender.sendMessage(ChatColor.translateAlternateColorCodes(altColorChar, String.format(Lang.developer_version, "http://point.rht0910.tk:8080/job/PluginManager/")));
					sender.sendMessage(ChatColor.translateAlternateColorCodes(altColorChar, String.format(Lang.source_code, "https://github.com/rht0910/PluginManager/")));
					sender.sendMessage(ChatColor.translateAlternateColorCodes(altColorChar, String.format(Lang.problem_case, "https://github.com/rht0910/PluginManager/issues/")));
				} catch(Exception | Error e) {
					e.printStackTrace();
				}
			} else if(args[0].equalsIgnoreCase("load")) {
				if(sender instanceof Player) {
					if(!sender.isPermissionSet("pluginmanager.admin")) {
						sender.sendMessage(ChatColor.translateAlternateColorCodes(altColorChar, Lang.no_permission));
						return false;
					}
				}
				if(args[1] == null) {
					sender.sendMessage(ChatColor.translateAlternateColorCodes(altColorChar, Lang.not_enough_args));
					return false;
				}
				PluginUtils.loadPlugin(sender, args[1]);
			} else if(args[0].equalsIgnoreCase("disable")) {
				if(sender instanceof Player) {
					if(!sender.isPermissionSet("pluginmanager.admin")) {
						Bukkit.getServer().getLogger().severe("No permission: /pman disable : " + sender.toString());
						sender.sendMessage(ChatColor.translateAlternateColorCodes('&', Lang.no_permission));
						return false;
					}
				}
				PluginUtils.unloadPlugin(sender, args[1]);
			} else if(args[0].equalsIgnoreCase("unload")) {
				if(sender instanceof Player) {
					if(!sender.isPermissionSet("pluginmanager.admin")) {
						Bukkit.getServer().getLogger().severe("No permission: /pman unload : " + sender.toString());
						sender.sendMessage(ChatColor.translateAlternateColorCodes(altColorChar, Lang.no_permission));
						return false;
					}
				}
				PluginUtils.unloadPlugin(sender, args[1]);
			} else if(args[0].equalsIgnoreCase("download")) {
				if(sender instanceof Player) {
					if(!sender.isOp()) {
						sender.sendMessage(ChatColor.translateAlternateColorCodes(altColorChar, Lang.you_are_not_operator));
						return false;
					}
					if(!sender.isPermissionSet("pluginmanager.admin")) {
						sender.sendMessage(ChatColor.translateAlternateColorCodes(altColorChar, Lang.no_permission));
						return false;
					}
				}
				if(args[1] == null || args[2] == null) {
					sender.sendMessage(ChatColor.translateAlternateColorCodes(altColorChar, Lang.not_enough_args));
					return false;
				}
				PluginUtils.Download(sender, args[1], args[2]);
			} else if(args[0].equalsIgnoreCase("restore")) {
				if(sender instanceof Player) {
					if(!sender.isOp()) {
						sender.sendMessage(ChatColor.translateAlternateColorCodes(altColorChar, Lang.you_are_not_operator));
						return false;
					}
					if(!sender.isPermissionSet("pluginmanager.admin")) {
						sender.sendMessage(ChatColor.translateAlternateColorCodes(altColorChar, Lang.no_permission));
						return false;
					}
					if(!sender.isPermissionSet("pluginmanager.super-admin")) {
						sender.sendMessage(ChatColor.translateAlternateColorCodes(altColorChar, Lang.no_permission));
						return false;
					}
				}
				if(args[0] == null) {
					sender.sendMessage(ChatColor.translateAlternateColorCodes(altColorChar, Lang.not_enough_args));
					return false;
				}
				Manager.getPluginUtil().RestorePlugin(sender, args[1]);
			} else if(args[0].equalsIgnoreCase("delete")) {
				if(sender instanceof Player) {
					if(!sender.isOp()) {
						sender.sendMessage(ChatColor.translateAlternateColorCodes(altColorChar, Lang.you_are_not_operator));
						return false;
					}
					if(!sender.isPermissionSet("pluginmanager.admin")) {
						sender.sendMessage(ChatColor.translateAlternateColorCodes(altColorChar, Lang.no_permission));
						return false;
					}
					if(!sender.isPermissionSet("pluginmanager.super-admin")) {
						sender.sendMessage(ChatColor.translateAlternateColorCodes(altColorChar, Lang.no_permission));
						return false;
					}
				}
				if(args[1] == null || args[2] == null) {
					sender.sendMessage(ChatColor.translateAlternateColorCodes(altColorChar, Lang.not_enough_args));
					return false;
				}
				Manager.getPluginUtil().DeletePlugin(sender, args[1], args[2]);
			} else if(args[0].equalsIgnoreCase("viewer")) {
				Bukkit.getServer().getLogger().warning(ChatColor.translateAlternateColorCodes(altColorChar, String.format(Lang.opened_config_viewer, sender.toString())));
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
						sender.sendMessage(ChatColor.translateAlternateColorCodes(altColorChar, Lang.you_are_not_operator));
						return false;
					}
					if(!sender.isPermissionSet("pluginmanager.admin")) {
						sender.sendMessage(ChatColor.translateAlternateColorCodes(altColorChar, Lang.no_permission));
						return false;
					}
				}
				sender.sendMessage(ChatColor.translateAlternateColorCodes(altColorChar, Lang.updating_plugin));
				try {
					PluginUtils.Download(sender, "PluginManager", "http://point.rht0910.tk:8080/job/PluginManager/lastSuccessfulBuild/artifact/target/PluginManager.jar");
				} catch(Exception e) {
					sender.sendMessage(ChatColor.translateAlternateColorCodes(altColorChar, Lang.failed_update_plugin));
					return false;
				}
				final Collection<? extends Player> onplayers = Bukkit.getServer().getOnlinePlayers();
				final Player[] players = (Player[]) onplayers.toArray();
				for(int i=0;i<=players.length;i++) {
					if(players[i].isOp()) {
						players[i].sendMessage(ChatColor.translateAlternateColorCodes(altColorChar, String.format(Lang.success_update_plugin, sender.toString())));
					}
				}
			} else if(args[0].equalsIgnoreCase("update-dev")) {
				if(sender instanceof Player) {
					if(!sender.isOp()) {
						sender.sendMessage(ChatColor.translateAlternateColorCodes(altColorChar, Lang.you_are_not_operator));
						return false;
					}
					if(!sender.isPermissionSet("pluginmanager.admin")) {
						sender.sendMessage(ChatColor.translateAlternateColorCodes(altColorChar, Lang.no_permission));
						return false;
					}
				}
				sender.sendMessage(ChatColor.RED + "Updating plugin...(Downloading from DEVELOPER UNSTABLE build)");
				try {
					PluginUtils.Download(sender, "PluginManager", "http://point.rht0910.tk:8080/job/PluginManager-branch%20of%20dev/lastSuccessfulBuild/artifact/target/PluginManager.jar");
				} catch(Exception e) {
					sender.sendMessage(ChatColor.translateAlternateColorCodes(altColorChar, Lang.failed_update_plugin));
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
						sender.sendMessage(ChatColor.translateAlternateColorCodes(altColorChar, Lang.you_are_not_operator));
						return false;
					}
					if(!sender.isPermissionSet("pluginmanager.admin")) {
						sender.sendMessage(ChatColor.translateAlternateColorCodes(altColorChar, Lang.no_permission));
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
						sender.sendMessage(ChatColor.translateAlternateColorCodes(altColorChar, Lang.you_are_not_operator));
						return false;
					}
					if(!sender.isPermissionSet("pluginmanager.admin")) {
						sender.sendMessage(ChatColor.translateAlternateColorCodes(altColorChar, Lang.no_permission));
						return false;
					}
				}
				sender.sendMessage(ChatColor.AQUA + "Updating plugin...(Downloading from stable build)");
				try {
					PluginUtils.Download(sender, "PluginManager", "http://local4.point.rht0910.tk:8080/job/PluginManager/lastSuccessfulBuild/artifact/target/PluginManager.jar");
				} catch(Exception e) {
					sender.sendMessage(ChatColor.translateAlternateColorCodes(altColorChar, Lang.failed_update_plugin));
					return false;
				}
				final Collection<? extends Player> onplayers = Bukkit.getServer().getOnlinePlayers();
				final Player[] players = (Player[]) onplayers.toArray();
				for(int i=0;i<=players.length;i++) {
					if(players[i].isOp()) {
						players[i].sendMessage(ChatColor.translateAlternateColorCodes(altColorChar, String.format(Lang.success_update_plugin, sender.toString())));
					}
				}
			} else if(args[0].equalsIgnoreCase("usage")) {
				tk.rht0910.plugin_manager.util.Command.getUsageOfCmd(sender, args[1]);
			} else if(args[0].equalsIgnoreCase("config")) {
				try {
					if(args[1] == null) {
						sender.sendMessage(ChatColor.GREEN + " ----- Plugin Manager[" + Lang.version + "] " + Lang.help + " -----");
						sender.sendMessage(ChatColor.RED + " ----- <" + Lang.required + "> [" + Lang.optional + "] - " + Lang.information);
						sender.sendMessage(ChatColor.AQUA + " - /pman help - " + Lang.pman_help_desc);
						sender.sendMessage(ChatColor.AQUA + " - /pman config language <en_US, ja_JP, ...> - " + Lang.pman_config_language);
						sender.sendMessage(ChatColor.AQUA + " - /pman config reload - " + Lang.pman_config_reload);
						sender.sendMessage(ChatColor.translateAlternateColorCodes(altColorChar, String.format(Lang.project_page, "https://dev.bukkit.org/projects/pluginmanagement/")));
						sender.sendMessage(ChatColor.translateAlternateColorCodes(altColorChar, String.format(Lang.developer_version, "http://point.rht0910.tk:8080/job/PluginManager/")));
						sender.sendMessage(ChatColor.translateAlternateColorCodes(altColorChar, String.format(Lang.source_code, "https://github.com/rht0910/PluginManager/")));
						return true;
					}
					if(args[1] == "") {
						sender.sendMessage(ChatColor.GREEN + " ----- Plugin Manager[" + Lang.version + "] " + Lang.help + " -----");
						sender.sendMessage(ChatColor.RED + " ----- <" + Lang.required + "> [" + Lang.optional + "] - " + Lang.information);
						sender.sendMessage(ChatColor.AQUA + " - /pman help - " + Lang.pman_help_desc);
						sender.sendMessage(ChatColor.AQUA + " - /pman config language <en_US, ja_JP, ...> - " + Lang.pman_config_language);
						sender.sendMessage(ChatColor.AQUA + " - /pman config reload - " + Lang.pman_config_reload);
						sender.sendMessage(ChatColor.translateAlternateColorCodes(altColorChar, String.format(Lang.project_page, "https://dev.bukkit.org/projects/pluginmanagement/")));
						sender.sendMessage(ChatColor.translateAlternateColorCodes(altColorChar, String.format(Lang.developer_version, "http://point.rht0910.tk:8080/job/PluginManager/")));
						sender.sendMessage(ChatColor.translateAlternateColorCodes(altColorChar, String.format(Lang.source_code, "https://github.com/rht0910/PluginManager/")));
						return true;
					}
				} catch (Exception | Error e) {
					sender.sendMessage(ChatColor.GREEN + " ----- Plugin Manager[" + Lang.version + "] " + Lang.help + " -----");
					sender.sendMessage(ChatColor.RED + " ----- <" + Lang.required + "> [" + Lang.optional + "] - " + Lang.information);
					sender.sendMessage(ChatColor.AQUA + " - /pman help - " + Lang.pman_help_desc);
					sender.sendMessage(ChatColor.AQUA + " - /pman config language <en_US, ja_JP, ...> - " + Lang.pman_config_language);
					sender.sendMessage(ChatColor.AQUA + " - /pman config reload - " + Lang.pman_config_reload);
					sender.sendMessage(ChatColor.translateAlternateColorCodes(altColorChar, String.format(Lang.project_page, "https://dev.bukkit.org/projects/pluginmanagement/")));
					sender.sendMessage(ChatColor.translateAlternateColorCodes(altColorChar, String.format(Lang.developer_version, "http://point.rht0910.tk:8080/job/PluginManager/")));
					sender.sendMessage(ChatColor.translateAlternateColorCodes(altColorChar, String.format(Lang.source_code, "https://github.com/rht0910/PluginManager/")));
					return true;
				}
				if(args[1].equalsIgnoreCase("language")) {
					this.getConfig().set("language", args[2]);
					this.saveConfig();
					this.reloadConfig();
					sender.sendMessage(ChatColor.translateAlternateColorCodes(altColorChar, String.format(Lang.set_language, args[2])));
					sender.sendMessage(ChatColor.translateAlternateColorCodes(altColorChar, Lang.reloaded_config));
				} else if(args[1].equalsIgnoreCase("reload")) {
					sender.sendMessage(ChatColor.translateAlternateColorCodes(altColorChar, Lang.reloading_config));
					try {
						this.reloadConfig();
					} catch (Exception | Error e) {
						sender.sendMessage(ChatColor.translateAlternateColorCodes(altColorChar, Lang.error_reload_config));
						return false;
					}
					sender.sendMessage(ChatColor.translateAlternateColorCodes(altColorChar, Lang.reloaded_config));
				}
			} else {
				Lang.use();
				sender.sendMessage(ChatColor.RED + Lang.invalid_args);
				// Manager.getCommand().ShowHelp(sender);
				sender.sendMessage(ChatColor.GREEN + " ----- Plugin Manager[" + Lang.version + "] " + Lang.help + " -----");
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
				sender.sendMessage(ChatColor.AQUA + " - /pman config language <en_US, ja_JP, ...> - " + Lang.pman_config_language);
				sender.sendMessage(ChatColor.AQUA + " - /pman config reload - " + Lang.pman_config_reload);
				sender.sendMessage(ChatColor.translateAlternateColorCodes(altColorChar, String.format(Lang.project_page, "https://dev.bukkit.org/projects/pluginmanagement/")));
				sender.sendMessage(ChatColor.translateAlternateColorCodes(altColorChar, String.format(Lang.developer_version, "http://point.rht0910.tk:8080/job/PluginManager/")));
				sender.sendMessage(ChatColor.translateAlternateColorCodes(altColorChar, String.format(Lang.source_code, "https://github.com/rht0910/PluginManager/")));
			}
		}
		} catch(Exception e) {
			Log.error(ChatColor.translateAlternateColorCodes(altColorChar, Lang.error_occured));
			Log.error(ChatColor.translateAlternateColorCodes(altColorChar, String.format(Lang.continue_error_catch, e)));
			e.printStackTrace();
		}
		return true;
	}
}
