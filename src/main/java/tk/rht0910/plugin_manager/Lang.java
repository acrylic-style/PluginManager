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
	}

	  /**
	   * ディレクトリを再帰的に読む(old description)
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
		  /*InputStream is1 = Main.class.getClassLoader().getResourceAsStream("language_en_US.yml");
		  InputStream is2 = Main.class.getClassLoader().getResourceAsStream("language_af_ZA.yml");
		  InputStream is3 = Main.class.getClassLoader().getResourceAsStream("language_ar_SA.yml");
		  InputStream is4 = Main.class.getClassLoader().getResourceAsStream("language_ca_ES.yml");
		  InputStream is5 = Main.class.getClassLoader().getResourceAsStream("language_ca_CZ.yml");
		  InputStream is6 = Main.class.getClassLoader().getResourceAsStream("language_da_DK.yml");
		  InputStream is7 = Main.class.getClassLoader().getResourceAsStream("language_de_DE.yml");
		  InputStream is8 = Main.class.getClassLoader().getResourceAsStream("language_el_GR.yml");
		  InputStream is9 = Main.class.getClassLoader().getResourceAsStream("language_es_ES.yml");
		  InputStream is10 = Main.class.getClassLoader().getResourceAsStream("language_fi_FI.yml");
		  InputStream is11 = Main.class.getClassLoader().getResourceAsStream("language_fr_FR.yml");
		  InputStream is12 = Main.class.getClassLoader().getResourceAsStream("language_he_IL.yml");
		  InputStream is13 = Main.class.getClassLoader().getResourceAsStream("language_hu_HU.yml");
		  InputStream is14 = Main.class.getClassLoader().getResourceAsStream("language_it_IT.yml");
		  InputStream is15 = Main.class.getClassLoader().getResourceAsStream("language_ja_JP.yml");
		  InputStream is16 = Main.class.getClassLoader().getResourceAsStream("language_ko_KR.yml");
		  InputStream is17 = Main.class.getClassLoader().getResourceAsStream("language_nl_NL.yml");
		  InputStream is18 = Main.class.getClassLoader().getResourceAsStream("language_no_NO.yml");
		  InputStream is19 = Main.class.getClassLoader().getResourceAsStream("language_pl_PL.yml");
		  InputStream is20 = Main.class.getClassLoader().getResourceAsStream("language_pt_BR.yml");
		  InputStream is21 = Main.class.getClassLoader().getResourceAsStream("language_pt_PT.yml");
		  InputStream is22 = Main.class.getClassLoader().getResourceAsStream("language_ro_RO.yml");
		  InputStream is23 = Main.class.getClassLoader().getResourceAsStream("language_ru_RU.yml");
		  InputStream is24 = Main.class.getClassLoader().getResourceAsStream("language_sr_SP.yml");
		  InputStream is25 = Main.class.getClassLoader().getResourceAsStream("language_sv_SE.yml");
		  InputStream is26 = Main.class.getClassLoader().getResourceAsStream("language_tr_TR.yml");
		  InputStream is27 = Main.class.getClassLoader().getResourceAsStream("language_uk_UA.yml");
		  InputStream is28 = Main.class.getClassLoader().getResourceAsStream("language_vi_VN.yml");
		  InputStream is29 = Main.class.getClassLoader().getResourceAsStream("language_zh_CN.yml");
		  InputStream is30 = Main.class.getClassLoader().getResourceAsStream("language_zh_CW.yml");
		  try {
			  FileOutputStream fos1 = new FileOutputStream(new File(Main.getPlugin(Main.class).getDataFolder(), "language_en_US.yml"));
			  FileOutputStream fos2 = new FileOutputStream(new File(Main.getPlugin(Main.class).getDataFolder(), "language_af_ZA.yml"));
			  FileOutputStream fos3 = new FileOutputStream(new File(Main.getPlugin(Main.class).getDataFolder(), "language_ar_SA.yml"));
			  FileOutputStream fos4 = new FileOutputStream(new File(Main.getPlugin(Main.class).getDataFolder(), "language_ca_ES.yml"));
			  FileOutputStream fos5 = new FileOutputStream(new File(Main.getPlugin(Main.class).getDataFolder(), "language_ca_CZ.yml"));
			  FileOutputStream fos6 = new FileOutputStream(new File(Main.getPlugin(Main.class).getDataFolder(), "language_da_DK.yml"));
			  FileOutputStream fos7 = new FileOutputStream(new File(Main.getPlugin(Main.class).getDataFolder(), "language_de_DE.yml"));
			  FileOutputStream fos8 = new FileOutputStream(new File(Main.getPlugin(Main.class).getDataFolder(), "language_el_GR.yml"));
			  FileOutputStream fos9 = new FileOutputStream(new File(Main.getPlugin(Main.class).getDataFolder(), "language_es_ES.yml"));
			  FileOutputStream fos10 = new FileOutputStream(new File(Main.getPlugin(Main.class).getDataFolder(), "language_fi_FI.yml"));
			  FileOutputStream fos11 = new FileOutputStream(new File(Main.getPlugin(Main.class).getDataFolder(), "language_fr_FR.yml"));
			  FileOutputStream fos12 = new FileOutputStream(new File(Main.getPlugin(Main.class).getDataFolder(), "language_he_IL.yml"));
			  FileOutputStream fos13 = new FileOutputStream(new File(Main.getPlugin(Main.class).getDataFolder(), "language_hu_HU.yml"));
			  FileOutputStream fos14 = new FileOutputStream(new File(Main.getPlugin(Main.class).getDataFolder(), "language_it_IT.yml"));
			  FileOutputStream fos15 = new FileOutputStream(new File(Main.getPlugin(Main.class).getDataFolder(), "language_ja_JP.yml"));
			  FileOutputStream fos16 = new FileOutputStream(new File(Main.getPlugin(Main.class).getDataFolder(), "language_ko_KR.yml"));
			  FileOutputStream fos17 = new FileOutputStream(new File(Main.getPlugin(Main.class).getDataFolder(), "language_nl_NL.yml"));
			  FileOutputStream fos18 = new FileOutputStream(new File(Main.getPlugin(Main.class).getDataFolder(), "language_no_NO.yml"));
			  FileOutputStream fos19 = new FileOutputStream(new File(Main.getPlugin(Main.class).getDataFolder(), "language_pl_PL.yml"));
			  FileOutputStream fos20 = new FileOutputStream(new File(Main.getPlugin(Main.class).getDataFolder(), "language_pt_BR.yml"));
			  FileOutputStream fos21 = new FileOutputStream(new File(Main.getPlugin(Main.class).getDataFolder(), "language_pt_PT.yml"));
			  FileOutputStream fos22 = new FileOutputStream(new File(Main.getPlugin(Main.class).getDataFolder(), "language_ro_RO.yml"));
			  FileOutputStream fos23 = new FileOutputStream(new File(Main.getPlugin(Main.class).getDataFolder(), "language_ru_RU.yml"));
			  FileOutputStream fos24 = new FileOutputStream(new File(Main.getPlugin(Main.class).getDataFolder(), "language_sr_SP.yml"));
			  FileOutputStream fos25 = new FileOutputStream(new File(Main.getPlugin(Main.class).getDataFolder(), "language_sv_SE.yml"));
			  FileOutputStream fos26 = new FileOutputStream(new File(Main.getPlugin(Main.class).getDataFolder(), "language_tr_TR.yml"));
			  FileOutputStream fos27 = new FileOutputStream(new File(Main.getPlugin(Main.class).getDataFolder(), "language_uk_UA.yml"));
			  FileOutputStream fos28 = new FileOutputStream(new File(Main.getPlugin(Main.class).getDataFolder(), "language_vi_VN.yml"));
			  FileOutputStream fos29 = new FileOutputStream(new File(Main.getPlugin(Main.class).getDataFolder(), "language_zh_CN.yml"));
			  FileOutputStream fos30 = new FileOutputStream(new File(Main.getPlugin(Main.class).getDataFolder(), "language_zh_CW.yml"));
			  IOUtils.copy(is1, fos1);
			  IOUtils.copy(is2, fos2);
			  IOUtils.copy(is3, fos3);
			  IOUtils.copy(is4, fos4);
			  IOUtils.copy(is5, fos5);
			  IOUtils.copy(is6, fos6);
			  IOUtils.copy(is7, fos7);
			  IOUtils.copy(is8, fos8);
			  IOUtils.copy(is9, fos9);
			  IOUtils.copy(is10, fos10);
			  IOUtils.copy(is11, fos11);
			  IOUtils.copy(is12, fos12);
			  IOUtils.copy(is13, fos13);
			  IOUtils.copy(is14, fos14);
			  IOUtils.copy(is15, fos15);
			  IOUtils.copy(is16, fos16);
			  IOUtils.copy(is17, fos17);
			  IOUtils.copy(is18, fos18);
			  IOUtils.copy(is19, fos19);
			  IOUtils.copy(is20, fos20);
			  IOUtils.copy(is21, fos21);
			  IOUtils.copy(is22, fos22);
			  IOUtils.copy(is23, fos23);
			  IOUtils.copy(is24, fos24);
			  IOUtils.copy(is25, fos25);
			  IOUtils.copy(is26, fos26);
			  IOUtils.copy(is27, fos27);
			  IOUtils.copy(is28, fos28);
			  IOUtils.copy(is29, fos29);
			  IOUtils.copy(is30, fos30);
			  IOUtils.closeQuietly(is1);
			  IOUtils.closeQuietly(fos1);
			  IOUtils.closeQuietly(is2);
			  IOUtils.closeQuietly(fos2);
			  IOUtils.closeQuietly(is3);
			  IOUtils.closeQuietly(fos3);
			  IOUtils.closeQuietly(is4);
			  IOUtils.closeQuietly(fos4);
			  IOUtils.closeQuietly(is5);
			  IOUtils.closeQuietly(fos5);
			  IOUtils.closeQuietly(is6);
			  IOUtils.closeQuietly(fos6);
			  IOUtils.closeQuietly(is7);
			  IOUtils.closeQuietly(fos7);
			  IOUtils.closeQuietly(is8);
			  IOUtils.closeQuietly(fos8);
			  IOUtils.closeQuietly(is9);
			  IOUtils.closeQuietly(fos9);
			  IOUtils.closeQuietly(is10);
			  IOUtils.closeQuietly(fos10);
			  IOUtils.closeQuietly(is11);
			  IOUtils.closeQuietly(fos11);
			  IOUtils.closeQuietly(is12);
			  IOUtils.closeQuietly(fos12);
			  IOUtils.closeQuietly(is13);
			  IOUtils.closeQuietly(fos13);
			  IOUtils.closeQuietly(is14);
			  IOUtils.closeQuietly(fos14);
			  IOUtils.closeQuietly(is15);
			  IOUtils.closeQuietly(fos15);
			  IOUtils.closeQuietly(is16);
			  IOUtils.closeQuietly(fos16);
			  IOUtils.closeQuietly(is17);
			  IOUtils.closeQuietly(fos17);
			  IOUtils.closeQuietly(is18);
			  IOUtils.closeQuietly(fos18);
			  IOUtils.closeQuietly(is19);
			  IOUtils.closeQuietly(fos19);
			  IOUtils.closeQuietly(is20);
			  IOUtils.closeQuietly(fos20);
			  IOUtils.closeQuietly(is21);
			  IOUtils.closeQuietly(fos21);
			  IOUtils.closeQuietly(is22);
			  IOUtils.closeQuietly(fos22);
			  IOUtils.closeQuietly(is23);
			  IOUtils.closeQuietly(fos23);
			  IOUtils.closeQuietly(is24);
			  IOUtils.closeQuietly(fos24);
			  IOUtils.closeQuietly(is25);
			  IOUtils.closeQuietly(fos25);
			  IOUtils.closeQuietly(is26);
			  IOUtils.closeQuietly(fos26);
			  IOUtils.closeQuietly(is27);
			  IOUtils.closeQuietly(fos27);
			  IOUtils.closeQuietly(is28);
			  IOUtils.closeQuietly(fos28);
			  IOUtils.closeQuietly(is29);
			  IOUtils.closeQuietly(fos29);
			  IOUtils.closeQuietly(is30);
			  IOUtils.closeQuietly(fos30);

		} catch (Exception | Error e) {
			e.printStackTrace();
		}
/*
	    File[] files = {
	    		new File("")
	    		};
	    if( files == null )
	      return;
	    for( File file : files ) {
	      if( !file.exists() ) {
	        continue;
	      // else if( file.isDirectory() )
	        // Nothing happened.
	      } else if( file.isFile() && file.canRead() && file.getPath().endsWith(".yml") ) {
	    		  execute( file );
	      }
	    }*/
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
