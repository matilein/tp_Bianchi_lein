package nemo;

public abstract class DepthStatus {
	
	public abstract Boolean ascendAuthorization();

    public abstract DepthStatus descend();

    public abstract DepthStatus capsuleRelease();
}