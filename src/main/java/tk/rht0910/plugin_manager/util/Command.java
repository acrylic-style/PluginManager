package tk.rht0910.plugin_manager.util;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import tk.rht0910.plugin_manager.Manager;

public final class Command {
	public boolean ShowHelp(CommandSender sender) {
		Manager.getSender(sender).sendMessage(ChatColor.GREEN + "----- Plugin Manager Help -----");
		Manager.getSender(sender).sendMessage(ChatColor.RED + "----- <Required> [Optional] - Information");
		Manager.getSender(sender).sendMessage(ChatColor.AQUA + " - /pman help - Displays this.");
		Manager.getSender(sender).sendMessage(ChatColor.AQUA + "- /pman load <Plugin name or Plugin File> - Load or Enable a plugin");
		Manager.getSender(sender).sendMessage(ChatColor.AQUA + "- /pman unload(or /pman disable) <Plugin name> - Disable plugin");
		Manager.getSender(sender).sendMessage(ChatColor.AQUA + "- /pman download <FileName> <URL> - Download plugin");
		Manager.getSender(sender).sendMessage(ChatColor.AQUA + "- /pman delete <(Current)FileName> <PluginName(or Backup file name)>");
		Manager.getSender(sender).sendMessage(ChatColor.AQUA + "- /pman restore <FileName>");
		Manager.getSender(sender).sendMessage(ChatColor.AQUA + "- /pman editor <Dir> <File> <Line(Count from 0)> <value>");
		Manager.getSender(sender).sendMessage(ChatColor.AQUA + "- /pman viewer <Dir> <File>");
		Manager.getSender(sender).sendMessage(ChatColor.AQUA + "- BukkitDev(Project page): https://dev.bukkit.org/projects/pluginmanagement/");
		Manager.getSender(sender).sendMessage(ChatColor.AQUA + "- Jenkins(Developer version): http://point.rht0910.tk:8080/job/PluginManager/");
		Manager.getSender(sender).sendMessage(ChatColor.AQUA + "- Source code: https://github.com/rht0910/PluginManager/");
		return true;
	}
}
