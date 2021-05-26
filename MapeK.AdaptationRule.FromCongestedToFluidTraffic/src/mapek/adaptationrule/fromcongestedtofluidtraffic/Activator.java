package mapek.adaptationrule.fromcongestedtofluidtraffic;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import sua.autonomouscar.mapek.infrastructure.adaptationrules.AdaptationRulesFromCongestedToFluidTraffic;

/**
 * The activator class controls the plug-in life cycle
 */
public class Activator implements BundleActivator {

	private static BundleContext context;
	protected AdaptationRulesFromCongestedToFluidTraffic adaptationRule = null;
	
	static BundleContext getContext() {
		return context;
	}

	public void start(BundleContext bundleContext) throws Exception {
		Activator.context = bundleContext;
		this.adaptationRule = new AdaptationRulesFromCongestedToFluidTraffic(bundleContext,"AdaptationRulesFromCongestedToFluidTraffic");
		this.adaptationRule.registerThing();
		
		
	}

	public void stop(BundleContext bundleContext) throws Exception {
		Activator.context = null;
		if ( this.adaptationRule != null )
			this.adaptationRule.unregisterThing();

	}


}
