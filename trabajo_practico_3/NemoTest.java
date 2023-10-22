package nemo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.LinkedList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

public class NemoTest {
	
	private String ascendOnSurfaceError = "Cant ascend on surface!";
	private String releaseCapsuleInGreatDepthError = "Destruction!";
	
	@Test public void test01InicialLocation() {
		Nemo nemo = new Nemo();
		nemoIsAtStartingPoint(nemo);
	  }
	
	@Test public void test02EmptyCommandDoesntAffect() {
		Nemo nemo = new Nemo();
		nemo.move("");
		nemoIsAtStartingPoint(nemo);
	  }
	
	@Test public void test03dCommandOnlyDescends() {
		Nemo nemo = new Nemo();
		nemo.move("d");
		compareLocation(0,0,-1, nemo);
	}
	
	@Test public void test04uCommandOnlyAscends() {
		Nemo nemo = new Nemo();
		nemo.move("d");
		nemo.move("u");
		nemoIsAtStartingPoint(nemo);
	}
	
	@Test public void test05fCommandSlidesNemoOnly1UnitOnInitialDirectionNorth() {
		Nemo nemo = new Nemo();
		nemo.move("f");
		compareLocation(0,1,0, nemo);
	}
	
	@Test public void test06lCommandDoesntAffectLocation() {
		Nemo nemo = new Nemo();
		nemo.move("l");
		nemoIsAtStartingPoint(nemo);
	}
	
	@Test public void test07lCommandDoesntAffectLocation() {
		Nemo nemo = new Nemo();
		nemo.move("r");
		nemoIsAtStartingPoint(nemo);
	}
	
	@Test public void test08lCommandRotatesDirectionLeft() {
		Nemo nemo = new Nemo();
		nemo.move("l");
		nemo.move("f");
		compareLocation(-1,0,0, nemo);
	}
	
	@Test public void test09rCommandRotatesDirectionRight() {
		Nemo nemo = new Nemo();
		nemo.move("r");
		nemo.move("f");
		compareLocation(1,0,0, nemo);
	}
	
	@Test public void test10NemoAcceptsMoreThanOneCommandAtOnce() {
		Nemo nemo = new Nemo();
		nemo.move("ffflf");
		compareLocation(-1,3,0, nemo);
	}
	
	@Test public void test11NemoMovesAccordingToHisRightRotation() {
		Nemo nemo = new Nemo();
		nemo.move("rfrf");
		compareLocation(1,-1,0, nemo);
	}
	
	@Test public void test12NemoMovesAccordingToHisLeftRotation() {
		Nemo nemo = new Nemo();
		nemo.move("lflf");
		compareLocation(-1,-1,0, nemo);
	}
	
	@Test public void test13NemoCanTurnBothRightAndLeftToMove() {
		Nemo nemo = new Nemo();
		nemo.move("ffrflf");
		compareLocation(1,3,0, nemo);
	}
	
	@Test public void test14DepthIsNegative1WhenInShallowDepth() {
		Nemo nemo = new Nemo();
		nemo.move("d");
		compareLocation(0,0,-1, nemo);
	}
	
	@Test public void test15NemoIsAllowedToMoveOnShallowDepth() {
		Nemo nemo = new Nemo();
		nemo.move("dlfrf");
		compareLocation(-1,1,-1, nemo);
	}
	
	@Test public void test16NemoIsAllowedToMoveOnGreatDepth() {
		Nemo nemo = new Nemo();
		nemo.move("dddlfrf");
		compareLocation(-1,1,-3, nemo);
	}
	
	@Test public void test17mCommandReleasesCapsuleOnSurface() {
		new Nemo().move("m");
	}
	
	@Test public void test18mCommandReleasesCapsuleOnShallowDepth() {
		new Nemo().move("dm");
	}
	
	@Test public void test19CapsuleExplodesInGreatDepth() {
		Nemo nemo = new Nemo();
		assertThrowsLike(()->nemo.move("ddm"), releaseCapsuleInGreatDepthError );
	}
	
	@Test public void test20uCommandCantAscend() {
		assertThrowsLike(()->new Nemo().move("u"), ascendOnSurfaceError);
	}
	
	public void compareLocation(int x, int y, int z, Nemo nemo) {
		LinkedList <Integer> location = nemo.location;
		assertEquals( location.getFirst(), x );
	    assertEquals( location.get(1), y );
	    assertEquals( location.getLast(), z );
	}
	
	private void nemoIsAtStartingPoint(Nemo nemo) {
		compareLocation(0,0,0, nemo);
	}
	
	private void assertThrowsLike( Executable executable, String message ) {
		    assertEquals( message,
		                  assertThrows( Exception.class, executable ).getMessage() );} 
}