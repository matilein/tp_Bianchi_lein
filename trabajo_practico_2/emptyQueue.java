package queue;

import java.util.LinkedList;

public class emptyQueue extends status {
	
    public Object take(LinkedList<Object> elements) {
    	throw new Error("Queue is empty");
    }
    
    public Object head(LinkedList<Object> elements) {
    	throw new Error("Queue is empty");
    }
}