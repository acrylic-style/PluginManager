package tk.rht0910.plugin_manager.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;

import com.google.common.io.Files;

import tk.rht0910.plugin_manager.language.Lang;
import tk.rht0910.plugin_manager.thread.AsyncDownload;
import tk.rht0910.tomeito_core.utils.Log;

/**
 *
 * Used by PluginManager class.
 *
 */
public final class PluginUtils {
	private static final char altColorChar = '&';

	public void loadPlugin(CommandSender sender, Plugin plugin) {
		loadPlugin(sender, plugin.getServer().getName());
	}

	/**
	 * Used only as reload plugin.
	 * @param plugin
	 * @return if success returns true, otherize returns false
	 */
	public static boolean enablePluginSilent(String plugin) {
		try {
			Plugin Bplugin = Bukkit.getPluginManager().getPlugin(plugin);
			Bukkit.getPluginManager().enablePlugin(Bplugin);
			return true;
		} catch(Throwable e) {
			Log.severe(Lang.error_occured);
			e.getCause().printStackTrace();
			return false;
		}
	}

	/**
	 * Load a plugin.
	 * @param sender CommandSender(Player, Console) : CommandSender
	 * @param plugin Plugin : String
	 * @param file Plugin file : String
	 */
	public static void loadPlugin(CommandSender sender, String plugin) {
		try {
			Lang.use();
			sender.sendMessage(ChatColor.translateAlternateColorCodes(altColorChar, Lang.starting_load_plugins));
			Bukkit.getServer().getLogger().warning(ChatColor.translateAlternateColorCodes(altColorChar, Lang.starting_load_plugins));
			Log.info("Checking for plugin is enabled...");
			if(!Bukkit.getPluginManager().isPluginEnabled(plugin)) {
				Log.info("The plugin is not enabled, starting load...");
				Plugin loadedornot = Bukkit.getPluginManager().loadPlugin(new File("./plugins/" + plugin + ".jar").getAbsoluteFile());
				Log.info("AbsolutePath: " + new File("./plugins/").getAbsolutePath());
				Log.info("Loading: " + new File("./plugins/" + plugin + ".jar").getAbsolutePath());
				if(loadedornot != null) {
					Bukkit.getPluginManager().enablePlugin(loadedornot);
					sender.sendMessage(ChatColor.translateAlternateColorCodes(altColorChar, Lang.success_load_plugin));
					Log.info("Successfully enabled plugin.");
					return;
				} else {
					Log.info("Cannot load.(File not found)");
				}
				Log.info("Checking for plugins folder...");
				File[] files = new File("./plugins/").getAbsoluteFile().listFiles();
				for(int i = 0; i < files.length; i++) {
					File file = files[i];
					String filename = file.getName();
					if(filename.startsWith(plugin)) {
						try {
							Log.info("Loading: " + file.getAbsolutePath());
							Plugin loaded = Bukkit.getPluginManager().loadPlugin(file.getAbsoluteFile());
							if(loaded != null) {
								Bukkit.getPluginManager().enablePlugin(loaded);
								sender.sendMessage(ChatColor.translateAlternateColorCodes(altColorChar, Lang.success_load_plugin));
								Log.info("Plugin loaded");
								return;
							} else {
								Log.info("Returned null, continue...");
							}
						} catch(Exception e) {
							sender.sendMessage(ChatColor.translateAlternateColorCodes(altColorChar, Lang.error_occured));
							e.printStackTrace();
						}
					}
				}
				Log.info("loop exited!");
				if(Bukkit.getPluginManager().isPluginEnabled(plugin)) {
					Log.warn("But not enabled plugin!");
					sender.sendMessage(ChatColor.translateAlternateColorCodes(altColorChar, Lang.failed_load_plugin));
				}
			} else {
				sender.sendMessage(ChatColor.translateAlternateColorCodes(altColorChar, Lang.already_enabled));
				Log.warn(ChatColor.translateAlternateColorCodes(altColorChar, Lang.already_enabled) + ": " + plugin);
			}
		} catch (Exception e) {
			sender.sendMessage(ChatColor.translateAlternateColorCodes(altColorChar, String.format(Lang.error_occured, e)));
			sender.sendMessage(ChatColor.translateAlternateColorCodes(altColorChar, String.format(Lang.failed_load_plugin, plugin)));
			Bukkit.getServer().getLogger().severe(ChatColor.translateAlternateColorCodes(altColorChar, String.format(Lang.error_occured, e)));
			e.printStackTrace();
			return;
		}
	}

	public boolean unloadPlugin(CommandSender sender, Plugin plugin) {
		return unloadPlugin(sender, plugin.getServer().getName());
	}

	public static boolean unloadPlugin(CommandSender sender, String name) {
		Lang.use();
		try {
			if(Bukkit.getPluginManager().isPluginEnabled(name)) {
				Bukkit.getPluginManager().disablePlugin(Bukkit.getPluginManager().getPlugin(name));
			} else {
				sender.sendMessage(ChatColor.translateAlternateColorCodes(altColorChar, Lang.already_disabled));
			}
		} catch(Exception e) {
			sender.sendMessage(ChatColor.translateAlternateColorCodes(altColorChar, String.format(Lang.please_report_developer_catch, e)));
			Log.error(ChatColor.translateAlternateColorCodes(altColorChar, Lang.please_report_developer));
			e.printStackTrace();
			Log.error("Caused by:");
			e.getCause().printStackTrace();
		}
		return true;
	}

	public static boolean editConfigFile(CommandSender sender, String dir, String cfile, String vine, String strs) {
		Lang.use();
		if(!sender.isOp()) {
			sender.sendMessage(ChatColor.translateAlternateColorCodes(altColorChar, Lang.you_are_not_operator));
			Bukkit.getServer().getLogger().severe("(operator)Tried to access Config editor by " + sender.toString());
			return false;
		}
		if(!sender.hasPermission("pluginmanager.editor")) {
			sender.sendMessage(ChatColor.translateAlternateColorCodes(altColorChar, Lang.no_permission));
			Bukkit.getServer().getLogger().severe("(pluginmanager.editor)Tried to access Config editor by " + sender.toString());
			return false;
		}
		if(!sender.hasPermission("pluginmanager.admin")) {
			sender.sendMessage(ChatColor.translateAlternateColorCodes(altColorChar, Lang.no_permission));
			Bukkit.getServer().getLogger().severe("(pluginmanager.admin)Tried to access Config editor by " + sender.toString());
			return false;
		}
		if(!sender.hasPermission("pluginmanager.super-admin")) {
			sender.sendMessage(ChatColor.translateAlternateColorCodes(altColorChar, Lang.no_permission));
			Bukkit.getServer().getLogger().severe("(pluginmanager.super-admin)Tried to access Config editor by " + sender.toString());
			return false;
		}
		if(!sender.hasPermission("pluginmanager.extra-admin")) {
			sender.sendMessage(ChatColor.translateAlternateColorCodes(altColorChar, Lang.no_permission));
			Bukkit.getServer().getLogger().severe("(pluginmanager.extra-admin)Tried to access Config editor by " + sender.toString());
			return false;
		}
		int line = new Integer(vine);
		File file = null;
		BufferedReader br = null;
		// args[0] = PluginConfigDirectory
		// args[1] = ConfigFileName(Default: config.yml)
		// args[2] = removed(v0.3.1)
		file = new File("plugins/" + dir + "/" + cfile + ".yml");
		if(!file.exists()) {
			file = new File("plugins/" + dir + "/" + cfile);
		}
		if(!file.exists()) {
			sender.sendMessage("Can't find config file. exiting now");
			return false;
		} else if(file.exists()) {
			if(!file.isDirectory()) {
				try {
					Files.copy(file, new File(file + ".bak"));
				} catch (IOException e) {
					sender.sendMessage("Config backup failed. exiting");
					Bukkit.getServer().getLogger().severe("Config backup failed. exiting");
					e.printStackTrace();
					return false;
				}
				try {
					br = new BufferedReader(new FileReader(file));
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
				if(!file.canWrite()) {
					file.setWritable(true);
				}
				if(!file.canRead()) {
					file.setReadable(true);
				}
				if(!file.canExecute()) {
					file.setExecutable(true);
				}
				List<String> list = new ArrayList<String>();
				String str;
				try {
					while((str = br.readLine()) != null){
						list.add(str);
					}
				} catch (IOException e) {
					e.printStackTrace();
				} finally {
					try {
						br.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				list.set(line, strs);
				Object[] arg = list.toArray();
				fileWrite(sender, arg, file);
			} else {
				sender.sendMessage(ChatColor.translateAlternateColorCodes(altColorChar, Lang.selected_directory));
			}
		}
		return true;
	}

	//public boolean ConfigViewer(CommandSender sender, String configDir, String configFile) {
	//	return ConfigViewer(sender, configDir, configFile, null);
	//}

	public static boolean configViewer(CommandSender sender, String configDir, String configFile, String marg) {
		if(configDir == null) {
			return false;
		}
		if(configFile == null) {
			return false;
		}
		String arg1 = configDir;
		String arg2 = configFile;
		String[] args = marg.split(",");
		Integer line_option = null;
		try {
			for(int i=0; i<=args.length; i++) {
				String[] split = args[i].split(":");
				if ("l".equals(split[0])) {
					line_option = Integer.parseInt(split[1]);
				} else {
					sender.sendMessage(ChatColor.translateAlternateColorCodes(altColorChar, Lang.invalid_args));
					return true;
				}
			}
		} catch(NullPointerException | ArrayIndexOutOfBoundsException ignore) {/* - Abort - */} catch (NumberFormatException ex) {
			Bukkit.getServer().getLogger().severe(ChatColor.translateAlternateColorCodes(altColorChar, Lang.error_occured));
			sender.sendMessage(ChatColor.translateAlternateColorCodes(altColorChar, Lang.error_occured));
			ex.printStackTrace();
		}
		BufferedReader br = null;
		File file = null;
		file = new File("plugins/" + arg1 + "/" + arg2 + ".yml");
		if(!file.exists()) {
			file = new File("plugins/" + arg1 + "/" + arg2);
		}
		if(!file.exists()) {
			sender.sendMessage("Can't find config file. exiting now");
			return false;
		} else if(file.exists()) {
			if(!file.isDirectory()) {
				try {
					br = new BufferedReader(new FileReader(file));
				} catch (FileNotFoundException e) {
					e.printStackTrace();
					return false;
				}
				if(!file.canWrite()) {
					file.setWritable(true);
				}
				if(!file.canRead()) {
					file.setReadable(true);
				}
				if(!file.canExecute()) {
					file.setExecutable(true);
				}
				List<String> list = new ArrayList<String>();
				String str;
				try {
					while((str = br.readLine()) != null){
						list.add(str);
					}
				} catch (IOException e) {
					e.printStackTrace();
					return false;
				} finally {
					try {
						br.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				if(line_option != null ) {
					sender.sendMessage(list.toArray()[line_option].toString());
				}
				Object[] arg = list.toArray();
				for(int i=0;i<=arg.length;i++) {
					sender.sendMessage("[" + i + "] " + arg[i]);
				}
				//Object[] arg = list.toArray();
				return true;
			} else {
				sender.sendMessage(ChatColor.RED + "Selected File is Directory, cannot continue.");
				return false;
			}
		}
		return true;
	}

	public static void fileWrite(CommandSender sender, Object[] args, File file) {
		FileWriter fw = null;
		try {
			fw = new FileWriter(file);
		} catch (IOException e) {
			Bukkit.getServer().getLogger().severe(ChatColor.translateAlternateColorCodes(altColorChar, Lang.error_occured));
			sender.sendMessage(ChatColor.translateAlternateColorCodes(altColorChar, Lang.error_occured));
			e.printStackTrace();
		}
		try {
			for(int i=0;i<=args.length;i++) {
				fw.write((String)args[i] + "\r\n");
			}
		} catch(Exception e) {
			Bukkit.getServer().getLogger().severe("Cannot Edit config: [Exception] by " + sender.toString());
			sender.sendMessage(ChatColor.RED + "Error!: This error is generated by catch");
			e.printStackTrace();
		} finally {
			try {
				fw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	/*     */    public void reload(CommandSender sender, Plugin plugin) {
		/* 375 */       if(plugin != null) {
			Bukkit.getServer().getLogger().info("Reloading plugin: " + plugin.toString());
		/* 376 */          unloadPlugin(sender, plugin);
		/* 377 */          loadPlugin(sender, plugin);
		Bukkit.getServer().getLogger().info("Reloaded plugin: " + plugin.toString());
		/*     */       }
		/* 379 */    }

	public static void reloadPlugin(CommandSender sender, String plugin) {
		if(plugin != null) {
			try {
				sender.sendMessage(ChatColor.translateAlternateColorCodes(altColorChar, String.format(Lang.reloading_plugin, plugin)));
				unloadPlugin(sender, plugin);
				if(enablePluginSilent(plugin)) {
					sender.sendMessage(ChatColor.translateAlternateColorCodes(altColorChar, String.format(Lang.reloaded_plugin, plugin)));
				} else {
					sender.sendMessage(ChatColor.translateAlternateColorCodes(altColorChar, String.format(Lang.reloading_plugin_error, plugin)));
				}
			} catch(Throwable e) {
				sender.sendMessage(ChatColor.translateAlternateColorCodes(altColorChar, String.format(Lang.error_occured, e)));
				e.printStackTrace();
			}
		}
	}

	public static void deletePlugin(CommandSender sender, String filename, String pluginName) {
		File file = new File("plugins/" + filename + ".jar");
		if(file.exists()) {
			if(Bukkit.getServer().getPluginManager().isPluginEnabled(Bukkit.getServer().getPluginManager().getPlugin(pluginName))) {
				Bukkit.getServer().getPluginManager().disablePlugin(Bukkit.getServer().getPluginManager().getPlugin(pluginName));
			}
			if(!file.isDirectory() && file.canRead()) {
					File dir = new File("plugins/plugins_backup/");
					if(!dir.exists() && !dir.mkdirs()) {
							Bukkit.getServer().getPluginManager().enablePlugin(Bukkit.getServer().getPluginManager().getPlugin(pluginName));
							return;
					}
					if(!dir.canWrite() && !dir.setWritable(true)) {
							Bukkit.getServer().getPluginManager().enablePlugin(Bukkit.getServer().getPluginManager().getPlugin(pluginName));
							sender.sendMessage("File is not writable or don't have permission.");
							return;
					}
					File to = new File("plugins/plugins_backup/" + pluginName + ".jar");
					try {
						Files.move(file, to);
						sender.sendMessage("Successfully Remove plugin by " + sender.toString());
						sender.sendMessage("Successfully Remove plugin. To restore plugin, Please enter command: '/pman restore " + pluginName + "'");
					} catch (IOException e) {
						Bukkit.getServer().getPluginManager().enablePlugin(Bukkit.getServer().getPluginManager().getPlugin(pluginName));
						sender.sendMessage("Unexpected error occurred.");
						e.printStackTrace();
					}
			} else {
				Bukkit.getServer().getPluginManager().enablePlugin(Bukkit.getServer().getPluginManager().getPlugin(pluginName));
				sender.sendMessage("Selected file is directory or cannot readable.");
				return;
			}
		} else {
			sender.sendMessage(ChatColor.RED + "Selected file is not exist!");
			return;
		}
	}

	public static void restorePlugin(CommandSender sender, String pluginName) {
		File from = new File("plugins/plugins_backup/" + pluginName + ".jar");
		File to = new File("plugins/" + pluginName + ".jar");
		try {
			Files.move(from, to);
			sender.sendMessage(ChatColor.GREEN + "Successfully Restore plugin by " + sender.toString());
			sender.sendMessage(ChatColor.GREEN + "Successfully Restore plugin. To Load and enable: /pman load " + pluginName);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void download(CommandSender sender, String file, String url) {
		AsyncDownload async_thread = new AsyncDownload(sender, file, url);
		async_thread.start();
	}

	public static void getUsageOfCmd(CommandSender sender, String cmd) {
		try {
			String usageof = Bukkit.getPluginCommand(cmd.replaceFirst("/", "")).getUsage();
			String cmd2 = cmd.replaceFirst("/", "");
			String usage = usageof.replaceFirst("/<command>", "/" + cmd2);
			sender.sendMessage(usage);
		} catch(Exception e) {
			sender.sendMessage(ChatColor.RED + "Can't show usage of command.(Command not found)");
			e.printStackTrace();
		}
	}
}