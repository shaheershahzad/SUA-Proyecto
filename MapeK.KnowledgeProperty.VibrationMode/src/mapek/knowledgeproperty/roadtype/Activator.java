package mapek.knowledgeproperty.roadtype;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import sua.autonomouscar.mapek.infrastructure.knowledgeproperties.KnowledgePropertyRoadType;
import sua.autonomouscar.mapek.infrastructure.knowledgeproperties.KnowledgePropertyVibrationMode;

public class Activator implements BundleActivator {

	private static BundleContext context;

	private KnowledgePropertyVibrationMode knowledgeProperty=null;

	static BundleContext getContext() {
		return context;
	}

	public void start(BundleContext bundleContext) throws Exception {
		Activator.context = bundleContext;		

		this.knowledgeProperty = new KnowledgePropertyVibrationMode(bundleContext,"KnowledgePropertyVibrationMode");
		this.knowledgeProperty.registerThing();
		
	}

	public void stop(BundleContext bundleContext) throws Exception {
		Activator.context = null;

		if ( this.knowledgeProperty != null )
			this.knowledgeProperty.unregisterThing();
	}
}
