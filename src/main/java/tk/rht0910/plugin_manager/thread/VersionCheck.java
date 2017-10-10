package tk.rht0910.plugin_manager.thread;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import tk.rht0910.plugin_manager.PluginManager;
import tk.rht0910.plugin_manager.language.Lang;
import tk.rht0910.tomeito_core.utils.Log;

public class VersionCheck extends Thread implements Runnable {
	private static Boolean player = false;
	private static CommandSender sender = null;
	private static final char altColorChar = '&';
	public VersionCheck(Boolean byPlayer, CommandSender sender) {
		VersionCheck.player = byPlayer;
		VersionCheck.sender = sender;
	}

	public VersionCheck() {
		VersionCheck.player = null;
		VersionCheck.sender = null;
	}

	public void run() {
		String response = null;
		String line = null;
		URL url = null;
		try {
			Log.info(ChatColor.translateAlternateColorCodes(altColorChar, Lang.started_version_check));
			if(player == true) {sender.sendMessage(ChatColor.translateAlternateColorCodes(altColorChar, Lang.started_version_check));}
			url = new URL("https://api.rht0910.tk/pluginmanager_version");
		} catch (MalformedURLException e3) {
			e3.printStackTrace();
		}
		HttpURLConnection conn = null;
		try {
			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/52.0.2743.116 Safari/537.36 Edge/15.16232");
			Log.info("Connection opened to https://api.rht0910.tk/pluginmanager_version");
		} catch (IOException e2) {
			e2.printStackTrace();
			Log.error("failed to open connection.");
		}
		BufferedReader rd = null;
		try {
			rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		try {
			while ((response = rd.readLine()) != null) {
			    line += response;
			}
			line = line.replaceAll("null", "");
			Log.info("Version Checker: Status code: " + conn.getResponseCode() + ", Get: " + line);
		} catch (IOException e) {
			e.printStackTrace();
			Log.error(String.format(Lang.error_occured, "Failed to open connection or invalid response code: " + e));
			sender.sendMessage(String.format(Lang.error_occured, e));
		}

		if(Lang.version.compareTo(line) == -1) {
			Log.info("New version available: " + line);
			PluginManager.is_available_new_version = true;
			PluginManager.newv = line;
			if(player == true) {
				sender.sendMessage(ChatColor.translateAlternateColorCodes(altColorChar, Lang.version_check_complete_update1));
				sender.sendMessage(ChatColor.translateAlternateColorCodes(altColorChar, String.format(Lang.version_check_complete_update2, PluginManager.current)));
				sender.sendMessage(ChatColor.translateAlternateColorCodes(altColorChar, String.format(Lang.version_check_complete_update3, line)));
				sender.sendMessage(ChatColor.translateAlternateColorCodes(altColorChar, Lang.version_check_complete_update4));
			}
			return;
		} else {
			Log.info("No updates found.");
			if(player == true) {sender.sendMessage(ChatColor.translateAlternateColorCodes(altColorChar, Lang.version_check_complete_update_no));}
			return;
		}
	}
}
