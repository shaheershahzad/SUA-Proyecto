package sua.autonomouscar.mapek.infrastructure.adaptationrules;

import org.osgi.framework.BundleContext;

import mapek.interfaces.IAdaptationRule;
import sua.autonomouscar.driving.interfaces.IL3_HighwayChauffer;
import sua.autonomouscar.driving.interfaces.IL3_TrafficJamChauffer;
import sua.autonomouscar.infrastructure.OSGiUtils;
import sua.autonomouscar.infrastructure.Thing;

public class AdaptationRulesFromCongestedToFluidTraffic extends Thing implements IAdaptationRule {

	public AdaptationRulesFromCongestedToFluidTraffic(BundleContext context, String id) {
		super(context, id);
		this.addImplementedInterface(IAdaptationRule.class.getName());
		// TODO Auto-generated constructor stub
	}

	@Override
	public void executeAdaptation() {
		// TODO Auto-generated method stub
		IL3_TrafficJamChauffer l3TrafficJamChauffer = OSGiUtils.getService(context, IL3_TrafficJamChauffer.class);
		IL3_HighwayChauffer l3HighwayChauffer = OSGiUtils.getService(context, IL3_HighwayChauffer.class);
		
		//IL1_AssistedDriving l1AssistedDriving = OSGiUtils.getService(context, IL1_AssistedDriving.class);
		
		
		if(l3TrafficJamChauffer.isDriving()) {
			l3TrafficJamChauffer.stopDriving();
			//if(l2AdaptiveCruiseControl.) {} 
			l3HighwayChauffer.startDriving();
			System.out.println("Executing adaptation rule: road is HighwayChauffer");
			
		}
		
	}

}
