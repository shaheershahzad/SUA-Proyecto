package sua.autonomouscar.mapek.infrastructure.monitorroadstate;

import mapek.interfaces.IMonitor;
import org.osgi.framework.BundleContext;

import sua.autonomouscar.infrastructure.Thing;
import sua.autonomouscar.interfaces.ERoadStatus;

public class MonitorRoadStatus extends Thing implements IMonitor<ERoadStatus>{

	public MonitorRoadStatus(BundleContext context ,String id){
		super(context, id);
		this.addImplementedInterface(IMonitor.class.getName());
	}
	
	
	@Override
	public void changedState(ERoadStatus newState) {
		
		System.out.println("Changed from Monitor to: "+newState.toString());

		
	}

}
