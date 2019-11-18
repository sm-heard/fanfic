package fanficreader;

import java.net.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.jsoup.Jsoup;
import org.jsoup.nodes.*;

public class Scrape {

    public static void main(String[] args) throws Exception {

      URL fanfic = new URL("https://www.fanfiction.net/u/2819468/Samghost");
      BufferedReader streambuffer = new BufferedReader(
          new InputStreamReader(fanfic.openStream()));

      StringBuilder pageText = new StringBuilder();

      String inputLine;
      while ((inputLine = streambuffer.readLine()) != null) {
        pageText.append(inputLine);
      }
      streambuffer.close();
      Document doc = Jsoup.parse(pageText.toString());
      String text = doc.body().text();
      List<Integer>  matches = new ArrayList<>();

      Pattern pattern = Pattern.compile("(Words: [0-9,]*)");
      Matcher m = pattern.matcher(text);
      while (m.find()){
        matches.add(Integer.valueOf(m.group().split(" ")[1].replace(",","")));
      }

      System.out.println(matches.stream().mapToInt(Integer::intValue).sum());

      //System.out.println(matches);
      //System.out.println(matches.size());
      //System.out.println(text);
      //System.out.println(pageText);
    }
  }
