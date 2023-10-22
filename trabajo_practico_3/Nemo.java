package nemo;

import java.util.Arrays;
import java.util.LinkedList;

public class Nemo {
	public LinkedList <Integer> location = new LinkedList<>(Arrays.asList(0,0,0));
	
	private DirectionManager directionManager = new DirectionManager();
	
	private LinkedList <DepthStatus> depth = new LinkedList<>();
	
	private LinkedList <Runnable> commands = new LinkedList<>(Arrays.asList(this::DescendAction,this::AscendAction,
			this::TurnRightAction,this::TurnLeftAction, this::ForwardAction, this::CapsuleReleaseAction));
	
	public Nemo() {
		DepthStatus initialDepth = new Surface();
    	depth.add(initialDepth);
 	}
	
	public void move(String comandos) {
	    comandos.chars().forEach(character -> {
	        int commandValue = MovementAction.commandFor((char) character);
	        commands.get(commandValue).run();
	    });
	}

	public void DescendAction() {
        DepthStatus currentDepthd = depth.getLast();
        depth.add(currentDepthd.descend());
        location.set(2, location.getLast()-1);}

	public void AscendAction() {
    	DepthStatus currentDepthu = depth.getLast();
    	currentDepthu.ascendAuthorization();
        depth.removeLast();
        location.set(2, location.getLast()+1);}
	
	public void TurnRightAction() {
		directionManager.turnRight();}
	
	public void TurnLeftAction() {
		directionManager.turnLeft();}
	
	public void ForwardAction() {
		LinkedList<Integer> movementVector = directionManager.moveForward();
		location.set(0, location.getFirst()+movementVector.getFirst());
		location.set(1, location.get(1)+movementVector.getLast());}
	
	public void CapsuleReleaseAction() {
    	DepthStatus currentDepthm = depth.getLast();
    	currentDepthm.capsuleRelease();}
	}