package tk.rht0910.plugin_manager.thread;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import tk.rht0910.plugin_manager.Lang;
import tk.rht0910.plugin_manager.Manager;
import tk.rht0910.plugin_manager.util.Log;

public class VersionCheck implements Runnable {
	public void run() {
		String response = null;
		String line = null;
		URL url = null;
		try {
			Log.info("Async version checking...");
			url = new URL("https://api.rht0910.tk/pluginmanager_version");
		} catch (MalformedURLException e3) {
			e3.printStackTrace();
		}
		HttpURLConnection conn = null;
		try {
			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/52.0.2743.116 Safari/537.36 Edge/15.16232");
		} catch (IOException e2) {
			e2.printStackTrace();
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
		}

		if(Lang.version.compareTo(line) == -1) {
			Log.info("New version available: " + line);
			Manager.is_available_new_version = true;
			return;
		} else {
			Log.info("No updates found.");
		}

		return;
	}
}
