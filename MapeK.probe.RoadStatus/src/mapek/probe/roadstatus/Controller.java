package mapek.probe.roadstatus;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceEvent;
import org.osgi.framework.ServiceListener;
import sua.autonomouscar.devices.interfaces.IRoadSensor;
import sua.autonomouscar.infrastructure.OSGiUtils;
import sua.autonomouscar.interfaces.ERoadStatus;
import sua.autonomouscar.mapek.infrastructure.monitorroadstate.MonitorRoadStatus;

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
				System.out.println("Changed from probe to: "+roadSensor.getRoadStatus().toString());
				MonitorRoadStatus monitor = OSGiUtils.getService(context, MonitorRoadStatus.class);
				
				monitor.changedState(roadSensor.getRoadStatus());
		}
		
	}
}
