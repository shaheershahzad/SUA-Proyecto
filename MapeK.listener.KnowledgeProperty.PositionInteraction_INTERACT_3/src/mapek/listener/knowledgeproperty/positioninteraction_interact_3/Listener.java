package mapek.listener.knowledgeproperty.positioninteraction_interact_3;

import mapek.interfaces.IAdaptationRule;
import mapek.interfaces.IKnowledgeProperty;
import mapek.interfaces.enums.EFallbackPlanNecessary;
import mapek.interfaces.enums.EKnowledgeVibrationModes;
import mapek.interfaces.enums.EPositionInteractionModes_INTERACT_3;
import sua.autonomouscar.infrastructure.OSGiUtils;
import sua.autonomouscar.interfaces.IIdentifiable;
import sua.autonomouscar.mapek.infrastructure.adaptationrules.AdaptationRuleDashboard_INTERACT_3;
import sua.autonomouscar.mapek.infrastructure.adaptationrules.AdaptationRuleFallbackPlanEmergency;
import sua.autonomouscar.mapek.infrastructure.adaptationrules.AdaptationRuleFallbackPlanRoadShoulder;
import sua.autonomouscar.mapek.infrastructure.adaptationrules.AdaptationRuleNoVibration;
import sua.autonomouscar.mapek.infrastructure.adaptationrules.AdaptationRuleSeatAndDisplay_INTERACT_3;
import sua.autonomouscar.mapek.infrastructure.adaptationrules.AdaptationRuleSpeakers_INTERACT_3;
import sua.autonomouscar.mapek.infrastructure.adaptationrules.AdaptationRuleVibrationDriverSit;
import sua.autonomouscar.mapek.infrastructure.adaptationrules.AdaptationRuleVibrationWheel;
import sua.autonomouscar.mapek.infrastructure.adaptationrules.AdaptationRuleVibrationWheelAndSit;
import sua.autonomouscar.mapek.infrastructure.knowledgeproperties.KnowledgePropertyFallBackPlanNecessary;
import sua.autonomouscar.mapek.infrastructure.knowledgeproperties.KnowledgePropertyPositionInteraction;
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
		
		if(knowledgeProperty.getKnowledge()!=null && knowledgeProperty instanceof KnowledgePropertyPositionInteraction) {
			switch ((EPositionInteractionModes_INTERACT_3)knowledgeProperty.getKnowledge()) {
			case DRIVER:
				
				AdaptationRuleSeatAndDisplay_INTERACT_3 adaptationRuleSeatAndDisplay =  (AdaptationRuleSeatAndDisplay_INTERACT_3)OSGiUtils.getService(context, IAdaptationRule.class, String.format("(%s=%s)", IIdentifiable.ID, "AdaptationRuleSeatAndDisplay_INTERACT_3"));
				adaptationRuleSeatAndDisplay.executeAdaptation();
				
				/*AdaptationRuleNoVibration adaptationRuleNoVibration =  (AdaptationRuleNoVibration)OSGiUtils.getService(context, IAdaptationRule.class, String.format("(%s=%s)", IIdentifiable.ID, "AdaptationRuleNoVibration"));
				adaptationRuleNoVibration.executeAdaptation();*/
				break;
			case COPILOT:
				
				AdaptationRuleDashboard_INTERACT_3 adaptationRuleDashboard =  (AdaptationRuleDashboard_INTERACT_3)OSGiUtils.getService(context, IAdaptationRule.class, String.format("(%s=%s)", IIdentifiable.ID, "AdaptationRuleDashboard_INTERACT_3"));
				adaptationRuleDashboard.executeAdaptation();
				
				/*AdaptationRuleVibrationDriverSit adaptationRuleVibrationDriverSit =  (AdaptationRuleVibrationDriverSit)OSGiUtils.getService(context, IAdaptationRule.class, String.format("(%s=%s)", IIdentifiable.ID, "AdaptationRuleVibrationDriverSit"));
				adaptationRuleVibrationDriverSit.executeAdaptation();*/
				break;
			case OTHER:
				
				AdaptationRuleSpeakers_INTERACT_3 adaptationRuleSpeakers =  (AdaptationRuleSpeakers_INTERACT_3)OSGiUtils.getService(context, IAdaptationRule.class, String.format("(%s=%s)", IIdentifiable.ID, "AdaptationRuleSpeakers_INTERACT_3"));
				adaptationRuleSpeakers.executeAdaptation();
				
				/*AdaptationRuleVibrationWheel adaptationRuleVibrationWheel =  (AdaptationRuleVibrationWheel)OSGiUtils.getService(context, IAdaptationRule.class, String.format("(%s=%s)", IIdentifiable.ID, "AdaptationRuleVibrationWheel"));
				adaptationRuleVibrationWheel.executeAdaptation();*/
				break;
				
			
			}
		}
		
		
	}
}
