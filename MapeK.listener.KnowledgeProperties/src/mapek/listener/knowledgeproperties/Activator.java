package mapek.listener.knowledgeproperties;


import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import sua.autonomouscar.mapek.infrastructure.knowledgeproperties.KnowledgePropertyRoadStatus;
import mapek.interfaces.IKnowledgeProperty;

public class Activator implements BundleActivator {

	private static BundleContext context;
	protected ListenerKnowledgePropertyRoadStatus listenerKnowledgePropertyRoadStatus;

	static BundleContext getContext() {
		return context;
	}

	public void start(BundleContext bundleContext) throws Exception {
		Activator.context = bundleContext;

		this.listenerKnowledgePropertyRoadStatus = new ListenerKnowledgePropertyRoadStatus(context);
		String listenerKnowledgePropertyRoadStatusFilter = "(objectclass="+IKnowledgeProperty.class.getName()+")";
		this.context.addServiceListener(listenerKnowledgePropertyRoadStatus, listenerKnowledgePropertyRoadStatusFilter);

	}

	public void stop(BundleContext bundleContext) throws Exception {
		Activator.context = null;
	}

}
