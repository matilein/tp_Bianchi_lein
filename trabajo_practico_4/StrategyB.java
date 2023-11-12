package linea;
public class StrategyB extends Strategy{
    public StrategyB() {strategyType = 'B';}
    public char getStrategyType() {
        return strategyType;
    }
    public boolean finished() {
        return (Linea.StrategyB('R')||Linea.StrategyB('B')|| Linea.Tie());
    }
}