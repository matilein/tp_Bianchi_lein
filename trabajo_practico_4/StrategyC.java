package linea;
public class StrategyC extends Strategy{
    public StrategyC() {strategyType = 'C';}
    public char getStrategyType() {
        return strategyType;
    }
    public boolean finished() {
        return (Linea.StrategyB('R')||Linea.StrategyB('B')||
                Linea.StrategyA('R')||Linea.StrategyA('B')|| Linea.Tie());
    }
}