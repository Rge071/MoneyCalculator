package moneycalculator.ui;

import moneycalculator.model.Currency;
import moneycalculator.model.Money;

public interface MoneyDialog {
    public Money get();
    public Currency getNewCurrency();
}
