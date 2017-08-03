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
	public static String pman_usage_desc = null;
	public static String alpha = null;
	public static String beta = null;

	/**
	 * Initialize a class
	 */
	public static void initialize() {
		readFolder();
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

	  /**
	   * ディレクトリを再帰的に読む
	   */
	  public static void readFolder() {

	    File[] files = Main.getPlugin(Main.class).getDataFolder().listFiles();
	    if( files == null )
	      return;
	    for( File file : files ) {
	      if( !file.exists() ) {
	        continue;
	      // else if( file.isDirectory() )
	        // Nothing happened.
	      } else if( file.isFile() && file.canRead() && file.getPath().endsWith(".yml") ) {
	    	  File file2 = new File(Main.getPlugin(Main.class).getDataFolder(), file.getName());
	    	  if(!file2.exists()) {
	    		  if(!(file2.getName() == "plugin.yml")) {
	    			  execute( file );
	    		  }
	    	  }
	      }
	    }
	  }

	  /**
	   * ファイルの処理
	   * @param filePath
	   */
	  public static void execute( File file ) {
		  File file2 = new File(Main.getPlugin(Main.class).getDataFolder(), file.getName());
	    try {
			Files.copy(file, file2);
		} catch (IOException e) {
			e.printStackTrace();
		}
	  }
}
