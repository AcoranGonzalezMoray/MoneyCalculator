package Persistence;

import Model.Currency;
import Model.CurrencyLoader;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CurrencyLoaderFromFile implements CurrencyLoader{
    private final String fileName;

    public CurrencyLoaderFromFile(String fileName) {
        this.fileName = fileName;
    }
    
    @Override
    public List<Currency> loadCurrencies() {
      
        List<Currency> list = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(new File(this.fileName)));
            IteratorReader iteratorReader = new IteratorReader(reader);
            for(String line: iteratorReader){
                list.add(currencyOf(line));
                
            }
        } catch (FileNotFoundException ex) {
            //Logger.getLogger(CurrencyLoaderFromFile.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Error: "+ex);
            
        } catch (IOException ex) {
            //Logger.getLogger(CurrencyLoaderFromFile.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Error: "+ex);
        }
        return list;
    }
    //Error
    private Currency currencyOf(String line) {
        String [] split  = line.split(", ");
        
        return new Currency(split[0], split[1], split[2]);
    }
    
}
