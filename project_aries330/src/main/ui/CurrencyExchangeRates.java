package ui;

import jdk.nashorn.internal.parser.JSONParser;
import org.json.simple.JSONObject;

import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;

public class CurrencyExchangeRates {

    private static JSONParser parser = new JSONParser();
    private static Object obj;

    static {
        try {
            obj = parser.parse(new FileReader("JSONFile.json"));
        } catch (IOException e) {
            System.out.println("IOException caught");
        } catch (ParseException e) {
            System.out.println("ParseException caught!");
        }
    }

    public static JSONObject jsonObject = (JSONObject) obj;
    public static String jsonObject2 = (String) jsonObject.get("source");

    public static JSONObject jsonObject3 = (JSONObject) jsonObject.get("quotes");
    private static Double jsonObject4 = (Double) jsonObject3.get("USDCAD");
    private static Double jsonObject5 = (Double) jsonObject3.get("USDCNY");
    private static Double jsonObject6 = (Double) jsonObject3.get("USDTWD");
    private static Double jsonObject7 = (Double) jsonObject3.get("USDJPY");
    private static Double jsonObject8 = (Double) jsonObject3.get("USDKRW");
    private static Double jsonObject9 = (Double) jsonObject3.get("USDNZD");
    private static Double jsonObject10 = (Double) jsonObject3.get("USDZAR");
    private static Double jsonObject11 = (Double) jsonObject3.get("USDGBP");
    private static Double jsonObject12 = (Double) jsonObject3.get("USDEUR");
    private static Double jsonObject13 = (Double) jsonObject3.get("USDSGD");
    private static Double jsonObject14 = (Double) jsonObject3.get("USDAUD");
    private static Double jsonObject15 = (Double) jsonObject3.get("USDMXN");
    private static Double jsonObject16 = (Double) jsonObject3.get("USDPHP");
    private static Double jsonObject17 = (Double) jsonObject3.get("USDRUB");
    private static Double jsonObject18 = (Double) jsonObject3.get("USDINR");
    private static Double jsonObject19 = (Double) jsonObject3.get("USDAED");


    public static void main(String[] args) {

        System.out.println("$1 Source (United States Dollar): " + jsonObject2);
        System.out.println("This is a list of selected currencies limited to our bank:");
        System.out.println("USD to CAD (Canadian Dollar): " + jsonObject4);
        System.out.println("USD to CNY (Chinese Yuan): " + jsonObject5);
        System.out.println("USD to TWD (New Taiwanese Dollar): " + jsonObject6);
        System.out.println("USD to JPY (Japanese Yen): " + jsonObject7);
        System.out.println("USD to KRW (Korean Won): " + jsonObject8);
        System.out.println("USD to NZD (New Zealand Dollar): " + jsonObject9);
        System.out.println("USD to ZAR (South African Rand): " + jsonObject10);
        System.out.println("USD to GBP (British Pound): " + jsonObject11);
        System.out.println("USD to EUR (Euro): " + jsonObject12);
        System.out.println("USD to SGD (Singaporean Dollar): " + jsonObject13);
        System.out.println("USD to AUD (Australian Dollar): " + jsonObject14);
        System.out.println("USD to MXN (Mexican Peso): " + jsonObject15);
        System.out.println("USD to PHP (Filipino Peso): " + jsonObject16);
        System.out.println("USD to RUB (Russian Ruble): " + jsonObject17);
        System.out.println("USD to INR (Indian Rupee): " + jsonObject18);
        System.out.println("USD to AED (United Arab Emirates Dirham): " + jsonObject19);
    }

    private static class JSONObject {
        private static void get(String s) {

        }
    }
}