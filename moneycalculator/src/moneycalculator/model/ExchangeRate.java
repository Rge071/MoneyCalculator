package moneycalculator.model;

public class ExchangeRate{
    
    private final Currency origen;
    private final Currency cambio;
    private final double rate;

    public ExchangeRate(Currency origen, Currency cambio, double rate){
        this.origen = origen;
        this.cambio = cambio;
        this.rate = rate;
    }

    public Currency getFrom(){
        return origen;
    }

    public Currency getTo(){
        return cambio;
    }

    public double getRate(){
        return rate;
    }
}
