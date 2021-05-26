package sua.autonomouscar.mapek.infrastructure.adaptationrules;

import org.osgi.framework.BundleContext;

import mapek.interfaces.IAdaptationRule;
import sua.autonomouscar.driving.interfaces.IL3_CityChauffer;
import sua.autonomouscar.driving.interfaces.IL3_HighwayChauffer;
import sua.autonomouscar.driving.interfaces.IL3_TrafficJamChauffer;
import sua.autonomouscar.infrastructure.OSGiUtils;
import sua.autonomouscar.infrastructure.Thing;

public class ADS_L3_6_1 extends Thing implements IAdaptationRule{

	public ADS_L3_6_1(BundleContext context, String id) {
		super(context, id);
		this.addImplementedInterface(IAdaptationRule.class.getName());
	}

	@Override
	public void executeAdaptation() {
		
		IL3_CityChauffer cityChauffer = OSGiUtils.getService(context, IL3_CityChauffer.class);
		IL3_HighwayChauffer highwayChauffer = OSGiUtils.getService(context, IL3_HighwayChauffer.class);
		
		if(cityChauffer.isDriving()) {
			cityChauffer.stopDriving();
			highwayChauffer.startDriving();
		}
		
		
	}

}
