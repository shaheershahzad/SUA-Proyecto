package mapek.adaptationrule.enteringl2froml3;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;


import sua.autonomouscar.mapek.infrastructure.adaptationrules.AdaptationRulesEnteringL2FromL3;

/**
 * The activator class controls the plug-in life cycle
 */
public class Activator implements BundleActivator {

	private static BundleContext context;
	protected AdaptationRulesEnteringL2FromL3 adaptationRule = null;
	
	static BundleContext getContext() {
		return context;
	}

	public void start(BundleContext bundleContext) throws Exception {
		Activator.context = bundleContext;
		this.adaptationRule = new AdaptationRulesEnteringL2FromL3(bundleContext,"AdaptationRulesEnteringL2FromL3");
		this.adaptationRule.registerThing();
		
		
	}

	public void stop(BundleContext bundleContext) throws Exception {
		Activator.context = null;
		if ( this.adaptationRule != null )
			this.adaptationRule.unregisterThing();

	}


}