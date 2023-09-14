package queue;

import java.util.LinkedList;

public abstract class status {

	public abstract Object take(LinkedList<Object> elements);

    public abstract Object head(LinkedList<Object> elements);
}