package mapek.probe.position_interact_3;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceEvent;
import org.osgi.framework.ServiceListener;

import mapek.interfaces.IMonitor;
import mapek.interfaces.enums.EMonitorInteractVibrationStates;
import sua.autonomouscar.devices.interfaces.IDistanceSensor;
import sua.autonomouscar.devices.interfaces.IHandsOnWheelSensor;
import sua.autonomouscar.devices.interfaces.IHumanSensors;
import sua.autonomouscar.devices.interfaces.ILineSensor;
import sua.autonomouscar.devices.interfaces.IRoadSensor;
import sua.autonomouscar.devices.interfaces.ISeatSensor;
import sua.autonomouscar.infrastructure.OSGiUtils;
import sua.autonomouscar.interfaces.IIdentifiable;
import sua.autonomouscar.mapek.infrastructure.monitors.MonitorFallbackPlanNecessary;
import sua.autonomouscar.mapek.infrastructure.monitors.MonitorInteractVibration;
import sua.autonomouscar.mapek.infrastructure.monitors.MonitorPositionInteraction;
import sua.autonomouscar.mapek.infrastructure.monitors.MonitorRoadStatus;

public class Listener  implements ServiceListener{
	protected BundleContext context = null;
	
	public Listener(BundleContext context) {
		this.context=context;
	}
	
	@Override
	public void serviceChanged(ServiceEvent event) {
		ISeatSensor seatSensor = (ISeatSensor)this.context.getService(event.getServiceReference());

		switch (event.getType()) {
			case ServiceEvent.REGISTERED:
			case ServiceEvent.MODIFIED:
				MonitorPositionInteraction monitorPositionInteraction =  (MonitorPositionInteraction)OSGiUtils.getService(context, IMonitor.class, String.format("(%s=%s)", IIdentifiable.ID, "MonitorPositionInteraction"));
				
				if(monitorPositionInteraction!=null) {
					if(seatSensor.isSeatOccuppied()) {
						//Actualizar el monitor a que el asiento del conductor est? ocupado
						monitorPositionInteraction.changedState(EMonitorInteractVibrationStates.DRIVER_SEATED);
					}else {
						//Actualizar el monitor a que el asiento del conductor no est? ocupado
						monitorPositionInteraction.changedState(EMonitorInteractVibrationStates.DRIVER_NOT_SEATED);

					}
				}
				
			
		}
		
	}
}
