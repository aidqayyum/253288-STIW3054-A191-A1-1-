package com.mycompany;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

public class wiki {
    public List<Data> Scrape(){
        try{
            System.out.println("");
            String URL = "https://github.com/STIW3054-A191/Assignments/wiki/List_of_Student";
            Document doc = Jsoup.connect(URL).get();
            String title = doc.title();
            System.out.printf("%66", title + "n");
            System.out.println("--------------------------------------------------------------------------------------------");
            System.out.printf("| %-5s| %-7s| %-5sn","No.","Matric","Name");
            System.out.println("--------------------------------------------------------------------------------------------");

            List<Data> info = new ArrayList<Data>();

            for (int i = 1; i <= 35; i++){
                Elements No = doc.select("wiki-body > div > table > tbody >tr:nth-child("+i+") > td:nth-child(1)");
                Elements Matric = doc.select("wiki-body > div > table > tbody >tr:nth-child("+i+") > td:nth-child(2)");
                Elements Name = doc.select("wiki-body > div > table > tbody >tr:nth-child("+i+") > td:nth-child(3)");

                System.out.printf("| %-5s| %-7s| %-5sn",No.text(), Matric.text(),Name.text());
                info.add(new Data(No.text(), Matric.text(), Name.text()));
            }
            return info;


        } catch (Exception e){
            return null;
        }
    }
}
