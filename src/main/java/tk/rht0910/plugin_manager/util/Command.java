package tk.rht0910.plugin_manager.util;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import tk.rht0910.plugin_manager.Manager;

public final class Command {
	public boolean ShowHelp(CommandSender sender) {
		Manager.getSender(sender).sendMessage(ChatColor.GREEN + "----- Plugin Manager Help -----");
		Manager.getSender(sender).sendMessage(ChatColor.RED + "----- <Required> [Optional] - [Permission] - Information");
		Manager.getSender(sender).sendMessage(ChatColor.AQUA + " - /pman [arg] [pluginmanager.help(Defaults granted permission for all operators.)] - Displays this.");
		Manager.getSender(sender).sendMessage(ChatColor.AQUA + "- /load(/pman load) <Plugin name or Plugin File> - Load or Enable a plugin");
		Manager.getSender(sender).sendMessage(ChatColor.AQUA + "- /unload(/pman disable) <Plugin name> - Disable plugin");
		Manager.getSender(sender).sendMessage(ChatColor.AQUA + "- /download(/pman download) <FileName> <URL> - Download plugin");
		Manager.getSender(sender).sendMessage(ChatColor.AQUA + "- /delete <(Current)FileName> <PluginName(or Backup file name)>");
		Manager.getSender(sender).sendMessage(ChatColor.AQUA + "- /restore <FileName>");
		Manager.getSender(sender).sendMessage(ChatColor.AQUA + "- /editor <Dir> <File> <Line(Count from 0)> <value>");
		Manager.getSender(sender).sendMessage(ChatColor.AQUA + "- /viewer <Dir> <File>");
		return true;
	}
}
