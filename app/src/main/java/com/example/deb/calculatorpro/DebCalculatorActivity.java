package com.example.deb.calculatorpro;

/**
 * Created by deb on 6/21/2016.
 */
public class DebCalculatorActivity {
    private double calculatorOperand;
    private double calculatorWaitingOperand;
    private String calculatorWaitingOperator;
    private double calculatorMemory;

    public static final String ADD = "+";
    public static final String SUBTRACT = "-";
    public static final String MULTIPLY = "*";
    public static final String DIVIDE = "/";

    public static final String CLEAR = "C" ;
    public static final String CLEARMEMORY = "MC";
    public static final String ADDTOMEMORY = "M+";
    public static final String SUBTRACTFROMMEMORY = "M-";
    public static final String RECALLMEMORY = "MR";
    public static final String SQUAREROOT = "√";
    public static final String SQUARED = "x²";
    public static final String INVERT = "1/x";
    public static final String TOGGLESIGN = "+/-";
    public static final String SINE = "sin";
    public static final String COSINE = "cos";
    public static final String TANGENT = "tan";
    //public static final String EQUALS = "=";

    public DebCalculatorActivity( ) {
        this.calculatorOperand = 0;

        this.calculatorWaitingOperand = 0;
        this.calculatorWaitingOperator = "";
        this.calculatorMemory = 0;
    }

    public double getCalculatorOperand() {
        return calculatorOperand;
    }

    public void setCalculatorOperand(double calculatorOperand) {
        this.calculatorOperand = calculatorOperand;
    }

    public double getCalculatorMemory() {
        return calculatorMemory;
    }

    public void setCalculatorMemory(double calculatorMemory) {
        this.calculatorMemory = calculatorMemory;
    }

    protected double performOperation(String operator){
        switch (operator){
            case CLEAR:
                calculatorOperand = 0;
                calculatorWaitingOperand = 0;
                calculatorWaitingOperator = "";
                break;
            case CLEARMEMORY:
                calculatorMemory = 0;
                break;
            case ADDTOMEMORY:
                calculatorMemory = calculatorMemory + calculatorOperand;
                break;
            case SUBTRACTFROMMEMORY:
                calculatorMemory = calculatorMemory - calculatorOperand;
                break;
            case RECALLMEMORY:
                calculatorOperand = calculatorMemory;
                break;
            case SQUAREROOT:
                calculatorOperand = Math.sqrt(calculatorOperand);
                break;
            case SQUARED:
                calculatorOperand = calculatorOperand * calculatorOperand;
                break;
            case INVERT:
                if (calculatorOperand != 0) {
                    calculatorOperand = 1 / calculatorOperand;
                }
                break;
            case TOGGLESIGN:
                calculatorOperand = -calculatorOperand;
                break;
            case SINE:
                calculatorOperand = Math.sin(Math.toRadians(calculatorOperand)); // Math.toRadians(mOperand) converts result to degrees
                break;
            case COSINE:
                calculatorOperand = Math.cos(Math.toRadians(calculatorOperand));
                break;
            case TANGENT:
                calculatorOperand = Math.tan(Math.toRadians(calculatorOperand));
                break;
            default:
                performWaitingOperation();
                calculatorWaitingOperator = operator;
                calculatorWaitingOperand = calculatorOperand;
                break;
        }

        return calculatorOperand;
    }

    protected void performWaitingOperation(){
        if (calculatorWaitingOperator.equals(ADD)) {
            calculatorOperand = calculatorWaitingOperand + calculatorOperand;
        }
        else if (calculatorWaitingOperator.equals(SUBTRACT)) {
            calculatorOperand = calculatorWaitingOperand - calculatorOperand;
        }
        else if (calculatorWaitingOperator.equals(MULTIPLY)) {
            calculatorOperand = calculatorWaitingOperand * calculatorOperand;
        }
        else if (calculatorWaitingOperator.equals(DIVIDE)) {
            if (calculatorOperand != 0) {
                calculatorOperand = calculatorWaitingOperand / calculatorOperand;
            }
        }
    }
}
