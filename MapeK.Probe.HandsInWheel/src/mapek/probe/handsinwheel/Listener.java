package mapek.probe.handsinwheel;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceEvent;
import org.osgi.framework.ServiceListener;

import mapek.interfaces.IMonitor;
import mapek.interfaces.enums.EMonitorInteractVibrationStates;
import sua.autonomouscar.devices.interfaces.IDistanceSensor;
import sua.autonomouscar.devices.interfaces.IHandsOnWheelSensor;
import sua.autonomouscar.devices.interfaces.ILineSensor;
import sua.autonomouscar.devices.interfaces.IRoadSensor;
import sua.autonomouscar.infrastructure.OSGiUtils;
import sua.autonomouscar.interfaces.IIdentifiable;
import sua.autonomouscar.mapek.infrastructure.monitors.MonitorFallbackPlanNecessary;
import sua.autonomouscar.mapek.infrastructure.monitors.MonitorInteractVibration;
import sua.autonomouscar.mapek.infrastructure.monitors.MonitorRoadStatus;

public class Listener  implements ServiceListener{
	protected BundleContext context = null;
	
	public Listener(BundleContext context) {
		this.context=context;
	}
	
	@Override
	public void serviceChanged(ServiceEvent event) {
		IHandsOnWheelSensor handsOnWheelSensor = (IHandsOnWheelSensor)this.context.getService(event.getServiceReference());

		switch (event.getType()) {
			case ServiceEvent.REGISTERED:
			case ServiceEvent.MODIFIED:
				MonitorInteractVibration monitorInteractVibration =  (MonitorInteractVibration)OSGiUtils.getService(context, IMonitor.class, String.format("(%s=%s)", IIdentifiable.ID, "MonitorInteractVibration"));
				if(handsOnWheelSensor.areTheHandsOnTheSteeringWheel()) {
					//Actualizar el monitor a que las manos están en el volante
					monitorInteractVibration.changedState(EMonitorInteractVibrationStates.HANDS_ON_WHEEL);
				}else {
					//Actualizar el monitor a que las manos NO están en el volante
					monitorInteractVibration.changedState(EMonitorInteractVibrationStates.HANDS_OFF_WHEEL);

				}
			
		}
		
	}
}
