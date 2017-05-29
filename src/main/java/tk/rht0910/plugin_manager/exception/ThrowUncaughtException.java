package tk.rht0910.plugin_manager.exception;

public class ThrowUncaughtException {
	public ThrowUncaughtException() {
	}

	public static void callException(String message) throws UncaughtException {
		throw new UncaughtException(message);
	}
}
