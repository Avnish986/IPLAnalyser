package com.analysis;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.analysis.dto.CSVWickets;
import com.csv.WrongCSVException;
import com.google.gson.Gson;

public class IPLWicketCSVTest {
	public final String WicketsCSVFile = "wickets.csv";

	IPLAnalysis iplAnalyser;

	@Before
	public void setUp() throws IOException {
		iplAnalyser = new IPLAnalysis();
	}
	
	@Test
	public void givenWicketsCSVFile_ShouldSortAccordingBowlingAverage() {
		int noOfEntries = 0;
		String sortedData = null;
		try {
			noOfEntries = iplAnalyser.loadWicketsCSV(WicketsCSVFile);
			sortedData = iplAnalyser.sortAccordingToBowlingAverage();
		} catch (WrongCSVException e) {
			System.out.println(e.getMessage());
		}
		System.out.println(sortedData);
		CSVWickets[] censusCsv = new Gson().fromJson(sortedData, CSVWickets[].class);
		Assert.assertEquals("Krishnappa Gowtham", censusCsv[98].player);
	}
	
}
