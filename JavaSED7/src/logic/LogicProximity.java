package logic;

import iotsuite.pubsubmiddleware.PubSubMiddleware;
import android.content.Context;
import iotsuite.semanticmodel.*;

import framework.*;

public class LogicProximity extends Proximity {

	private double userTempPreference = 0;
	private String userProfile;
	private boolean responseArrived = false;
	private boolean continueFlag = true;

	public LogicProximity(PubSubMiddleware pubSubM, Device deviceInfo,
			Object ui, Context myContext) {
		super(pubSubM, deviceInfo);

	}

	@Override
	public void onNewbadgeDisappeared(BadgeStruct arg) {
		TempStruct tempStruct = new TempStruct(-100, "C");
		settempPref(tempStruct);
	}

	@Override
	public void onNewbadgeDetected(BadgeStruct arg) {
		userProfile = arg.getbadgeID();
		getprofile(userProfile);
	}

	public void onNewprofileReceived(TempStruct arg) {
		userTempPreference = arg.gettempValue();
		TempStruct tempStruct = new TempStruct(userTempPreference, "C");
		settempPref(tempStruct);
	}

}