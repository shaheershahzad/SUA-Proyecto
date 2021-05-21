package mapek.adaptationrule.roadcongestionated;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import sua.autonomouscar.devices.interfaces.IRoadSensor;
import sua.autonomouscar.mapek.infrastructure.adaptationrules.AdaptationRuleRoadCongestionated;
import sua.autonomouscar.mapek.infrastructure.monitors.MonitorRoadStatus;
import sua.autonomouscar.mapek.infrastructure.monitors.MonitorRoadType;

public class Activator implements BundleActivator {

	private static BundleContext context;
	protected AdaptationRuleRoadCongestionated adaptationRuleRoadCongestionated = null;
	
	static BundleContext getContext() {
		return context;
	}

	public void start(BundleContext bundleContext) throws Exception {
		Activator.context = bundleContext;
		this.adaptationRuleRoadCongestionated = new AdaptationRuleRoadCongestionated(bundleContext,"AdaptationRuleRoadCongestionated");
		this.adaptationRuleRoadCongestionated.registerThing();
		
		
	}

	public void stop(BundleContext bundleContext) throws Exception {
		Activator.context = null;
		if ( this.adaptationRuleRoadCongestionated != null )
			this.adaptationRuleRoadCongestionated.unregisterThing();

	}


}
