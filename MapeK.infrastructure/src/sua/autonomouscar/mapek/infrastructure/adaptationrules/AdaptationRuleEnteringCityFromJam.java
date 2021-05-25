package sua.autonomouscar.mapek.infrastructure.adaptationrules;

import org.osgi.framework.BundleContext;

import mapek.interfaces.IAdaptationRule;
import sua.autonomouscar.driving.interfaces.IL3_CityChauffer;
import sua.autonomouscar.driving.interfaces.IL3_HighwayChauffer;
import sua.autonomouscar.driving.interfaces.IL3_TrafficJamChauffer;
import sua.autonomouscar.infrastructure.OSGiUtils;
import sua.autonomouscar.infrastructure.Thing;

public class AdaptationRuleEnteringCityFromJam extends Thing implements IAdaptationRule{

	public AdaptationRuleEnteringCityFromJam(BundleContext context, String id) {
		super(context, id);
		this.addImplementedInterface(IAdaptationRule.class.getName());
	}

	@Override
	public void executeAdaptation() {
		
		IL3_TrafficJamChauffer trafficJamChauffer = OSGiUtils.getService(context, IL3_TrafficJamChauffer.class);
		IL3_CityChauffer cityChauffer = OSGiUtils.getService(context, IL3_CityChauffer.class);
		
		
		if(trafficJamChauffer.isDriving()) {
			trafficJamChauffer.stopDriving();
			cityChauffer.startDriving();
		}
		
		
	}

}
