package queue;

import java.util.LinkedList;

public class Queue {

    public LinkedList <Object> queue = new LinkedList<>();
    private status estadoActual;
    
    public Queue() {
    	estadoActual = new emptyQueue();
        }

    
    public boolean isEmpty() {
        return queue.isEmpty();
    }

    public Queue add(Object algo) {
        queue.add(algo);
        estadoActual = new noEmpty();
        return this;
    }
    public Object take() {
    	return (estadoActual).take(queue);
        
    }

    public Object head() {
    	return (estadoActual).head(queue);
      
    }

    public int size() {
        return queue.size();
    }

}