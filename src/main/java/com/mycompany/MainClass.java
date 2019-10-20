package com.mycompany;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainClass {
    public List<Data> Scrape1(){
        try{
            String URL = "https://github.com/STIW3054-A191/Main-Issues/issues/1";
            Document doc = Jsoup.connect(URL).get();
            String title = doc.title();
            System.out.printf("%66", title + "n");
            System.out.println("--------------------------------------------------------------------------------------------");
            System.out.printf("| %-5s| %-7s| %-5sn","No.","Matric","Name");
            System.out.println("--------------------------------------------------------------------------------------------");

            List<Data> info1 = new ArrayList<Data>();

            Elements list = doc.getElementsByClass("js-timeline-item js-timeline-progressive-focus-container");
            for (int i = 1; i<list.size(); i++) {
                Elements table = list.get(i).getElementsByTag("p");
                for (int j = 0; j<table.size(); j++){

                    String Matric = null;

                    Pattern matric = Pattern.compile("(\\d(6))");
                    Matcher noMatric = matric.matcher(table.get(j).text());

                    Pattern matric1 = Pattern.compile("(\\d(5))");
                    Matcher noMatric1 = matric1.matcher(table.get(j).text());

                    if (noMatric.find()){
                        System.out.printf("| %-5s", i);
                        System.out.printf("| %-17s", noMatric.group());
                        Matric = noMatric.group();
                    }

                    else if (noMatric1.find()){
                        System.out.printf("| %-5s", i);
                        System.out.printf("| %-17s", noMatric1.group());
                        Matric = noMatric1.group();
                    }

                    else {
                        System.out.print(" ");
                    }

                    String Names = null;
                    Pattern name1 = Pattern.compile("(Name) (.*) (Matric)");
                    Matcher SName1 = name1.matcher(table.get(j).text());

                    Pattern name2 = Pattern.compile("(name) (.*) (Link)");
                    Matcher SName2 = name1.matcher(table.get(j).text());

                    Pattern name3 = Pattern.compile("(Name) (.*) (Link)");
                    Matcher SName3 = name1.matcher(table.get(j).text());

                    if (SName1.find()){
                        System.out.printf("| %-50s", SName1.group(2).replaceAll(":", " "));
                        Names = SName1.group(2).replaceAll(":", " ");
                    }
                    else if (SName2.find()){
                        System.out.printf("| %-50s", SName2.group(2).replaceAll(":", " "));
                        Names = SName2.group(2).replaceAll(":", " ");
                    }
                    else if (SName3.find()){
                        System.out.printf("| %-50s", SName3.group(2).replaceAll(":", " "));
                        Names = SName3.group(2).replaceAll(":", " ");
                    }
                    else {
                        System.out.print("  ");
                    }

                    Pattern Link = Pattern.compile("https://.*");
                    Matcher linkList = Link.matcher(table.get(j).text());

                    if (linkList.find()){
                        System.out.printf("| %-70s\n", linkList.group());

                    }
                    else  {
                        System.out.print("");
                    }

                    info1.add((new Data(Matric, Names,linkList.group())));

                }

            }
            return info1;

        }catch (Exception e) {
            return  null;
        }
    }
}
