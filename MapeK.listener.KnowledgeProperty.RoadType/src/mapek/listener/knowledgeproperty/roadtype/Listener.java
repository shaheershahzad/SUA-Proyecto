package mapek.listener.knowledgeproperty.roadtype;

import mapek.interfaces.IAdaptationRule;
import mapek.interfaces.IKnowledgeProperty;
import mapek.interfaces.enums.EKnowledgeRoadStatus;
import mapek.interfaces.enums.EKnowledgeRoadType;
import sua.autonomouscar.infrastructure.OSGiUtils;
import sua.autonomouscar.interfaces.IIdentifiable;
import sua.autonomouscar.mapek.infrastructure.adaptationrules.ADS_L3_3;
import sua.autonomouscar.mapek.infrastructure.adaptationrules.ADS_L3_6_1;
import sua.autonomouscar.mapek.infrastructure.adaptationrules.ADS_L3_6_2;
import sua.autonomouscar.mapek.infrastructure.adaptationrules.AdaptationRuleEnteringCityFromJam;
import sua.autonomouscar.mapek.infrastructure.knowledgeproperties.KnowledgePropertyRoadStatus;
import sua.autonomouscar.mapek.infrastructure.knowledgeproperties.KnowledgePropertyRoadType;
import sua.autonomouscar.mapek.infrastructure.adaptationrules.AdaptationRulesEnteringL2FromL3;

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
		KnowledgePropertyRoadStatus knowledgePropertyRoadStatus =  (KnowledgePropertyRoadStatus)OSGiUtils.getService(context, IKnowledgeProperty.class, String.format("(%s=%s)", IIdentifiable.ID, "KnowledgePropertyRoadStatus"));

		
		if(knowledgeProperty.getKnowledge()!=null && knowledgeProperty instanceof KnowledgePropertyRoadType) {
			switch ((EKnowledgeRoadType)knowledgeProperty.getKnowledge()) {
			case CITY:
				//System.out.println("Executing adaptation rule: road is city");
				AdaptationRuleEnteringCityFromJam adaptationRuleEnteringCityFromJam =  (AdaptationRuleEnteringCityFromJam)OSGiUtils.getService(context, IAdaptationRule.class, String.format("(%s=%s)", IIdentifiable.ID, "AdaptationRuleEnteringCityFromJam"));
				adaptationRuleEnteringCityFromJam.executeAdaptation();
				
				if(knowledgePropertyRoadStatus != null && knowledgePropertyRoadStatus.getKnowledge() == EKnowledgeRoadStatus.FLUID) {
					ADS_L3_3 ads_L3_3 =  (ADS_L3_3)OSGiUtils.getService(context, IAdaptationRule.class, String.format("(%s=%s)", IIdentifiable.ID, "ADS_L3_3"));
					ads_L3_3.executeAdaptation();
				}
				
				break;
			case HIGHWAY:
				//System.out.println("Executing adaptation rule: road is highway");
				
				if(knowledgePropertyRoadStatus != null && knowledgePropertyRoadStatus.getKnowledge() == EKnowledgeRoadStatus.FLUID) {
					ADS_L3_6_1 ads_L3_6_1 =  (ADS_L3_6_1)OSGiUtils.getService(context, IAdaptationRule.class, String.format("(%s=%s)", IIdentifiable.ID, "ADS_L3_6_1"));
					ads_L3_6_1.executeAdaptation();
				}else if(knowledgePropertyRoadStatus != null && knowledgePropertyRoadStatus.getKnowledge() == EKnowledgeRoadStatus.CONGESTIONATED) {
					ADS_L3_6_2 ads_L3_6_2 =  (ADS_L3_6_2)OSGiUtils.getService(context, IAdaptationRule.class, String.format("(%s=%s)", IIdentifiable.ID, "ADS_L3_6_2"));
					ads_L3_6_2.executeAdaptation();
				}
				
				break;
			case OFF_ROAD:
				//System.out.println("Executing adaptation rule: road is off road");
				AdaptationRulesEnteringL2FromL3 adaptationRulesEnteringL2FromL3OffR =  (AdaptationRulesEnteringL2FromL3)OSGiUtils.getService(context, IAdaptationRule.class, String.format("(%s=%s)", IIdentifiable.ID, "AdaptationRulesEnteringL2FromL3"));
				adaptationRulesEnteringL2FromL3OffR.executeAdaptation();
				break;
			case STD_ROAD:
				//System.out.println("Executing adaptation rule: road is std road");
				AdaptationRulesEnteringL2FromL3 adaptationRulesEnteringL2FromL3STD =  (AdaptationRulesEnteringL2FromL3)OSGiUtils.getService(context, IAdaptationRule.class, String.format("(%s=%s)", IIdentifiable.ID, "AdaptationRulesEnteringL2FromL3"));
				adaptationRulesEnteringL2FromL3STD.executeAdaptation();
				break;
			}
		}
		
		
	}
}
/*
package mapek.listener.knowledgeproperty.roadtype;

import mapek.interfaces.IAdaptationRule;
import mapek.interfaces.IKnowledgeProperty;
import mapek.interfaces.enums.EKnowledgeRoadType;
import sua.autonomouscar.infrastructure.OSGiUtils;
import sua.autonomouscar.interfaces.IIdentifiable;
import sua.autonomouscar.mapek.infrastructure.adaptationrules.AdaptationRuleEnteringCityFromJam;
import sua.autonomouscar.mapek.infrastructure.adaptationrules.AdaptationRulesEnteringL2FromL3;
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
		
		if(knowledgeProperty.getKnowledge()!=null && knowledgeProperty instanceof KnowledgePropertyRoadType) {
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
				AdaptationRulesEnteringL2FromL3 adaptationRulesEnteringL2FromL3OffR =  (AdaptationRulesEnteringL2FromL3)OSGiUtils.getService(context, IAdaptationRule.class, String.format("(%s=%s)", IIdentifiable.ID, "AdaptationRulesEnteringL2FromL3"));
				adaptationRulesEnteringL2FromL3OffR.executeAdaptation();
				break;
			case STD_ROAD:
				//System.out.println("Executing adaptation rule: road is std road");
				AdaptationRulesEnteringL2FromL3 adaptationRulesEnteringL2FromL3STD =  (AdaptationRulesEnteringL2FromL3)OSGiUtils.getService(context, IAdaptationRule.class, String.format("(%s=%s)", IIdentifiable.ID, "AdaptationRulesEnteringL2FromL3"));
				adaptationRulesEnteringL2FromL3STD.executeAdaptation();
				break;
			}
		}
		
		
	}
}
*/