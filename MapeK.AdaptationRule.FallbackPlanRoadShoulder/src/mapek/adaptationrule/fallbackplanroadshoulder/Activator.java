package mapek.adaptationrule.fallbackplanroadshoulder;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import sua.autonomouscar.mapek.infrastructure.adaptationrules.AdaptationRuleEnteringCityFromJam;
import sua.autonomouscar.mapek.infrastructure.adaptationrules.AdaptationRuleFallbackPlanRoadShoulder;


public class Activator implements BundleActivator {

	private static BundleContext context;
	protected AdaptationRuleFallbackPlanRoadShoulder adaptationRule = null;
	
	static BundleContext getContext() {
		return context;
	}

	public void start(BundleContext bundleContext) throws Exception {
		Activator.context = bundleContext;
		this.adaptationRule = new AdaptationRuleFallbackPlanRoadShoulder(bundleContext,"AdaptationRuleFallbackPlanRoadShoulder");
		this.adaptationRule.registerThing();
		
		
	}

	public void stop(BundleContext bundleContext) throws Exception {
		Activator.context = null;
		if ( this.adaptationRule != null )
			this.adaptationRule.unregisterThing();

	}


}
