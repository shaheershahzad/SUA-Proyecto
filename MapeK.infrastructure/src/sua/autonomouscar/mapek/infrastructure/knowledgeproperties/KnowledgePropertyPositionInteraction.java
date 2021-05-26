package sua.autonomouscar.mapek.infrastructure.knowledgeproperties;

import org.osgi.framework.BundleContext;

import mapek.interfaces.IKnowledgeProperty;
import mapek.interfaces.enums.EKnowledgeRoadType;
import mapek.interfaces.enums.EKnowledgeVibrationModes;
import mapek.interfaces.enums.EPositionInteractionModes_INTERACT_3;
import sua.autonomouscar.infrastructure.Thing;

public class KnowledgePropertyPositionInteraction extends Thing implements IKnowledgeProperty<EPositionInteractionModes_INTERACT_3> {
	protected EPositionInteractionModes_INTERACT_3 roadType;
	private String propertyName = "KnowledgePositionInteraction";
	
	public KnowledgePropertyPositionInteraction(BundleContext context, String id) {
		super(context, id);
		this.addImplementedInterface(IKnowledgeProperty.class.getName());
	}

	@Override
	public void updateKnowledge(EPositionInteractionModes_INTERACT_3 newKnowledge) {
		this.roadType = newKnowledge;
		this.setProperty(propertyName, newKnowledge);
		
	}

	@Override
	public EPositionInteractionModes_INTERACT_3 getKnowledge() {
		return (EPositionInteractionModes_INTERACT_3) this.getProperty(propertyName);
	}

}
