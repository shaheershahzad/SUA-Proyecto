package mapek.listener.knowledgeproperty.roadtype;

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
		
		if(knowledgeProperty instanceof KnowledgePropertyRoadType) {
			switch ((EKnowledgeRoadType)knowledgeProperty.getKnowledge()) {
			case CITY:
				//System.out.println("Executing adaptation rule: road is city");
				AdaptationRuleEnteringCityFromJam adaptationRuleEnteringCityFromJam =  (AdaptationRuleEnteringCityFromJam)OSGiUtils.getService(context, IAdaptationRule.class, String.format("(%s=%s)", IIdentifiable.ID, "AdaptationRuleEnteringCityFromJam"));
				adaptationRuleEnteringCityFromJam.executeAdaptation();
				
				break;
			case HIGHWAY:
				//System.out.println("Executing adaptation rule: road is higway");
				
				break;
			case OFF_ROAD:
				//System.out.println("Executing adaptation rule: road is off road");
				
				break;
			case STD_ROAD:
				//System.out.println("Executing adaptation rule: road is std road");
				
				break;
			}
		}
		
		
	}
}
