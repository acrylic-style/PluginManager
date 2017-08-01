package tk.rht0910.plugin_manager.util;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import tk.rht0910.plugin_manager.Lang;
import tk.rht0910.plugin_manager.Manager;

public final class Command {
	public boolean showHelp(CommandSender sender) {
		Manager.getSender(sender).sendMessage(ChatColor.GREEN + " ----- Plugin Manager[v0.8.3] " + Lang.help + Lang.alpha + " -----");
		Manager.getSender(sender).sendMessage(ChatColor.RED + " ----- <" + Lang.required + "> [" + Lang.optional + "] - " + Lang.information);
		Manager.getSender(sender).sendMessage(ChatColor.AQUA + " - /pman help - " + Lang.pman_help_desc);
		Manager.getSender(sender).sendMessage(ChatColor.AQUA + " - /pman load <Plugin name or Plugin File> - " + Lang.pman_load_desc);
		Manager.getSender(sender).sendMessage(ChatColor.AQUA + " - /pman unload(or /pman disable) <Plugin name> - " + Lang.pman_unload_desc);
		Manager.getSender(sender).sendMessage(ChatColor.AQUA + " - /pman download <FileName> <URL> - " + Lang.pman_download_desc);
		Manager.getSender(sender).sendMessage(ChatColor.AQUA + " - /pman delete <(Current)FileName> <PluginName(or Backup file name)> - " + Lang.pman_delete_desc);
		Manager.getSender(sender).sendMessage(ChatColor.AQUA + " - /pman restore <FileName> - " + Lang.pman_restore_desc);
		Manager.getSender(sender).sendMessage(ChatColor.AQUA + " - /pman editor <Dir> <File> <Line(Count from 0)> <value> - " + Lang.pman_editor_desc);
		Manager.getSender(sender).sendMessage(ChatColor.AQUA + " - /pman viewer <Dir> <File> - " + Lang.pman_viewer_desc);
		Manager.getSender(sender).sendMessage(ChatColor.AQUA + " - /pman update - " + Lang.pman_update_desc);
		Manager.getSender(sender).sendMessage(ChatColor.AQUA + " - /pman usage <Command> - " + Lang.pman_usage_desc);
		Manager.getSender(sender).sendMessage(ChatColor.AQUA + " - /pman config language <en_US, ja_JP, ...> - Change language.");
		Manager.getSender(sender).sendMessage(ChatColor.AQUA + " - /pman config reload - Reload config.");
		Manager.getSender(sender).sendMessage(ChatColor.AQUA + " - BukkitDev(Project page): https://dev.bukkit.org/projects/pluginmanagement/");
		Manager.getSender(sender).sendMessage(ChatColor.AQUA + " - Jenkins(Developer version): http://point.rht0910.tk:8080/job/PluginManager/");
		Manager.getSender(sender).sendMessage(ChatColor.AQUA + " - Source code: https://github.com/rht0910/PluginManager/");
		return true;
	}

	public static boolean getUsageOfCmd(CommandSender sender, String cmd) {
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
