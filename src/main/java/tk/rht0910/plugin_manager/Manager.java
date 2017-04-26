package tk.rht0910.plugin_manager;

import tk.rht0910.plugin_manager.util.Command;
import tk.rht0910.plugin_manager.util.PluginUtils;

public abstract class Manager {
	private static PluginUtils pluginUtils;
	private static Command command;

	static {
		pluginUtils = new PluginUtils();
		command = new Command();
	}

	public static PluginUtils getPluginUtil() {
		return pluginUtils;
	}

	public static Command getCommand() {
		return command;
	}
}
