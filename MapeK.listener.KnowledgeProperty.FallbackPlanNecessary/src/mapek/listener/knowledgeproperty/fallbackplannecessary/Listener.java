package mapek.listener.knowledgeproperty.fallbackplannecessary;

import mapek.interfaces.IAdaptationRule;
import mapek.interfaces.IKnowledgeProperty;
import mapek.interfaces.enums.EFallbackPlanNecessary;
import mapek.interfaces.enums.EKnowledgeRoadStatus;
import mapek.interfaces.enums.EKnowledgeRoadType;
import sua.autonomouscar.infrastructure.OSGiUtils;
import sua.autonomouscar.infrastructure.Thing;
import sua.autonomouscar.interfaces.IIdentifiable;
import sua.autonomouscar.mapek.infrastructure.adaptationrules.AdaptationRuleEnteringCityFromJam;
import sua.autonomouscar.mapek.infrastructure.adaptationrules.AdaptationRuleFallbackPlanEmergency;
import sua.autonomouscar.mapek.infrastructure.adaptationrules.AdaptationRuleFallbackPlanRoadShoulder;
import sua.autonomouscar.mapek.infrastructure.adaptationrules.AdaptationRuleRoadCongestionated;
import sua.autonomouscar.mapek.infrastructure.knowledgeproperties.KnowledgePropertyFallBackPlanNecessary;
import sua.autonomouscar.mapek.infrastructure.knowledgeproperties.KnowledgePropertyRoadStatus;
import sua.autonomouscar.mapek.infrastructure.knowledgeproperties.KnowledgePropertyRoadType;

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
		
		if(knowledgeProperty.getKnowledge()!=null && knowledgeProperty instanceof KnowledgePropertyFallBackPlanNecessary) {
			switch ((EFallbackPlanNecessary)knowledgeProperty.getKnowledge()) {
			case EMERGENCY_FALLBACK:
				//System.out.println("Executing adaptation rule: EMERGENCY_FALLBACK");
				
				AdaptationRuleFallbackPlanEmergency adaptationRuleFallbackPlanEmergency =  (AdaptationRuleFallbackPlanEmergency)OSGiUtils.getService(context, IAdaptationRule.class, String.format("(%s=%s)", IIdentifiable.ID, "AdaptationRuleFallbackPlanEmergency"));
				adaptationRuleFallbackPlanEmergency.executeAdaptation();
				
				break;
			case FALLBACK_ROAD_SHOULDER:
				//System.out.println("Executing adaptation rule: FALLBACK_ROAD_SHOULDER");

				AdaptationRuleFallbackPlanRoadShoulder adaptationRuleFallbackPlanRoadShoulder =  (AdaptationRuleFallbackPlanRoadShoulder)OSGiUtils.getService(context, IAdaptationRule.class, String.format("(%s=%s)", IIdentifiable.ID, "AdaptationRuleFallbackPlanRoadShoulder"));
				adaptationRuleFallbackPlanRoadShoulder.executeAdaptation();
				break;
			}
		}
		
		
	}
}
