package mapek.knowledgeproperties.fallbackplannecessary;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import sua.autonomouscar.mapek.infrastructure.knowledgeproperties.KnowledgePropertyFallBackPlanNecessary;
import sua.autonomouscar.mapek.infrastructure.knowledgeproperties.KnowledgePropertyRoadType;

public class Activator implements BundleActivator {

	private static BundleContext context;

	private KnowledgePropertyFallBackPlanNecessary knowledgePropertyFallBackPlanNecessary=null;

	static BundleContext getContext() {
		return context;
	}

	public void start(BundleContext bundleContext) throws Exception {
		Activator.context = bundleContext;		

		this.knowledgePropertyFallBackPlanNecessary = new KnowledgePropertyFallBackPlanNecessary(bundleContext,"KnowledgePropertyFallBackPlanNecessary");
		this.knowledgePropertyFallBackPlanNecessary.registerThing();
		
	}

	public void stop(BundleContext bundleContext) throws Exception {
		Activator.context = null;

		if ( this.knowledgePropertyFallBackPlanNecessary != null )
			this.knowledgePropertyFallBackPlanNecessary.unregisterThing();
	}
}
