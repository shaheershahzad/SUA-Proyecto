package sua.autonomouscar.mapek.infrastructure.monitors;

import mapek.interfaces.IKnowledgeProperty;
import mapek.interfaces.IMonitor;
import mapek.interfaces.enums.EDrivingConfig_ADS_2;
import mapek.interfaces.enums.EFallbackPlanNecessary;

import org.osgi.framework.BundleContext;

import sua.autonomouscar.infrastructure.OSGiUtils;
import sua.autonomouscar.infrastructure.Thing;
import sua.autonomouscar.interfaces.IIdentifiable;
import sua.autonomouscar.mapek.infrastructure.knowledgeproperties.KnowledgePropertyDrivingConfig_ADS_2;
import sua.autonomouscar.mapek.infrastructure.knowledgeproperties.KnowledgePropertyFallBackPlanNecessary;

public class MonitorDrivingConfig_ADS_2 extends Thing implements IMonitor<Boolean>{

	public MonitorDrivingConfig_ADS_2(BundleContext context ,String id){
		super(context, id);
		this.addImplementedInterface(IMonitor.class.getName());
	}
	
	
	@Override
	public void changedState(Boolean newState) {
		
		KnowledgePropertyDrivingConfig_ADS_2 knowledgeProperty =  (KnowledgePropertyDrivingConfig_ADS_2)OSGiUtils.getService(context, IKnowledgeProperty.class, String.format("(%s=%s)", IIdentifiable.ID, "KnowledgePropertyDrivingConfig_ADS_2"));
		if(knowledgeProperty!=null) {
			if(newState) {
				knowledgeProperty.updateKnowledge(EDrivingConfig_ADS_2.NO_CONFIG);
			}else {
				knowledgeProperty.updateKnowledge(EDrivingConfig_ADS_2.CONFIG);
			}
		}
		
	}

}