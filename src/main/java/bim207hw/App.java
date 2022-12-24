package bim207hw;

import java.io.FileInputStream;
//import java.io.IOException;
import java.io.InputStream;

import opennlp.tools.util.Span;
import opennlp.tools.namefind.NameFinderME;
import opennlp.tools.namefind.TokenNameFinderModel;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
public class App 
{

    public static void main( String[] args ) 
    {
            try(InputStream modelIn = new FileInputStream("en-ner-person.bin")){
            TokenNameFinderModel model = new TokenNameFinderModel(modelIn);
            NameFinderME nameFinder = new NameFinderME(model);
            String str[];
            String finalName;
            final String url = "https://opennlp.apache.org/books-tutorials-and-talks.html";
            final Document document =  Jsoup.connect(url).get();
            String ticker;
            Elements row = document.select("body");
            ticker = row.select("body").text();
            str=ticker.split(" ");
            Span[] nameSpans = nameFinder.find(str);
            for (Span name : nameSpans)
            {
                StringBuilder builder = new StringBuilder();
                for (int i=name.getStart();i < name.getEnd();i++)
                {
                    builder.append(str[i]).append(" ");
                    
                }
                finalName = builder.toString();
                System.out.println(finalName); 
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }
       
    }
}

