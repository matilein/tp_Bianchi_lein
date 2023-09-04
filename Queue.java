package queue;

import java.util.ArrayList;
import java.util.List;


public class Queue {
  public List<Object> queue = new ArrayList<>();
  public boolean isEmpty() {
		// TODO Auto-generated method stub
		return queue.isEmpty();
	}

	public Queue add( Object  cargo ) {
		// TODO Auto-generated method stub
		queue.add(cargo);
		return this;
	}

	public Object take() {
    // TODO Auto-generated method stub
		return null;
	}

	public Object head() {
		// TODO Auto-generated method stub
		try {
			return queue.get(0);
		    } catch ( IndexOutOfBoundsException e ) {
		      throw new Error("Queue is empty");
		    }
	}

	public int size() {
		// TODO Auto-generated method stub
		return queue.size();
	}

}
