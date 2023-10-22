package nemo;

public class ShallowDepth extends DepthStatus {

	public Boolean ascendAuthorization() {
		return true;
	}

	public DepthStatus descend() {
		return new GreatDepth();
	}

	public DepthStatus capsuleRelease() {
		return this;
	}
}