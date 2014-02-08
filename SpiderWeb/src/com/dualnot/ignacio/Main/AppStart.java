package com.dualnot.ignacio.Main;

import java.io.*;

public class AppStart {
	
	public static void main(String[] args){
		Spider s = new Spider();
		s.crawl("http://www.thepiratebay.se");
		
	}

}
