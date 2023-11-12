package linea;
public class GameOver extends TurnStatus{
    public void playRedAt(){throw new RuntimeException("Game is finished");}
    public void playBlueAt(){throw new RuntimeException("Game is finished");}
}
