package moneycalculator.control;

import moneycalculator.model.ExchangeRate;
import moneycalculator.model.Money;

public class ChangerHandler implements Changer{
    @Override
    public Money calculate(Money dinero, ExchangeRate exchangeRate){
        double newAmount = (dinero.getAmount() * exchangeRate.getRate());  
        Money newMoney = new Money(newAmount,exchangeRate.getTo());  
        return newMoney;
    }    
}
