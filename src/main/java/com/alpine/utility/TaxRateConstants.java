package com.alpine.utility;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TaxRateConstants {
    public final static Map<String, Double> taxRates = new HashMap<String, Double>() {
        {
            put("AL", 0.06);
            put("AK", 0.0);
            put("AZ", 0.056);
            put("AR", 0.065);
            put("CA", 0.0725);
            put("CO", 0.029);
            put("CT", 0.0635);
            put("DE", 0.0);
            put("DC", 0.06);
            put("FL", 0.06);
            put("GA", 0.04);
            put("HI", 0.04);
            put("ID", 0.06);
            put("IL", 0.0625);
            put("IN", 0.07);
            put("IA", 0.06);
            put("KS", 0.065);
            put("KY", 0.06);
            put("LA", 0.0445);
            put("ME", 0.055);
            put("MD", 0.06);
            put("MA", 0.065);
            put("MI", 0.06);
            put("MN", 0.0688);
            put("MS", 0.07);
            put("MO", 0.0423);
            put("MT", 0.0);
            put("NE", 0.055);
            put("NV", 0.0685);
            put("NH", 0.0);
            put("NJ", 0.0663);
            put("NM", 0.0488);
            put("NY", 0.04);
            put("NC", 0.0475);
            put("ND", 0.05);
            put("OH", 0.0575);
            put("OK", 0.045);
            put("OR", 0.0);
            put("PA", 0.06);
            put("RI", 0.07);
            put("SC", 0.06);
            put("SD", 0.042);
            put("TN", 0.07);
            put("TX", 0.0625);
            put("UT", 0.0610);
            put("VT", 0.06);
            put("VA", 0.0530);
            put("WA", 0.0650);
            put("WV", 0.06);
            put("WI", 0.05);
            put("WY", 0.04);

            put("AB", 0.05);
            put("BC", 0.12);
            put("MB", 0.12);
            put("NB", 0.15);
            put("NL", 0.15);
            put("NS", 0.15);
            put("NT", 0.05);
            put("NU", 0.05);
            put("ON", 0.13);
            put("PE", 0.15);
            put("QC", 0.15);
            put("SK", 0.11);
            put("YT", 0.05);
        }
    };
    public final static List<String> listOfTaxRates = new ArrayList<>(taxRates.keySet());
}
