/*******************************************************************************
 * Software Name : RCS IMS Stack
 *
 * Copyright (C) 2010 France Telecom S.A.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/
package com.orangelabs.rcs.sip;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.MatrixCursor;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.orangelabs.rcs.core.ims.service.sip.SipSessionError;
import com.orangelabs.rcs.service.api.client.ClientApiListener;
import com.orangelabs.rcs.service.api.client.capability.CapabilityApiIntents;
import com.orangelabs.rcs.service.api.client.sip.ISipSession;
import com.orangelabs.rcs.service.api.client.sip.ISipSessionEventListener;
import com.orangelabs.rcs.service.api.client.sip.SipApi;
import com.orangelabs.rcs.sip.utils.Utils;

/**
 * Originating side
 * 
 * @author jexa7410
 */
public class OriginatingSide extends Activity implements ClientApiListener {
	/**
	 * SIP API
	 */
	private SipApi sipApi;

	/**
	 * SIP session
	 */
	private ISipSession sipSession = null;

	/**
	 * Progress dialog
	 */
	private Dialog progressDialog = null;

	/**
	 * UI handler
	 */
	private final Handler handler = new Handler();
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// Set layout
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		setContentView(R.layout.session_originating);

		// Set title
		setTitle(R.string.title_sip_originating);
		
		// Set contact selector
		Spinner spinner = (Spinner)findViewById(R.id.contact);
		spinner.setAdapter(Utils.createContactListAdapter(this));

		// Set buttons callback
		Button initiateBtn = (Button)findViewById(R.id.initiate_btn);
		initiateBtn.setOnClickListener(btnInitiateListener);
		if (spinner.getAdapter().getCount() == 0) {
			initiateBtn.setEnabled(false);
		}
		Button closeBtn = (Button)findViewById(R.id.close_btn);
		closeBtn.setVisibility(View.GONE);
		closeBtn.setOnClickListener(btnCloseListener);

		// Connect to API
		sipApi = new SipApi(getApplicationContext());
		sipApi.addApiEventListener(this);
		sipApi.connectApi();
	}
	
	@Override
	public void onDestroy() {
		super.onDestroy();

		// Remove session listener
		if (sipSession != null) {
			try {
				sipSession.removeSessionListener(sipSessionListener);
			} catch (Exception e) {
			}
		}

		// Disconnect from API
        sipApi.removeApiEventListener(this);
		sipApi.disconnectApi();
	}

	/**
	 * API is disabled (e.g. server not started)
	 */
	public void handleApiDisabled() {
		handler.post(new Runnable(){
			public void run(){
				Utils.showMessageAndExit(OriginatingSide.this, getString(R.string.label_api_disabled));
			}
		});
	}

	/**
	 * API is connected to the server
	 */
	public void handleApiConnected() {
		handler.post(new Runnable(){
			public void run(){
				Button initiateBtn = (Button)findViewById(R.id.initiate_btn);
				initiateBtn.setEnabled(true);
			}
		});
	}

	/**
	 * API is disconnected from the server
	 */
	public void handleApiDisconnected() {
		handler.post(new Runnable(){
			public void run(){
				Utils.showMessageAndExit(OriginatingSide.this, getString(R.string.label_api_disconnected));
			}
		});
	}

	/**
	 * Initiate button callback
	 */
	private OnClickListener btnInitiateListener = new OnClickListener() {
		public void onClick(View v) {
			// Get remote contact
			Spinner spinner = (Spinner)findViewById(R.id.contact);
			MatrixCursor cursor = (MatrixCursor) spinner.getSelectedItem();
			final String remote = cursor.getString(1);

			// Get feature tag
			final String featureTag = CapabilityApiIntents.RCSE_EXTENSION_BASE + "=\"" + CapabilityApiIntents.RCSE_EXTENSION_PREFIX + ".orange.sipdemo\"";

			// Get SDP offer
			final String sdpOffer = SessionSettings.getLocalSdp(OriginatingSide.this);

			// Initiate session in background
			Thread thread = new Thread() {
				public void run() {
					try {
						// Initiate session
						sipSession = sipApi.initiateSession(remote, featureTag,	sdpOffer);
						sipSession.addSessionListener(sipSessionListener);
					} catch (Exception e) {
						handler.post(new Runnable() {
							public void run() {
								hideProgressDialog();
								Utils.showMessageAndExit(OriginatingSide.this, getString(R.string.label_invitation_failed));
							}
						});
					}
				}
			};
			thread.start();

			// Display a progress dialog
			progressDialog = Utils.showProgressDialog(OriginatingSide.this,	getString(R.string.label_session_in_progress));
			progressDialog.setOnCancelListener(new OnCancelListener() {
				public void onCancel(DialogInterface dialog) {
					Toast.makeText(OriginatingSide.this, getString(R.string.label_session_initiation_canceled),	Toast.LENGTH_SHORT).show();
					quitSession();
				}
			});
		}
	};

	/**
	 * Close button callback
	 */
	private OnClickListener btnCloseListener = new OnClickListener() {
		public void onClick(View v) {
			// Close the session
			quitSession();
		}
	};

	/**
	 * Hide progress dialog
	 */
	private void hideProgressDialog() {
		if (progressDialog != null) {
			progressDialog.dismiss();
			progressDialog = null;
		}
	}

	/**
	 * SIP session event listener
	 */
	private ISipSessionEventListener sipSessionListener = new ISipSessionEventListener.Stub() {
		// Session is started
		public void handleSessionStarted() {
			try {
				handler.post(new Runnable() { 
					public void run() {
						// Hide progress dialog
						hideProgressDialog();
					}
				});
	
				// Display session
				Intent intent = new Intent(OriginatingSide.this, SessionInProgress.class);
				intent.putExtra("sessionId", sipSession.getSessionID());
				startActivity(intent);
				
	        	// Exit activity
	        	finish();     
			} catch(Exception e) {
				handler.post(new Runnable(){
					public void run(){
						Utils.showMessageAndExit(OriginatingSide.this, getString(R.string.label_invitation_failed));
					}
				});
			}
		}

		// Session has been aborted
		public void handleSessionAborted(int reason) {
			handler.post(new Runnable() {
				public void run() {
					// Hide progress dialog
					hideProgressDialog();

					// Display message
					Utils.showMessageAndExit(OriginatingSide.this, getString(R.string.label_session_aborted));
				}
			});
		}

		// Session has been terminated by remote
		public void handleSessionTerminatedByRemote() {
			handler.post(new Runnable() {
				public void run() {
					// Hide progress dialog
					hideProgressDialog();

					// Display session status
					Utils.showMessageAndExit(OriginatingSide.this, getString(R.string.label_session_terminated_by_remote));
				}
			});
		}

		// Session error
		public void handleSessionError(final int error) {
			handler.post(new Runnable() {
				public void run() {
					// Hide progress dialog
					hideProgressDialog();

					// Display session status
					if (error == SipSessionError.SESSION_INITIATION_DECLINED) {
						Utils.showMessageAndExit(OriginatingSide.this, getString(R.string.label_session_declined));
					} else {
						Utils.showMessageAndExit(OriginatingSide.this, getString(R.string.label_session_failed,	error));
					}
				}
			});
		}
	};

	/**
	 * Quit the session
	 */
	private void quitSession() {
		// Stop session
		Thread thread = new Thread() {
			public void run() {
				try {
					if (sipSession != null) {
						try {
							sipSession.cancelSession();
							sipSession
									.removeSessionListener(sipSessionListener);
						} catch (Exception e) {
						}
						sipSession = null;
					}
				} catch (Exception e) {
				}
			}
		};
		thread.start();

		// Exit activity
		finish();
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		switch (keyCode) {
		case KeyEvent.KEYCODE_BACK:
			if (sipSession != null) {
				AlertDialog.Builder builder = new AlertDialog.Builder(this);
				builder.setTitle(getString(R.string.label_confirm_close));
				builder.setPositiveButton(getString(R.string.label_ok),
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int which) {
								// Quit the session
								quitSession();
							}
						});
				builder.setNegativeButton(getString(R.string.label_cancel),
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int which) {
								// Exit activity
								finish();
							}
						});
				builder.setCancelable(true);
				builder.show();
			}else {
				finish();
			}
			return true;
		}

		return super.onKeyDown(keyCode, event);
	}
}