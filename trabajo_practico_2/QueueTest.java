package queue;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.Test;

public class QueueTest {

  @Test public void test01QueueShouldBeEmptyWhenCreated() {
    assertTrue( newQ().isEmpty() );
  }

  @Test public void test02AddElementsToTheQueue() {
    assertFalse( q_Something().isEmpty() );
  }

  @Test public void test03AddedElementsIsAtHead() {
    assertEquals( "Something", q_Something().head() );
  }
  
  @Test public void test04TakeRemovesElementsFromTheQueue() {
    Queue queue = q_Something();
    queue.take();
    
    assertTrue( queue.isEmpty() );
  }

  @Test public void test05TakeReturnsLastAddedObject() {
    assertEquals( "Something" , q_Something().take() );
  }

  @Test public void test06QueueBehavesFIFO() {
    Queue queue = q_First_Second();

    assertEquals( queue.take(), "First" );
    assertEquals( queue.take(), "Second" );
    assertTrue( queue.isEmpty() );
  }

  @Test public void test07HeadReturnsFirstAddedObject() {
    assertEquals( q_First_Second().head(), "First" );
  }

  @Test public void test08HeadDoesNotRemoveObjectFromQueue() {
    Queue queue = q_Something();
    assertEquals( 1, queue.size() );
    queue.head();
    assertEquals( 1, queue.size() );
  }

  @Test public void test09SizeRepresentsObjectInTheQueue() {
    assertEquals( 2, q_First_Second().size() );
  }

  @Test public void test10CanNotTakeWhenThereAreNoObjectsInTheQueue() {
    Queue queue = newQ();
    catchtakeError(queue);
  }

  @Test public void test09CanNotTakeWhenThereAreNoObjectsInTheQueueAndTheQueueHadObjects() {
    Queue queue = q_Something();
    queue.take();
    catchtakeError(queue);
  }

  @Test public void test10CanNotHeadWhenThereAreNoObjectsInTheQueue() {
    Queue queue = newQ();
    try {
      queue.head();
      fail( "Expected Error was not thrown." );
    } catch (Error e) {
      assertTrue( e.getMessage().equals( "Queue is empty" ) );
    }
  }
  
  private Queue newQ() {
		return new Queue();
	}
  
  private Queue q_Something() {
		Queue queue = newQ().add( "Something" );
		return queue;
	}
  
  private Queue q_First_Second() {
		return newQ().add( "First" ).add( "Second" );
	}
  
  private void catchtakeError(Queue queue) {
		try {
	      queue.take();
	      fail( "Expected Error was not thrown." );
	    } catch (Error e) {
	      assertTrue( e.getMessage().equals( "Queue is empty" ) );
	    }
	}
}