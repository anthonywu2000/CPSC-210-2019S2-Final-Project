package ui;
//shamelessly quoting from: http://zetcode.com/articles/javareadwebpage/

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class ReadWebPageEx {

    private static BufferedReader br;

    public static void scan() throws IOException {
        String apikey = "1a419f15d8f390b05f77b68703f093cc"; //fill this in with the API key they email you
        String currencyexchangeratesquery = "http://www.apilayer.net/api/live?access_key=";
        String theURL = currencyexchangeratesquery + apikey;
        URL url = new URL(theURL);
        br = new BufferedReader(new InputStreamReader(url.openStream()));
    }

    public static void main(String[] args) throws MalformedURLException, IOException {

        //BufferedReader br = null;

        try {
            scan();
            String line;
            StringBuilder sb = new StringBuilder();
            while ((line = br.readLine()) != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
            }
            FileWriter fileWriter = new FileWriter("JSONfile.json");
            fileWriter.write(String.valueOf(sb));
            fileWriter.flush();
        } finally {
            if (br != null) {
                br.close();
            }
        }
    }
}