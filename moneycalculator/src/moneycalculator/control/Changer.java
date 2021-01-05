
package moneycalculator.control;

import moneycalculator.model.ExchangeRate;
import moneycalculator.model.Money;


public interface Changer {
    public Money calculate(Money dinero, ExchangeRate exchangeRate); 
}
