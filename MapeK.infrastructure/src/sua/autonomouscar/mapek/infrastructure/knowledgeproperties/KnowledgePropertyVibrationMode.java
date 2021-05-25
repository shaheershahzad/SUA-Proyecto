package sua.autonomouscar.mapek.infrastructure.knowledgeproperties;

import org.osgi.framework.BundleContext;

import mapek.interfaces.IKnowledgeProperty;
import mapek.interfaces.enums.EKnowledgeRoadType;
import mapek.interfaces.enums.EKnowledgeVibrationModes;
import sua.autonomouscar.infrastructure.Thing;

public class KnowledgePropertyVibrationMode extends Thing implements IKnowledgeProperty<EKnowledgeVibrationModes> {
	protected EKnowledgeVibrationModes roadType;
	private String propertyName = "KnowledgeVibrationMode";
	
	public KnowledgePropertyVibrationMode(BundleContext context, String id) {
		super(context, id);
		this.addImplementedInterface(IKnowledgeProperty.class.getName());
	}

	@Override
	public void updateKnowledge(EKnowledgeVibrationModes newKnowledge) {
		this.roadType = newKnowledge;
		this.setProperty(propertyName, newKnowledge);
		
	}

	@Override
	public EKnowledgeVibrationModes getKnowledge() {
		return (EKnowledgeVibrationModes) this.getProperty(propertyName);
	}

}
