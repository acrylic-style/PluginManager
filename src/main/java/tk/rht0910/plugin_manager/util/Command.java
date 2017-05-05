package tk.rht0910.plugin_manager.util;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import tk.rht0910.plugin_manager.Manager;

public final class Command {
	public boolean ShowHelp(CommandSender sender) {
		Manager.getSender(sender).sendMessage(ChatColor.GREEN + "----- Plugin Manager Help -----");
		Manager.getSender(sender).sendMessage(ChatColor.RED + "----- <Required> [Optional] - [Permission] - Information");
		Manager.getSender(sender).sendMessage(ChatColor.AQUA + " - /pman <[help]> [pluginmanager.help(Defaults granted permission for all operators.)] - Displays this.");
		Manager.getSender(sender).sendMessage(ChatColor.DARK_RED + "--- Requires permission [pluginmanager.admin] ---");
		Manager.getSender(sender).sendMessage(ChatColor.AQUA + "- /load(/pman load) <Plugin name or Plugin File> [pluginmanager.load] - Load or Enable a plugin");
		Manager.getSender(sender).sendMessage(ChatColor.AQUA + "- /unload(/pman disable) <Plugin name> [pluginmanager.unload] - Disable plugin");
		Manager.getSender(sender).sendMessage(ChatColor.AQUA + "- /download(/pman download) <FileName> <URL> [pluginmanager.download] - Download plugin");
		Manager.getSender(sender).sendMessage(ChatColor.DARK_RED + "--- Requires permission [pluginmanager.admin] and [pluginmanager.super-admin] ---");
		Manager.getSender(sender).sendMessage(ChatColor.AQUA + "- /delete <(Current)FileName> <PluginName(or Backup file name)> [pluginmanager.delete]");
		Manager.getSender(sender).sendMessage(ChatColor.AQUA + "- /restore <FileName> [pluginmanager.restore]");
		Manager.getSender(sender).sendMessage(ChatColor.DARK_RED + "--- Required permission [pluginmanager.admin], [pluginmanager.super-admin] and [pluginmanager.extra-admin] ---");
		Manager.getSender(sender).sendMessage(ChatColor.AQUA + "- /editor <Dir> <File> <Line(Count from 0)> <value> [pluginmanager.editor]");
		Manager.getSender(sender).sendMessage(ChatColor.AQUA + "- /viewer <Dir> <File> [pluginmanager.viewer]");
		return true;
	}
}
