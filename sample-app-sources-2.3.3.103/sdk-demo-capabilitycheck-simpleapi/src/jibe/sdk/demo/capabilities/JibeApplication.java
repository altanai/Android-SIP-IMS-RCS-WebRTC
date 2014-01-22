package jibe.sdk.demo.capabilities;

import android.app.Application;
import jibe.sdk.client.apptoapp.Config;

public class JibeApplication extends Application {

	public static final String APP_ID = "02cfac86b4a749209abd2cac8e66e49c";
	private static final String APP_SECRET = "b61e633627ef40b79699329c6e4df859";

	@Override
	public void onCreate() {
		super.onCreate();
		// set up App-ID and App-Secret in one central place.
		Config.getInstance().setAppToAppIdentifier(APP_ID, APP_SECRET);
	}

}
