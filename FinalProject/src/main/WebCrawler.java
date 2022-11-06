package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.*;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class WebCrawler 
{
	 
	// initialize MAX_DEPTH variable with final value  
	private static final int MAX_DEPTH = 2;
	
	// create set that will store links  
	private HashSet<String> urlLinks;
	public static HashSet<String> mobilePhoneLink;
	
	// initialize set using constructor  
	public WebCrawler() 
	{   
		urlLinks = new HashSet<>();   
	}  

	// create method that finds all the page link in the given URL   
	public void getPageLinks(String URL, int depth) 
	{   

		//we use the conditional statement to check whether we have already crawled the URL or not.  
		// we also check whether the depth reaches to MAX_DEPTH or not  
		if (!urlLinks.contains(URL) && (depth <MAX_DEPTH))
		{   
			if ( (urlLinks.contains("mobile")) || (urlLinks.contains("phone")) )
			{
				urlLinks.add(URL);
				
			}
			System.out.println(">> Depth: " + depth + " [" + URL + "]");   
			// use try catch block for recursive process  
			try 
			{   
				// if the URL is not present in the set, we add it to the set  
				urlLinks.add(URL);   
				// fetch the HTML code of the given URL by using the connect() and get() method and store the result in Document  
				Document doc = Jsoup.connect(URL).get();   

				// we use the select() method to parse the HTML code for extracting links of other URLs and store them into Elements  
				Elements availableLinksOnPage = doc.select("a[href]");   
				// increase depth  
				depth++;   
				// for each extracted URL, we repeat above process  
				for (Element page : availableLinksOnPage) 
				{   

					// call getPageLinks() method and pass the extracted URL to it as an argument  
					getPageLinks(page.attr("abs:href"), depth);   
				}   
			}   
			// handle exception  
			catch (IOException e) 
			{   
				// print exception messages  
				System.err.println("For '" + URL + "': " + e.getMessage());   
			}   
		}   
	}
	// main() method start  
		
	public static void main(String[] args) 	
	{   
		// create instance of the WebCrawlerExampleWithDepth class  
		WebCrawler obj = new WebCrawler();  
		      
		// pick a URL from the frontier and call the getPageLinks()method and pass 0 as starting depth  
		obj.getPageLinks("https://www.bestbuy.ca/en-ca", 0);
		Iterator<String> itr = mobilePhoneLink.iterator();
		
		while (itr.hasNext()) 
		{
            System.out.println(itr.next());
        }
		
	}  
}
	
	    
