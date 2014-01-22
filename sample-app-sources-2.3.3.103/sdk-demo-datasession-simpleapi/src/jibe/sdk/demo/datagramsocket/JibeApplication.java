package jibe.sdk.demo.datagramsocket;

import android.app.Application;
import jibe.sdk.client.apptoapp.Config;

public class JibeApplication extends Application {

	public static final String APP_ID = "19d90f578c9b489b95bfb2cc21d5fb60";
	private static final String APP_SECRET = "a31ef8629bd847dcb206d5e29c0d4a86";

	@Override
	public void onCreate() {
		super.onCreate();
		// set up App-ID and App-Secret in one central place.
		Config.getInstance().setAppToAppIdentifier(APP_ID, APP_SECRET);
	}

}