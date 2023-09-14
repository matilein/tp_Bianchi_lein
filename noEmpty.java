package queue;

import java.util.LinkedList;

public class noEmpty extends status {
	
    public Object take(LinkedList<Object> elements) {
    	 return elements.removeFirst();
	}
    public Object head(LinkedList<Object> elements) {
   	 	 return elements.getFirst();
	}
}