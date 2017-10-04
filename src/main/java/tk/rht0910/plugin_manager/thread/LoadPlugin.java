package tk.rht0910.plugin_manager.thread;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.UnknownDependencyException;

import tk.rht0910.plugin_manager.language.Lang;
import tk.rht0910.tomeito_core.utils.Log;

public class LoadPlugin implements Runnable {

	private CommandSender sender = null;
	private String plugin = null;
	private String file = null;
	private char altColorChar = '&';

	public LoadPlugin(CommandSender sender, String plugin, String file) {
		this.sender = sender;
		this.plugin = plugin;
		this.file = file;
	}

	@Override
	public void run() {
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
	}
}
