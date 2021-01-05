package moneycalculator.ui.swing;

import java.awt.Component;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import moneycalculator.model.Currency;
import moneycalculator.ui.MoneyDialog;
import moneycalculator.model.Money;


public class SwingMoneyDialog extends JPanel implements MoneyDialog{
    private final Currency[] currencies;
    private String amount;
    private Currency currencyFrom;
    private Currency currencyTo;

    public SwingMoneyDialog(List<Currency> currencies){
        this.currencies = currencies.toArray(new Currency[0]);
        this.add(amount());
        this.add(currencyFrom());
        this.add(new JLabel("a"));
        this.add(currencyTo());
    }
    
    @Override
    public Money get(){
        return new Money(Double.parseDouble(amount),currencyFrom);
    }
    
    @Override
    public Currency getNewCurrency(){
        return currencyTo;
    }

    private Component amount(){
        JTextField field = new JTextField();
        field.setColumns(10);
        field.getDocument().addDocumentListener(amountChanged());
        amount = field.getText();
        return field;
    }

    private Component currencyFrom(){
        JComboBox combo = new JComboBox(currencies);
        combo.addItemListener(currecyFromChanged());
        currencyFrom = (Currency) combo.getSelectedItem();
        return combo;
    }
    
    private Component currencyTo(){
        JComboBox combo2 = new JComboBox(currencies);
        combo2.addItemListener(currecyToChanged());
        currencyTo = (Currency) combo2.getSelectedItem();
        return combo2;
    }

    private DocumentListener amountChanged(){
        return new DocumentListener(){
            @Override
            public void insertUpdate(DocumentEvent event){
                updateAmount(event.getDocument());
            }
            @Override
            public void removeUpdate(DocumentEvent event){
                updateAmount(event.getDocument());
            }
            @Override
            public void changedUpdate(DocumentEvent event){
                updateAmount(event.getDocument());
            }

            private void updateAmount(Document document){
                try {
                    amount = document.getText(0, document.getLength());
                } catch (BadLocationException ex) {
                    System.out.println("ERROR SwingMoneyDialog::amountChanged (Bad Location Exception)" + ex.getMessage());
                }
            }
        };
    }

    private ItemListener currecyFromChanged(){
        return new ItemListener(){
            @Override
            public void itemStateChanged(ItemEvent item){
                if(item.getStateChange() == ItemEvent.DESELECTED) return;
                currencyFrom = (Currency) item.getItem();
            }
        };
    }
        
    private ItemListener currecyToChanged(){
        return new ItemListener(){
            @Override
            public void itemStateChanged(ItemEvent item){
                if(item.getStateChange() == ItemEvent.DESELECTED) return;
                currencyTo = (Currency) item.getItem();
            }
        };
    }   
}
