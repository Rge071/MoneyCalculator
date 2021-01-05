package moneycalculator;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import moneycalculator.control.Changer;
import moneycalculator.control.ChangerHandler;
import moneycalculator.model.Currency;
import moneycalculator.model.ExchangeRate;
import moneycalculator.model.Money;
import moneycalculator.persistance.rest.RestExchangeRateLoader;
import moneycalculator.ui.MoneyDialog;
import moneycalculator.ui.MoneyDisplay;
import moneycalculator.ui.swing.SwingMoneyDialog;
import moneycalculator.ui.swing.SwingMoneyDisplay;

public class MoneyCalculatorFrame extends JFrame{
    private final List<Currency> currencies; 
    private MoneyDialog moneyDialog;
    private MoneyDisplay moneyDisplay;
    private Changer changer = new ChangerHandler();

    public MoneyCalculatorFrame(List<Currency> currencies){
        this.currencies = currencies;
        this.setTitle("Money Calculator");
        this.setSize(400,400);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.add(moneyDialog(), BorderLayout.NORTH);
        this.add(toolbar());
        this.add(moneyDisplay(), BorderLayout.SOUTH);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    private Component moneyDialog(){
        SwingMoneyDialog swingMoneyDialog = new SwingMoneyDialog(currencies);
        this.moneyDialog = swingMoneyDialog;
        return swingMoneyDialog;
    }

    private Component toolbar(){
        JPanel  panel = new JPanel();
        panel.add(calculateButton());
        return panel;
    }

    private Component moneyDisplay(){
        SwingMoneyDisplay swingMoneyDisplay = new SwingMoneyDisplay();
        this.moneyDisplay = swingMoneyDisplay;
        return swingMoneyDisplay;
    }

    private JButton calculateButton(){
        JButton button = new JButton("Calcular");
        button.addActionListener(calculate());
        return button;
    }

    private ActionListener calculate(){
        return new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent event){
                RestExchangeRateLoader loader = new RestExchangeRateLoader();
                Money money = moneyDialog.get();
                Currency cNew = moneyDialog.getNewCurrency();
                ExchangeRate exchangeRate=loader.load(money.getCurrency(),cNew);
                Money newMoney = changer.calculate(money,exchangeRate);
                moneyDisplay.Display(newMoney);
            }
        };
    }    
}
