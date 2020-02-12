/**
 * Created by Akshayraj
 */
package ark.coding.interviews.amzn;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class CurrencyRatios {
    private static final String USD = "USD";
    private static final String GBP = "GBP";
    private static final String EUR = "EUR";
    private static final String CAD = "CAD";
    private static final String AUD = "AUD";
    private static final String INR = "INR";
    private static final String RUB = "RUB";
    private static final String CNY = "CNY";
    private static final String BDT = "BDT";
    private static final String LKR = "LKR";
    private static final String MMK = "MMK";
    private static final String THB = "THB";
    private static final String NZD = "NZD";
    private static final String SGD = "SGD";


    public static void main(String[] args) {
        Map<String, Map<String, Double>> exchangeRates = new HashMap<String, Map<String, Double>>();
            Map<String, Double> fromUSD = new HashMap<String, Double>();
            fromUSD.put(GBP, 0.775645);
            fromUSD.put(EUR, 0.913617);
            fromUSD.put(CAD, 1.33087);
            fromUSD.put(AUD, 1.49848);
        exchangeRates.put(USD, fromUSD);

            Map<String, Double> fromGBP = new HashMap<String, Double>();
            fromGBP.put(USD, 1/0.775645);
            fromGBP.put(INR, 92.2009);
            fromGBP.put(RUB, 82.7244);
            fromGBP.put(CNY, 9.02666);
        exchangeRates.put(GBP, fromGBP);

        Map<String, Double> fromAUD = new HashMap<String, Double>();
        fromAUD.put(NZD, 1.04240);
        fromAUD.put(SGD, 0.927246);
        fromAUD.put(THB, 20.9053);
        exchangeRates.put(AUD, fromAUD);

            Map<String, Double> fromINR = new HashMap<String, Double>();
            fromINR.put(BDT, 1.18770);
            fromINR.put(LKR, 2.53708);
            fromINR.put(MMK, 20.2724);
            fromINR.put(THB, 0.438027);
        exchangeRates.put(INR, fromINR);

        System.out.println(getCurrencyRatio(exchangeRates, BDT, USD));
        // (USD/GBP) * (GBP/INR) * (INR/BDT) = USD/BDT

        System.out.println(getCurrencyRatio(exchangeRates, USD, THB));
        // (USD/GBP) * (GBP/INR) * (INR/THB) = USD/THB
        // (USD/AUD) * (AUD/THB)             = USD/THB
    }

    static double getCurrencyRatio(Map<String, Map<String, Double>> exchangeRates, String fromCurrency, String toCurrency) {
        return findRatioForCurrency(exchangeRates, fromCurrency, toCurrency, new HashSet<>());
    }

    static double findRatioForCurrency(
            Map<String, Map<String, Double>> exchangeRates,
            String fromCurrency,
            String toCurrency,
            Set<String> solvedCurrencies) {
        if (fromCurrency == toCurrency) {
            return 1;
        }

        if (solvedCurrencies.contains(fromCurrency)
            || !exchangeRates.containsKey(fromCurrency)) {
            return 0;
        }

        solvedCurrencies.add(fromCurrency);
        for (Map.Entry<String, Double> entry : exchangeRates.get(fromCurrency).entrySet()) {
            String currency = entry.getKey();
            Double exchangeRate = entry.getValue();
            Double exchangeRateOfNewCurrency = findRatioForCurrency(exchangeRates, currency, toCurrency, solvedCurrencies);
            if (exchangeRateOfNewCurrency != 0.0) {
                return exchangeRate * exchangeRateOfNewCurrency;
            }
        }
        return 0.0;
    }
}
