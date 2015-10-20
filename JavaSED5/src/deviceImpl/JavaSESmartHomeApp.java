package deviceImpl;

import logic.*;
import framework.*;
import android.content.Context;

public class JavaSESmartHomeApp implements ISmartHomeApp {

	private ListenerSmartHomeApp listenerOffCommand = null;

	private ListenerSmartHomeApp listenerSetTempCommand = null;

	private ListenerSmartHomeApp listenerProfileRequest = null;

	public JavaSESmartHomeApp(Context context, LogicSmartHomeApp obj) {

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

	public void ProfileResponseReceived(TempStruct response) {

	}

}