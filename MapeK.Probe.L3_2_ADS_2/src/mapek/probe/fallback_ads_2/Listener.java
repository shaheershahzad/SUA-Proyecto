package mapek.probe.fallback_ads_2;

import mapek.interfaces.IMonitor;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceEvent;
import org.osgi.framework.ServiceListener;

import sua.autonomouscar.devices.interfaces.ILineSensor;
import sua.autonomouscar.devices.interfaces.IRoadSensor;
import sua.autonomouscar.infrastructure.OSGiUtils;
import sua.autonomouscar.interfaces.ERoadStatus;
import sua.autonomouscar.interfaces.IIdentifiable;
import sua.autonomouscar.mapek.infrastructure.monitors.MonitorDrivingConfig_ADS_2;
import sua.autonomouscar.mapek.infrastructure.monitors.MonitorRoadStatus;
import sua.autonomouscar.mapek.infrastructure.monitors.MonitorRoadType;

public class Listener  implements ServiceListener{
	protected BundleContext context = null;
	
	public Listener(BundleContext context) {
		this.context=context;
	}
	
	@Override
	public void serviceChanged(ServiceEvent event) {
		MonitorDrivingConfig_ADS_2 monitorDrivingConfig_ADS_2 =  (MonitorDrivingConfig_ADS_2)OSGiUtils.getService(context, IMonitor.class, String.format("(%s=%s)", IIdentifiable.ID, "MonitorDrivingConfig_ADS_2"));
		switch (event.getType()) {
			case ServiceEvent.REGISTERED:
				if(monitorDrivingConfig_ADS_2 != null) {
					monitorDrivingConfig_ADS_2.changedState(false);
				}
				break;
			case ServiceEvent.UNREGISTERING:
				if(monitorDrivingConfig_ADS_2 != null) {
					monitorDrivingConfig_ADS_2.changedState(true);
				}
				break;
		}
		
	}
}
