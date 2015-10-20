package logic;

import iotsuite.common.GlobalVariable;
import iotsuite.pubsubmiddleware.PubSubMiddleware;
import android.app.Activity;
import iotsuite.semanticmodel.*;
import framework.*;
import factory.*;
import deviceImpl.*;

public class LogicSmartHomeApp extends SmartHomeApp {

	ISmartHomeApp objSmartHomeApp;
	Activity ui;
	public LogicSmartHomeApp obj = this;
	public String deviceType;
	JavaSESmartHomeApp objJavaSESmartHomeApp = null;

	public LogicSmartHomeApp(PubSubMiddleware myPubSubMiddleware,
			final Device deviceInfo, final Object ui) {
		super(myPubSubMiddleware, deviceInfo);
		deviceType = deviceInfo.getType();

		if (deviceType.equals(GlobalVariable.deviceJAVASEType)) {

			objSmartHomeApp = SmartHomeAppFactory.getSmartHomeAppDriver(
					deviceInfo.getType(), (Activity) ui, obj);

			objSmartHomeApp.OffGUI(handlerCommand);

			objSmartHomeApp.SetTempGUI(handlerCommand);

			objSmartHomeApp.ProfileGUI(handlerCommand);

		}

	}

	ListenerSmartHomeApp handlerCommand = new ListenerSmartHomeApp() {

		@Override
		public void onNewOffCommand() {
			OffFromGUI();
		}

		@Override
		public void onNewSetTempCommand(TempStruct newValue) {
			SetTempFromGUI(newValue);
		}

		@Override
		public void onNewProfileRequest(String request) {
			ProfileRequest(request);
		}

	};

	public void onNewProfileResponse(TempStruct arg) {
		objJavaSESmartHomeApp.ProfileResponseReceived(arg);

	}
}