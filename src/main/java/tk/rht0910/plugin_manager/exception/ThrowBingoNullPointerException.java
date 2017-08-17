package tk.rht0910.plugin_manager.exception;

public class ThrowBingoNullPointerException {
	public ThrowBingoNullPointerException() {
	}

	public static void callException() throws BingoNullPointerException {
		throw new BingoNullPointerException();
	}
}
