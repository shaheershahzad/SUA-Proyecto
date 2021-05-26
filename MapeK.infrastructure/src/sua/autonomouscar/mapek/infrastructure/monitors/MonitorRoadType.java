package sua.autonomouscar.mapek.infrastructure.monitors;

import mapek.interfaces.IKnowledgeProperty;
import mapek.interfaces.IMonitor;
import mapek.interfaces.enums.EKnowledgeRoadType;

import org.osgi.framework.BundleContext;

import sua.autonomouscar.infrastructure.OSGiUtils;
import sua.autonomouscar.infrastructure.Thing;
import sua.autonomouscar.interfaces.ERoadType;
import sua.autonomouscar.interfaces.IIdentifiable;
import sua.autonomouscar.mapek.infrastructure.knowledgeproperties.KnowledgePropertyRoadType;

public class MonitorRoadType extends Thing implements IMonitor<ERoadType>{

	public MonitorRoadType(BundleContext context ,String id){
		super(context, id);
		this.addImplementedInterface(IMonitor.class.getName());
	}
	
	
	@Override
	public void changedState(ERoadType newState) {
		
		//System.out.println("Changed from Monitor to: "+newState.toString());
		//Change Knowledge property
		KnowledgePropertyRoadType knowledgeProperty =  (KnowledgePropertyRoadType)OSGiUtils.getService(context, IKnowledgeProperty.class, String.format("(%s=%s)", IIdentifiable.ID, "KnowledgePropertyRoadType"));
		if(knowledgeProperty!=null) {
			EKnowledgeRoadType roadType = null;
			switch(newState) {
			case CITY:
				roadType = EKnowledgeRoadType.CITY;
				break;
			case HIGHWAY:
				roadType = EKnowledgeRoadType.HIGHWAY;
				break;
			case OFF_ROAD:
				roadType = EKnowledgeRoadType.OFF_ROAD;
				break;

			case STD_ROAD:
				roadType = EKnowledgeRoadType.STD_ROAD;
				break;
				
			}
			knowledgeProperty.updateKnowledge(roadType);
		}
		
	}

}
/*
package sua.autonomouscar.mapek.infrastructure.monitors;

import mapek.interfaces.IKnowledgeProperty;
import mapek.interfaces.IMonitor;
import mapek.interfaces.enums.EKnowledgeRoadType;

import org.osgi.framework.BundleContext;

import sua.autonomouscar.infrastructure.OSGiUtils;
import sua.autonomouscar.infrastructure.Thing;
import sua.autonomouscar.interfaces.ERoadType;
import sua.autonomouscar.interfaces.IIdentifiable;
import sua.autonomouscar.mapek.infrastructure.knowledgeproperties.KnowledgePropertyRoadType;

public class MonitorRoadType extends Thing implements IMonitor<ERoadType>{

	public MonitorRoadType(BundleContext context ,String id){
		super(context, id);
		this.addImplementedInterface(IMonitor.class.getName());
	}
	
	
	@Override
	public void changedState(ERoadType newState) {
		
		//System.out.println("Changed from Monitor to: "+newState.toString());
		//Change Knowledge property
		KnowledgePropertyRoadType knowledgeProperty =  (KnowledgePropertyRoadType)OSGiUtils.getService(context, IKnowledgeProperty.class, String.format("(%s=%s)", IIdentifiable.ID, "KnowledgePropertyRoadType"));
		if(knowledgeProperty!=null) {
			EKnowledgeRoadType roadType = null;
			switch(newState) {
			case CITY:
				roadType = EKnowledgeRoadType.CITY;
				break;
			case HIGHWAY:
				roadType = EKnowledgeRoadType.HIGHWAY;
				break;
			case OFF_ROAD:
				roadType = EKnowledgeRoadType.OFF_ROAD;
				break;
			case STD_ROAD:
				roadType = EKnowledgeRoadType.STD_ROAD;
				break;
				
			}
			knowledgeProperty.updateKnowledge(roadType);
		}
		
	}

}
*/