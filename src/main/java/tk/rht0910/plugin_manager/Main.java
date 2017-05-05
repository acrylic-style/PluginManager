package tk.rht0910.plugin_manager;

import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import com.google.common.io.Files;

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
		if (command.getName().equalsIgnoreCase("unload")) {
			if(!sender.isOp()) {
				sender.sendMessage(ChatColor.DARK_RED + "You are not Operator!");
				return false;
			}
			if(!sender.isPermissionSet("pluginmanager.admin")) {
				sender.sendMessage(ChatColor.DARK_RED + "Not enough permission");
				return false;
			}
			try {
				Bukkit.getServer().getLogger().severe("[Warning] Disabling plugin: " + args[0] + "!");
				Bukkit.getServer().getPluginManager().disablePlugin(Bukkit.getServer().getPluginManager().getPlugin(args[0]));
			} catch (Exception e) {
				sender.sendMessage("An error occurred: " + e);
			}
			Manager.getPluginUtil().unloadPlugin(sender, args[0]);
			sender.sendMessage(ChatColor.AQUA + "Disabled plugin");
		} else if(command.getName().equalsIgnoreCase("load")) {
			if(!sender.isOp()) {
				sender.sendMessage(ChatColor.DARK_RED + "You are not Operator!");
				return false;
			}
			if(!sender.isPermissionSet("pluginmanager.admin")) {
				sender.sendMessage(ChatColor.DARK_RED + "Not enough permission");
				return false;
			}
			Bukkit.getServer().getLogger().warning("Loading plugin: " + args[0]);
			try {
				try{
				Bukkit.getServer().getPluginManager().enablePlugin(Bukkit.getServer().getPluginManager().getPlugin(args[0]));
				} catch(Exception ignored) {}
				File plugin = new File("plugins/" + args[0] + ".jar");
				try {
					File[] objFiles = (new File("plugins/")).listFiles();
					if ( objFiles != null ) {
						for(int i=0; i< objFiles.length; i++ ) {
							File file = objFiles[i];
							String file_str = args[0];
							if(file_str.length() < 1) {
								sender.sendMessage(ChatColor.RED + "Not allowed plugin file name length is 1 or order.");
							}
							Pattern p = Pattern.compile("\\w");
							Matcher m = p.matcher(file_str);
							if(m.find()) {
								try {
									if(!Bukkit.getServer().getPluginManager().isPluginEnabled(Bukkit.getServer().getPluginManager().getPlugin(args[0]))) {
										if(!Bukkit.getServer().getPluginManager().isPluginEnabled(args[0])) {
											Bukkit.getServer().getPluginManager().loadPlugin(file);
											Plugin pm = Bukkit.getServer().getPluginManager().getPlugin(args[0]);
											Bukkit.getServer().getPluginManager().enablePlugin(pm);
										}
									}
								} catch(Exception e) {
									sender.sendMessage(ChatColor.RED + "An error occurred: [" + e + "]. Plugin is already enabled?");
									e.printStackTrace();
								}
							}
						}
					}
				} catch (Exception e) {
					sender.sendMessage(ChatColor.RED + "An error occurred: " + e);
					e.printStackTrace();
				}
				// ----------
				try {
					if(!Bukkit.getServer().getPluginManager().isPluginEnabled(Bukkit.getServer().getPluginManager().getPlugin(args[0]))) {
						if(!Bukkit.getServer().getPluginManager().isPluginEnabled(args[0])) {
							Bukkit.getServer().getPluginManager().loadPlugin(plugin);
							Plugin pm = Bukkit.getServer().getPluginManager().getPlugin(args[0]);
							Bukkit.getServer().getPluginManager().enablePlugin(pm);
						}
					}
				}
				catch(Exception ignored) {}
			} catch (Exception e) {
				sender.sendMessage("An error occurred: " + e);
				return false;
			}
			sender.sendMessage(ChatColor.AQUA + "Enabled plugin");
		} else if(command.getName().equalsIgnoreCase("download")) {
			if(!sender.isOp()) {
				sender.sendMessage(ChatColor.DARK_RED + "You are not Operator!");
				return false;
			}
			if(!sender.isPermissionSet("pluginmanager.admin")) {
				sender.sendMessage(ChatColor.DARK_RED + "No permission");
				return false;
			}
			Bukkit.getServer().getLogger().warning("Downloading plugin via PluginManager by " + sender.toString());
			DownloadFile(sender, args);
		} else if(command.getName().equalsIgnoreCase("editor")) {
			Manager.getPluginUtil().EditConfigFile(sender, args, args[2], args[3]);
		} else if(command.getName().equalsIgnoreCase("viewer")) {
			Bukkit.getServer().getLogger().warning("Opening config viewer by " + sender.toString());
			//if(args[2] == null || args[2] == "") {
				Manager.getPluginUtil().ConfigViewer(sender, args[0], args[1]);
			//} else {
			//	Manager.getPluginUtil().ConfigViewer(sender, args[0], args[1], new Integer(args[2]));
			//}
		} else if(command.getName().equalsIgnoreCase("pman")) {
			if(args[0] == "help") {
				Manager.getCommand().ShowHelp(sender);
			} else if(args[0] == "load") {
				if(!sender.isPermissionSet("pluginmanager.admin")) {
					sender.sendMessage(ChatColor.DARK_RED + "No permission");
					return false;
				}
				if(args[1] == null) {
					sender.sendMessage(ChatColor.RED + "Not enough args");
					return false;
				}
				try {
					try
					{
						Bukkit.getServer().getLogger().info("Enabling plugin: " + args[1]);
						Bukkit.getServer().getPluginManager().enablePlugin(Bukkit.getServer().getPluginManager().getPlugin(args[1]));
					}
					catch(Exception e) { e.printStackTrace(); }
					File plugin = new File("plugins/" + args[1] + ".jar");
					try {
						Bukkit.getServer().getLogger().info("Loading plugin: " + args[1]);
						if(Bukkit.getServer().getPluginManager().isPluginEnabled(Bukkit.getServer().getPluginManager().getPlugin(args[1])) == false) {
							if(Bukkit.getServer().getPluginManager().isPluginEnabled(args[1]) == false) {
								Bukkit.getServer().getPluginManager().loadPlugin(plugin);
							}
						} else {
							sender.sendMessage(ChatColor.RED + "Plugin is already enabled!");
							Bukkit.getServer().getLogger().severe("Plugin is already enabled: \"plugins/" + args[1] + ".jar\"!");
						}
					}
					catch(Exception e) {
						Bukkit.getServer().getLogger().severe("Couldn't loading plugin: " + args[1]);
						e.printStackTrace();
					}
				} catch (Exception e) {
					sender.sendMessage("An error occurred: " + e);
					Bukkit.getServer().getLogger().severe("An error occured in Enable, Load: " + args[1]);
					e.printStackTrace();
					return false;
				}
				sender.sendMessage(ChatColor.AQUA + "Enabled plugin");
			} else if(args[0] == "disable") {
				if(!sender.isPermissionSet("pluginmanager.admin")) {
					Bukkit.getServer().getLogger().severe("No permission: /pman disable : " + sender.toString());
					sender.sendMessage(ChatColor.DARK_RED + "No permission");
					return false;
				}
				if(args[1] == null) {
					sender.sendMessage(ChatColor.RED + "Not enough args");
					return false;
				}
				try {
					Bukkit.getServer().getLogger().info("Disabling plugin: " + args[1]);
					Bukkit.getServer().getPluginManager().disablePlugin(Bukkit.getServer().getPluginManager().getPlugin(args[1]));
				} catch (Exception e) {
					sender.sendMessage("An error occurred: " + e);
					return false;
				}
				sender.sendMessage(ChatColor.AQUA + "Disabled plugin");
			} else if(args[0] == "download") {
				if(!sender.isOp()) {
					sender.sendMessage(ChatColor.DARK_RED + "You are not Operator!");
					return false;
				}
				if(!sender.isPermissionSet("pluginmanager.admin")) {
					sender.sendMessage(ChatColor.DARK_RED + "No permission");
					return false;
				}
				if(args[1] == null) {
					sender.sendMessage(ChatColor.RED + "Not enough args");
					return false;
				}
				if(args[2] == null) {
					sender.sendMessage(ChatColor.RED + "Not enough args");
					return false;
				}
				try {
					((CommandSender) sender.getServer()).sendMessage("Downloading plugin '" + args[1] + "(URL: " + args[2] + ")' by " + sender.toString());
					URL url = new URL(args[2]);
					HttpURLConnection conn = (HttpURLConnection) url.openConnection();
					conn.setAllowUserInteraction(false);
					conn.setInstanceFollowRedirects(true);
					conn.setRequestMethod("GET");
					conn.connect();
					int httpStatusCode = conn.getResponseCode();
					if(httpStatusCode != HttpURLConnection.HTTP_OK){ throw new Exception(); } // Input Stream
					DataInputStream dataInStream = new DataInputStream( conn.getInputStream()); // Output Stream
					DataOutputStream dataOutStream = new DataOutputStream( new BufferedOutputStream( new FileOutputStream("plugins/" + args[1] + ".jar"))); // Read Data
					byte[] b = new byte[4096]; int readByte = 0; while(-1 != (readByte = dataInStream.read(b))){ dataOutStream.write(b, 0, readByte); } // Close Stream
					dataInStream.close(); dataOutStream.close();
					((CommandSender) sender.getServer()).sendMessage("Downloaded plugin '" + args[1] + "(URL: " + args[2] + ")' by " + sender.toString());
					} catch (FileNotFoundException e) { e.printStackTrace(); } catch (ProtocolException e) { e.printStackTrace(); } catch (MalformedURLException e) { e.printStackTrace(); } catch (IOException e) { e.printStackTrace(); } catch (Exception e) { e.printStackTrace();
				}
			}
		} else if(command.getName().equalsIgnoreCase("edit")) {
			if(!sender.isOp()) {
				sender.sendMessage(ChatColor.DARK_RED + "You are not Operator!");
				return false;
			}
			if(!sender.isPermissionSet("pluginmanager.admin")) {
				sender.sendMessage(ChatColor.DARK_RED + "No permission");
				return false;
			}
		} else if(command.getName().equalsIgnoreCase("delete")) {
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
			if(args[1] == null) {
				sender.sendMessage(ChatColor.RED + "Not enough args, cannot continue.");
				return false;
			}
			DeletePlugin(sender, args[0], args[1]);
		} else if(command.getName().equalsIgnoreCase("restore")) {
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
			RestorePlugin(sender, args[0]);
		} else {
			sender.sendMessage("Invalid args :P");
			return false;
		}
		return true;
	}

	public static void ShowHelp(CommandSender sender) {
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

	public static void DeletePlugin(CommandSender sender, String filename, String pluginName) {
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
							sender.sendMessage(ChatColor.AQUA + "[PluginManager]" + ChatColor.RED + " [Warning] " + ChatColor.BLACK + "Tried to DELETE plugin by " + ChatColor.DARK_RED + sender.toString());
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
						sender.sendMessage(ChatColor.AQUA + "[PluginManager]" + ChatColor.RED + " [Warning] " + ChatColor.BLACK + "Tried to DELETE plugin by " + ChatColor.DARK_RED + sender.toString());
						e.printStackTrace();
					}
				} else {
					Bukkit.getServer().getPluginManager().enablePlugin(Bukkit.getServer().getPluginManager().getPlugin(pluginName));
					sender.sendMessage(ChatColor.AQUA + "[PluginManager]" + ChatColor.RED + " [Warning] " + ChatColor.BLACK + "Tried to DELETE plugin by " + ChatColor.DARK_RED + sender.toString());
					sender.sendMessage("File is not readable.");
					return;
				}
			} else {
				Bukkit.getServer().getPluginManager().enablePlugin(Bukkit.getServer().getPluginManager().getPlugin(pluginName));
				sender.sendMessage(ChatColor.AQUA + "[PluginManager]" + ChatColor.RED + " [Warning] " + ChatColor.BLACK + "Tried to DELETE plugin by " + ChatColor.DARK_RED + sender.toString());
				sender.sendMessage("Selected file is DIRECTORY.");
				return;
			}
		} else {
			sender.sendMessage(ChatColor.RED + "Selected file is not exists!");
			sender.sendMessage(ChatColor.AQUA + "[PluginManager]" + ChatColor.RED + " [Warning] " + ChatColor.BLACK + "Tried to DELETE plugin by " + ChatColor.DARK_RED + sender.toString());
			return;
		}
	}


	public static void RestorePlugin(CommandSender sender, String pluginName) {
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

	// EditConfigFile is moved to PluginUtils.java(class).

	public static void DownloadFile(CommandSender sender, String[] args) {
		try {
			sender.sendMessage(ChatColor.RED + "Downloading plugin" + ChatColor.BLACK + " '" + args[0] + "(URL: " + args[1] + ")' by " + sender.toString());
			URL url = new URL(args[1]);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setAllowUserInteraction(false);
			conn.setInstanceFollowRedirects(true);
			conn.setRequestMethod("GET");
			conn.connect();
			@SuppressWarnings("unused")
			int httpStatusCode = conn.getResponseCode();
			//if(httpStatusCode != HttpURLConnection.HTTP_OK){ if(httpStatusCode != -1) {throw new Exception();} } // Input Stream
			DataInputStream dataInStream = new DataInputStream( conn.getInputStream()); // Output Stream
			DataOutputStream dataOutStream = new DataOutputStream( new BufferedOutputStream( new FileOutputStream("plugins/" + args[0] + ".jar"))); // Read Data
			byte[] b = new byte[4096]; int readByte = 0; while(-1 != (readByte = dataInStream.read(b))){ dataOutStream.write(b, 0, readByte); } // Close Stream
			dataInStream.close(); dataOutStream.close();
			sender.sendMessage(ChatColor.RED + "Downloaded plugin" + ChatColor.BLACK + " '" + args[0] + "(URL: " + args[1] + ")' by " + sender.toString());
			} catch (FileNotFoundException e) { e.printStackTrace(); } catch (ProtocolException e) { e.printStackTrace(); } catch (MalformedURLException e) { e.printStackTrace(); } catch (IOException e) { e.printStackTrace(); } catch (Exception e) { e.printStackTrace();
		}
	}

	private static String[] array = {};

	public static String[] printCollection(Collection<Object> c) {
	    for (Object e : c) {
	        array = (String[]) e;
	    }
	    return array;
	}
}
