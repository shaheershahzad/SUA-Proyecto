package sua.autonomouscar.mapek.infrastructure.knowledgeproperties;

import org.osgi.framework.BundleContext;

import mapek.interfaces.IKnowledgeProperty;
import mapek.interfaces.enums.EDrivingConfig_ADS_2;
import mapek.interfaces.enums.EFallbackPlanNecessary;
import sua.autonomouscar.infrastructure.Thing;

public class KnowledgePropertyDrivingConfig_ADS_2 extends Thing implements IKnowledgeProperty<EDrivingConfig_ADS_2> {
	protected EDrivingConfig_ADS_2 drivingConfig;
	private String propertyName = "KnowledgeDrivingConfig";
	
	public KnowledgePropertyDrivingConfig_ADS_2(BundleContext context, String id) {
		super(context, id);
		this.addImplementedInterface(IKnowledgeProperty.class.getName());
	}

	@Override
	public void updateKnowledge(EDrivingConfig_ADS_2 newKnowledge) {
		this.drivingConfig = newKnowledge;
		this.setProperty(propertyName, newKnowledge);
		
	}

	@Override
	public EDrivingConfig_ADS_2 getKnowledge() {
		return (EDrivingConfig_ADS_2) this.getProperty(propertyName);
	}

}
