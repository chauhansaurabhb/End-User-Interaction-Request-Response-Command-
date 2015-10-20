package logic;

import iotsuite.pubsubmiddleware.*;
import iotsuite.common.GlobalVariable;
import android.content.Context;
import iotsuite.semanticmodel.*;
import android.app.Activity;
import android.app.Dialog;
import framework.*;
import factory.*;

import framework.*;

public class LogicBadgeReader extends BadgeReader {

	IBadgeReader objBadgeReader;
	Activity ui;
	public LogicBadgeReader obj = this;
	Device deviceInfo;
	public String deviceType;

	public LogicBadgeReader(PubSubMiddleware pubSubM, final Device deviceInfo,
			final Object ui, Context myContext) {
		super(pubSubM, deviceInfo);

		deviceType = deviceInfo.getType();

		if (deviceType.equals(GlobalVariable.deviceJAVASEType)) {

			IBadgeReader objBadgeReader = BadgeReaderFactory.getBadgeReader(
					myDeviceInfo.getType(), null, null);

			if (objBadgeReader.isEventDriven()) {

				objBadgeReader.getbadgeDisappeared(badgeDisappearedEvent);

				objBadgeReader.getbadgeDetected(badgeDetectedEvent);

			} else {

				objBadgeReader.getbadgeDisappeared(badgeDisappearedEvent);

				objBadgeReader.getbadgeDetected(badgeDetectedEvent);

			}

		}

	}

	ListenerbadgeDisappeared badgeDisappearedEvent = new ListenerbadgeDisappeared() {

		@Override
		public void onNewbadgeDisappeared(BadgeStruct response) {

			BadgeStruct sBadgeStruct = new BadgeStruct(response.getbadgeID(),
					response.getbadgeEvent());
			setBadgeDisappeared(sBadgeStruct);
		}

	};

	ListenerbadgeDetected badgeDetectedEvent = new ListenerbadgeDetected() {

		@Override
		public void onNewbadgeDetected(BadgeStruct response) {

			BadgeStruct sBadgeStruct = new BadgeStruct(response.getbadgeID(),
					response.getbadgeEvent());
			setBadgeDetected(sBadgeStruct);
		}

	};

}