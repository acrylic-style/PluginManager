package tk.rht0910.plugin_manager.util;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.SimpleCommandMap;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;

import com.google.common.io.Files;

import tk.rht0910.plugin_manager.Manager;
import tk.rht0910.plugin_manager.thread.LoadPlugin;

public final class PluginUtils {
	public void loadPlugin(CommandSender sender, Plugin plugin) {
		loadPlugin(sender, plugin.getServer().getName());
	}

	public void loadPlugin(CommandSender sender, String plugin) {
		LoadPlugin.run();
		/*try {
			Bukkit.getServer().getLogger().warning("Starting plugins load via PluginManager...");
			File[] objFiles = (new File("plugins/")).listFiles();
			if ( objFiles != null ) {
				int trying = 0;
				for(int i=0; i< objFiles.length; i++ ) {
					if(trying == 0) {
						trying = 1;
					} else if(trying == 1) {
						trying = 2;
					} else if(trying == 2) {
						trying = 3;
					} else if(trying == 3) {
						return "Giving up";
					}
					File file = objFiles[i];
					String file_str = plugin;
					if(file_str.length() < 2) {
						sender.sendMessage(ChatColor.RED + "Not allowed plugin file name length is 2 or older.");
					}
					Pattern p = Pattern.compile("\\w");
					Matcher m = p.matcher(file_str);
					if(m.find()) {
						int is = 0;
						try {
							if(!Bukkit.getServer().getPluginManager().isPluginEnabled(Bukkit.getServer().getPluginManager().getPlugin(plugin))) {
								if(!Bukkit.getServer().getPluginManager().isPluginEnabled(plugin)) {
									Bukkit.getServer().getPluginManager().loadPlugin(file);
									Plugin pm = Bukkit.getServer().getPluginManager().getPlugin(plugin);
									if(!Bukkit.getServer().getPluginManager().isPluginEnabled(pm)) {
										Bukkit.getServer().getPluginManager().enablePlugin(pm);
									}
								}
							} else {
								sender.sendMessage(ChatColor.RED + "This plugin is already enabled!");
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
								Bukkit.getServer().getLogger().severe("Load is failed! : Additional information: Args[0]: \"" + plugin + "\", Args[1]: \"not defined\" Player: \"" + sender.toString() + "\"(IP:" + sender.getServer().getIp() + ")");
							}
							sender.sendMessage(ChatColor.GREEN + "Load is success!");
							Bukkit.getServer().getLogger().info("Load is success! : Additional information: Args[0]: \"" + plugin + "\", Args[1]: \"not defined\" Player: \"" + sender.toString() + "\"(IP:" + sender.getServer().getIp() + ")");
						}
					}
				}
			} else {
				sender.sendMessage(ChatColor.RED + "Unknown error!");
				Bukkit.getServer().getLogger().severe("Unknown error!:PluginUtils.java:loadPlugin(objFiles notfound, what?)");
			}
		} catch (Exception e) {
			sender.sendMessage(ChatColor.RED + "An error occurred: " + e);
			sender.sendMessage(ChatColor.RED + "Load failed. Error log to console.");
			Bukkit.getServer().getLogger().severe("An error occurred: " + e);
			e.printStackTrace();
			sender.sendMessage("Load failed: " + plugin);
			return "Load failed.";
		}
		return "Successfully to load plugin: " + plugin;*/
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

	public boolean EditConfigFile(CommandSender sender, String dir, String cfile, String vine, String strs) {
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

	public boolean ConfigViewer(CommandSender sender, String configDir, String configFile/*, Integer line_option) {*/ ) {
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
			Bukkit.getServer().getLogger().info("Reloading plugin: " + plugin.toString());
		/* 376 */          unloadPlugin(sender, plugin);
		/* 377 */          loadPlugin(sender, plugin);
		Bukkit.getServer().getLogger().info("Reloaded plugin: " + plugin.toString());
		/*     */       }
		/* 379 */    }

	public void DeletePlugin(CommandSender sender, String filename, String pluginName) {
		sender.sendMessage(ChatColor.AQUA + "[PluginManager]" + ChatColor.RED + " [Warning] " + ChatColor.BLACK + "REMOVING plugin(" + pluginName + ") by " + ChatColor.DARK_RED + sender.toString());
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
							sender.sendMessage(ChatColor.AQUA + "[PluginManager]" + ChatColor.RED + " [Warning] " + ChatColor.BLACK + "Tried to DELETE plugin by " + ChatColor.DARK_RED + sender.toString());
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
						sender.sendMessage("Successfully Remove plugin. To restore plugin, Please enter command: '/restore " + pluginName + "'");
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

	public void RestorePlugin(CommandSender sender, String pluginName) {
		sender.sendMessage(ChatColor.AQUA + "[PluginManager]" + ChatColor.YELLOW + " [Warning] " + ChatColor.BLACK + "Restoring plugin(" + pluginName + ") by " + sender.toString());
		File from = new File("plugins/plugins_backup/" + pluginName + ".jar");
		File to = new File("plugins/" + pluginName + ".jar");
		try {
			Files.move(from, to);
			sender.sendMessage("Successfully Restore plugin by " + sender.toString());
			sender.sendMessage("Successfully Restore plugin. To Load and enable: /load " + pluginName);
		} catch (IOException e) {
			sender.sendMessage(ChatColor.AQUA + "[PluginManager]" + ChatColor.RED + " [Warning] " + ChatColor.BLACK + "Tried to RESTORE plugin by " + ChatColor.DARK_RED + sender.toString());
			e.printStackTrace();
		}
	}

	public void Download(CommandSender sender, String file, String url) {
		try {
			sender.sendMessage(ChatColor.RED + "Downloading plugin" + ChatColor.BLACK + " '" + file + "(URL: " + url + ")' by " + sender.toString());
			URL url2 = new URL(url);
			HttpURLConnection conn = (HttpURLConnection) url2.openConnection();
			conn.setAllowUserInteraction(false);
			conn.setInstanceFollowRedirects(true);
			conn.setRequestMethod("GET");
			conn.connect();
			@SuppressWarnings("unused")
			int httpStatusCode = conn.getResponseCode();
			//if(httpStatusCode != HttpURLConnection.HTTP_OK){ if(httpStatusCode != -1) {throw new Exception();} } // Input Stream
			DataInputStream dataInStream = new DataInputStream( conn.getInputStream()); // Output Stream
			DataOutputStream dataOutStream = new DataOutputStream( new BufferedOutputStream( new FileOutputStream("plugins/" + file + ".jar"))); // Read Data
			byte[] b = new byte[4096]; int readByte = 0; while(-1 != (readByte = dataInStream.read(b))){ dataOutStream.write(b, 0, readByte); } // Close Stream
			dataInStream.close(); dataOutStream.close();
			sender.sendMessage(ChatColor.RED + "Downloaded plugin" + ChatColor.BLACK + " '" + file + "(URL: " + url + ")'");
			} catch (FileNotFoundException e) { e.printStackTrace(); } catch (ProtocolException e) { e.printStackTrace(); } catch (MalformedURLException e) { e.printStackTrace(); } catch (IOException e) { e.printStackTrace(); } catch (Exception e) { e.printStackTrace();
		}
	}
}
// Thank you for PlugMan developers.
