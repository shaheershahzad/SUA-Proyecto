package sua.autonomouscar.mapek.infrastructure.adaptationrules;

import org.osgi.framework.BundleContext;

import mapek.interfaces.IAdaptationRule;
import sua.autonomouscar.driving.interfaces.IL3_DrivingService;
import sua.autonomouscar.driving.interfaces.IL3_HighwayChauffer;
import sua.autonomouscar.driving.interfaces.IL3_TrafficJamChauffer;
import sua.autonomouscar.infrastructure.OSGiUtils;
import sua.autonomouscar.infrastructure.Thing;
import sua.autonomouscar.interaction.interfaces.INotificationService;

public class AdaptationRuleSeatAndDisplay_INTERACT_3 extends Thing implements IAdaptationRule{

	public AdaptationRuleSeatAndDisplay_INTERACT_3(BundleContext context, String id) {
		super(context, id);
		this.addImplementedInterface(IAdaptationRule.class.getName());
	}

	@Override
	public void executeAdaptation() {
		
		System.out.println("Enabling Interaction with Seat Vibration and Driver Display");

		IL3_DrivingService drivingService = OSGiUtils.getService(context, IL3_DrivingService.class);

		if(drivingService.isDriving()) {
			INotificationService notificationService = OSGiUtils.getService(context, INotificationService.class);
			//Se eliminan primero para que no haya duplicidad
			notificationService.removeInteractionMechanism("DriverSeat_HapticVibration");
			notificationService.removeInteractionMechanism("DriverDisplay_VisualText");
			
			notificationService.addInteractionMechanism("DriverSeat_HapticVibration");
			notificationService.addInteractionMechanism("DriverDisplay_VisualText");

		}
		
		
	}

}
