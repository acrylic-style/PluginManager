package tk.rht0910.plugin_manager.util;

public class StringTool {
	public static Version toVersion(String version) {
		return new Version(version);
	}

	public static int toInteger(String value) throws NumberFormatException {
		return new Integer(value);
	}
}
