package jibe.sdk.demo.videocall;

import android.app.Application;
import jibe.sdk.client.apptoapp.Config;

public class JibeApplication extends Application {
	private static final String APP_ID = "50faa7ec8fe14fb891647cb57b367c19";
	private static final String APP_SECRET = "3559b880a7a841b8b0d4ca5ff0a6edb9";

	@Override
	public void onCreate() {
		super.onCreate();
		// set up App-ID and App-Secret in one central place.
		Config.getInstance().setAppToAppIdentifier(APP_ID, APP_SECRET);
	}

}