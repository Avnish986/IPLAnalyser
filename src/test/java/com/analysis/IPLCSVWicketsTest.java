package com.analysis;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.analysis.dto.CSVRuns;
import com.analysis.dto.CSVWickets;
import com.csv.WrongCSVException;
import com.google.gson.Gson;

public class IPLCSVWicketsTest {
	public final String WicketsCSVFile = "wickets.csv";

	IPLAnalysis iplAnalyser;

	@Before
	public void setUp() throws IOException {
		iplAnalyser = new IPLAnalysis();
	}

	@Test
	public void givenWicketsCSVFile_ShouldSort_AccordingTopBowlingAverage() {
		int noOfEntries = 0;
		String data = null;
		try {
			noOfEntries = iplAnalyser.loadWicketsCSV(WicketsCSVFile);
			data = iplAnalyser.sortAccordingToBowlingAverage();
		} catch (WrongCSVException e) {
			System.out.println(e.getMessage());
		}
		System.out.println(data);
		CSVWickets[] censusCsv = new Gson().fromJson(data, CSVWickets[].class);
		Assert.assertEquals("Krishnappa Gowtham", censusCsv[98].player);
	}

	@Test
	public void givenWicketsCSVFile_ShouldSort_AccordingTopStrikingRate() {
		int noOfEntries = 0;
		String data = null;
		try {
			noOfEntries = iplAnalyser.loadWicketsCSV(WicketsCSVFile);
			data = iplAnalyser.sortAccordingToTopStrikingRate();
		} catch (WrongCSVException e) {
			System.out.println(e.getMessage());
		}
		System.out.println(data);
		CSVWickets[] censusCsv = new Gson().fromJson(data, CSVWickets[].class);
		Assert.assertEquals("Krishnappa Gowtham", censusCsv[98].player);
	}

	@Test
	public void givenWicketsCSVFile_ShouldSort_AccordingTopEconomyRate() {
		int noOfEntries = 0;
		String data = null;
		try {
			noOfEntries = iplAnalyser.loadWicketsCSV(WicketsCSVFile);
			data = iplAnalyser.sortAccordingToTopEconomyRate();
		} catch (WrongCSVException e) {
			System.out.println(e.getMessage());
		}
		System.out.println(data);
		CSVWickets[] censusCsv = new Gson().fromJson(data, CSVWickets[].class);
		Assert.assertEquals("Shivam Dube", censusCsv[0].player);
	}
	
	@Test
	public void givenWicketsCSVFile_ShouldSort_AccordingBestStrikingRateWith5wAnd4w() {
		int noOfEntries = 0;
		String data = null;
		try {
			noOfEntries = iplAnalyser.loadWicketsCSV(WicketsCSVFile);
			data = iplAnalyser.sortAccordingToBestStrikingRateWith5wAnd4w();
		} catch (WrongCSVException e) {
			System.out.println(e.getMessage());
		}
		System.out.println(data);
		CSVWickets[] censusCsv = new Gson().fromJson(data, CSVWickets[].class);
		Assert.assertEquals("Lasith Malinga", censusCsv[98].player);
	}
	
	@Test
	public void givenWicketsCSVFile_ShouldSort_AccordingGreatBowlingAverageWithBestStrikingRate() {
		int noOfEntries = 0;
		String data = null;
		try {
			noOfEntries = iplAnalyser.loadWicketsCSV(WicketsCSVFile);
			data = iplAnalyser.sortAccordingToGreatBowlingAveragesWithBestStrikingRate();
		} catch (WrongCSVException e) {
			System.out.println(e.getMessage());
		}
		System.out.println(data);
		CSVWickets[] censusCsv = new Gson().fromJson(data, CSVWickets[].class);
		Assert.assertEquals("Krishnappa Gowtham", censusCsv[98].player);
	}
	
	@Test
	public void givenWicketsCSVFile_ShouldSort_AccordingPlayerTakingMaximumWicketsWithGreatBowlingAverage() {
		int noOfEntries = 0;
		String data = null;
		try {
			noOfEntries = iplAnalyser.loadWicketsCSV(WicketsCSVFile);
			data = iplAnalyser.sortAccordingPlayerWithMaximumWicketsWithGreatBowlingAverages();
		} catch (WrongCSVException e) {
			System.out.println(e.getMessage());
		}
		System.out.println(data);
		CSVWickets[] censusCsv = new Gson().fromJson(data, CSVWickets[].class);
		Assert.assertEquals("Imran Tahir", censusCsv[98].player);
	}

}
