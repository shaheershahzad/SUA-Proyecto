package mapek.adaptationrule.speakers_interact_3;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import sua.autonomouscar.mapek.infrastructure.adaptationrules.AdaptationRuleSpeakers_INTERACT_3;
import sua.autonomouscar.mapek.infrastructure.adaptationrules.AdaptationRuleVibrationDriverSit;

public class Activator implements BundleActivator {

	private static BundleContext context;
	protected AdaptationRuleSpeakers_INTERACT_3 adaptationRule = null;
	
	static BundleContext getContext() {
		return context;
	}

	public void start(BundleContext bundleContext) throws Exception {
		Activator.context = bundleContext;
		this.adaptationRule = new AdaptationRuleSpeakers_INTERACT_3(bundleContext,"AdaptationRuleSpeakers_INTERACT_3");
		this.adaptationRule.registerThing();
		
		
	}

	public void stop(BundleContext bundleContext) throws Exception {
		Activator.context = null;
		if ( this.adaptationRule != null )
			this.adaptationRule.unregisterThing();

	}

}
