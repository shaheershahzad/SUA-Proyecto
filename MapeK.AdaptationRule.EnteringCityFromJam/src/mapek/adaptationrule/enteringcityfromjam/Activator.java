package mapek.adaptationrule.enteringcityfromjam;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import sua.autonomouscar.mapek.infrastructure.adaptationrules.AdaptationRuleEnteringCityFromJam;


public class Activator implements BundleActivator {

	private static BundleContext context;
	protected AdaptationRuleEnteringCityFromJam adaptationRuleEnteringCityFromJam = null;
	
	static BundleContext getContext() {
		return context;
	}

	public void start(BundleContext bundleContext) throws Exception {
		Activator.context = bundleContext;
		this.adaptationRuleEnteringCityFromJam = new AdaptationRuleEnteringCityFromJam(bundleContext,"AdaptationRuleEnteringCityFromJam");
		this.adaptationRuleEnteringCityFromJam.registerThing();
		
		
	}

	public void stop(BundleContext bundleContext) throws Exception {
		Activator.context = null;
		if ( this.adaptationRuleEnteringCityFromJam != null )
			this.adaptationRuleEnteringCityFromJam.unregisterThing();

	}


}
