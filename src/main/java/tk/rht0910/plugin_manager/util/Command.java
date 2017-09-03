package tk.rht0910.plugin_manager.util;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import tk.rht0910.plugin_manager.Lang;

public final class Command {
	public boolean showHelp(CommandSender sender) {
		Lang.use();
		sender.sendMessage(ChatColor.GREEN + " ----- Plugin Manager[v0.8.3] " + Lang.help + Lang.alpha + " -----");
		sender.sendMessage(ChatColor.RED + " ----- <" + Lang.required + "> [" + Lang.optional + "] - " + Lang.information);
		sender.sendMessage(ChatColor.AQUA + " - /pman help - " + Lang.pman_help_desc);
		sender.sendMessage(ChatColor.AQUA + " - /pman load <Plugin name or Plugin File> - " + Lang.pman_load_desc);
		sender.sendMessage(ChatColor.AQUA + " - /pman unload(or /pman disable) <Plugin name> - " + Lang.pman_unload_desc);
		sender.sendMessage(ChatColor.AQUA + " - /pman download <FileName> <URL> - " + Lang.pman_download_desc);
		sender.sendMessage(ChatColor.AQUA + " - /pman delete <(Current)FileName> <PluginName(or Backup file name)> - " + Lang.pman_delete_desc);
		sender.sendMessage(ChatColor.AQUA + " - /pman restore <FileName> - " + Lang.pman_restore_desc);
		sender.sendMessage(ChatColor.AQUA + " - /pman editor <Dir> <File> <Line(Count from 0)> <value> - " + Lang.pman_editor_desc);
		sender.sendMessage(ChatColor.AQUA + " - /pman viewer <Dir> <File> - " + Lang.pman_viewer_desc);
		sender.sendMessage(ChatColor.AQUA + " - /pman update - " + Lang.pman_update_desc);
		sender.sendMessage(ChatColor.AQUA + " - /pman update-dev - Update to UNSTABLE and DEVELOPER version.");
		sender.sendMessage(ChatColor.AQUA + " - /pman usage <Command> - " + Lang.pman_usage_desc);
		sender.sendMessage(ChatColor.AQUA + " - /pman config language <en_US, ja_JP, ...> - " + Lang.pman_config_language);
		sender.sendMessage(ChatColor.AQUA + " - /pman config reload - " + Lang.pman_config_reload);
		sender.sendMessage(ChatColor.AQUA + " - " + Lang.project_page + ": https://dev.bukkit.org/projects/pluginmanagement/");
		sender.sendMessage(ChatColor.AQUA + " - " + Lang.developer_version + ": http://point.rht0910.tk:8080/job/PluginManager/");
		sender.sendMessage(ChatColor.AQUA + " - " + Lang.source_code + ": https://github.com/rht0910/PluginManager/");
		return true;
	}

	public static boolean HelpOfPlugin(CommandSender sender) {
		Lang.use();
		sender.sendMessage(ChatColor.GREEN + " ----- Plugin Manager[v0.8.3] " + Lang.help + Lang.alpha + " -----");
		sender.sendMessage(ChatColor.RED + " ----- <" + Lang.required + "> [" + Lang.optional + "] - " + Lang.information);
		sender.sendMessage(ChatColor.AQUA + " - /pman help - " + Lang.pman_help_desc);
		sender.sendMessage(ChatColor.AQUA + " - /pman load <Plugin name or Plugin File> - " + Lang.pman_load_desc);
		sender.sendMessage(ChatColor.AQUA + " - /pman unload(or /pman disable) <Plugin name> - " + Lang.pman_unload_desc);
		sender.sendMessage(ChatColor.AQUA + " - /pman download <FileName> <URL> - " + Lang.pman_download_desc);
		sender.sendMessage(ChatColor.AQUA + " - /pman delete <(Current)FileName> <PluginName(or Backup file name)> - " + Lang.pman_delete_desc);
		sender.sendMessage(ChatColor.AQUA + " - /pman restore <FileName> - " + Lang.pman_restore_desc);
		sender.sendMessage(ChatColor.AQUA + " - /pman editor <Dir> <File> <Line(Count from 0)> <value> - " + Lang.pman_editor_desc);
		sender.sendMessage(ChatColor.AQUA + " - /pman viewer <Dir> <File> - " + Lang.pman_viewer_desc);
		sender.sendMessage(ChatColor.AQUA + " - /pman update - " + Lang.pman_update_desc);
		sender.sendMessage(ChatColor.AQUA + " - /pman update-dev - Update to UNSTABLE and DEVELOPER version.");
		sender.sendMessage(ChatColor.AQUA + " - /pman usage <Command> - " + Lang.pman_usage_desc);
		sender.sendMessage(ChatColor.AQUA + " - /pman config language <en_US, ja_JP, ...> - " + Lang.pman_config_language);
		sender.sendMessage(ChatColor.AQUA + " - /pman config reload - " + Lang.pman_config_reload);
		sender.sendMessage(ChatColor.AQUA + " - " + Lang.project_page + ": https://dev.bukkit.org/projects/pluginmanagement/");
		sender.sendMessage(ChatColor.AQUA + " - " + Lang.developer_version + ": http://point.rht0910.tk:8080/job/PluginManager/");
		sender.sendMessage(ChatColor.AQUA + " - " + Lang.source_code + ": https://github.com/rht0910/PluginManager/");
		return true;
	}

	public static boolean getUsageOfCmd(CommandSender sender, String cmd) {
		try {
			String usageof = Bukkit.getPluginCommand(cmd.replaceFirst("/", "")).getUsage();
			String cmd2 = cmd.replaceFirst("/", "");
			String usage = usageof.replaceFirst("/<command>", "/" + cmd2);
			sender.sendMessage(usage);
		} catch(Exception e) {
			sender.sendMessage(ChatColor.RED + "Can't show usage of command.(Command not found)");
			e.printStackTrace();
		}
		return true;
	}
}
