package jibe.sdk.demo.viewme;

import jibe.sdk.client.apptoapp.Config;
import android.app.Application;

public class JibeApplication extends Application {

	@Override
	public void onCreate() {
		super.onCreate();
		// set up App-ID and App-Secret in one central place.
		Config.getInstance().setAppToAppIdentifier("9fab66429fda410593671f96596e62f6", "7838e75b140d4ae890b71a85aef1b45b");
	}
}
