package jibe.sdk.demo.datagramsocket;

import jibe.sdk.client.JibeIntents;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class SessionReceiver extends BroadcastReceiver {
	
	// The manifest file declares that this class will be the handler for incoming sessions with 
	// the specific APP_ID of 19d90f578c9b489b95bfb2cc21d5fb60:
	//
    //    <receiver android:name=".SessionReceiver">
	//		<intent-filter>
	//			<action android:name="jibe.intent.action.incomingSession.19d90f578c9b489b95bfb2cc21d5fb60"></action>.
	//		</intent-filter>		
	//	</receiver>
    //
    // The Android system will pass us the Intent, and will wake us up first, if we are not running.
    
	private static final String LOG_TAG = SessionReceiver.class
			.getName();
	
	@Override
	public void onReceive(Context context, Intent intent) {
		// Need to check for action==null since as of Android 4.x applications need to be activated to before they can receive
		// broadcasts which are not explicitly directed at a class. This is done via a "dummy intent".
		if (intent.getAction() == null) {
    		return;
    	}
		Log.d(LOG_TAG, "Broadcast receiver got intent. Action = "+intent.getAction());
		Intent activityIntent = new Intent(context, DatagramSocketConnectionDemo.class);
	
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
