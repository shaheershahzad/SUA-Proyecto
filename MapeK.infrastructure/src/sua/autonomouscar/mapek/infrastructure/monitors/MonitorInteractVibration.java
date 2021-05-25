package sua.autonomouscar.mapek.infrastructure.monitors;

import mapek.interfaces.IKnowledgeProperty;
import mapek.interfaces.IMonitor;
import mapek.interfaces.enums.EKnowledgeRoadType;
import mapek.interfaces.enums.EKnowledgeVibrationModes;
import mapek.interfaces.enums.EMonitorInteractVibrationStates;

import org.osgi.framework.BundleContext;

import sua.autonomouscar.infrastructure.OSGiUtils;
import sua.autonomouscar.infrastructure.Thing;
import sua.autonomouscar.interfaces.ERoadType;
import sua.autonomouscar.interfaces.IIdentifiable;
import sua.autonomouscar.mapek.infrastructure.knowledgeproperties.KnowledgePropertyRoadType;
import sua.autonomouscar.mapek.infrastructure.knowledgeproperties.KnowledgePropertyVibrationMode;

public class MonitorInteractVibration extends Thing implements IMonitor<EMonitorInteractVibrationStates>{

	String propertyHandsOnWheelName = "HandsOnWheel";
	String propertyDriverSitName = "DriverSit";
	
	public MonitorInteractVibration(BundleContext context ,String id){
		super(context, id);
		this.addImplementedInterface(IMonitor.class.getName());
	}
	
	
	@Override
	public void changedState(EMonitorInteractVibrationStates newState) {
		//System.out.println("Changed from Monitor to: "+newState.toString());
		//Change Knowledge property
		KnowledgePropertyVibrationMode knowledgeProperty =  (KnowledgePropertyVibrationMode)OSGiUtils.getService(context, IKnowledgeProperty.class, String.format("(%s=%s)", IIdentifiable.ID, "KnowledgePropertyVibrationMode"));
		if(knowledgeProperty!=null) {
			EKnowledgeRoadType roadType = null;
			switch(newState) {
			case DRIVER_NOT_SEATED:
				this.setProperty(propertyDriverSitName, false);
				break;
			case DRIVER_SEATED:
				this.setProperty(propertyDriverSitName, true);
				
				break;
			case HANDS_OFF_WHEEL:
				this.setProperty(propertyHandsOnWheelName, false);
				
				break;
			case HANDS_ON_WHEEL:
				this.setProperty(propertyHandsOnWheelName, true);
				break;
			
				
			}
			
			Boolean hasHandsOnWheel = (Boolean) this.getProperty(propertyHandsOnWheelName);
			Boolean driverIsSit = (Boolean) this.getProperty(propertyDriverSitName);
			
			if(hasHandsOnWheel==null)hasHandsOnWheel=false;
			if(driverIsSit==null)driverIsSit=false;

			
			if(hasHandsOnWheel&&driverIsSit) {
				knowledgeProperty.updateKnowledge(EKnowledgeVibrationModes.WHEEL_SIT);
			}else if(hasHandsOnWheel&&!driverIsSit) {
				knowledgeProperty.updateKnowledge(EKnowledgeVibrationModes.ONLY_WHEEL);
			}else if(!hasHandsOnWheel&&driverIsSit) {
				knowledgeProperty.updateKnowledge(EKnowledgeVibrationModes.ONLY_SIT);
			}else if(!hasHandsOnWheel&&!driverIsSit) {
				knowledgeProperty.updateKnowledge(EKnowledgeVibrationModes.NOTHING);
			}
			
		}
		
	}

}
