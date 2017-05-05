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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.SimpleCommandMap;
import org.bukkit.plugin.InvalidPluginException;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.UnknownDependencyException;

import com.google.common.io.Files;

import tk.rht0910.plugin_manager.Manager;

public final class PluginUtils {
	public String loadPlugin(CommandSender sender, Plugin plugin, String[] args) {
		return loadPlugin(sender, plugin.getServer().getName(), args);
	}

	public String loadPlugin(CommandSender sender, String plugin, String[] args) {
		try {
			Bukkit.getServer().getLogger().warning("Starting plugins load via PluginManager...");
			File[] objFiles = (new File("plugins/")).listFiles();
			if ( objFiles != null ) {
				for(int i=0; i< objFiles.length; i++ ) {
					File file = objFiles[i];
					String file_str = args[0];
					if(file_str.length() < 2) {
						sender.sendMessage(ChatColor.RED + "Not allowed plugin file name length is 2 or order.");
					}
					Pattern p = Pattern.compile("\\w");
					Matcher m = p.matcher(file_str);
					if(m.find()) {
						int is = 0;
						try {
							if(!Bukkit.getServer().getPluginManager().isPluginEnabled(Bukkit.getServer().getPluginManager().getPlugin(args[0]))) {
								if(!Bukkit.getServer().getPluginManager().isPluginEnabled(args[0])) {
									Bukkit.getServer().getPluginManager().loadPlugin(file);
									Plugin pm = Bukkit.getServer().getPluginManager().getPlugin(args[0]);
									if(!Bukkit.getServer().getPluginManager().isPluginEnabled(pm)) {
										Bukkit.getServer().getPluginManager().enablePlugin(pm);
									}
								}
							}
						} catch(InvalidPluginException e) {
							e.printStackTrace();
							is = 1;
						} catch(NullPointerException e) {
							e.printStackTrace();
							is = 1;
						} catch(UnknownDependencyException e) {
							e.printStackTrace();
							is = 1;
						} catch(Exception e) {
							if(is == 0) {
								sender.sendMessage(ChatColor.RED + "An error occurred: [" + e + "]. unknown error");
							}
							e.printStackTrace();
						} finally {
							if(is == 1) {
								sender.sendMessage(ChatColor.DARK_RED + "Load is failed.");
								Bukkit.getServer().getLogger().severe("Load is failed! : Additional information: Args[0]: \"" + args[0] + "\", Args[1]: \"" + args[1] + "\" Player: \"" + sender.toString() + "\"(IP:" + sender.getServer().getIp() + ")");
							}
							sender.sendMessage("Load is success!");
							Bukkit.getServer().getLogger().info("Load is success! : Additional information: Args[0]: \"" + args[0] + "\", Args[1]: \"" + args[1] + "\" Player: \"" + sender.toString() + "\"(IP:" + sender.getServer().getIp() + ")");
						}
					}
				}
			} else {
				sender.sendMessage(ChatColor.RED + "Unknown error!");
				Bukkit.getServer().getLogger().severe("Unknown error!:PluginUtils.java:29 ... PluginUtils.java:74");
			}
		} catch (Exception e) {
			sender.sendMessage(ChatColor.RED + "An error occurred: " + e);
			sender.sendMessage(ChatColor.RED + "Load failed. Error log to console.");
			Bukkit.getServer().getLogger().severe("An error occurred: " + e);
			e.printStackTrace();
			sender.sendMessage("Load failed: " + args[0]);
			return "Load failed.";
		}
		return "Successfully to load plugin: " + args[0];
	}

	public boolean unloadPlugin(CommandSender sender, Plugin plugin) {
		return unloadPlugin(sender, plugin.getServer().getName());
	}

	@SuppressWarnings({ "rawtypes", "unused" })
	public boolean unloadPlugin(CommandSender sender, String name) {
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
		/*     */
		/* 443 */          } catch (Exception e) {
			sender.sendMessage(ChatColor.RED + "An error occurred: " + e);
			return false;
		}
		}
		return true;
	}

	public boolean EditConfigFile(CommandSender sender, String[] args, String vine, String strs) {
		if(!sender.isOp()) {
			sender.sendMessage(ChatColor.RED + "You are not Operator!");
			Bukkit.getServer().getLogger().severe("(operator)Tried to access Config editor by " + sender.toString());
			return false;
		}
		if(!sender.hasPermission("pluginmanager.editor")) {
			sender.sendMessage(ChatColor.RED + "You don't not have permission.");
			Bukkit.getServer().getLogger().severe("(pluginmanager.editor)Tried to access Config editor by " + sender.toString());
			return false;
		}
		if(!sender.hasPermission("pluginmanager.admin")) {
			sender.sendMessage(ChatColor.RED + "You don't not have permission.");
			Bukkit.getServer().getLogger().severe("(pluginmanager.admin)Tried to access Config editor by " + sender.toString());
			return false;
		}
		if(!sender.hasPermission("pluginmanager.super-admin")) {
			sender.sendMessage(ChatColor.RED + "You don't not have permission.");
			Bukkit.getServer().getLogger().severe("(pluginmanager.super-admin)Tried to access Config editor by " + sender.toString());
			return false;
		}
		if(!sender.hasPermission("pluginmanager.extra-admin")) {
			sender.sendMessage(ChatColor.RED + "You don't not have permission.");
			Bukkit.getServer().getLogger().severe("(pluginmanager.extra-admin)Tried to access Config editor by " + sender.toString());
			return false;
		}
		int line = new Integer(vine);
		File file = null;
		BufferedReader br = null;
		// args[0] = PluginConfigDirectory
		// args[1] = ConfigFileName(Default: config.yml)
		// args[2] = removed(v0.3.1)
		file = new File("plugins/" + args[0] + "/" + args[1] + ".yml");
		if(!file.exists()) {
			file = new File("plugins/" + args[0] + "/" + args[1]);
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

	public boolean ConfigViewer(CommandSender sender, String[] args/*, Integer line_option) {*/ ) {
		if(args == null) {
			Manager.getCommand().ShowHelp(sender);
		}
		String configDir = args[0];
		String configFile = args[1];
		if(configDir == null) {
			Manager.getCommand().ShowHelp(sender);
		}
		if(configFile == null) {
			Manager.getCommand().ShowHelp(sender);
		}
		String arg1 = configDir;
		String arg2 = configFile;
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
							return false;
						}
					}
				/*if(line_option != null) {
					try {
						for(int i=0; i<=list.size(); i++) {
							if(i != line_option) {
								list.remove(i);
							} else {
								list.set(0, (String) list.toArray()[line_option]);
							}
						}
					} finally {
						try {
							br.close();
						} catch(IOException e) {
							e.printStackTrace();
						}
					}
				}*/
				Object[] arg = list.toArray();
				for(int i=0;i<=arg.length;i++) {
					sender.sendMessage("[" + i + "] " + arg[i]);
				}
				return true;
			} else {
				sender.sendMessage(ChatColor.RED + "Selected File is Directory, cannot continue.");
				return false;
			}
		}
		return true;
	}

	public void FileWrite(CommandSender sender, Object[] args, File file) {
		FileWriter fw = null;
		try {
			fw = new FileWriter(file);
		} catch (IOException e) {
			Bukkit.getServer().getLogger().severe("Cannot Edit config: [IOException. FileWriter object not created.] by " + sender.toString());
			sender.sendMessage(ChatColor.RED + "Error!:IOException. FileWriter object not created.");
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
	/*     */    public void reload(CommandSender sender, Plugin plugin, String[] args) {
		/* 375 */       if(plugin != null) {
			Bukkit.getServer().getLogger().info("Reloading plugin: " + args[0] + "(" + plugin.toString() + ")");
		/* 376 */          unloadPlugin(sender, plugin);
		/* 377 */          loadPlugin(sender, plugin, args);
		/*     */       }
		/* 379 */    }
}
// Thank you for PlugMan developers.
