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

}
