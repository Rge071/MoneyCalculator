package moneycalculator.model;

public class Currency {
    private final String code;
    private final String nombre;
    private final String symbol;

    public Currency(String code, String nombre, String symbol) {
        this.code = code;
        this.nombre = nombre;
        this.symbol = symbol;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return nombre;
    }

    public String getSymbol() {
        return symbol;
    }

    @Override
    public String toString() {
        return code;
    }   
}
