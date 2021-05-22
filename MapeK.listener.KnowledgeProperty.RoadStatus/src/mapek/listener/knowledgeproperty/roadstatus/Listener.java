package mapek.listener.knowledgeproperty.roadstatus;

import mapek.interfaces.IAdaptationRule;
import mapek.interfaces.IKnowledgeProperty;
import mapek.interfaces.enums.EKnowledgeRoadStatus;
import mapek.interfaces.enums.EKnowledgeRoadType;
import sua.autonomouscar.infrastructure.OSGiUtils;
import sua.autonomouscar.infrastructure.Thing;
import sua.autonomouscar.interfaces.IIdentifiable;
import sua.autonomouscar.mapek.infrastructure.adaptationrules.AdaptationRuleEnteringCityFromJam;
import sua.autonomouscar.mapek.infrastructure.adaptationrules.AdaptationRuleRoadCongestionated;
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
		
		if(knowledgeProperty instanceof KnowledgePropertyRoadStatus) {
			switch ((EKnowledgeRoadStatus)knowledgeProperty.getKnowledge()) {
			case CONGESTIONATED:
				//System.out.println("Executing adaptation rule: road congestionated");
				
				AdaptationRuleRoadCongestionated adaptationRuleRoadCongestionated =  (AdaptationRuleRoadCongestionated)OSGiUtils.getService(context, IAdaptationRule.class, String.format("(%s=%s)", IIdentifiable.ID, "AdaptationRuleRoadCongestionated"));
				adaptationRuleRoadCongestionated.executeAdaptation();
				
				break;
			case FLUID:
				//System.out.println("Executing adaptation rule: road fluid");
				
				break;
			}
		}
		
		
	}
}
