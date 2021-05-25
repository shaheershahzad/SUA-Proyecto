package mapek.listener.knowledgeproperty.vibrationmode;

import mapek.interfaces.IAdaptationRule;
import mapek.interfaces.IKnowledgeProperty;
import mapek.interfaces.enums.EFallbackPlanNecessary;
import mapek.interfaces.enums.EKnowledgeVibrationModes;
import sua.autonomouscar.infrastructure.OSGiUtils;
import sua.autonomouscar.interfaces.IIdentifiable;
import sua.autonomouscar.mapek.infrastructure.adaptationrules.AdaptationRuleEnteringCityFromJam;
import sua.autonomouscar.mapek.infrastructure.adaptationrules.AdaptationRuleFallbackPlanEmergency;
import sua.autonomouscar.mapek.infrastructure.adaptationrules.AdaptationRuleFallbackPlanRoadShoulder;
import sua.autonomouscar.mapek.infrastructure.adaptationrules.AdaptationRuleNoVibration;
import sua.autonomouscar.mapek.infrastructure.adaptationrules.AdaptationRuleVibrationDriverSit;
import sua.autonomouscar.mapek.infrastructure.adaptationrules.AdaptationRuleVibrationWheel;
import sua.autonomouscar.mapek.infrastructure.adaptationrules.AdaptationRuleVibrationWheelAndSit;
import sua.autonomouscar.mapek.infrastructure.knowledgeproperties.KnowledgePropertyFallBackPlanNecessary;
import sua.autonomouscar.mapek.infrastructure.knowledgeproperties.KnowledgePropertyVibrationMode;

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
		
		if(knowledgeProperty.getKnowledge()!=null && knowledgeProperty instanceof KnowledgePropertyVibrationMode) {
			switch ((EKnowledgeVibrationModes)knowledgeProperty.getKnowledge()) {
			case NOTHING:
				AdaptationRuleNoVibration adaptationRuleNoVibration =  (AdaptationRuleNoVibration)OSGiUtils.getService(context, IAdaptationRule.class, String.format("(%s=%s)", IIdentifiable.ID, "AdaptationRuleNoVibration"));
				adaptationRuleNoVibration.executeAdaptation();
				break;
			case ONLY_SIT:
				AdaptationRuleVibrationDriverSit adaptationRuleVibrationDriverSit =  (AdaptationRuleVibrationDriverSit)OSGiUtils.getService(context, IAdaptationRule.class, String.format("(%s=%s)", IIdentifiable.ID, "AdaptationRuleVibrationDriverSit"));
				adaptationRuleVibrationDriverSit.executeAdaptation();
				break;
			case ONLY_WHEEL:
				AdaptationRuleVibrationWheel adaptationRuleVibrationWheel =  (AdaptationRuleVibrationWheel)OSGiUtils.getService(context, IAdaptationRule.class, String.format("(%s=%s)", IIdentifiable.ID, "AdaptationRuleVibrationWheel"));
				adaptationRuleVibrationWheel.executeAdaptation();
				break;
			case WHEEL_SIT:
				AdaptationRuleVibrationWheelAndSit adaptationRuleVibrationWheelAndSit =  (AdaptationRuleVibrationWheelAndSit)OSGiUtils.getService(context, IAdaptationRule.class, String.format("(%s=%s)", IIdentifiable.ID, "AdaptationRuleVibrationWheelAndSit"));
				adaptationRuleVibrationWheelAndSit.executeAdaptation();
				break;
				
			
			}
		}
		
		
	}
}
