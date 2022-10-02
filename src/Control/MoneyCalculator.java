package Control;

import Model.Currency;
import Model.ExchangeRate;
import Persistence.CurrencyLoaderFromFile;
import Persistence.rest.ExchangeRateLoaderFromWebService;
import java.util.List;

public class MoneyCalculator {

    public static void main(String[] args){
    
        CurrencyLoaderFromFile currencyLoaderFromFile = new CurrencyLoaderFromFile ("Curren.txt");
        List<Currency> list =currencyLoaderFromFile.loadCurrencies();
        for(Currency currency : list ){
            System.out.println(currency.getCode());
        }
        ExchangeRateLoaderFromWebService exchangeRateLoaderFromWebService = new ExchangeRateLoaderFromWebService() ;
        for(Currency currencyFrom : list){
            
            for(Currency currencyTo :list){
                if (currencyFrom.getCode().equals(currencyTo.getCode())){
                    ExchangeRate exchangeRate =  exchangeRateLoaderFromWebService.exh(currencyFrom, currencyTo);
                    //System.out.println(exchangeRateLoaderFromWebService.read(currencyFrom.getCode(), currencyTo.getCode()));
                }
            }
        
        }
    }
    
}
