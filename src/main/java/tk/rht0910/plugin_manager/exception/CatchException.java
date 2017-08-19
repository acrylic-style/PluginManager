package tk.rht0910.plugin_manager.exception;

import java.lang.Thread.UncaughtExceptionHandler;

public class CatchException implements UncaughtExceptionHandler {
	public void uncaughtException(Thread t, Throwable e) {
		System.out.println(t.getName());
	}
}
