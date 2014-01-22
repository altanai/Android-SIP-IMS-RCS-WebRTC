package jibe.sdk.demo.audiocall;

import jibe.sdk.client.JibeIntents;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class AudioCallReceiver extends BroadcastReceiver {
	
	// The manifest file declares that this class will be the handler for incoming calls with 
	// the specific APP_ID of 34cdde44609f481d8c5c438824c27774:
	//
    //    <receiver android:name=".AudioCallReceiver">
	//		<intent-filter>
	//			<action android:name="jibe.intent.action.incomingCall.34cdde44609f481d8c5c438824c27774"></action>.
	//		</intent-filter>		
	//	</receiver>
    //
    // The Android system will pass us the Intent, and will wake us up first, if we are not running.
    
	private static final String LOG_TAG = AudioCallReceiver.class
			.getName();
	
	@Override
	public void onReceive(Context context, Intent intent) {
		// Need to check for action==null since as of Android 4.x applications need to be activated to before they can receive
		// broadcasts which are not explicitly directed at a class. This is done via a "dummy intent".
		if (intent.getAction() == null) {
    		return;
    	}
		Log.d(LOG_TAG, "Broadcast receiver got intent. Action = "+intent.getAction());
		Intent activityIntent = new Intent(context, AudioCallDemo.class);
	
		activityIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_SINGLE_TOP);
	
	    // Copy the necessary information from the incoming Intent to the outgoing Intent.
		activityIntent.putExtras(intent.getExtras());
		activityIntent.setAction(intent.getAction());
	
		Log.d(LOG_TAG, "Broadcast receiver is sending intent. Action = "+activityIntent.getAction());
		long currentCallSession = activityIntent.getLongExtra(JibeIntents.EXTRA_SESSION_ID, -1);
		
		Log.d(LOG_TAG, "Broadcast receiver is sending intent. - session:"+currentCallSession);
		context.startActivity(activityIntent);
	}
}
