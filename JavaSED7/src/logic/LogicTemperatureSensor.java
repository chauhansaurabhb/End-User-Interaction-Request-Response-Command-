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

public class LogicTemperatureSensor extends TemperatureSensor {

	ITemperatureSensor objTemperatureSensor;
	Activity ui;
	public LogicTemperatureSensor obj = this;
	Device deviceInfo;
	public String deviceType;

	public LogicTemperatureSensor(PubSubMiddleware pubSubM,
			final Device deviceInfo, final Object ui, Context myContext) {
		super(pubSubM, deviceInfo);

		deviceType = deviceInfo.getType();

		if (deviceType.equals(GlobalVariable.deviceJAVASEType)) {

			ITemperatureSensor objTemperatureSensor = TemperatureSensorFactory
					.getTemperatureSensor(myDeviceInfo.getType(), null, null);

			if (objTemperatureSensor.isEventDriven()) {

				objTemperatureSensor.gettempMeasurement(tempMeasurementEvent);

			} else {

				objTemperatureSensor.gettempMeasurement(tempMeasurementEvent);

			}

		}

	}

	ListenertempMeasurement tempMeasurementEvent = new ListenertempMeasurement() {

		@Override
		public void onNewtempMeasurement(TempStruct response) {

			TempStruct sTempStruct = new TempStruct(response.gettempValue(),
					response.getunitOfMeasurement());
			setTempMeasurement(sTempStruct);
		}

	};

}