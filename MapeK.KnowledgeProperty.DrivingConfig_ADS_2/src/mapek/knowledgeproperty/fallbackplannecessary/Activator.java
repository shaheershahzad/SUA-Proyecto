package mapek.knowledgeproperty.fallbackplannecessary;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import sua.autonomouscar.mapek.infrastructure.knowledgeproperties.KnowledgePropertyDrivingConfig_ADS_2;
import sua.autonomouscar.mapek.infrastructure.knowledgeproperties.KnowledgePropertyFallBackPlanNecessary;

public class Activator implements BundleActivator {

	private static BundleContext context;

	private KnowledgePropertyDrivingConfig_ADS_2 knowledgePropertyDrivingConfig_ADS_2=null;

	static BundleContext getContext() {
		return context;
	}

	public void start(BundleContext bundleContext) throws Exception {
		Activator.context = bundleContext;		

		this.knowledgePropertyDrivingConfig_ADS_2 = new KnowledgePropertyDrivingConfig_ADS_2(bundleContext,"KnowledgePropertyDrivingConfig_ADS_2");
		this.knowledgePropertyDrivingConfig_ADS_2.registerThing();
		
	}

	public void stop(BundleContext bundleContext) throws Exception {
		Activator.context = null;

		if ( this.knowledgePropertyDrivingConfig_ADS_2 != null )
			this.knowledgePropertyDrivingConfig_ADS_2.unregisterThing();
	}
}
