package tk.rht0910.plugin_manager.util;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import tk.rht0910.plugin_manager.Manager;

public final class Command {
	public boolean showHelp(CommandSender sender) {
		Manager.getSender(sender).sendMessage(ChatColor.GREEN + " ----- Plugin Manager[v0.7" + ChatColor.AQUA + "(BETA)" + ChatColor.AQUA + "] Help -----");
		Manager.getSender(sender).sendMessage(ChatColor.RED + " ----- <Required> [Optional] - Information");
		Manager.getSender(sender).sendMessage(ChatColor.AQUA + " - /pman help - Displays this.");
		Manager.getSender(sender).sendMessage(ChatColor.AQUA + " - /pman load <Plugin name or Plugin File> - Load or Enable a plugin");
		Manager.getSender(sender).sendMessage(ChatColor.AQUA + " - /pman unload(or /pman disable) <Plugin name> - Disable plugin");
		Manager.getSender(sender).sendMessage(ChatColor.AQUA + " - /pman download <FileName> <URL> - Download plugin");
		Manager.getSender(sender).sendMessage(ChatColor.AQUA + " - /pman delete <(Current)FileName> <PluginName(or Backup file name)> - Delete(move) plugin.");
		Manager.getSender(sender).sendMessage(ChatColor.AQUA + " - /pman restore <FileName> - Restore deleted plugin.");
		Manager.getSender(sender).sendMessage(ChatColor.AQUA + " - /pman editor <Dir> <File> <Line(Count from 0)> <value> - Edit config.");
		Manager.getSender(sender).sendMessage(ChatColor.AQUA + " - /pman viewer <Dir> <File> - View config.");
		Manager.getSender(sender).sendMessage(ChatColor.AQUA + " - /pman update - Update this plugin.");
		Manager.getSender(sender).sendMessage(ChatColor.AQUA + " - /pman usage <Command> - Usage of command.");
		Manager.getSender(sender).sendMessage(ChatColor.AQUA + " - BukkitDev(Project page): https://dev.bukkit.org/projects/pluginmanagement/");
		Manager.getSender(sender).sendMessage(ChatColor.AQUA + " - Jenkins(Developer version): http://point.rht0910.tk:8080/job/PluginManager/");
		Manager.getSender(sender).sendMessage(ChatColor.AQUA + " - Source code: https://github.com/rht0910/PluginManager/");
		return true;
	}

	public boolean getUsageOfCmd(CommandSender sender, String cmd) {
		try {
			String usage = Bukkit.getPluginCommand(cmd).getUsage();
			Manager.getSender(sender).sendMessage(usage);
		} catch(Exception e) {
			sender.sendMessage("Can't show usage of command.(Command not found)");
			e.printStackTrace();
		}
		return true;
	}
}
