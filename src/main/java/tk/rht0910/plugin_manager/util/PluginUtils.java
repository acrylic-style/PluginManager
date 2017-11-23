package tk.rht0910.plugin_manager.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.SimpleCommandMap;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.UnknownDependencyException;

import com.google.common.io.Files;

import tk.rht0910.plugin_manager.language.Lang;
import tk.rht0910.plugin_manager.thread.AsyncDownload;
import tk.rht0910.tomeito_core.utils.Log;

public final class PluginUtils {
	private static final char altColorChar = '&';

	public void loadPlugin(CommandSender sender, Plugin plugin, String file) {
		loadPlugin(sender, plugin.getServer().getName(), file);
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
	 * Load a plugin as asynchronous.
	 * @param sender CommandSender(Player, Console) : CommandSender
	 * @param plugin Plugin : String
	 * @param file Plugin file : String
	 */
	public static void loadPlugin(CommandSender sender, String plugin, String file) {
		try {
			Lang.use();
			sender.sendMessage(ChatColor.translateAlternateColorCodes(altColorChar, Lang.starting_load_plugins));
			Bukkit.getServer().getLogger().warning(ChatColor.translateAlternateColorCodes(altColorChar, Lang.starting_load_plugins));
			File[] objFiles = (new File("plugins/")).listFiles();
			if ( objFiles != null ) {
				int trying = 0;
				for(int i=0; i< objFiles.length; i++ ) {
					if(trying == 0) {
						trying = 1;
					} else if(trying == 1) {
						return;
					}
					File file2 = //objFiles[i];
							new File("plugins/" + file);
					//String file_str = plugin;
					//Pattern p = Pattern.compile(".");
					//Matcher m = p.matcher(file_str);
					//if(m.find()) {
						int is = 0;
						try {
							if(!Bukkit.getServer().getPluginManager().isPluginEnabled(Bukkit.getServer().getPluginManager().getPlugin(plugin))) {
								if(!Bukkit.getServer().getPluginManager().isPluginEnabled(plugin)) {
									try {
										Bukkit.getServer().getPluginManager().loadPlugin(file2);
									} catch(Exception | Error e) {
										Log.error("Error while loading plugin: " + plugin + "(" + file2 + "), errors dumped below:");
										e.printStackTrace();
									}

									try {
										Bukkit.getServer().getPluginManager().loadPlugin(new File(file2 + ".jar"));
									} catch(Exception | Error e) {
										Log.error("Error while loading plugin: " + plugin + "(" + file2 + ".jar), errors dumped below:");
										e.printStackTrace();
									}
									Plugin pm = Bukkit.getServer().getPluginManager().getPlugin(plugin);
									Bukkit.getServer().getPluginManager().enablePlugin(pm);
								}
							} else {
								sender.sendMessage(ChatColor.translateAlternateColorCodes(altColorChar, Lang.already_enabled));
							}
						} catch(NullPointerException e) {
							e.printStackTrace();
							is = 1;
						} catch(UnknownDependencyException e) {
							e.printStackTrace();
							is = 1;
						} catch(Exception e) {
							if(is == 0) {
								sender.sendMessage(ChatColor.translateAlternateColorCodes(altColorChar, String.format(Lang.error_occured, e)));
							}
							e.printStackTrace();
						} finally {
							if(is == 1) {
								sender.sendMessage(ChatColor.translateAlternateColorCodes(altColorChar, String.format(Lang.failed_load_plugin, plugin)));
								Bukkit.getServer().getLogger().severe("Couldn't load plugin: Args[0]: \"" + plugin + "\", Args[1]: \"not defined\" Player: \"" + sender.toString() + "\"(IP:" + sender.getServer().getIp() + ")");
							} else {
								sender.sendMessage(ChatColor.translateAlternateColorCodes(altColorChar, String.format(Lang.success_load_plugin, plugin)));
								Bukkit.getServer().getLogger().info("Loaded plugin: Args[0]: \"" + plugin + "\", Args[1]: \"not defined\" Player: \"" + sender.toString() + "\"(IP:" + sender.getServer().getIp() + ")");
							}
						}
					}
				}
			//} else {
				//sender.sendMessage(ChatColor.translateAlternateColorCodes(altColorChar, Lang.please_report_developer));
				//Bukkit.getServer().getLogger().severe(ChatColor.translateAlternateColorCodes(altColorChar, String.format(Lang.please_report_developer_catch, "objFiles not found")));
			//}
		} catch (Exception e) {
			sender.sendMessage(ChatColor.translateAlternateColorCodes(altColorChar, String.format(Lang.error_occured, e)));
			sender.sendMessage(ChatColor.translateAlternateColorCodes(altColorChar, String.format(Lang.failed_load_plugin, plugin)));
			Bukkit.getServer().getLogger().severe(ChatColor.translateAlternateColorCodes(altColorChar, String.format(Lang.error_occured, e)));
			e.printStackTrace();
			return;
		}
		return;
		//Thread lp = new Thread(new LoadPlugin(sender, plugin, file));
		//lp.start();
	}

	public boolean unloadPlugin(CommandSender sender, Plugin plugin) {
		return unloadPlugin(sender, plugin.getServer().getName());
	}

	@SuppressWarnings({ "rawtypes", "unused" })
	public static boolean unloadPlugin(CommandSender sender, String name) {
		Lang.use();
		/* Thank you for PlugMan Developers */
		/* 399 */       Plugin plugin = Bukkit.getServer().getPluginManager().getPlugin(name);
		/*     */
		/* 401 */       PluginManager pluginManager = Bukkit.getServer().getPluginManager();
		/*     */
		/* 403 */       SimpleCommandMap commandMap = null;
		/*     */
		/* 405 */       List plugins = null;
		/*     */
		/* 407 */       Map names = null;
		/* 408 */       Map commands = null;
		/* 409 */       Map listeners = null;
		/* 411 */       boolean reloadlisteners = true;
		/*     */
		/* 413 */       if(pluginManager != null) {
		/*     */
		/* 415 */          pluginManager.disablePlugin(plugin);
		/*     */
		/*     */
		/*     */          try {
		/* 419 */             Field cl = Bukkit.getServer().getPluginManager().getClass().getDeclaredField("plugins");
		/* 420 */             cl.setAccessible(true);
		/* 421 */             plugins = (List)cl.get(pluginManager);
		/*     */
		/* 423 */             Field ex = Bukkit.getServer().getPluginManager().getClass().getDeclaredField("lookupNames");
		/* 424 */             ex.setAccessible(true);
		/* 425 */             names = (Map)ex.get(pluginManager);
		/*     */             Field c;
		/*     */             try {
		/* 428 */                c = Bukkit.getServer().getPluginManager().getClass().getDeclaredField("listeners");
		/* 429 */                c.setAccessible(true);
		/* 430 */                listeners = (Map)c.get(pluginManager);
		/* 431 */             } catch (Exception arg13) {
		/* 432 */                reloadlisteners = false;
		/*     */             }
		/*     */
		/* 435 */             c = Bukkit.getServer().getPluginManager().getClass().getDeclaredField("commandMap");
		/* 436 */             c.setAccessible(true);
		/* 437 */             commandMap = (SimpleCommandMap)c.get(pluginManager);
		/*     */
		/* 439 */             Field value = SimpleCommandMap.class.getDeclaredField("knownCommands");
		/* 440 */             value.setAccessible(true);
		/* 441 */             commands = (Map)value.get(commandMap);
		commands.remove((Map) value.get(commandMap));
		/*     */
		/* 443 */          } catch (Exception e) {
			sender.sendMessage(ChatColor.translateAlternateColorCodes(altColorChar, Lang.error_occured));
			e.printStackTrace();
			return false;
		} finally {
			sender.sendMessage(ChatColor.translateAlternateColorCodes(altColorChar, String.format(Lang.unloaded_plugin, name)));
		}
		}
		return true;
	}

	public static boolean EditConfigFile(CommandSender sender, String dir, String cfile, String vine, String strs) {
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
				FileWrite(sender, arg, file);
			} else {
				sender.sendMessage(ChatColor.RED + "Selected File is Directory, cannot continue.");
			}
		}
		return true;
	}

	//public boolean ConfigViewer(CommandSender sender, String configDir, String configFile) {
	//	return ConfigViewer(sender, configDir, configFile, null);
	//}

	public static boolean ConfigViewer(CommandSender sender, String configDir, String configFile, String marg) {
		if(configDir == null) {
			Manager.getCommand().showHelp(sender);
		}
		if(configFile == null) {
			Manager.getCommand().showHelp(sender);
		}
		String arg1 = configDir;
		String arg2 = configFile;
		String[] args = marg.split(",");
		Integer line_option = null;
		try {
		for(int i=0; i<=args.length; i++) {
			if(args[i].contains("l:")) { // l : line
				// String line_debug = args[i].replaceAll("l:", "");
				Log.debug(args[i].replaceAll("l:", ""));
				line_option = new Integer(args[i].replaceAll("l:", ""));
			} else {
				sender.sendMessage(ChatColor.translateAlternateColorCodes(altColorChar, String.format(Lang.unknown_args, args[i])));
			}
		}
		} catch(NullPointerException | ArrayIndexOutOfBoundsException ignoore) {/* - Abort - */}
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
				if(line_option != null ) {
					try {
						for(int i=0; i<=list.size(); i++) {
							if(i != line_option) {
								list.remove(i);
							} else {
								list.set(0, (String) list.toArray()[line_option]);
								continue;
							}
						}
					} catch(Exception | Error ignored) {
					} finally {
						try {
							br.close();
						} catch(IOException e) {
							e.printStackTrace();
						}
					}
				} else {
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
							return false;
						}
					}
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

	public static void FileWrite(CommandSender sender, Object[] args, File file) {
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
		/* 377 */          loadPlugin(sender, plugin, plugin.toString());
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

	public static void DeletePlugin(CommandSender sender, String filename, String pluginName) {
		File file = new File("plugins/" + filename + ".jar");
		if(file.exists()) {
			if(Bukkit.getServer().getPluginManager().isPluginEnabled(Bukkit.getServer().getPluginManager().getPlugin(pluginName))) {
				Bukkit.getServer().getPluginManager().disablePlugin(Bukkit.getServer().getPluginManager().getPlugin(pluginName));
			}
			if(!file.isDirectory()) {
				if(file.canRead()) {
					File dir = new File("plugins/plugins_backup/");
					if(!dir.exists()) {
						if(!dir.mkdirs()) {
							Bukkit.getServer().getPluginManager().enablePlugin(Bukkit.getServer().getPluginManager().getPlugin(pluginName));
							return;
						}
					}
					if(!dir.canWrite()) {
						if(!dir.setWritable(true, false)) {
							Bukkit.getServer().getPluginManager().enablePlugin(Bukkit.getServer().getPluginManager().getPlugin(pluginName));
							sender.sendMessage("File is not writable.");
							return;
						}
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
					sender.sendMessage("File is not readable.");
					return;
				}
			} else {
				Bukkit.getServer().getPluginManager().enablePlugin(Bukkit.getServer().getPluginManager().getPlugin(pluginName));
				sender.sendMessage("Selected file is DIRECTORY.");
				return;
			}
		} else {
			sender.sendMessage(ChatColor.RED + "Selected file is not exists!");
			return;
		}
	}

	public static void RestorePlugin(CommandSender sender, String pluginName) {
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

	public static void Download(CommandSender sender, String file, String url) {
		AsyncDownload async_thread = new AsyncDownload(sender, file, url);
		async_thread.start();
	}
}
// Thank you for PlugMan developers.