package sua.autonomouscar.mapek.infrastructure.knowledgeproperties;

import org.osgi.framework.BundleContext;

import mapek.interfaces.IKnowledgeProperty;
import mapek.interfaces.enums.EVehicleContext;
import sua.autonomouscar.infrastructure.Thing;

public class KnowledgePropertyVehicleContext extends Thing implements IKnowledgeProperty<EVehicleContext> {
	protected EVehicleContext vehicleContext;
	private String propertyName = "KnowledgeVehicleContext";
	
	public KnowledgePropertyVehicleContext(BundleContext context, String id) {
		super(context, id);
		this.addImplementedInterface(IKnowledgeProperty.class.getName());
	}

	@Override
	public void updateKnowledge(EVehicleContext newKnowledge) {
		this.vehicleContext = newKnowledge;
		this.setProperty(propertyName, newKnowledge);
		
	}

	@Override
	public EVehicleContext getKnowledge() {
		return (EVehicleContext) this.getProperty(propertyName);
	}

}
