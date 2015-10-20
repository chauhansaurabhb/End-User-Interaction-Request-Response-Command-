package framework;

import iotsuite.pubsubmiddleware.PubSubMiddleware;
import iotsuite.pubsubmiddleware.Subscriber;
import iotsuite.semanticmodel.Device;
import java.util.List;
import iotsuite.common.RegionSubscription;
import iotsuite.common.Logger;

public abstract class SmartHomeApp implements Runnable, Subscriber {

	protected final PubSubMiddleware myPubSubMiddleware;
	protected final Device myDeviceInfo;
	// private String partitionAttribute = "NoInstance";
	private String partitionAttribute = "Room";

	public SmartHomeApp(PubSubMiddleware pubSubM, Device deviceInfo) {

		this.myPubSubMiddleware = pubSubM;
		this.myDeviceInfo = deviceInfo;
		postInitialize();

	}

	protected void postInitialize() {
		subscribeProfile();
	}

	public void OffFromGUI() {

		this.myPubSubMiddleware.publish("Off", null, myDeviceInfo);
	}

	public void SetTempFromGUI(TempStruct newValue) {

		this.myPubSubMiddleware.publish("SetTemp", newValue, myDeviceInfo);
	}

	public void ProfileRequest(String arg) {

		this.myPubSubMiddleware.publish("getProfile", arg, myDeviceInfo);

	}

	public void notifyReceived(String eventName, Object arg, Device deviceInfo) {
		try {

			if (eventName.equals("Profile")) {
				Logger.log(myDeviceInfo.getName(), "SmartHomeApp",
						"Notification Received Profile");
				onNewProfileResponse((TempStruct) arg);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void run() {
	}

	public abstract void onNewProfileResponse(TempStruct arg);

	public void subscribeProfile() {

		List<String> regionInfo = RegionSubscription.getSubscriptionRequest(
				partitionAttribute, myDeviceInfo.getRegionLabels(),
				myDeviceInfo.getRegion());
		this.myPubSubMiddleware.subscribe(this, "Profile", regionInfo);
	}

}