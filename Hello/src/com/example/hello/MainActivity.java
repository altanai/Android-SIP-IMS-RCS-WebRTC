package com.example.hello;


import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.Menu;
import android.widget.Button;
import android.widget.EditText;
import android.view.View.OnClickListener;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity {

	private Button button1;
	EditText username,password;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);


		username = (EditText)findViewById(R.id.editText1);
		password = (EditText)findViewById(R.id.editText2);
		button1=(Button)findViewById(R.id.button1);
		button1.setOnClickListener(button1Listener);    



	}
	public void onBackPressed() {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setCancelable(false);
		builder.setMessage("Do you want to Exit?");
		builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

			public void onClick(DialogInterface dialog, int which) {

				finish();
			}
		});
		builder.setNegativeButton("No",new DialogInterface.OnClickListener() {

			public void onClick(DialogInterface dialog, int which) {

				dialog.cancel();
			}
		});
		AlertDialog alert=builder.create();
		alert.show();
	}    



	private OnClickListener button1Listener=new  OnClickListener(){
		public void onClick(View v1){
			if(username.getText().toString().equals("") || password.getText().toString().equals(""))
			{
				AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
				alertDialogBuilder.setTitle("Error!");
				alertDialogBuilder
				.setMessage("Please enter your Login credentials!")
				.setCancelable(false)
				.setNeutralButton("OK",new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog,int id) {

						dialog.cancel();
					}
				});

				AlertDialog alertDialog = alertDialogBuilder.create();

				alertDialog.show();   			
			}


			if(username.getText().toString().equals("abhishek")&& password.getText().toString().equals("tcs"))
			{
				Intent homepage = new Intent(MainActivity.this, Wait.class);
				startActivity(homepage);
				finish();                       


			}
			else if(username.getText().toString().equals("kartik")&& password.getText().toString().equals("tcs"))
			{   		

				Intent homepage = new Intent(MainActivity.this, Wait.class);
				startActivity(homepage);
				finish();  


			}
			else if(!(username.getText().toString().equals("abhishek")&& password.getText().toString().equals("tcs")) &&(!username.getText().toString().equals("")&& !password.getText().toString().equals("")&&!(username.getText().toString().equals("kartik")&& password.getText().toString().equals("tcs")) &&(!username.getText().toString().equals("")&& !password.getText().toString().equals("")) ) )
			{

				AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
				alertDialogBuilder.setTitle("Error!");
				alertDialogBuilder
				.setMessage("Username and Password doesn't match")
				.setCancelable(false)
				.setNeutralButton("OK",new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog,int id) {

						dialog.cancel();
						username.setText("");
						password.setText("");
						username.requestFocus();

					}
				});

				AlertDialog alertDialog = alertDialogBuilder.create();

				alertDialog.show(); 


			}

		}
	};  





	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}


