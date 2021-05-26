package mapek.probe.sensorfailureplan;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceEvent;
import org.osgi.framework.ServiceListener;

import mapek.interfaces.IMonitor;
import sua.autonomouscar.devices.interfaces.IDistanceSensor;
import sua.autonomouscar.devices.interfaces.ILineSensor;
import sua.autonomouscar.devices.interfaces.IRoadSensor;
import sua.autonomouscar.infrastructure.OSGiUtils;
import sua.autonomouscar.interfaces.IIdentifiable;
import sua.autonomouscar.mapek.infrastructure.monitors.MonitorVehicleContext;


public class Listener implements ServiceListener{
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
				 MonitorVehicleContext monitorVehicleContext =  (MonitorVehicleContext) OSGiUtils.getService(context, IMonitor.class, String.format("(%s=%s)", IIdentifiable.ID, "MonitorVehicleContext"));

				 if(monitorVehicleContext!=null) {
					 if(rightLineSensor==null||rightDistanceSensor==null) {
							//Actualizar el monitor a que NO se puede realizar el cambio/remplazo 
							monitorVehicleContext.changedState(false);
						}else {
							//Actualizar el monitor a que SI se puede realizar el cambio/remplazo 
							monitorVehicleContext.changedState(true);

						}
				 }
				
			
		}
		
	}
}