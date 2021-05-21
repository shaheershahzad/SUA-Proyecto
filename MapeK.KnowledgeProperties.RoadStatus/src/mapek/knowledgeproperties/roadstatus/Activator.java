package mapek.knowledgeproperties.roadstatus;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import sua.autonomouscar.mapek.infrastructure.knowledgeproperties.KnowledgePropertyRoadStatus;

public class Activator implements BundleActivator {

	private static BundleContext context;

	private KnowledgePropertyRoadStatus knowledgePropertyRoadStatus=null;

	static BundleContext getContext() {
		return context;
	}

	public void start(BundleContext bundleContext) throws Exception {
		Activator.context = bundleContext;
		this.knowledgePropertyRoadStatus = new KnowledgePropertyRoadStatus(bundleContext,"KnowledgePropertyRoadStatus");
		this.knowledgePropertyRoadStatus.registerThing();
		
	}

	public void stop(BundleContext bundleContext) throws Exception {
		Activator.context = null;
		if ( this.knowledgePropertyRoadStatus != null )
			this.knowledgePropertyRoadStatus.unregisterThing();

	}

}
