package linea;
public abstract class Strategy {
    public char strategyType;
    public abstract char getStrategyType();
    public abstract boolean finished();
}