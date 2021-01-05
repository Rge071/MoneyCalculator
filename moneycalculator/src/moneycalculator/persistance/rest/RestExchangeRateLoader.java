package moneycalculator.persistance.rest;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import moneycalculator.model.Currency;
import moneycalculator.model.ExchangeRate;
import moneycalculator.persistance.ExchangeRateLoader;

public class RestExchangeRateLoader implements ExchangeRateLoader{

    @Override
    public ExchangeRate load(Currency from, Currency to) {
        try {
            return new ExchangeRate(from, to, read(from.getCode(), to.getCode()));
        } catch (MalformedURLException ex) {
            System.out.print("ERROR RestExchangeRateLoader::load (Mal formed URL)" + ex.getMessage());
            return null;
        } catch (IOException ex){
            System.out.print("ERROR RestExchangeRateLoader::load (IOException)" + ex.getMessage());
            return null;
        }
        
    }
    
    private double read(String from, String to) throws MalformedURLException, IOException{
        if(from.equals(to))return 1;
        String line = read(new URL("https://api.exchangeratesapi.io/latest?base=" + from + "&symbols=" + to));
        return Double.parseDouble(line.substring(line.indexOf(to) + 5, line.indexOf("}")));
    }

    private String read(URL url) throws IOException {
        InputStream is = url.openStream();
        byte[] buffer = new byte[1024];
        int length = is.read(buffer);
        return new String(buffer, 0, length);
    }
    
}
