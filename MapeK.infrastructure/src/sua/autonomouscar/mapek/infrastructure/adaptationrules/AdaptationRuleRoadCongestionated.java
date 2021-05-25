package sua.autonomouscar.mapek.infrastructure.adaptationrules;

import org.osgi.framework.BundleContext;

import mapek.interfaces.IAdaptationRule;
import sua.autonomouscar.driving.interfaces.IL3_HighwayChauffer;
import sua.autonomouscar.driving.interfaces.IL3_TrafficJamChauffer;
import sua.autonomouscar.infrastructure.OSGiUtils;
import sua.autonomouscar.infrastructure.Thing;

public class AdaptationRuleRoadCongestionated extends Thing implements IAdaptationRule{

	public AdaptationRuleRoadCongestionated(BundleContext context, String id) {
		super(context, id);
		this.addImplementedInterface(IAdaptationRule.class.getName());
	}

	@Override
	public void executeAdaptation() {
		
		IL3_HighwayChauffer highwayChaufferService = OSGiUtils.getService(context, IL3_HighwayChauffer.class);
		IL3_TrafficJamChauffer trafficJamChaufferService = OSGiUtils.getService(context, IL3_TrafficJamChauffer.class);

		if(highwayChaufferService.isDriving()) {
				highwayChaufferService.stopDriving();
				trafficJamChaufferService.startDriving();
			
		}
		
		
	}

}
