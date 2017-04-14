package feedparser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import it.sauronsoftware.feed4j.FeedParser;
import it.sauronsoftware.feed4j.bean.Feed;
import it.sauronsoftware.feed4j.bean.FeedHeader;
import it.sauronsoftware.feed4j.bean.FeedItem;

public class TestFP {

  public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new FileReader(new File("data/economist.txt")));
	    int k = 0;
		String line;
		
	    String titolo = null;
	    String descrizione = null;
	   
	      FileWriter fileWritter = new FileWriter(new File("feedatafromeconomist.txt"));
          PrintWriter out = new PrintWriter(fileWritter);
	    while((line = br.readLine())!= null){
	
	    	String u = line;
	    	System.out.println("----------------------------------------------------"+u);
	    	URL url = new URL(u);
	    	try{
	    	Feed feed = FeedParser.parse(url);
	    	
		System.out.println("** HEADER **");
		FeedHeader header = feed.getHeader();
		String blog = header.getTitle();
		System.out.println("titolo blog "+blog);
		out.println("<blog>");
		out.println("<blogtitle>"+blog+"</blogtitle>");
		
		System.out.println("** ITEMS **");
		int items = feed.getItemCount();
		for (int i = 0; i < items; i++) {
			FeedItem item = feed.getItem(i);
			System.out.println("Title: " + item.getTitle());
		
			titolo =  item.getTitle();
			System.out.println("Plain text description: " + item.getDescriptionAsText());
			descrizione = item.getDescriptionAsText();
		   
			
     		out.println("<title>"+titolo+"</title>");
     		out.println("<description>"+descrizione+"</description>");
     		
     		out.println("<comments>"+item.getComments()+"</comments>");
		System.out.println("item: "+k+" inserito.");
	    k++;
		}
        }catch (Exception e){
	    		if(e.equals("it.sauronsoftware.feed4j.FeedXMLParseException")){
	    			continue;
	    		}
	    	}
		out.println("</blog>");
		
	    }
	    out.close();
	    } 
	   
	}
