package sua.autonomouscar.mapek.infrastructure.knowledgeproperties;

import org.osgi.framework.BundleContext;

import mapek.interfaces.IKnowledgeProperty;
import mapek.interfaces.enums.EKnowledgeRoadStatus;
import sua.autonomouscar.infrastructure.Thing;

public class KnowledgePropertyRoadStatus extends Thing implements IKnowledgeProperty<EKnowledgeRoadStatus> {

	protected EKnowledgeRoadStatus roadStatus;
	private String propertyName = "KnowledgeRoadStatus";
	
	public KnowledgePropertyRoadStatus(BundleContext context, String id) {
		super(context, id);
		this.addImplementedInterface(IKnowledgeProperty.class.getName());
	}

	@Override
	public void updateKnowledge(EKnowledgeRoadStatus newKnowledge) {
		this.roadStatus = newKnowledge;
		this.setProperty(propertyName, newKnowledge);
		
	}

	@Override
	public EKnowledgeRoadStatus getKnowledge() {
		return (EKnowledgeRoadStatus) this.getProperty(propertyName);
		
	}

}
