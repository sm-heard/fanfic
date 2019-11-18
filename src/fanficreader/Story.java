package fanficreader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Story {

  private static void getChapterText() throws IOException {
    String root = "https://www.fanfiction.net/";
    root += getStoryLinks().get(0);
    Document document = Jsoup.connect(root).get();

    Pattern pattern = Pattern.compile("Chapters: (\\d+)");
    Matcher matcher = pattern.matcher(document.body().text());

    int numOfChapters = 0;
    while (matcher.find()) {
      numOfChapters = Integer.parseInt(matcher.group(1));
    }
    if (numOfChapters == 0) {
      numOfChapters = 1;
    }


    ArrayList chapterText = new ArrayList<String>();

    for (int i = 1; i < numOfChapters+1; i++) {
      Document document1 = Jsoup.connect(root+String.valueOf(i)).get();
      chapterText.add(document1.getElementById("storytext").text());
    }

    System.out.println(numOfChapters);
    System.out.println("\n\n\n" + chapterText.get(chapterText.size()-1));
  }

  public static List getStoryTitles() throws IOException {
    String root ="https://www.fanfiction.net/u/2819468/Samghost";

    Document document = Jsoup.connect(root).get();
    Elements stitles =  document.getElementsByClass("stitle");

    List storyTitles = new ArrayList<String>();

    for (Element stitle:stitles) {
      storyTitles.add(stitle.text());
    }

    System.out.println(storyTitles);
    System.out.println(storyTitles.size());

    return storyTitles;
  }

  private static List getStoryLinks() throws IOException {
    String root ="https://www.fanfiction.net/u/2819468/Samghost";

    Document document = Jsoup.connect(root).get();
    Elements stitles =  document.getElementsByClass("stitle");

    List storyLinks = new ArrayList<String>();

    for (Element stitle:stitles) {
      Pattern pattern = Pattern.compile("(s/\\d+/)");
      Matcher matcher = pattern.matcher(stitle.attr("href"));
      while(matcher.find()){storyLinks.add(matcher.group().trim());}
    }

    System.out.println(storyLinks);

    return storyLinks;
  }

  public static void main(String[] args) throws Exception {


    getChapterText();
    //getStoryTitles();
    //getStoryLinks();
  }
}