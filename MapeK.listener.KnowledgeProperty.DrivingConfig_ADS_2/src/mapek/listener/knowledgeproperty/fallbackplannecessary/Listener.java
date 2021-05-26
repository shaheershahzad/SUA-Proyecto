package mapek.listener.knowledgeproperty.fallbackplannecessary;

import mapek.interfaces.IAdaptationRule;
import mapek.interfaces.IKnowledgeProperty;
import mapek.interfaces.enums.EDrivingConfig_ADS_2;
import mapek.interfaces.enums.EFallbackPlanNecessary;
import sua.autonomouscar.infrastructure.OSGiUtils;
import sua.autonomouscar.interfaces.IIdentifiable;
import sua.autonomouscar.mapek.infrastructure.adaptationrules.AdaptationRuleDrivingConfig_ADS_2;
import sua.autonomouscar.mapek.infrastructure.adaptationrules.AdaptationRuleFallbackPlanEmergency;
import sua.autonomouscar.mapek.infrastructure.adaptationrules.AdaptationRuleFallbackPlanRoadShoulder;
import sua.autonomouscar.mapek.infrastructure.knowledgeproperties.KnowledgePropertyDrivingConfig_ADS_2;
import sua.autonomouscar.mapek.infrastructure.knowledgeproperties.KnowledgePropertyFallBackPlanNecessary;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceEvent;
import org.osgi.framework.ServiceListener;


public class Listener  implements ServiceListener{
	protected BundleContext context = null;
	
	public Listener(BundleContext context) {
		this.context=context;
		 
	}
	
	
	@Override
	public void serviceChanged(ServiceEvent event) {
		IKnowledgeProperty knowledgeProperty = (IKnowledgeProperty)this.context.getService(event.getServiceReference());
		
		if(knowledgeProperty.getKnowledge()!=null && knowledgeProperty instanceof KnowledgePropertyDrivingConfig_ADS_2) {
			switch ((EDrivingConfig_ADS_2)knowledgeProperty.getKnowledge()) {
			case CONFIG:	
				System.out.println("No changes needed");
				break;
			case NO_CONFIG:
				AdaptationRuleDrivingConfig_ADS_2 adaptationRuleDrivingRule_ADS_2 =  (AdaptationRuleDrivingConfig_ADS_2)OSGiUtils.getService(context, IAdaptationRule.class, String.format("(%s=%s)", IIdentifiable.ID, "AdaptationRuleDrivingConfig_ADS_2"));
				adaptationRuleDrivingRule_ADS_2.executeAdaptation();
				break;
			}
		}
		
		
	}
}
