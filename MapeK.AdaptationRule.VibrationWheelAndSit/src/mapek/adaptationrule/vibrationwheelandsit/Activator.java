package mapek.adaptationrule.vibrationwheelandsit;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import sua.autonomouscar.mapek.infrastructure.adaptationrules.AdaptationRuleRoadCongestionated;
import sua.autonomouscar.mapek.infrastructure.adaptationrules.AdaptationRuleVibrationWheelAndSit;

public class Activator implements BundleActivator {

	private static BundleContext context;
	protected AdaptationRuleVibrationWheelAndSit adaptationRule = null;
	
	static BundleContext getContext() {
		return context;
	}

	public void start(BundleContext bundleContext) throws Exception {
		Activator.context = bundleContext;
		this.adaptationRule = new AdaptationRuleVibrationWheelAndSit(bundleContext,"AdaptationRuleVibrationWheelAndSit");
		this.adaptationRule.registerThing();
		
		
	}

	public void stop(BundleContext bundleContext) throws Exception {
		Activator.context = null;
		if ( this.adaptationRule != null )
			this.adaptationRule.unregisterThing();

	}


}
