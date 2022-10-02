package Persistence.rest;

import Model.Currency;
import Model.ExchangeRate;
//Paquete rest
public interface ExchangeRateLoader {
    public ExchangeRate exh (Currency form, Currency to);
}
