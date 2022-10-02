package Model;
//
public class ExchangeRate {
    private final Currency currency_front;
    private final Currency currency_to;
    private final double rate;

    public ExchangeRate(Currency currency_front, Currency currency_to, double rate) {
        this.currency_front = currency_front;
        this.currency_to = currency_to;
        this.rate = rate;
    }

    public Currency getCurrency_front() {
        return currency_front;
    }

    public Currency getCurrency_to() {
        return currency_to;
    }

    public double getRate() {
        return rate;
    }
    
    
}
