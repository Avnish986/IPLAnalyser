package com.analysis;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.analysis.dto.CSVRuns;
import com.csv.WrongCSVException;
import com.google.gson.Gson;

public class IPLAnalyserTest {
	public final String RunsCSVFile = "runs.csv";
	public final String WicketsCSVFile = "wickets.csv";

	IPLAnalysis iplAnalyser;

	@Before
	public void setUp() throws IOException {
		iplAnalyser = new IPLAnalysis();
	}

	@Test
	public void givenRunsCSVFile_ShouldSort_AccordingBattingAverage() {
		int noOfEntries = 0;
		String data = null;
		try {
			noOfEntries = iplAnalyser.loadRunsCSV(RunsCSVFile);
			data = iplAnalyser.sortAccordingToBattingAverage();
		} catch (WrongCSVException e) {
			System.out.println(e.getMessage());
		}
		System.out.println(data);
		CSVRuns[] censusCsv = new Gson().fromJson(data, CSVRuns[].class);
		Assert.assertEquals("MS Dhoni", censusCsv[100].player);
	}

	@Test
	public void givenRunsCSVFile_ShouldSortAccordingToStrikeRate() {
		int noOfEntries = 0;
		String sortedData = null;
		try {
			noOfEntries = iplAnalyser.loadRunsCSV(RunsCSVFile);
			sortedData = iplAnalyser.sortAccordingToStrikeRate();
		} catch (WrongCSVException e) {
			System.out.println(e.getMessage());
		}
		System.out.println(sortedData);
		CSVRuns[] censusCsv = new Gson().fromJson(sortedData, CSVRuns[].class);
		Assert.assertEquals("Ishant Sharma", censusCsv[100].player);
	}
}
