package sua.autonomouscar.mapek.infrastructure.adaptationrules;

import org.osgi.framework.BundleContext;

import mapek.interfaces.IAdaptationRule;
import sua.autonomouscar.driving.interfaces.IL1_AssistedDriving;
import sua.autonomouscar.driving.interfaces.IL2_AdaptiveCruiseControl;
import sua.autonomouscar.driving.interfaces.IL3_CityChauffer;
import sua.autonomouscar.driving.interfaces.IL3_HighwayChauffer;
import sua.autonomouscar.infrastructure.OSGiUtils;
import sua.autonomouscar.infrastructure.Thing;

public class AdaptationRulesEnteringL2FromL3 extends Thing implements IAdaptationRule {

	public AdaptationRulesEnteringL2FromL3(BundleContext context, String id) {
		super(context, id);
		this.addImplementedInterface(IAdaptationRule.class.getName());
		// TODO Auto-generated constructor stub
	}

	@Override
	public void executeAdaptation() {
		// TODO Auto-generated method stub
		IL3_HighwayChauffer l3HighwayChauffer = OSGiUtils.getService(context, IL3_HighwayChauffer.class);
		IL2_AdaptiveCruiseControl l2AdaptiveCruiseControl = OSGiUtils.getService(context, IL2_AdaptiveCruiseControl.class);
		IL1_AssistedDriving l1AssistedDriving = OSGiUtils.getService(context, IL1_AssistedDriving.class);
		
		
		if(l3HighwayChauffer.isDriving()) {
			l3HighwayChauffer.stopDriving();
			//if(l2AdaptiveCruiseControl.) {} 
			l2AdaptiveCruiseControl.startDriving();
		}
		
	}

}
