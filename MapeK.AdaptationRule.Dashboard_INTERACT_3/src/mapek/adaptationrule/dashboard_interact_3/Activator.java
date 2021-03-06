package mapek.adaptationrule.dashboard_interact_3;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import sua.autonomouscar.mapek.infrastructure.adaptationrules.AdaptationRuleDashboard_INTERACT_3;
import sua.autonomouscar.mapek.infrastructure.adaptationrules.AdaptationRuleSeatAndDisplay_INTERACT_3;

public class Activator implements BundleActivator {

	private static BundleContext context;
	protected AdaptationRuleDashboard_INTERACT_3 adaptationRule = null;
	
	static BundleContext getContext() {
		return context;
	}

	public void start(BundleContext bundleContext) throws Exception {
		Activator.context = bundleContext;
		this.adaptationRule = new AdaptationRuleDashboard_INTERACT_3(bundleContext,"AdaptationRuleDashboard_INTERACT_3");
		this.adaptationRule.registerThing();
		
		
	}

	public void stop(BundleContext bundleContext) throws Exception {
		Activator.context = null;
		if ( this.adaptationRule != null )
			this.adaptationRule.unregisterThing();

	}

}
