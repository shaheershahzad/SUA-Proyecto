package sua.autonomouscar.mapek.infrastructure.monitors;

import mapek.interfaces.IKnowledgeProperty;
import mapek.interfaces.IMonitor;
import mapek.interfaces.enums.EKnowledgeRoadStatus;
import org.osgi.framework.BundleContext;

import sua.autonomouscar.infrastructure.OSGiUtils;
import sua.autonomouscar.infrastructure.Thing;
import sua.autonomouscar.interfaces.ERoadStatus;
import sua.autonomouscar.interfaces.IIdentifiable;
import sua.autonomouscar.mapek.infrastructure.knowledgeproperties.KnowledgePropertyRoadStatus;

public class MonitorRoadStatus extends Thing implements IMonitor<ERoadStatus>{

	public MonitorRoadStatus(BundleContext context ,String id){
		super(context, id);
		this.addImplementedInterface(IMonitor.class.getName());
	}
	
	
	@Override
	public void changedState(ERoadStatus newState) {
		
		//System.out.println("Changed from Monitor to: "+newState.toString());
		//Change Knowledge property
		KnowledgePropertyRoadStatus knowledgeProperty =  (KnowledgePropertyRoadStatus)OSGiUtils.getService(context, IKnowledgeProperty.class, String.format("(%s=%s)", IIdentifiable.ID, "KnowledgePropertyRoadStatus"));
		if(knowledgeProperty!=null) {
			EKnowledgeRoadStatus roadStatus = null;
			switch(newState) {
			case COLLAPSED:
			case JAM:
				roadStatus = EKnowledgeRoadStatus.CONGESTIONATED;
				break;
			case FLUID:
				roadStatus = EKnowledgeRoadStatus.FLUID;
				break;
				
			}
			knowledgeProperty.updateKnowledge(roadStatus);
		}
		
	}

}
