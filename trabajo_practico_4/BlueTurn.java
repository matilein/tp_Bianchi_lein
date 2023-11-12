package linea;
public class BlueTurn extends TurnStatus{
    public void playRedAt(){
        throw new RuntimeException("Red's turn, not blue's");
    }
    public void playBlueAt() {}
}
