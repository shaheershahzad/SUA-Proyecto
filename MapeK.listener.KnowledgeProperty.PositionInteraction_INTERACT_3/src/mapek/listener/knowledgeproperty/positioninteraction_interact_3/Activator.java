package mapek.listener.knowledgeproperty.positioninteraction_interact_3;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import mapek.interfaces.IKnowledgeProperty;

public class Activator implements BundleActivator {

	private static BundleContext context;
	protected Listener listenerKnowledge;

	static BundleContext getContext() {
		return context;
	}

	public void start(BundleContext bundleContext) throws Exception {
		Activator.context = bundleContext;

		this.listenerKnowledge = new Listener(context);
		String listenerKnowledgeFilter = "(objectclass="+IKnowledgeProperty.class.getName()+")";
		this.context.addServiceListener(listenerKnowledge, listenerKnowledgeFilter);

	}

	public void stop(BundleContext bundleContext) throws Exception {
		Activator.context = null;
	}

}
