package mapek.knowledgeproperty.roadtype;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import sua.autonomouscar.mapek.infrastructure.knowledgeproperties.KnowledgePropertyRoadType;

public class Activator implements BundleActivator {

	private static BundleContext context;

	private KnowledgePropertyRoadType knowledgePropertyRoadType=null;

	static BundleContext getContext() {
		return context;
	}

	public void start(BundleContext bundleContext) throws Exception {
		Activator.context = bundleContext;		

		this.knowledgePropertyRoadType = new KnowledgePropertyRoadType(bundleContext,"KnowledgePropertyRoadType");
		this.knowledgePropertyRoadType.registerThing();
		
	}

	public void stop(BundleContext bundleContext) throws Exception {
		Activator.context = null;

		if ( this.knowledgePropertyRoadType != null )
			this.knowledgePropertyRoadType.unregisterThing();
	}
}
