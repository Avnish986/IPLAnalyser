package com.analysis;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.List;

import com.analysis.dto.*;
import com.csv.*;
import com.csv.WrongCSVException.ExceptionType;
import com.google.gson.Gson;

public class IPLAnalysis {
	List<CSVRuns> runList = null;
	List<CSVWickets> wicketList = null;

	public int loadRunsCSV(String filePath) throws WrongCSVException {
		try {
			Reader reader = Files.newBufferedReader(Paths.get(filePath));
			ICSVBuilder icsvBuilder = CSVBuilderFactory.createCSVBuilder();
			runList = icsvBuilder.getCSVList(reader, CSVRuns.class);
			return runList.size();
		} catch (IOException e) {
			throw new WrongCSVException("File not found", WrongCSVException.ExceptionType.WRONG_CSV);
		} catch (RuntimeException e) {
			throw new WrongCSVException("File data not proper", WrongCSVException.ExceptionType.WRONG_HEADER);

		} catch (WrongCSVException e) {
			throw new WrongCSVException(e.getMessage(), WrongCSVException.ExceptionType.WRONG_HEADER);
		}
	}

	public int loadWicketsCSV(String filePath) throws WrongCSVException {
		try {
			Reader reader = Files.newBufferedReader(Paths.get(filePath));
			ICSVBuilder icsvBuilder = CSVBuilderFactory.createCSVBuilder();
			wicketList = icsvBuilder.getCSVList(reader, CSVWickets.class);
			return wicketList.size();
		} catch (IOException e) {
			throw new WrongCSVException("File not found", WrongCSVException.ExceptionType.WRONG_CSV);
		} catch (RuntimeException e) {
			throw new WrongCSVException("File data not proper", WrongCSVException.ExceptionType.WRONG_HEADER);

		} catch (WrongCSVException e) {
			throw new WrongCSVException(e.getMessage(), WrongCSVException.ExceptionType.WRONG_HEADER);
		}
	}

	public String sortAccordingToBattingAverage() throws WrongCSVException {
		if (runList == null || runList.size() == 0) {
			throw new WrongCSVException("File error", WrongCSVException.ExceptionType.WRONG_HEADER);
		}
		Comparator<CSVRuns> censusComparator = Comparator.comparing(ipl -> ipl.getAvg());
		this.sort(runList, censusComparator);
		String sortedBatting = new Gson().toJson(runList);
		return sortedBatting;
	}

	private void sort(List<CSVRuns> list, Comparator<CSVRuns> censusComparator) {
		for (int i = 0; i < list.size() - 1; i++) {
			for (int j = 0; j < list.size() - 1 - i; j++) {
				CSVRuns census1 = list.get(j);
				CSVRuns census2 = list.get(j + 1);
				if (censusComparator.compare(census1, census2) > 0) {
					list.set(j, census2);
					list.set(j + 1, census1);
				}
			}
		}
	}

	public String sortAccordingToStrikeRate() throws WrongCSVException {
		if (runList == null || runList.size() == 0) {
			throw new WrongCSVException("File error", WrongCSVException.ExceptionType.WRONG_HEADER);
		}
		Comparator<CSVRuns> censusComparator = Comparator.comparing(ipl -> ipl.sr);
		this.sort(runList, censusComparator);
		String sortedBatting = new Gson().toJson(runList);
		return sortedBatting;
	}

	public String sortAccordingToMostSixesPlusFours() throws WrongCSVException {
		if (runList == null || runList.size() == 0) {
			throw new WrongCSVException("File error", WrongCSVException.ExceptionType.WRONG_HEADER);
		}
		Comparator<CSVRuns> censusComparator = Comparator.comparing(ipl -> ipl.sixes + ipl.fours);
		this.sort(runList, censusComparator);
		String sortedBatting = new Gson().toJson(runList);
		return sortedBatting;
	}
	
	public String sortAccordingToBestStrikingRateWithMostSixesPlusFours() throws WrongCSVException {
		if (runList == null || runList.size() == 0) {
			throw new WrongCSVException("File error", WrongCSVException.ExceptionType.WRONG_HEADER);
		}
		Comparator<CSVRuns> censusComparator = Comparator.comparing(ipl -> ipl.sixes + ipl.fours);
		this.sort(runList, censusComparator);
		runList.stream().sorted(Comparator.comparing(ipl -> ipl.sr));
		String sortedBatting = new Gson().toJson(runList);
		return sortedBatting;
	}
	
	public String sortAccordingToBestAverageWithBestStrikingRate() throws WrongCSVException {
		if (runList == null || runList.size() == 0) {
			throw new WrongCSVException("File error", WrongCSVException.ExceptionType.WRONG_HEADER);
		}
		Comparator<CSVRuns> censusComparator = Comparator.comparing(ipl -> ipl.sr);
		this.sort(runList, censusComparator);
		runList.stream().sorted(Comparator.comparing(ipl -> ipl.getAvg()));
		String sortedBatting = new Gson().toJson(runList);
		return sortedBatting;
	}
	
	public String sortAccordingToHitMaximumRunsWithBestAverage() throws WrongCSVException {
		if (runList == null || runList.size() == 0) {
			throw new WrongCSVException("File error", WrongCSVException.ExceptionType.WRONG_HEADER);
		}
		Comparator<CSVRuns> censusComparator = Comparator.comparing(ipl -> ipl.getAvg());
		this.sort(runList, censusComparator);
		runList.stream().sorted(Comparator.comparing(ipl -> ipl.runs));
		String sortedBatting = new Gson().toJson(runList);
		return sortedBatting;
	}

}
