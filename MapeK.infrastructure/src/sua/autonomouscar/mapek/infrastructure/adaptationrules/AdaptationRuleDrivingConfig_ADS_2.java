package sua.autonomouscar.mapek.infrastructure.adaptationrules;

import org.osgi.framework.BundleContext;

import mapek.interfaces.IAdaptationRule;
import sua.autonomouscar.driving.interfaces.IDrivingService;
import sua.autonomouscar.driving.interfaces.IEmergencyFallbackPlan;
import sua.autonomouscar.driving.interfaces.IL0_ManualDriving;
import sua.autonomouscar.driving.interfaces.IL3_CityChauffer;
import sua.autonomouscar.driving.interfaces.IL3_DrivingService;
import sua.autonomouscar.driving.interfaces.IL3_HighwayChauffer;
import sua.autonomouscar.driving.interfaces.IL3_TrafficJamChauffer;
import sua.autonomouscar.driving.interfaces.IParkInTheRoadShoulderFallbackPlan;
import sua.autonomouscar.infrastructure.OSGiUtils;
import sua.autonomouscar.infrastructure.Thing;

public class AdaptationRuleDrivingConfig_ADS_2 extends Thing implements IAdaptationRule{

	public AdaptationRuleDrivingConfig_ADS_2(BundleContext context, String id) {
		super(context, id);
		this.addImplementedInterface(IAdaptationRule.class.getName());
	}

	@Override
	public void executeAdaptation() {
		IL0_ManualDriving manualDriving = OSGiUtils.getService(context, IL0_ManualDriving.class);
		
		
		manualDriving.startDriving();
		
		
	}

}
