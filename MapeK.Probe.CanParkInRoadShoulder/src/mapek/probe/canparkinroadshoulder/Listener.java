package mapek.probe.canparkinroadshoulder;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceEvent;
import org.osgi.framework.ServiceListener;

import mapek.interfaces.IMonitor;
import sua.autonomouscar.devices.interfaces.IDistanceSensor;
import sua.autonomouscar.devices.interfaces.ILineSensor;
import sua.autonomouscar.infrastructure.OSGiUtils;
import sua.autonomouscar.interfaces.IIdentifiable;
import sua.autonomouscar.mapek.infrastructure.monitors.MonitorFallbackPlanNecessary;

public class Listener  implements ServiceListener{
	protected BundleContext context = null;
	
	public Listener(BundleContext context) {
		this.context=context;
	}
	
	@Override
	public void serviceChanged(ServiceEvent event) {
		//System.out.println("Detected change: "+event.getType());

		ILineSensor rightLineSensor=OSGiUtils.getService(context, ILineSensor.class, String.format("(%s=%s)", IIdentifiable.ID, "RightLineSensor"));
		IDistanceSensor rightDistanceSensor =	OSGiUtils.getService(context, IDistanceSensor.class, String.format("(%s=%s)", IIdentifiable.ID, "RightDistanceSensor"));

		switch (event.getType()) {
			case ServiceEvent.MODIFIED_ENDMATCH:
			case ServiceEvent.UNREGISTERING:
			case ServiceEvent.REGISTERED:
			case ServiceEvent.MODIFIED:
				MonitorFallbackPlanNecessary monitorFallbackPlanNecessary =  (MonitorFallbackPlanNecessary) OSGiUtils.getService(context, IMonitor.class, String.format("(%s=%s)", IIdentifiable.ID, "MonitorFallbackPlanNecessary"));

				if(monitorFallbackPlanNecessary!=null) {
					if(rightLineSensor==null||rightDistanceSensor==null) {
						//Actualizar el monitor a que NO se puede aparcar en cuneta 
						monitorFallbackPlanNecessary.changedState(false);
					}else {
						//Actualizar el monitor a que SI se puede aparcar en cuneta 
						monitorFallbackPlanNecessary.changedState(true);

					}
				}
				
			
		}
		
	}
}
