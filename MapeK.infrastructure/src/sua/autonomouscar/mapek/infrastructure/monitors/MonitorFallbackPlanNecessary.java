package sua.autonomouscar.mapek.infrastructure.monitors;

import mapek.interfaces.IKnowledgeProperty;
import mapek.interfaces.IMonitor;
import mapek.interfaces.enums.EFallbackPlanNecessary;

import org.osgi.framework.BundleContext;

import sua.autonomouscar.infrastructure.OSGiUtils;
import sua.autonomouscar.infrastructure.Thing;
import sua.autonomouscar.interfaces.IIdentifiable;
import sua.autonomouscar.mapek.infrastructure.knowledgeproperties.KnowledgePropertyFallBackPlanNecessary;

public class MonitorFallbackPlanNecessary extends Thing implements IMonitor<Boolean>{

	public MonitorFallbackPlanNecessary(BundleContext context ,String id){
		super(context, id);
		this.addImplementedInterface(IMonitor.class.getName());
	}
	
	
	@Override
	public void changedState(Boolean newState) {
		
		KnowledgePropertyFallBackPlanNecessary knowledgeProperty =  (KnowledgePropertyFallBackPlanNecessary)OSGiUtils.getService(context, IKnowledgeProperty.class, String.format("(%s=%s)", IIdentifiable.ID, "KnowledgePropertyFallBackPlanNecessary"));
		if(knowledgeProperty!=null) {
			if(newState) {
				//Can park in road shoulder
				knowledgeProperty.updateKnowledge(EFallbackPlanNecessary.FALLBACK_ROAD_SHOULDER);
			}else {
				//Can not park in road shoulder
				knowledgeProperty.updateKnowledge(EFallbackPlanNecessary.EMERGENCY_FALLBACK);
			}
		}
		
	}

}
