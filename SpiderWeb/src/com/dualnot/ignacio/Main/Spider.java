package com.dualnot.ignacio.Main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Spider {
	
	private int links_read = 0;
	private List<String> links_visited = new ArrayList<String>();
	private List<String> links_to_visit = new ArrayList<String>();
	
	public Spider(){
		
	}
	
	public void crawl(String url_address){
		for (String link: this.get_links(url_address)){
			System.out.println(link);
		}
		
	}
	
	public List<String> get_links(String url_address){
		BufferedReader br = null;
		InputStream is = null;
		List<String> list = new ArrayList<String>();
		try {
			URL url = new URL(url_address);
			is = url.openStream();
			br = new BufferedReader(new InputStreamReader(is));
			String line;
			while (( line = br.readLine())!=null){
				String token = "<a href=\"";
				while (line.toLowerCase().indexOf(token)!=-1){
					line = line.substring(line.indexOf(token),line.length());
					line = line.substring(line.indexOf(token) + token.length(), line.length());
					list.add ( line.substring(0, line.indexOf("\"")));	
					System.out.println(line.substring(0, line.indexOf("\"")));
				}
				
			}
		} catch (java.net.MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (java.io.IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		finally {
				try {
					if (is != null) is.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		return list;
	}
}
