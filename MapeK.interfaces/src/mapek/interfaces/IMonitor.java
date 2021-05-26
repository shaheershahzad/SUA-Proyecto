package mapek.interfaces;

public interface IMonitor<E> {
	public void changedState(E newState);
}
