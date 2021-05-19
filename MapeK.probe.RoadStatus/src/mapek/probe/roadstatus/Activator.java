package mapek.probe.roadstatus;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import sua.autonomouscar.devices.interfaces.IRoadSensor;

public class Activator implements BundleActivator {

	private static BundleContext context;
	protected Controller controller =null;

	static BundleContext getContext() {
		return context;
	}

	public void start(BundleContext bundleContext) throws Exception {
		Activator.context = bundleContext;
		this.controller = new Controller(bundleContext);
		
		String listenerFilter = "(objectclass="+IRoadSensor.class.getName()+")";
		this.context.addServiceListener(controller, listenerFilter);
		
	}

	public void stop(BundleContext bundleContext) throws Exception {
		this.context.removeServiceListener(controller);
		Activator.context = null;
	}

}
