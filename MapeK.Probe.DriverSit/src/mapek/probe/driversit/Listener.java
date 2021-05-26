package mapek.probe.driversit;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceEvent;
import org.osgi.framework.ServiceListener;

import mapek.interfaces.IMonitor;
import mapek.interfaces.enums.EMonitorInteractVibrationStates;
import sua.autonomouscar.devices.interfaces.IDistanceSensor;
import sua.autonomouscar.devices.interfaces.ILineSensor;
import sua.autonomouscar.devices.interfaces.ISeatSensor;
import sua.autonomouscar.infrastructure.OSGiUtils;
import sua.autonomouscar.interfaces.IIdentifiable;
import sua.autonomouscar.mapek.infrastructure.monitors.MonitorFallbackPlanNecessary;
import sua.autonomouscar.mapek.infrastructure.monitors.MonitorInteractVibration;

public class Listener  implements ServiceListener{
	protected BundleContext context = null;
	
	public Listener(BundleContext context) {
		this.context=context;
	}
	
	@Override
	public void serviceChanged(ServiceEvent event) {
		//System.out.println("Detected change: "+event.getType());

		ISeatSensor seatSensor = OSGiUtils.getService(context, ISeatSensor.class, String.format("(%s=%s)", IIdentifiable.ID, "DriverSeatSensor"));

		if(seatSensor!=null) {
			switch (event.getType()) {
			case ServiceEvent.REGISTERED:
			case ServiceEvent.MODIFIED:
				MonitorInteractVibration monitorInteractVibration =  (MonitorInteractVibration)OSGiUtils.getService(context, IMonitor.class, String.format("(%s=%s)", IIdentifiable.ID, "MonitorInteractVibration"));
				if(monitorInteractVibration!=null) {
					if(seatSensor.isSeatOccuppied()) {
						//Actualizar el monitor a que el asiento del piloto está ocupado
						monitorInteractVibration.changedState(EMonitorInteractVibrationStates.DRIVER_SEATED);
					}else {
						//Actualizar el monitor a que el asiento del piloto no está ocupado
						monitorInteractVibration.changedState(EMonitorInteractVibrationStates.DRIVER_NOT_SEATED);

					}
				}
				
			
			}
		}
		
	}
}
