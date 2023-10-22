package nemo;

public class GreatDepth extends DepthStatus {
	
	private String releaseCapsuleInGreatDepthError = "Destruction!";
	
	public Boolean ascendAuthorization() {
		return true;
	}
	
	public DepthStatus descend() {
		return new GreatDepth();
	}

	public DepthStatus capsuleRelease() {
		throw new RuntimeException(releaseCapsuleInGreatDepthError);
	}
}