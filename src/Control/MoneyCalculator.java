package Control;

import Model.Currency;
import Model.ExchangeRate;
import Persistence.CurrencyLoaderFromFile;
import Persistence.rest.ExchangeRateLoaderFromWebService;
import View.GUI;
import java.util.List;

public class MoneyCalculator {

    public static void main(String[] args){
        //MODEL
        CurrencyLoaderFromFile currencyLoaderFromFile = new CurrencyLoaderFromFile ("Curren.txt");
        List<Currency> list =currencyLoaderFromFile.loadCurrencies();
        ExchangeRateLoaderFromWebService exchangeRateLoaderFromWebService = new ExchangeRateLoaderFromWebService() ;
        
        //VIEW
        GUI gui = new GUI(list, exchangeRateLoaderFromWebService);
    }
    
}
