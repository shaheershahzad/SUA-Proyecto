package mapek.probe.roadstatus;

import mapek.interfaces.IMonitor;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceEvent;
import org.osgi.framework.ServiceListener;

import sua.autonomouscar.devices.interfaces.ILineSensor;
import sua.autonomouscar.devices.interfaces.IRoadSensor;
import sua.autonomouscar.infrastructure.OSGiUtils;
import sua.autonomouscar.interfaces.ERoadStatus;
import sua.autonomouscar.interfaces.IIdentifiable;
import sua.autonomouscar.mapek.infrastructure.monitors.MonitorRoadStatus;
import sua.autonomouscar.mapek.infrastructure.monitors.MonitorRoadType;

public class Controller  implements ServiceListener{
	protected BundleContext context = null;
	
	public Controller(BundleContext context) {
		this.context=context;
	}
	
	@Override
	public void serviceChanged(ServiceEvent event) {
		IRoadSensor roadSensor = (IRoadSensor)this.context.getService(event.getServiceReference());
		switch (event.getType()) {
			case ServiceEvent.REGISTERED:
			case ServiceEvent.MODIFIED:
				//System.out.println("Changed from probe to: "+roadSensor.getRoadStatus().toString());
				MonitorRoadStatus monitorRoadStatus =  (MonitorRoadStatus)OSGiUtils.getService(context, IMonitor.class, String.format("(%s=%s)", IIdentifiable.ID, "MonitorRoadStatus"));
				if(monitorRoadStatus!=null) {
					monitorRoadStatus.changedState(roadSensor.getRoadStatus());
				}
				
				MonitorRoadType monitorRoadType =  (MonitorRoadType)OSGiUtils.getService(context, IMonitor.class, String.format("(%s=%s)", IIdentifiable.ID, "MonitorRoadType"));
				if(monitorRoadType!=null) {
					monitorRoadType.changedState(roadSensor.getRoadType());
				}
		}
		
	}
}
