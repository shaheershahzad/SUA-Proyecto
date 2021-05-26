package sua.autonomouscar.mapek.infrastructure.monitors;

import mapek.interfaces.IKnowledgeProperty;
import mapek.interfaces.IMonitor;
import mapek.interfaces.enums.EKnowledgeRoadType;
import mapek.interfaces.enums.EKnowledgeVibrationModes;
import mapek.interfaces.enums.EMonitorInteractVibrationStates;
import mapek.interfaces.enums.EPositionInteractionModes_INTERACT_3;

import org.osgi.framework.BundleContext;

import sua.autonomouscar.infrastructure.OSGiUtils;
import sua.autonomouscar.infrastructure.Thing;
import sua.autonomouscar.interfaces.ERoadType;
import sua.autonomouscar.interfaces.IIdentifiable;
import sua.autonomouscar.mapek.infrastructure.knowledgeproperties.KnowledgePropertyPositionInteraction;
import sua.autonomouscar.mapek.infrastructure.knowledgeproperties.KnowledgePropertyRoadType;
import sua.autonomouscar.mapek.infrastructure.knowledgeproperties.KnowledgePropertyVibrationMode;

public class MonitorPositionInteraction extends Thing implements IMonitor<EMonitorInteractVibrationStates>{

	String propertyDriverSitName = "DriverSit";
	
	public MonitorPositionInteraction(BundleContext context ,String id){
		super(context, id);
		this.addImplementedInterface(IMonitor.class.getName());
	}
	
	
	@Override
	public void changedState(EMonitorInteractVibrationStates newState) {
		//System.out.println("Changed from Monitor to: "+newState.toString());
		//Change Knowledge property
		KnowledgePropertyPositionInteraction knowledgeProperty =  (KnowledgePropertyPositionInteraction)OSGiUtils.getService(context, IKnowledgeProperty.class, String.format("(%s=%s)", IIdentifiable.ID, "KnowledgePropertyPositionInteraction"));
		if(knowledgeProperty!=null) {
			EKnowledgeRoadType roadType = null;
			switch(newState) {
			case DRIVER_NOT_SEATED:
				this.setProperty(propertyDriverSitName, false);
				break;
			case DRIVER_SEATED:
				this.setProperty(propertyDriverSitName, true);
				
				break;
			
				
			}
			
			Boolean driverIsSit = (Boolean) this.getProperty(propertyDriverSitName);
			
			if(driverIsSit==null)driverIsSit=false;

			
			if(driverIsSit) {
				knowledgeProperty.updateKnowledge(EPositionInteractionModes_INTERACT_3.DRIVER);
			}else if(!driverIsSit) {
				knowledgeProperty.updateKnowledge(EPositionInteractionModes_INTERACT_3.COPILOT);
			}else {
				knowledgeProperty.updateKnowledge(EPositionInteractionModes_INTERACT_3.OTHER);
			}
			
		}
		
	}

}
