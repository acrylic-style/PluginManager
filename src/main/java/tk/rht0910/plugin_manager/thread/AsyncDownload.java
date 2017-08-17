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

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class AsyncDownload extends Thread {
	private CommandSender sender = null;
	private String file = null;
	private String url = null;
	public AsyncDownload(CommandSender asender, String afile, String aurl) {
		sender = asender;
		file = afile;
		url = aurl;
	}

	public void run() {
		Bukkit.getLogger().info("------------------------------\n DOWNLOADING PLUGIN\n------------------------------\n");
		long start = System.currentTimeMillis();
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
			} catch (FileNotFoundException e) { e.printStackTrace(); } catch (ProtocolException e) { e.printStackTrace(); } catch (MalformedURLException e) { e.printStackTrace(); } catch (IOException e) { e.printStackTrace(); } catch (Exception e) {
				e.printStackTrace();
				long stop = System.currentTimeMillis();
				long total = start - stop;
				Bukkit.getLogger().severe("------------------------------\n DOWNLOAD FAILURE\n-------------------------------\n Total time: " + total + "\n Finished at: \n" + stop + "\n------------------------------\n");
				return;
		}
		long stop = System.currentTimeMillis();
		long total = start - stop;
		Bukkit.getLogger().severe("------------------------------\n DOWNLOAD SUCCESS\n-------------------------------\n Total time: " + total + "\n Finished at: \n" + stop + "\n------------------------------\n");
		return;
	}
}
