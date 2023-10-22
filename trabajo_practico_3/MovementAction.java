package nemo;

import java.util.List;

public abstract  class MovementAction {
	
	 public abstract int execute();
	 public abstract char getLetra();
	 public static List<MovementAction> moveList = List.of(
			 	new DescendMove(),
	 			new CapsuleRelease(),
	 			new AscendMove(),
	 			new RightMove(),
	 			new LeftMove(),
	 			new ForwardMove()
			 );
	 
	public static int commandFor(char character) {		
		MovementAction matchingCommand=moveList.stream()
				.filter(command->command.getLetra()==character)
							 .findFirst()
							 .orElseThrow();
					 return matchingCommand.execute();
			}
}