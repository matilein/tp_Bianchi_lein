package queue;

import java.util.LinkedList;

public class emptyQueue extends status {
    @Override
    public Object take(LinkedList<Object> elements) {
        // Lanza una excepción DudeException en lugar de imprimir
    	throw new Error("Queue is empty");
    }

    @Override
    public Object head(LinkedList<Object> elements) {
        // Lanza una excepción DudeException en lugar de imprimir
    	throw new Error("Queue is empty");
    }
    
    // Otros métodos específicos de Toxicdude si es necesario
}