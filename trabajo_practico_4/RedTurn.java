package linea;
public class RedTurn extends TurnStatus{
    public void playRedAt(){}
    public void playBlueAt() {
        throw new RuntimeException("Red's turn, not blue's");
    }
}
