package sua.autonomouscar.mapek.infrastructure.monitors;

import org.osgi.framework.BundleContext;

import mapek.interfaces.IKnowledgeProperty;
import mapek.interfaces.IMonitor;
import mapek.interfaces.enums.EVehicleContext;
import sua.autonomouscar.infrastructure.OSGiUtils;
import sua.autonomouscar.infrastructure.Thing;
import sua.autonomouscar.interfaces.IIdentifiable;
import sua.autonomouscar.mapek.infrastructure.knowledgeproperties.KnowledgePropertyVehicleContext;

public class MonitorVehicleContext extends Thing implements IMonitor<Boolean>{

	public MonitorVehicleContext(BundleContext context ,String id){
		super(context, id);
		this.addImplementedInterface(IMonitor.class.getName());
	}
	
	
	@Override
	public void changedState(Boolean newState) {
		
		KnowledgePropertyVehicleContext knowledgeProperty =  (KnowledgePropertyVehicleContext)OSGiUtils.getService(context, IKnowledgeProperty.class, String.format("(%s=%s)", IIdentifiable.ID, "KnowledgePropertyVehicleContext"));
		if(knowledgeProperty!=null) {
			if(newState) {
				//FALLBACKPLAN
				knowledgeProperty.updateKnowledge(EVehicleContext.FALLBACKPLAN);
			}else {
				//TAKEOVER
				knowledgeProperty.updateKnowledge(EVehicleContext.TAKE_OVER);
			}
		}
		
	}

}
