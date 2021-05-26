package mapek.probe.roadstatus;

import mapek.interfaces.IMonitor;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceEvent;
import org.osgi.framework.ServiceListener;

import sua.autonomouscar.devices.interfaces.IRoadSensor;
import sua.autonomouscar.infrastructure.OSGiUtils;
import sua.autonomouscar.interfaces.IIdentifiable;
import sua.autonomouscar.mapek.infrastructure.monitors.MonitorRoadStatus;

public class Listener  implements ServiceListener{
	protected BundleContext context = null;
	
	public Listener(BundleContext context) {
		this.context=context;
	}
	
	@Override
	public void serviceChanged(ServiceEvent event) {
		IRoadSensor roadSensor = (IRoadSensor)this.context.getService(event.getServiceReference());
		switch (event.getType()) {
			case ServiceEvent.REGISTERED:
			case ServiceEvent.MODIFIED:
				MonitorRoadStatus monitorRoadStatus =  (MonitorRoadStatus)OSGiUtils.getService(context, IMonitor.class, String.format("(%s=%s)", IIdentifiable.ID, "MonitorRoadStatus"));
				if(monitorRoadStatus!=null) {
					monitorRoadStatus.changedState(roadSensor.getRoadStatus());
				}
				
		}
		
	}
}

package mapek.probe.roadstatus;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceEvent;
import org.osgi.framework.ServiceListener;

import mapek.interfaces.IMonitor;
import sua.autonomouscar.devices.interfaces.IRoadSensor;
import sua.autonomouscar.infrastructure.OSGiUtils;
import sua.autonomouscar.interfaces.IIdentifiable;
import sua.autonomouscar.mapek.infrastructure.monitors.MonitorRoadStatus;

public class Listener  implements ServiceListener{
	protected BundleContext context = null;
	
	public Listener(BundleContext context) {
		this.context=context;
	}
	
	@Override
	public void serviceChanged(ServiceEvent event) {
		IRoadSensor roadSensor = (IRoadSensor)this.context.getService(event.getServiceReference());
		switch (event.getType()) {
			case ServiceEvent.REGISTERED:
			case ServiceEvent.MODIFIED:
				MonitorRoadStatus monitorRoadStatus =  (MonitorRoadStatus)OSGiUtils.getService(context, IMonitor.class, String.format("(%s=%s)", IIdentifiable.ID, "MonitorRoadStatus"));
				if(monitorRoadStatus!=null) {
					monitorRoadStatus.changedState(roadSensor.getRoadStatus());
				}
				
		}
		
	}
}
