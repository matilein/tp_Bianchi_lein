package nemo;

public class Surface extends DepthStatus {
		
	private String ascendOnSurfaceError = "Cant ascend on surface!";
	
    public Boolean ascendAuthorization() {
    	throw new RuntimeException(ascendOnSurfaceError);
    }

    public DepthStatus descend() {
		return new ShallowDepth();
	}
    
    public DepthStatus capsuleRelease() {
    	return this;
    }
}