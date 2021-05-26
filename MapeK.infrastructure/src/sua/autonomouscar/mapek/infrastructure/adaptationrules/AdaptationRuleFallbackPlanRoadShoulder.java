package sua.autonomouscar.mapek.infrastructure.adaptationrules;

import org.osgi.framework.BundleContext;

import mapek.interfaces.IAdaptationRule;
import sua.autonomouscar.driving.interfaces.IDrivingService;
import sua.autonomouscar.driving.interfaces.IEmergencyFallbackPlan;
import sua.autonomouscar.driving.interfaces.IL3_DrivingService;
import sua.autonomouscar.driving.interfaces.IL3_HighwayChauffer;
import sua.autonomouscar.driving.interfaces.IL3_TrafficJamChauffer;
import sua.autonomouscar.driving.interfaces.IParkInTheRoadShoulderFallbackPlan;
import sua.autonomouscar.infrastructure.OSGiUtils;
import sua.autonomouscar.infrastructure.Thing;

public class AdaptationRuleFallbackPlanRoadShoulder extends Thing implements IAdaptationRule{

	public AdaptationRuleFallbackPlanRoadShoulder(BundleContext context, String id) {
		super(context, id);
		this.addImplementedInterface(IAdaptationRule.class.getName());
	}

	@Override
	public void executeAdaptation() {
		IL3_DrivingService il3_DrivingService= OSGiUtils.getService(context, IL3_DrivingService.class);
		
		if(il3_DrivingService.isDriving()) {
			il3_DrivingService.setFallbackPlan("ParkInTheRoadShoulderFallbackPlan");		
		}
		
		
	}

}
