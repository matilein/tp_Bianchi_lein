package linea;
public class StrategyA extends Strategy{
    public StrategyA() {strategyType = 'A';}
    public char getStrategyType() {
        return strategyType;
    }
    public boolean finished() {
        return (Linea.StrategyA('R')||Linea.StrategyA('B')|| Linea.Tie());
    }
}