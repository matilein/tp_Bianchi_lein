package nemo;

import java.util.Arrays;
import java.util.LinkedList;

public class DirectionManager {
	private LinkedList <Runnable> cardinalDirections = new LinkedList<>(Arrays.asList(this::north,this::east,this::south,this::west));
	private LinkedList <Integer> movementVector = new LinkedList<>(Arrays.asList(0,0));
	
	public void turnRight() {
		cardinalDirections.addLast(cardinalDirections.removeFirst());
	}
	
	public void turnLeft() {
		cardinalDirections.addFirst(cardinalDirections.removeLast());
	}
	
	public void north(){
		movementVector.set(0,0);
		movementVector.set(1,1);
	}
	
	public void east(){
		movementVector.set(0,1);
		movementVector.set(1,0);
	}
	
	public void south(){
		movementVector.set(1,-1);
		movementVector.set(0,0);
	}
	
	public void west(){
		movementVector.set(0,-1);
		movementVector.set(1,0);
	}
	
	public LinkedList<Integer> moveForward(){
		cardinalDirections.getFirst().run();
		return movementVector;
	}
}
