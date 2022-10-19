package Control;

import Model.Currency;
import Model.ExchangeRate;
import Persistence.CurrencyLoaderFromFile;
import Persistence.rest.ExchangeRateLoaderFromWebService;
import java.util.List;

public class MoneyCalculator {

    public static void main(String[] args){
        //MODEL
        CurrencyLoaderFromFile currencyLoaderFromFile = new CurrencyLoaderFromFile ("Curren.txt");
        List<Currency> list =currencyLoaderFromFile.loadCurrencies();
        ExchangeRateLoaderFromWebService exchangeRateLoaderFromWebService = new ExchangeRateLoaderFromWebService() ;
        for(Currency currencyFrom : list){
            for(Currency currencyTo :list){
                if (currencyFrom.getCode().compareTo(currencyTo.getCode())!=0){
                    ExchangeRate exchangeRate =  exchangeRateLoaderFromWebService.exh(currencyFrom, currencyTo);
                    System.out.println("Rate from " + exchangeRate.getCurrency_front().getCode() + " to "+ exchangeRate.getCurrency_to().getCode() + " RATE: "+exchangeRate.getRate());
                }
            }
        
        }
        
        //VIEW
        
    }
    
}
