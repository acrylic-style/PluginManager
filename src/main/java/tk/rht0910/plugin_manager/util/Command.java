package tk.rht0910.plugin_manager.util;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public final class Command {
	public boolean Help(CommandSender sender) {
		sender.sendMessage(ChatColor.GREEN + "----- Plugin Manager Help -----");
		sender.sendMessage(ChatColor.RED + "----- <Requirement args> [Permission] - Information");
		sender.sendMessage(ChatColor.GRAY + "----- <[Options]> -----");
		sender.sendMessage(ChatColor.AQUA + "- /pman <[help]> [pluginmanager.help(Defaults granted permission for all operators.)] - Displays this.");
		sender.sendMessage(ChatColor.DARK_BLUE + "--- Requires permission [pluginmanager.admin] ---");
		sender.sendMessage(ChatColor.AQUA + "- /load(/pman load) <Plugin name or Plugin File> [pluginmanager.load] - Load or Enable a plugin");
		sender.sendMessage(ChatColor.AQUA + "- /unload(/pman disable) <Plugin name> [pluginmanager.unload] - Disable plugin");
		sender.sendMessage(ChatColor.AQUA + "- /download(/pman download) <FileName> <URL> [pluginmanager.download] - Download plugin");
		sender.sendMessage(ChatColor.DARK_BLUE + "--- Requires permission [pluginmanager.admin] and [pluginmanager.super-admin] ---");
		sender.sendMessage(ChatColor.AQUA + "- /delete <(Current)FileName> <PluginName(or Backup file name)> [pluginmanager.delete]");
		sender.sendMessage(ChatColor.AQUA + "- /restore <FileName> [pluginmanager.restore]");
		sender.sendMessage(ChatColor.DARK_RED + "--- Required permission [pluginmanager.admin], [pluginmanager.super-admin] and [pluginmanager.extra-admin] ---");
		sender.sendMessage(ChatColor.AQUA + "- /editor <ConfigDir> <ConfigFile> <Line(Count from 0)> <e=xample> [pluginmanager.editor]");
		sender.sendMessage(ChatColor.AQUA + "- /viewer <ConfigDir> <ConfigFile> [pluginmanager.viewer]");
		return true;
	}
}
