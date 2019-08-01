package tk.rht0910.plugin_manager.util;

import xyz.acrylicstyle.tomeito_core.utils.Log;

public class StringTool {
	public static Version toVersion(String version) throws IllegalArgumentException {
		return new Version(version);
	}

	public static int toInteger(String value) throws NumberFormatException {
		return new Integer(value);
	}

	/**
	 *
	 * @param input
	 * @return Returns true if 0 or "" or null or false
	 * Return false if error or not empty
	 */
	public static <T> boolean isEmpty(T o) {
		try {
			if(o == null) {
				return true;
			} else if(o.toString() == "") {
				return true;
			} else if(Integer.parseInt(o.toString()) == 0) {
				return true;
			} else if(!Boolean.parseBoolean(o.toString())) {
				return true;
			} else {
				return false;
			}
		} catch(NumberFormatException e1) {
			Log.error("String cannot be cast to Integer");
			e1.printStackTrace();
			return false;
		}
	}

	public static <T> boolean isNull(T o) {
		return o == null;
	}
}
