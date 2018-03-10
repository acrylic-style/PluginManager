package tk.rht0910.plugin_manager;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

import tk.rht0910.plugin_manager.exception.CatchException;
import tk.rht0910.plugin_manager.language.Lang;
import tk.rht0910.plugin_manager.thread.VersionCheck;
import tk.rht0910.plugin_manager.util.Manager;
import tk.rht0910.plugin_manager.util.PluginUtils;
import tk.rht0910.tomeito_core.utils.Log;

/**
 *
 * The Perfect Plugin Manager
 *
 */
public final class PluginManager extends JavaPlugin implements TabCompleter, Listener {
	//private static final String[] COMMANDS = {""};
	public char altColorChar = '&';
	public static Boolean is_available_new_version = false;
	public static String current = "";
	public static String newv = "";
	public static Boolean warning = false;

	public static String getLanguageCode() {
		String getty = PluginManager.getPlugin(PluginManager.class).getConfig().getString("language");
		if(getty == "" || getty == null || !getty.contains("_")) {
			getty = Locale.getDefault().toString();
			warning = true;
		}
		return getty;
	}

	@Override
	public void onEnable() {
		try {
			PluginManager.this.getConfig().options().copyDefaults(true);
			PluginManager.this.saveConfig();
			CatchException catchException = new CatchException();
				Thread thread = new Thread(new VersionCheck(null, null, "https://api.rht0910.tk/pluginmanager_version"), "");
				thread.setUncaughtExceptionHandler(catchException);
				thread.start();
				getServer().getPluginManager().registerEvents(this, this);
			Lang.initialize();
			current = Lang.version;
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
			Bukkit.getServer().getLogger().info("[PluginManager] Loaded PluginManager v1.4.1");
		} catch(Exception e) {
			Bukkit.getServer().getLogger().info("[PluginManager] Got Unknown error: " + e);
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

	@Override
	public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
		if(!command.getName().equalsIgnoreCase("pman")) return super.onTabComplete(sender, command, alias, args);
		if(args.length == 1) {
			if(args[0].length() == 0) { // Until /pman
				// /pman <TAB>
				return Arrays.asList("load", "unload", "reload", "help", "disable", "download", "delete", "restore", "update", "update-dev", "editor",
						"viewer", "usage", "check", "config", "check-dev");
			/*} else if(args[0].length() == 1) {
				return Arrays.asList("af_ZA", "ar_SA", "ca_ES", "cs_CZ", "da_DK", "de_DE", "el_GR", "en_US", "es_ES", "fi_FI", "fr_FR",
						"he_IL", "hu_HU", "it_IT", "ja_JP", "ko_KR", "nl_NL", "no_NO", "pl_PL", "pt_BR", "pt_PT", "ro_RO", "ru_RU", "sr_SP",
						"sv_SE", "tr_TR", "uk_UA", "vi_VN", "zh_CN", "zh_TW");
			*/} else {
				// Correct first input string

				// /pman <HERE>
				// Example: /pman l <TAB> -> /pman load (Auto tab completed)
				if ("load".startsWith(args[0])) {
					return Collections.singletonList("load");
				} else if ("unload".startsWith(args[0])) {
					return Collections.singletonList("unload");
				} else if("help".startsWith(args[0])) {
					return Collections.singletonList("help");
				} else if("reload".startsWith(args[0])) {
					return Collections.singletonList("reload");
				} else if("disable".startsWith(args[0])) {
					return Collections.singletonList("disable");
				} else if("download".startsWith(args[0])) {
					return Collections.singletonList("download");
				} else if("delete".startsWith(args[0])) {
					return Collections.singletonList("delete");
				} else if("restore".startsWith(args[0])) {
					return Collections.singletonList("restore");
				} else if("update".startsWith(args[0])) {
					return Collections.singletonList("update");
				} else if("update-dev".startsWith(args[0])) {
					return Collections.singletonList("update-dev");
				} else if("editor".startsWith(args[0])) {
					return Collections.singletonList("editor");
				} else if("viewer".startsWith(args[0])) {
					return Collections.singletonList("viewer");
				} else if("usage".startsWith(args[0])) {
					return Collections.singletonList("usage");
				} else if("check".startsWith(args[0])) {
					return Collections.singletonList("check");
				} else if("check-dev".startsWith(args[0])) {
					return Collections.singletonList("check-dev");
				} else if("config".startsWith(args[0])) {
					return Collections.singletonList("config");
				}
			}
		} else if(args.length == 2) {
			if(args[0] == "config") {
				if(args[1] == "language") {
					if(args[2].length() == 0) {
						return Arrays.asList("af_ZA", "ar_SA", "ca_ES", "cs_CZ", "da_DK", "de_DE", "el_GR", "en_US", "es_ES", "fi_FI", "fr_FR",
								"he_IL", "hu_HU", "it_IT", "ja_JP", "ko_KR", "nl_NL", "no_NO", "pl_PL", "pt_BR", "pt_PT", "ro_RO", "ru_RU", "sr_SP",
								"sv_SE", "tr_TR", "uk_UA", "vi_VN", "zh_CN", "zh_TW");
					} else {
						if("af_ZA".startsWith(args[2])) {
							return Collections.singletonList("af_ZA");
						} else if("ar_SA".startsWith(args[2])) {
							return Collections.singletonList("ar_SA");
						} else if("ca_ES".startsWith(args[2])) {
							return Collections.singletonList("ca_ES");
						} else if("cs_CZ".startsWith(args[2])) {
							return Collections.singletonList("cs_CZ");
						} else if("da_DK".startsWith(args[2])) {
							return Collections.singletonList("da_DK");
						} else if("de_DE".startsWith(args[2])) {
							return Collections.singletonList("de_DE");
						} else if("el_GR".startsWith(args[2])) {
							return Collections.singletonList("el_GR");
						} else if("en_US".startsWith(args[2])) {
							return Collections.singletonList("en_US");
						} else if("es_ES".startsWith(args[2])) {
							return Collections.singletonList("fi_FI");
						} else if("fr_FR".startsWith(args[2])) {
							return Collections.singletonList("fr_FR");
						} else if("hr_IL".startsWith(args[2])) {
							return Collections.singletonList("hu_HU");
						} else if("it_IT".startsWith(args[2])) {
							return Collections.singletonList("it_IT");
						} else if("ja_JP".startsWith(args[2])) {
							return Collections.singletonList("ja_JP");
						} else if("ko_KR".startsWith(args[2])) {
							return Collections.singletonList("ko_KR");
						} else if("nl_NL".startsWith(args[2])) {
							return Collections.singletonList("nl_NL");
						} else if("no_NO".startsWith(args[2])) {
							return Collections.singletonList("no_NO");
						} else if("pl_PL".startsWith(args[2])) {
							return Collections.singletonList("pl_PL");
						} else if("pt_BR".startsWith(args[2])) {
							return Collections.singletonList("pt_BR");
						} else if("pt_PT".startsWith(args[2])) {
							return Collections.singletonList("pt_PT");
						} else if("ro_RO".startsWith(args[2])) {
							return Collections.singletonList("ro_RO");
						} else if("ru_RU".startsWith(args[2])) {
							return Collections.singletonList("ru_RU");
						} else if("sr_SP".startsWith(args[2])) {
							return Collections.singletonList("sr_SP");
						} else if("sv_SE".startsWith(args[2])) {
							return Collections.singletonList("sv_SE");
						} else if("tr_TR".startsWith(args[2])) {
							return Collections.singletonList("tr_TR");
						} else if("uk_UA".startsWith(args[2])) {
							return Collections.singletonList("vi_VN");
						} else if("zh_CN".startsWith(args[2])) {
							return Collections.singletonList("zh_CN");
						} else if("zh_TW".startsWith(args[2])) {
							return Collections.singletonList("zh_TW");
						}
					}
				}
				if(args[1].length() == 0) {
					return Arrays.asList("reload", "language");
				}
				if("reload".startsWith(args[1])) {
					return Collections.singletonList("reload");
				} else if("language".startsWith(args[1])) {
					return Collections.singletonList("language");
				}
			}
		}
		// Calling JavaPlugin#onTabComplete()
		return super.onTabComplete(sender, command, alias, args);
	}

	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		try {
			Lang.use(); // Initialize variables
		if(command.getName().equalsIgnoreCase("pman")) {
			if(sender instanceof Player) {
				if(!sender.isOp()) {
					sender.sendMessage(ChatColor.translateAlternateColorCodes(altColorChar, Lang.you_are_not_operator));
					return false;
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
					sender.sendMessage(ChatColor.AQUA + " - /pman load <Plugin name> <PluginFile> - " + Lang.pman_load_desc);
					sender.sendMessage(ChatColor.AQUA + " - /pman unload(or /pman disable) <Plugin name> - " + Lang.pman_unload_desc);
					sender.sendMessage(ChatColor.AQUA + " - /pman reload <Plugin> - " + Lang.pman_reload_desc);
					sender.sendMessage(ChatColor.AQUA + " - /pman download <FileName> <URL> - " + Lang.pman_download_desc);
					sender.sendMessage(ChatColor.AQUA + " - /pman delete <PluginFileName> <PluginName(or Backup file name)> - " + Lang.pman_delete_desc);
					sender.sendMessage(ChatColor.AQUA + " - /pman restore <FileName> - " + Lang.pman_restore_desc);
					sender.sendMessage(ChatColor.AQUA + " - /pman editor <Dir> <File> <Line(Count from 0)> <value> - " + Lang.pman_editor_desc);
					sender.sendMessage(ChatColor.AQUA + " - /pman viewer <Dir> <File> [options] - " + Lang.pman_viewer_desc);
					sender.sendMessage(ChatColor.AQUA + " - /pman update - " + Lang.pman_update_desc);
					sender.sendMessage(ChatColor.AQUA + " - /pman update-dev - Update to UNSTABLE and DEVELOPER version.");
					sender.sendMessage(ChatColor.AQUA + " - /pman usage <Command> - " + Lang.pman_usage_desc);
					sender.sendMessage(ChatColor.AQUA + " - /pman config language <en_US, ja_JP, ...> - " + Lang.pman_config_language);
					sender.sendMessage(ChatColor.AQUA + " - /pman config reload - " + Lang.pman_config_reload);
					sender.sendMessage(ChatColor.AQUA + " - /pman check - " + Lang.pman_check_desc);
					sender.sendMessage(ChatColor.AQUA + " - /pman check-dev - " + Lang.pman_check_desc + ChatColor.RED + "(dev)");
					sender.sendMessage(ChatColor.translateAlternateColorCodes(altColorChar, String.format(Lang.project_page, "https://dev.bukkit.org/projects/pluginmanagement/")));
					sender.sendMessage(ChatColor.translateAlternateColorCodes(altColorChar, String.format(Lang.developer_version, "http://ci.rht0910.tk/job/PluginManager/")));
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
				if(args.length == 0) {
					sender.sendMessage(ChatColor.translateAlternateColorCodes(altColorChar, Lang.not_enough_args));
					return false;
				} else if(args.length == 1) {
					PluginUtils.loadPlugin(sender, args[0]);
				} else if(args.length > 2) {
					PluginUtils.loadPlugin(sender, args[0]);
					sender.sendMessage(ChatColor.YELLOW + "Note: Two arguments are not supported. Use /pman load <Plugin>");
				}
			} else if(args[0].equalsIgnoreCase("disable")) {
				if(sender instanceof Player) {
					if(!sender.isPermissionSet("pluginmanager.admin")) {
						Bukkit.getServer().getLogger().severe("No permission: /pman disable : " + sender.toString());
						sender.sendMessage(ChatColor.translateAlternateColorCodes(altColorChar, Lang.no_permission));
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
			} else if(args[0].equalsIgnoreCase("reload")) {
				if(sender instanceof Player) {
					if(!sender.isPermissionSet("pluginmanager.admin")) {
						Bukkit.getServer().getLogger().severe("No permission: /pman reload : " + sender.toString());
						sender.sendMessage(ChatColor.translateAlternateColorCodes(altColorChar, Lang.no_permission));
						return false;
					}
				}
				PluginUtils.reloadPlugin(sender, args[1]);
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
				if(args.length < 2) {
					sender.sendMessage(ChatColor.translateAlternateColorCodes(altColorChar, Lang.not_enough_args));
					return false;
				}
				PluginUtils.RestorePlugin(sender, args[1]);
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
				if(args.length < 3) {
					sender.sendMessage(ChatColor.translateAlternateColorCodes(altColorChar, Lang.not_enough_args));
					return false;
				}
				PluginUtils.DeletePlugin(sender, args[1], args[2]);
			} else if(args[0].equalsIgnoreCase("viewer")) {
				Bukkit.getServer().getLogger().warning(ChatColor.translateAlternateColorCodes(altColorChar, String.format(Lang.opened_config_viewer, sender.toString())));
				try {
					if(args.length < 3 || args.length <= 2) {
						PluginUtils.ConfigViewer(sender, args[1], args[2], "");
					} else {
						PluginUtils.ConfigViewer(sender, args[1], args[2], args[3]);
					}
				} catch(Exception | Error e) {
					// PluginUtils.ConfigViewer(sender, args[1], args[2], "");
					Log.severe(Lang.error_occured);
					e.printStackTrace();
					e.getCause().printStackTrace();
				}
			} else if(args[0].equalsIgnoreCase("editor")) {
				if(args.length < 5) {
					sender.sendMessage(ChatColor.translateAlternateColorCodes(altColorChar, Lang.not_enough_args));
					return false;
				}
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
					PluginUtils.Download(sender, "PluginManager", "https://ci.rht0910.tk/job/PluginManager/lastSuccessfulBuild/artifact/target/PluginManager.jar");
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
				sender.sendMessage(ChatColor.translateAlternateColorCodes(altColorChar, Lang.updating_plugin));
				try {
					PluginUtils.Download(sender, "PluginManager", "https://ci.rht0910.tk/job/PluginManager-dev/lastSuccessfulBuild/artifact/target/PluginManager.jar");
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
				tk.rht0910.plugin_manager.command.Command.getUsageOfCmd(sender, args[1]);
			} else if(args[0].equalsIgnoreCase("check")) {
				VersionCheck vc = new VersionCheck(true, sender, "https://api.rht0910.tk/pluginmanager_version", "");
				vc.start();
			} else if(args[0].equalsIgnoreCase("check-dev")) {
				VersionCheck vcd = new VersionCheck(true, sender, "https://api.rht0910.tk/pluginmanager_dev_version", "(dev)");
				vcd.start();
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
						sender.sendMessage(ChatColor.translateAlternateColorCodes(altColorChar, String.format(Lang.problem_case, "https://github.com/rht0910/PluginManager/issues/")));
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
						sender.sendMessage(ChatColor.translateAlternateColorCodes(altColorChar, String.format(Lang.problem_case, "https://github.com/rht0910/PluginManager/issues/")));
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
					sender.sendMessage(ChatColor.translateAlternateColorCodes(altColorChar, String.format(Lang.problem_case, "https://github.com/rht0910/PluginManager/issues/")));
					return true;
				}
				if(args[1].equalsIgnoreCase("language")) {
					try {
						this.getConfig().set("language", args[2]);
					} catch(Exception | Error e) {
						sender.sendMessage(ChatColor.GREEN + " ----- Plugin Manager[" + Lang.version + "] " + Lang.help + " -----");
						sender.sendMessage(ChatColor.RED + " ----- <" + Lang.required + "> [" + Lang.optional + "] - " + Lang.information);
						sender.sendMessage(ChatColor.AQUA + " - /pman help - " + Lang.pman_help_desc);
						sender.sendMessage(ChatColor.AQUA + " - /pman config language <en_US, ja_JP, ...> - " + Lang.pman_config_language);
						sender.sendMessage(ChatColor.AQUA + " - /pman config reload - " + Lang.pman_config_reload);
						sender.sendMessage(ChatColor.translateAlternateColorCodes(altColorChar, String.format(Lang.project_page, "https://dev.bukkit.org/projects/pluginmanagement/")));
						sender.sendMessage(ChatColor.translateAlternateColorCodes(altColorChar, String.format(Lang.developer_version, "http://point.rht0910.tk:8080/job/PluginManager/")));
						sender.sendMessage(ChatColor.translateAlternateColorCodes(altColorChar, String.format(Lang.source_code, "https://github.com/rht0910/PluginManager/")));
						sender.sendMessage(ChatColor.translateAlternateColorCodes(altColorChar, String.format(Lang.problem_case, "https://github.com/rht0910/PluginManager/issues/")));
						Log.warn(Lang.error_occured);

						e.printStackTrace();
						return true;
					}
					this.saveConfig();
					this.reloadConfig();
					warning = false;
					Lang.use();

					sender.sendMessage(ChatColor.translateAlternateColorCodes(altColorChar, String.format(Lang.set_language, args[2])));
					sender.sendMessage(ChatColor.translateAlternateColorCodes(altColorChar, Lang.reloaded_config));
				} else if(args[1].equalsIgnoreCase("reload")) {
					sender.sendMessage(ChatColor.translateAlternateColorCodes(altColorChar, Lang.reloading_config));
					try {
						this.reloadConfig();
						warning = false;
						Lang.use();
					} catch (Exception | Error e) {
						sender.sendMessage(ChatColor.translateAlternateColorCodes(altColorChar, Lang.error_reload_config));
						return false;
					}
					sender.sendMessage(ChatColor.translateAlternateColorCodes(altColorChar, Lang.reloaded_config));
				}
			} else if(args[0].equalsIgnoreCase("sun")) {
				World world = Bukkit.getWorld(Bukkit.getPlayer(sender.getName()).getWorld().getName());
				world.setStorm(false);
				world.setThundering(false);
				world.setWeatherDuration(Integer.MAX_VALUE);
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
				sender.sendMessage(ChatColor.AQUA + " - /pman check - " + Lang.pman_check_desc);
				sender.sendMessage(ChatColor.translateAlternateColorCodes(altColorChar, String.format(Lang.project_page, "https://dev.bukkit.org/projects/pluginmanagement/")));
				sender.sendMessage(ChatColor.translateAlternateColorCodes(altColorChar, String.format(Lang.developer_version, "http://point.rht0910.tk:8080/job/PluginManager/")));
				sender.sendMessage(ChatColor.translateAlternateColorCodes(altColorChar, String.format(Lang.source_code, "https://github.com/rht0910/PluginManager/")));
				sender.sendMessage(ChatColor.translateAlternateColorCodes(altColorChar, String.format(Lang.problem_case, "https://github.com/rht0910/PluginManager/issues/")));
			}
			if(warning == true) {
				sender.sendMessage(ChatColor.translateAlternateColorCodes(altColorChar, Lang.warning_lang_invalid));
			}
		}
		} catch(Throwable e) {
			Log.error(ChatColor.translateAlternateColorCodes(altColorChar, Lang.error_occured));
			Log.error(ChatColor.translateAlternateColorCodes(altColorChar, String.format(Lang.continue_error_catch, e)));
			e.printStackTrace();
		}
		return true;
	}

	@SuppressWarnings("static-access")
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerJoin(PlayerJoinEvent event) {
		Log.info("Event attached to " + event.getPlayer().getName() + ", EventPriority: HIGHEST, EventHandler: On");
		if(event.getPlayer().isOp() == true) {
			Log.info(event.getPlayer().getName() + " is OP!");
			if(this.is_available_new_version == true) {
				Log.info("New version found, notifying...");
				String new_version_available3 = ChatColor.translateAlternateColorCodes(altColorChar, String.format(Lang.new_version_available, this.current, this.newv));
				String new_version_available4 = ChatColor.translateAlternateColorCodes(altColorChar, Lang.new_version_available2);
				event.getPlayer().sendMessage(new_version_available3);
				event.getPlayer().sendMessage(new_version_available4);
			} else {

			}
		}
	}
}
