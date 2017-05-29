package tk.rht0910.plugin_manager.exception;

public class ThrowDebugException {
	public ThrowDebugException() {
	}

	public static void callException() throws DebugException {
		throw new DebugException();
	}
}
