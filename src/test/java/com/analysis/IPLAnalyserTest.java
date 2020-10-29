package com.analysis;

import java.io.IOException;
import java.util.List;

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
		String data = null;
		try {
			noOfEntries = iplAnalyser.loadRunsCSV(RunsCSVFile);
			data = iplAnalyser.sortAccordingToStrikeRate();
		} catch (WrongCSVException e) {
			System.out.println(e.getMessage());
		}
		System.out.println(data);
		CSVRuns[] censusCsv = new Gson().fromJson(data, CSVRuns[].class);
		Assert.assertEquals("Ishant Sharma", censusCsv[100].player);
	}

	@Test
	public void givenMostRunsCSVFile_ShouldLoadAndSortAccordingToMostSixesAndFours() {
		int noOfEntries = 0;
		String data = null;
		try {
			noOfEntries = iplAnalyser.loadRunsCSV(RunsCSVFile);
			data = iplAnalyser.sortAccordingToMostSixesPlusFours();
		} catch (WrongCSVException e) {
			System.out.println(e.getMessage());
		}
		System.out.println(data);
		CSVRuns[] censusCsv = new Gson().fromJson(data, CSVRuns[].class);
		Assert.assertEquals("Andre Russell", censusCsv[100].player);
	}

	@Test
	public void givenMostRunsCSVFile_ShouldSortAccordingToMostStrikeRateWithMostSixesAndMostFours() {
		int noOfEntries = 0;
		String data = null;
		try {
			noOfEntries = iplAnalyser.loadRunsCSV(RunsCSVFile);
			data = iplAnalyser.sortAccordingToBestStrikingRateWithMostSixesPlusFours();
		} catch (WrongCSVException e) {
			System.out.println(e.getMessage());
		}
		System.out.println(data);
		CSVRuns[] censusCsv = new Gson().fromJson(data, CSVRuns[].class);
		Assert.assertEquals("Andre Russell", censusCsv[100].player);
	}

	@Test
	public void givenMostRunsCSVFile_ShouldSortAccordingToBestAverageWithBestStrikeRate() {
		int noOfEntries = 0;
		String data = null;
		try {
			noOfEntries = iplAnalyser.loadRunsCSV(RunsCSVFile);
			data = iplAnalyser.sortAccordingToBestAverageWithBestStrikingRate();
		} catch (WrongCSVException e) {
			System.out.println(e.getMessage());
		}
		System.out.println(data);
		CSVRuns[] censusCsv = new Gson().fromJson(data, CSVRuns[].class);
		Assert.assertEquals("Ishant Sharma", censusCsv[100].player);
	}

	@Test
	public void givenMostRunsCSVFile_ShouldSortAccordingToBestRunsWithBestAverage() {
		int noOfEntries = 0;
		String data = null;
		try {
			noOfEntries = iplAnalyser.loadRunsCSV(RunsCSVFile);
			data = iplAnalyser.sortAccordingToHitMaximumRunsWithBestAverage();
		} catch (WrongCSVException e) {
			System.out.println(e.getMessage());
		}
		System.out.println(data);
		CSVRuns[] censusCsv = new Gson().fromJson(data, CSVRuns[].class);
		Assert.assertEquals("MS Dhoni", censusCsv[100].player);
	}

	@Test
	public void givenMostRunsCSVFile_ShouldSortAccordingToMAximum100WithBestAverage() {
		int noOfEntries = 0;
		String data = null;
		try {
			noOfEntries = iplAnalyser.loadRunsCSV(RunsCSVFile);
			data = iplAnalyser.sortAccordingPlayerWithMaximum100WithGreatBattingAverages();
		} catch (WrongCSVException e) {
			System.out.println(e.getMessage());
		}
		System.out.println(data);
		CSVRuns[] censusCsv = new Gson().fromJson(data, CSVRuns[].class);
		Assert.assertEquals("David Warner ", censusCsv[100].player);
	}

	@Test
	public void givenMostRunsCSVFile_ShouldReturn_BestBattingAverageButWithZero100sAndZero50s() {
		int noOfEntries = 0;
		List<CSVRuns> data = null;
		try {
			noOfEntries = iplAnalyser.loadRunsCSV(RunsCSVFile);
			data = iplAnalyser.getPlayerWithZero100sOrZero50sButBestBattingAverage();
		} catch (WrongCSVException e) {
			System.out.println(e.getMessage());
		}
		Assert.assertEquals("Marcus Stoinis", data.get(55).player);
	}
}
