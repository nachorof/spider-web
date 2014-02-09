package com.dualnot.ignacio.Main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Spider {
	
	private List<String> links_visited = new ArrayList<String>();
	private List<String> links_to_visit = new ArrayList<String>();
	
	public Spider(){
		
	}
	
	public void crawl(String url_address){
		this.links_visited.add(url_address);
		this.links_to_visit.addAll( this.get_links(url_address));
		for (String link: this.links_to_visit){
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
					line = line.substring(line.toLowerCase().indexOf(token) + token.length(), line.length());
					String link = line.substring(0, line.indexOf("\"")); 
					list.add ( link );	
				}
			}
			is.close();
		} catch (java.net.MalformedURLException e) {
			e.printStackTrace();
		} catch (java.io.IOException e) {
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
