package tk.rht0910.plugin_manager.thread;

import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Calendar;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import tk.rht0910.plugin_manager.language.Lang;
import xyz.acrylicstyle.tomeito_core.utils.Log;

public class AsyncDownload extends Thread {
	private CommandSender sender = null;
	private String file = null;
	private String url = null;
	private char altColorChar = '&';

	public AsyncDownload(CommandSender asender, String afile, String aurl) {
		sender = asender;
		file = afile;
		url = aurl;
	}

	public void run() {
		Log.info(ChatColor.translateAlternateColorCodes(altColorChar, Lang.start_dl_plugin));
		Log.info(ChatColor.translateAlternateColorCodes(altColorChar, String.format(Lang.url, url)));
		Log.info(ChatColor.translateAlternateColorCodes(altColorChar, String.format(Lang.filename, file)));
		long start = System.currentTimeMillis();
		String start_time = Calendar.YEAR + "/" + Calendar.MONTH + "/" + Calendar.DAY_OF_MONTH + ":" + Calendar.HOUR_OF_DAY + ":" + Calendar.MINUTE + ":" + Calendar.SECOND + "." + Calendar.MILLISECOND;
		try {
			sender.sendMessage(ChatColor.translateAlternateColorCodes(altColorChar, Lang.start_dl_plugin));
			sender.sendMessage(ChatColor.translateAlternateColorCodes(altColorChar, String.format(Lang.start_time, start_time)));
			sender.sendMessage(ChatColor.translateAlternateColorCodes(altColorChar, String.format(Lang.url, url)));
			sender.sendMessage(ChatColor.translateAlternateColorCodes(altColorChar, String.format(Lang.filename, file)));
			URL url2 = new URL(url);
			HttpURLConnection conn = (HttpURLConnection) url2.openConnection();
			conn.setAllowUserInteraction(false);
			conn.setInstanceFollowRedirects(true);
			conn.setRequestMethod("GET");
			conn.connect();
			//int httpStatusCode = conn.getResponseCode();
			//if(httpStatusCode != HttpURLConnection.HTTP_OK){ if(httpStatusCode != -1) {throw new Exception();} } // Input Stream
			DataInputStream dataInStream = new DataInputStream( conn.getInputStream()); // Output Stream
			DataOutputStream dataOutStream = new DataOutputStream( new BufferedOutputStream( new FileOutputStream("plugins/" + file + ".jar"))); // Read Data
			byte[] b = new byte[4096]; int readByte = 0; while(-1 != (readByte = dataInStream.read(b))){ dataOutStream.write(b, 0, readByte); } // Close Stream
			dataInStream.close(); dataOutStream.close();
			} catch (FileNotFoundException e) { e.printStackTrace(); } catch (ProtocolException e) { e.printStackTrace(); } catch (MalformedURLException e) { e.printStackTrace(); } catch (IOException e) { e.printStackTrace(); } catch (Exception e) {
				e.printStackTrace();
				String stop_time = Calendar.YEAR + "/" + Calendar.MONTH + "/" + Calendar.DAY_OF_MONTH + ":" + Calendar.HOUR_OF_DAY + ":" + Calendar.MINUTE + ":" + Calendar.SECOND + "." + Calendar.MILLISECOND;
				long stop = System.currentTimeMillis();
				long total = stop - start;
				Log.severe(ChatColor.translateAlternateColorCodes(altColorChar, Lang.download_failed));
				sender.sendMessage(ChatColor.translateAlternateColorCodes(altColorChar, Lang.download_failed));
				sender.sendMessage(ChatColor.translateAlternateColorCodes(altColorChar, String.format(Lang.start_time, start_time)));
				sender.sendMessage(ChatColor.translateAlternateColorCodes(altColorChar, String.format(Lang.total_time, total)));
				sender.sendMessage(ChatColor.translateAlternateColorCodes(altColorChar, String.format(Lang.finished_time, stop_time)));
				return;
		}
		String stop_time = Calendar.YEAR + "/" + Calendar.MONTH + "/" + Calendar.DAY_OF_MONTH + ":" + Calendar.HOUR_OF_DAY + ":" + Calendar.MINUTE + ":" + Calendar.SECOND + "." + Calendar.MILLISECOND;
		long stop = System.currentTimeMillis();
		long total = stop - start;
		Log.info(Lang.download_success);
		sender.sendMessage(ChatColor.translateAlternateColorCodes(altColorChar, Lang.download_success));
		sender.sendMessage(ChatColor.translateAlternateColorCodes(altColorChar, String.format(Lang.start_time, start_time)));
		sender.sendMessage(ChatColor.translateAlternateColorCodes(altColorChar, String.format(Lang.total_time, total)));
		sender.sendMessage(ChatColor.translateAlternateColorCodes(altColorChar, String.format(Lang.finished_time, stop_time)));
	}
}
