package mapek.probe.canparkinroadshoulder;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import sua.autonomouscar.devices.interfaces.IDistanceSensor;
import sua.autonomouscar.devices.interfaces.ILineSensor;
import sua.autonomouscar.devices.interfaces.IRoadSensor;

public class Activator implements BundleActivator {

	private static BundleContext context;
	protected Listener controller =null;

	static BundleContext getContext() {
		return context;
	}

	public void start(BundleContext bundleContext) throws Exception {
		Activator.context = bundleContext;
		this.controller = new Listener(bundleContext);
		String listenerFilter= 
				"(|(objectClass="+IDistanceSensor.class.getName()+
				")(objectClass="+ILineSensor.class.getName()+"))";
		
		this.context.addServiceListener(controller, listenerFilter);
		//this.context.addServiceListener(controller, listenerFilterLineSensor);
		
	}

	public void stop(BundleContext bundleContext) throws Exception {
		this.context.removeServiceListener(controller);
		Activator.context = null;
	}

}
