package sua.autonomouscar.mapek.infrastructure.knowledgeproperties;

import org.osgi.framework.BundleContext;

import mapek.interfaces.IKnowledgeProperty;
import mapek.interfaces.enums.EKnowledgeRoadType;
import sua.autonomouscar.infrastructure.Thing;

public class KnowledgePropertyRoadType extends Thing implements IKnowledgeProperty<EKnowledgeRoadType> {
	protected EKnowledgeRoadType roadType;
	private String propertyName = "KnowledgeRoadType";
	
	public KnowledgePropertyRoadType(BundleContext context, String id) {
		super(context, id);
		this.addImplementedInterface(IKnowledgeProperty.class.getName());
	}

	@Override
	public void updateKnowledge(EKnowledgeRoadType newKnowledge) {
		this.roadType = newKnowledge;
		this.setProperty(propertyName, newKnowledge);
		
	}

	@Override
	public EKnowledgeRoadType getKnowledge() {
		return (EKnowledgeRoadType) this.getProperty(propertyName);
	}

}

package sua.autonomouscar.mapek.infrastructure.knowledgeproperties;

import org.osgi.framework.BundleContext;

import mapek.interfaces.IKnowledgeProperty;
import mapek.interfaces.enums.EKnowledgeRoadType;
import sua.autonomouscar.infrastructure.Thing;

public class KnowledgePropertyRoadType extends Thing implements IKnowledgeProperty<EKnowledgeRoadType> {
	protected EKnowledgeRoadType roadType;
	private String propertyName = "KnowledgeRoadType";
	
	public KnowledgePropertyRoadType(BundleContext context, String id) {
		super(context, id);
		this.addImplementedInterface(IKnowledgeProperty.class.getName());
	}

	@Override
	public void updateKnowledge(EKnowledgeRoadType newKnowledge) {
		this.roadType = newKnowledge;
		this.setProperty(propertyName, newKnowledge);
	}

	@Override
	public EKnowledgeRoadType getKnowledge() {
		return (EKnowledgeRoadType) this.getProperty(propertyName);
	}

}
