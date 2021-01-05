package moneycalculator;

import moneycalculator.persistance.CurrencyListLoader;
import moneycalculator.persistance.file.FileCurrencyListLoader;

public class MoneyCalculator {

    public static void main(String[] args) throws Exception{
        CurrencyListLoader Loader=new FileCurrencyListLoader("Divisas.txt");
        MoneyCalculatorFrame calc=new MoneyCalculatorFrame(Loader.currencies());
    }
}
