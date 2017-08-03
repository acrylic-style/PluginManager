package tk.rht0910.plugin_manager;

public final class Lang {
	public static String help = null;
	public static String invalid_args = null;
	public static String required = null;
	public static String optional = null;
	public static String information = null;
	public static String pman_help_desc = null;
	public static String pman_load_desc = null;
	public static String pman_unload_desc = null;
	public static String pman_download_desc = null;
	public static String pman_delete_desc = null;
	public static String pman_restore_desc = null;
	public static String pman_editor_desc = null;
	public static String pman_viewer_desc = null;
	public static String pman_update_desc = null;
	public static String pman_usage_desc = null;
	public static String alpha = null;
	public static String beta = null;

	/**
	 * Initialize a class
	 */
	public static void initialize() {
		 help = (String) LanguageProvider.load("help", "Help");
		 invalid_args = (String) LanguageProvider.load("invalid_args", "Invalid args");
		 required = (String) LanguageProvider.load("requried", "Required");
		 optional = (String) LanguageProvider.load("optional", "Optional");
		 information = (String) LanguageProvider.load("information", "Information");
		 pman_help_desc = (String) LanguageProvider.load("pman_help_desc", "Display this.");
		 pman_load_desc = (String) LanguageProvider.load("pman_load_desc", "Load a plugin.");
		 pman_unload_desc = (String) LanguageProvider.load("pman_unload_desc", "Unload a plugin.");
		 pman_download_desc = (String) LanguageProvider.load("pman_download_desc", "Download a plugin.");
		 pman_delete_desc = (String) LanguageProvider.load("pman_delete_desc", "Remove a plugin.");
		 pman_restore_desc = (String) LanguageProvider.load("pman_restore_desc", "Restore a removed plugin.");
		 pman_editor_desc = (String) LanguageProvider.load("pman_editor_desc", "Edit a plugin config.");
		 pman_viewer_desc = (String) LanguageProvider.load("pman_viewer_desc", "View a plugin config.");
		 pman_update_desc = (String) LanguageProvider.load("pman_update_desc", "Update this plugin.");
		 pman_usage_desc = (String) LanguageProvider.load("pman_usage_desc", "Usage of command.");
		 alpha = (String) LanguageProvider.load("alpha", "(ALPHA)");
		 beta = (String) LanguageProvider.load("beta", "(BETA)");
	}
}
