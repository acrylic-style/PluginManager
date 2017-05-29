package tk.rht0910.plugin_manager.thread;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.InvalidPluginException;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.UnknownDependencyException;

import tk.rht0910.plugin_manager.exception.DebugException;
import tk.rht0910.plugin_manager.exception.ThrowDebugException;

public class LoadPlugin {
	public static void run() {
		Thread t = new Thread("PM-LoadPlugin"){
			@SuppressWarnings("unused")
			public void run(CommandSender sender, String plugin) {
				try {
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
								return;
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
					return;
				}
				try {
					ThrowDebugException.callException();
				} catch (DebugException e) {
					e.printStackTrace();
				}
				return;
			}
		};
		t.start();
	}
}
