package org.example.helper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class WebFileDataSetServiceImpl implements DataSetService {

    private static final String dictionaryTextUrl = "http://codekata.com/data/wordlist.txt";

    @Override
    public List<String> getDataSet() {
        final URL dictionaryFile;
        final List<String> dictionary = new ArrayList<>();
        try {
            dictionaryFile = new URL(dictionaryTextUrl);
            final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(dictionaryFile.openStream()));
            String inputLine;

            int i = 0;
            while ((inputLine = bufferedReader.readLine()) != null) {
                dictionary.add(inputLine);
            }
            bufferedReader.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dictionary;
    }
}
