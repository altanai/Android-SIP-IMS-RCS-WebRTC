package jibe.sdk.demo.audiocall;

import android.app.Application;
import jibe.sdk.client.apptoapp.Config;

public class JibeApplication extends Application {

	private static final String APP_ID = "34cdde44609f481d8c5c438824c27774";
	private static final String APP_SECRET = "f2c3a038667546a18f3799ce569ceb69";

	@Override
	public void onCreate() {
		super.onCreate();
		// set up App-ID and App-Secret in one central place.
		Config.getInstance().setAppToAppIdentifier(APP_ID, APP_SECRET);
	}

}