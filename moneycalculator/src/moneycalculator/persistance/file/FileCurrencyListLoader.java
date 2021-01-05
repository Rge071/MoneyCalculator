package moneycalculator.persistance.file;

import moneycalculator.model.Currency;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import moneycalculator.persistance.CurrencyListLoader;

public class FileCurrencyListLoader implements CurrencyListLoader{
    private final String filename;
    public FileCurrencyListLoader(String filename){
        this.filename = filename;
    }
    @Override
    public List<Currency> currencies(){
        List<Currency> list = new ArrayList<>();
        try{
            BufferedReader reader = new BufferedReader(new FileReader(new File(filename)));
            while(true){
                String line = reader.readLine();
                if(line == null) break;
                list.add(currencyOf(line));
            }
        }catch(FileNotFoundException ex){
            System.out.println("ERROR FileCurrencyListLoader::currencies (File Not Found)" + ex.getMessage());
        }catch(IOException ex){
            System.out.println("ERROR FileCurrencyListLoader::currencies (IOException)" + ex.getMessage());
        }
        return list;
    }

    private Currency currencyOf(String line){
        String[] split = line.split(",");
        return new Currency(split[0],split[1],split[2]);
    }
}
