package sua.autonomouscar.mapek.infrastructure.knowledgeproperties;

import org.osgi.framework.BundleContext;

import mapek.interfaces.IKnowledgeProperty;
import mapek.interfaces.enums.EFallbackPlanNecessary;
import mapek.interfaces.enums.EKnowledgeRoadStatus;
import mapek.interfaces.enums.EKnowledgeRoadType;
import sua.autonomouscar.infrastructure.Thing;

public class KnowledgePropertyFallBackPlanNecessary extends Thing implements IKnowledgeProperty<EFallbackPlanNecessary> {
	protected EFallbackPlanNecessary fallbackPlanNecessary;
	private String propertyName = "KnowledgeRoadType";
	
	public KnowledgePropertyFallBackPlanNecessary(BundleContext context, String id) {
		super(context, id);
		this.addImplementedInterface(IKnowledgeProperty.class.getName());
	}

	@Override
	public void updateKnowledge(EFallbackPlanNecessary newKnowledge) {
		this.fallbackPlanNecessary = newKnowledge;
		this.setProperty(propertyName, newKnowledge);
		
	}

	@Override
	public EFallbackPlanNecessary getKnowledge() {
		return (EFallbackPlanNecessary) this.getProperty(propertyName);
	}

}
