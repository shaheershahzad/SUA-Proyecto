package mapek.listener.knowledgeproperties;

import mapek.interfaces.IKnowledgeProperty;
import mapek.interfaces.enums.EKnowledgeRoadStatus;
import mapek.interfaces.enums.EKnowledgeRoadType;
import sua.autonomouscar.infrastructure.Thing;
import sua.autonomouscar.mapek.infrastructure.knowledgeproperties.KnowledgePropertyRoadStatus;
import sua.autonomouscar.mapek.infrastructure.knowledgeproperties.KnowledgePropertyRoadType;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceEvent;
import org.osgi.framework.ServiceListener;


public class ListenerKnowledgePropertyRoadStatus  implements ServiceListener{
	protected BundleContext context = null;
	
	public ListenerKnowledgePropertyRoadStatus(BundleContext context) {
		this.context=context;
		 
	}
	
	
	@Override
	public void serviceChanged(ServiceEvent event) {
		IKnowledgeProperty knowledgeProperty = (IKnowledgeProperty)this.context.getService(event.getServiceReference());
		
		if(knowledgeProperty instanceof KnowledgePropertyRoadStatus) {
			switch ((EKnowledgeRoadStatus)knowledgeProperty.getKnowledge()) {
			case CONGESTIONATED:
				System.out.println("Executing adaptation rule: road congestionated");
				break;
			case FLUID:
				System.out.println("Executing adaptation rule: road fluid");
				
				break;
			}
		}else if(knowledgeProperty instanceof KnowledgePropertyRoadType) {
			switch ((EKnowledgeRoadType)knowledgeProperty.getKnowledge()) {
			case CITY:
				System.out.println("Executing adaptation rule: road is city");
				break;
			case HIGHWAY:
				System.out.println("Executing adaptation rule: road is higway");
				
				break;
			case OFF_ROAD:
				System.out.println("Executing adaptation rule: road is off road");
				
				break;
			case STD_ROAD:
				System.out.println("Executing adaptation rule: road is std road");
				
				break;
			}
		}
		
		
	}
}
