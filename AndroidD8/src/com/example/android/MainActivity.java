package com.example.android;

import framework.TempStruct;
import iotsuite.android.localmiddleware.IDataListener;
import iotsuite.android.localmiddleware.PubSubsSensingFramework;
import android.os.Bundle;

import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.android.R;

public class MainActivity extends Activity implements IDataListener {
	
	public static PubSubsSensingFramework pubSubSensingFramework;

	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		new sim.deviceD8.Startup().startDevice(this,
				this.getApplicationContext());
		
		pubSubSensingFramework = PubSubsSensingFramework.getInstance();
		pubSubSensingFramework.registerForSensorData(this, "profileResponse");

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	@Override
	public void onDataReceived(String eventName, Object data) {
		
	   if(eventName.equals("profileResponse")){
			Log.i("ProfileDB", "I am onDataReceived...MainActivity" +  data);
			TempStruct tempData = (TempStruct) data;
			TextView tv = (TextView) findViewById(R.id.textView1);
			tv.setText(Double.toString(tempData.gettempValue()));
	   }

	}

	public void OffCommandInterface(View view) {
		// TODO : Device Developer will connect appropriate UI element with the
		// below function.
		deviceImpl.AndroidSmartHomeApp.listenerOffCommand.onNewOffCommand();
	}

	public void SetTempCommandInterface(View view) {
		// TODO : Device Developer will connect appropriate UI element with the
		// below function.

		EditText e1 = (EditText) findViewById(R.id.edit_temp);
		String message = e1.getText().toString();
		Double value = Double.parseDouble(message);
		deviceImpl.AndroidSmartHomeApp.listenerSetTempCommand
				.onNewSetTempCommand(new TempStruct(value, "C"));
	}

	
	public void profileRequestInterface(View view){
		EditText editText = (EditText) findViewById(R.id.edit_message);
		String message = editText.getText().toString();
		deviceImpl.AndroidSmartHomeApp.listenerProfileRequest.onNewProfileRequest(message);		
	
	}

}