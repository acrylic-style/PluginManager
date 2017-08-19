package tk.rht0910.plugin_manager;

import java.io.File;
import java.io.IOException;

import com.google.common.io.Files;

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
	public static String pman_config_language = null;
	public static String pman_config_reload = null;
	public static String pman_usage_desc = null;
	public static String pman_version_desc = null;
	public static String pman_version = null;
	public static String alpha = null;
	public static String beta = null;
	public static String failed_update_plugin = null;
	public static String updating_plugin = null;
	public static String success_update_plugin = null;
	public static String you_are_not_operator = null;
	public static String no_permission = null;
	public static String opened_config_viewer = null;
	public static String not_enough_args = null;
	public static String running_on = null;
	public static String available_commands = null;
	public static String project_page = null;
	public static String developer_version = null;
	public static String source_code = null;
	public static String starting_load_plugins = null;
	public static String already_enabled = null;
	public static String failed_load_plugin = null;
	public static String success_load_plugin = null;
	public static String error_occured = null;
	public static String error_unload_plugin = null;
	public static String unloaded_plugin = null;
	public static String reloading_config = null;
	public static String error_reload_config = null;
	public static String reloaded_config = null;
	public static String set_language = null;
	public static String version = null;
	public static String init_complete = null;
	public static String init_error = null;
	public static String please_report_developer = null;
	public static String please_report_developer_catch = null;
	public static String continue_error = null;
	public static String continue_error_catch = null;
	public static String problem_case = null;
	public static String new_version_available = null;
	public static String new_version_available2 = null;
	public static String started_version_check = null;
	public static String already_checking = null;
	public static String version_check_complete1 = null;
	public static String version_check_complete_update_no = null;
	public static String version_check_complete_update1 = null;
	public static String version_check_complete_update2 = null;
	public static String version_check_complete_update3 = null;
	public static String version_check_complete_update4 = null;

	/**
	 * Initialize a class
	 */
	public static void initialize() {
		readFolder();
		use();
	}

	public static void use() {
		 help = (String) LanguageProvider.load("help", "Help");
		 invalid_args = (String) LanguageProvider.load("invalid_args", "Invalid args");
		 required = (String) LanguageProvider.load("required", "Required");
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
		 pman_config_language = (String) LanguageProvider.load("pman_config_language", "Change language");
		 pman_config_reload = (String) LanguageProvider.load("pman_config_reload", "Reload config");
		 pman_usage_desc = (String) LanguageProvider.load("pman_usage_desc", "Usage of command.");
		 alpha = (String) LanguageProvider.load("alpha", "(ALPHA)");
		 beta = (String) LanguageProvider.load("beta", "(BETA)");
		 failed_update_plugin = (String) LanguageProvider.load("failed_update_plugin", "&cFailed to update(Is server down?)");
		 updating_plugin = (String) LanguageProvider.load("updating_plugin", "&aUpdating plugin...");
		 success_update_plugin = (String) LanguageProvider.load("success_update_plugin", "&aSuccess! Updated plugin, please reload.");
		 you_are_not_operator = (String) LanguageProvider.load("you_are_not_operator", "&cYou are not Operator.");
		 no_permission = (String) LanguageProvider.load("no_permission", "&cYou don't have permission.");
		 opened_config_viewer = (String) LanguageProvider.load("opened_config_viewer", "%s opened config viewer.");
		 not_enough_args = (String) LanguageProvider.load("not_enough_args", "&cNot enough args");
		 running_on = (String) LanguageProvider.load("running_on", "&bPluginManager is running on: %s");
		 available_commands = (String) LanguageProvider.load("available_commands", "&bAvailable commands: '/pman help'");
		 project_page = (String) LanguageProvider.load("project_page", "&b - Project page: %s");
		 developer_version = (String) LanguageProvider.load("developer_version", "&b - Developer version: %s");
		 source_code = (String) LanguageProvider.load("source_code", "&b - Source code: %s");
		 starting_load_plugins = (String) LanguageProvider.load("starting_load_plugins", "Starting load plugins in PluginManager...");
		 already_enabled = (String) LanguageProvider.load("already_enabled", "&cPlugin is already enabled!");
		 version = (String) LanguageProvider.load("version", "1.0");
		 pman_version_desc = (String) LanguageProvider.load("pman_version_desc", "Shows plugin version.");
		 pman_version = (String) LanguageProvider.load("pman_version", "&aPluginManaver:&b&n %s");
		 failed_load_plugin = (String) LanguageProvider.load("failed_load_plugin", "&cCouldn't load plugin: %s");
		 success_load_plugin = (String) LanguageProvider.load("success_load_plugin", "&aLoaded plugin: %s");
		 error_occured = (String) LanguageProvider.load("error_occured", "&cAn error occured: %s");
		 error_unload_plugin = (String) LanguageProvider.load("error_unload_plugin", "&cError while unloading plugin: %s");
		 unloaded_plugin = (String) LanguageProvider.load("unloaded_plugin", "&aUnloaded plugin: %s");
		 reloading_config = (String) LanguageProvider.load("reloading_config", "&aReloading config...");
		 error_reload_config = (String) LanguageProvider.load("error_reload_config", "&cError while reloading config: PluginManager");
		 reloaded_config = (String) LanguageProvider.load("reloaded_config", "&aReloaded config: PluginManager");
		 set_language = (String) LanguageProvider.load("set_language", "Set config: language: %s");
		 init_complete = LanguageProvider.load("init_complete", "Initialize complete.");
		 init_error = LanguageProvider.load("init_error", "Error while initializing, disabling plugin, please see errors.");
		 please_report_developer = LanguageProvider.load("please_report_developer", "&4Error cannot be resolved in user. Please report to developer!");
		 please_report_developer_catch = LanguageProvider.load("please_report_developer_catch", "&4Error cannot be resolved in user. Please report to developer! : %s");
		 continue_error = LanguageProvider.load("continue_error", "&4If you see error continuing? please report to developer!");
		 continue_error_catch = LanguageProvider.load("continue_error_catch", "&4If you see error continuing? please report to developer!(Please tell to developer: %s)");
		 problem_case = LanguageProvider.load("problem_case", "&b - In case of problems: %s");
		 new_version_available = LanguageProvider.load("new_version_available", "&aNew version available for PluginManager: Current: %s, New: %s");
		 new_version_available2 = LanguageProvider.load("new_version_available2", "&aRun &b/pman update &ato update.");
		 started_version_check = LanguageProvider.load("started_version_check", "&aRunning version check...");
		 already_checking = LanguageProvider.load("already_checking", "&cAlready version checking!");
		 version_check_complete1 = LanguageProvider.load("version_check_complete1", "&aVersion check complete.");
		 version_check_complete_update_no = LanguageProvider.load("version_check_complete1", "&aNo updates found.");
		 version_check_complete_update1 = LanguageProvider.load("version_check_complete_update1", "&aUpdate found!");
		 version_check_complete_update2 = LanguageProvider.load("version_check_complete_update2", "&bCurrent: %s");
		 version_check_complete_update3 = LanguageProvider.load("version_check_complete_update3", "&eNew: %s");
		 version_check_complete_update4 = LanguageProvider.load("version_check_complete_update4", "&aRun &b/pman update&a to update.");
	}

	  /**
	   * Copy files
	   */
	  public static void readFolder() {
		  Main.getPlugin(Main.class).saveResource("language_en_US.yml", true);
		  Main.getPlugin(Main.class).saveResource("language_af_ZA.yml", true);
		  Main.getPlugin(Main.class).saveResource("language_ar_SA.yml", true);
		  Main.getPlugin(Main.class).saveResource("language_ca_ES.yml", true);
		  Main.getPlugin(Main.class).saveResource("language_cs_CZ.yml", true);
		  Main.getPlugin(Main.class).saveResource("language_da_DK.yml", true);
		  Main.getPlugin(Main.class).saveResource("language_de_DE.yml", true);
		  Main.getPlugin(Main.class).saveResource("language_el_GR.yml", true);
		  Main.getPlugin(Main.class).saveResource("language_es_ES.yml", true);
		  Main.getPlugin(Main.class).saveResource("language_fi_FI.yml", true);
		  Main.getPlugin(Main.class).saveResource("language_fr_FR.yml", true);
		  Main.getPlugin(Main.class).saveResource("language_he_IL.yml", true);
		  Main.getPlugin(Main.class).saveResource("language_hu_HU.yml", true);
		  Main.getPlugin(Main.class).saveResource("language_it_IT.yml", true);
		  Main.getPlugin(Main.class).saveResource("language_ja_JP.yml", true);
		  Main.getPlugin(Main.class).saveResource("language_ko_KR.yml", true);
		  Main.getPlugin(Main.class).saveResource("language_nl_NL.yml", true);
		  Main.getPlugin(Main.class).saveResource("language_no_NO.yml", true);
		  Main.getPlugin(Main.class).saveResource("language_pl_PL.yml", true);
		  Main.getPlugin(Main.class).saveResource("language_pt_BR.yml", true);
		  Main.getPlugin(Main.class).saveResource("language_pt_PT.yml", true);
		  Main.getPlugin(Main.class).saveResource("language_ro_RO.yml", true);
		  Main.getPlugin(Main.class).saveResource("language_ru_RU.yml", true);
		  Main.getPlugin(Main.class).saveResource("language_sr_SP.yml", true);
		  Main.getPlugin(Main.class).saveResource("language_sv_SE.yml", true);
		  Main.getPlugin(Main.class).saveResource("language_tr_TR.yml", true);
		  Main.getPlugin(Main.class).saveResource("language_uk_UA.yml", true);
		  Main.getPlugin(Main.class).saveResource("language_vi_VN.yml", true);
		  Main.getPlugin(Main.class).saveResource("language_zh_CN.yml", true);
		  Main.getPlugin(Main.class).saveResource("language_zh_TW.yml", true);
	  }

	  /**
	   * ファイルの処理
	   * @param filePath
	   */
	  public static void execute( File file ) {
		  File file2 = new File(Main.getPlugin(Main.class).getDataFolder(), file.getName());
	    try {
			if(!file2.exists()) {
				Files.copy(file, file2);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	  }
}
