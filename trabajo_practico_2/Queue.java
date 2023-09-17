package queue;

import java.util.LinkedList;

public class Queue {

    public LinkedList <Object> queue = new LinkedList<>();
    public LinkedList <status> estados = new LinkedList<>();
    private status estadoActual;
        
    public Queue() {
    	estadoActual = new emptyQueue();
    	estados.add(estadoActual);
        }
    
    public boolean isEmpty() {
        return queue.isEmpty();
    }

    public Queue add(Object newItem) {
        queue.add(newItem);
        estadoActual = new notEmpty();
        estados.add(estadoActual);
        return this;
    }
    
    public Object take() {
    	Object takenItem = (estadoActual).take(queue);
    	estados.removeLast();
    	estadoActual = estados.getLast();
    	return takenItem;
    }

    public Object head() {
    	return (estadoActual).head(queue);
    }

    public int size() {
        return queue.size();
    }
}