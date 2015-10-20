package deviceImpl;

import logic.*;
import framework.*;
import android.content.Context;
import iotsuite.android.localmiddleware.IDataListener;
import iotsuite.android.localmiddleware.PubSubsSensingFramework;

public class AndroidSmartHomeApp implements ISmartHomeApp, IDataListener {

	public static ListenerSmartHomeApp listenerOffCommand = null;
	public static ListenerSmartHomeApp listenerSetTempCommand = null;
	public static PubSubsSensingFramework pubSubSensingFramework;
	public static ListenerSmartHomeApp listenerProfileRequest = null;

	public AndroidSmartHomeApp(Context context, LogicSmartHomeApp obj) {

		pubSubSensingFramework = PubSubsSensingFramework.getInstance();
		pubSubSensingFramework.registerForSensorData(this, "Profile");

	}

	@Override
	public void OffGUI(ListenerSmartHomeApp handler) {
		listenerOffCommand = handler;
	}

	@Override
	public void SetTempGUI(ListenerSmartHomeApp handler) {
		listenerSetTempCommand = handler;
	}

	@Override
	public void ProfileGUI(ListenerSmartHomeApp handler) {
		listenerProfileRequest = handler;
	}

	public void onDataReceived(String eventName, Object data) {
		// TODO : Developer will write Device Driver code.
		// This method will get data from Logic method

	}

}