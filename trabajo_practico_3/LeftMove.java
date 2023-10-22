package nemo;

public class LeftMove extends MovementAction {
    private char letra = 'l'; 

    public int execute() {
    	int x = 3;
        return x;
    }

    public char getLetra() {
        return letra;
    }
}