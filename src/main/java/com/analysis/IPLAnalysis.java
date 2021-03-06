package com.analysis;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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
		runList.stream()
		.sorted(Comparator.comparing(ipl -> ipl.sr));
		String sortedBatting = new Gson().toJson(runList);
		return sortedBatting;
	}

	public String sortAccordingToBestAverageWithBestStrikingRate() throws WrongCSVException {
		if (runList == null || runList.size() == 0) {
			throw new WrongCSVException("File error", WrongCSVException.ExceptionType.WRONG_HEADER);
		}
		Comparator<CSVRuns> censusComparator = Comparator.comparing(ipl -> ipl.sr);
		this.sort(runList, censusComparator);
		runList.stream()
		.sorted(Comparator.comparing(ipl -> ipl.getAvg()));
		String sortedBatting = new Gson().toJson(runList);
		return sortedBatting;
	}

	public String sortAccordingToHitMaximumRunsWithBestAverage() throws WrongCSVException {
		if (runList == null || runList.size() == 0) {
			throw new WrongCSVException("File error", WrongCSVException.ExceptionType.WRONG_HEADER);
		}
		Comparator<CSVRuns> censusComparator = Comparator.comparing(ipl -> ipl.getAvg());
		this.sort(runList, censusComparator);
		runList.stream()
		.sorted(Comparator.comparing(ipl -> ipl.runs));
		String sortedBatting = new Gson().toJson(runList);
		return sortedBatting;
	}

	public String sortAccordingToBowlingAverage() throws WrongCSVException {
		if (wicketList == null || wicketList.size() == 0) {
			throw new WrongCSVException("File error", WrongCSVException.ExceptionType.WRONG_HEADER);
		}
		Comparator<CSVWickets> censusComparator = Comparator.comparing(ipl -> ipl.getAvg());
		this.sort(wicketList, censusComparator);
		String sortedBatting = new Gson().toJson(wicketList);
		return sortedBatting;
	}

	public String sortAccordingToTopStrikingRate() throws WrongCSVException {
		if (wicketList == null || wicketList.size() == 0) {
			throw new WrongCSVException("File error", WrongCSVException.ExceptionType.WRONG_HEADER);
		}
		Comparator<CSVWickets> censusComparator = Comparator.comparing(ipl -> ipl.getSR());
		this.sort(wicketList, censusComparator);
		String sortedBatting = new Gson().toJson(wicketList);
		return sortedBatting;
	}

	public String sortAccordingToTopEconomyRate() throws WrongCSVException {
		if (wicketList == null || wicketList.size() == 0) {
			throw new WrongCSVException("File error", WrongCSVException.ExceptionType.WRONG_HEADER);
		}
		Comparator<CSVWickets> censusComparator = Comparator.comparing(ipl -> ipl.econ);
		this.sort(wicketList, censusComparator);
		String sortedBatting = new Gson().toJson(wicketList);
		return sortedBatting;
	}

	public String sortAccordingToBestStrikingRateWith5wAnd4w() throws WrongCSVException {
		if (wicketList == null || wicketList.size() == 0) {
			throw new WrongCSVException("File error", WrongCSVException.ExceptionType.WRONG_HEADER);
		}
		Comparator<CSVWickets> censusComparator = Comparator.comparing(ipl -> ipl.four + ipl.five);
		this.sort(wicketList, censusComparator);
		wicketList.stream()
		.sorted(Comparator.comparing(ipl -> ipl.getSR()));
		String sortedBatting = new Gson().toJson(wicketList);
		return sortedBatting;
	}

	public String sortAccordingToGreatBowlingAveragesWithBestStrikingRate() throws WrongCSVException {
		if (wicketList == null || wicketList.size() == 0) {
			throw new WrongCSVException("File error", WrongCSVException.ExceptionType.WRONG_HEADER);
		}
		Comparator<CSVWickets> censusComparator = Comparator.comparing(ipl -> ipl.getSR());
		this.sort(wicketList, censusComparator);
		wicketList.stream()
		.sorted(Comparator.comparing(ipl -> ipl.getAvg()));
		String sortedBatting = new Gson().toJson(wicketList);
		return sortedBatting;
	}

	public String sortAccordingPlayerWithMaximumWicketsWithGreatBowlingAverages() throws WrongCSVException {
		if (wicketList == null || wicketList.size() == 0) {
			throw new WrongCSVException("File error", WrongCSVException.ExceptionType.WRONG_HEADER);
		}
		Comparator<CSVWickets> censusComparator = Comparator.comparing(ipl -> ipl.wkts);
		this.sort(wicketList, censusComparator);
		wicketList.stream()
		.sorted(Comparator.comparing(ipl -> ipl.getAvg()));
		String sortedBatting = new Gson().toJson(wicketList);
		return sortedBatting;
	}

	public List<String> getBestBowlerAndBattingAverage() {

		List<String> bestList = new ArrayList<>();

		List<CSVRuns> battingAvg = runList.stream()
								   .sorted((playerA, playerB) -> 
								   Double.compare(playerA.getAvg(), playerB.getAvg()))
				                   .collect(Collectors.toList());

		List<CSVWickets> bowlingAvg = wicketList.stream()
				                      .sorted((playerA, playerB) -> 
				                      Double.compare(playerA.getAvg(), playerB.getAvg()))
				                      .collect(Collectors.toList());

		for (CSVRuns playerBat : battingAvg) {
			for (CSVWickets playerBowler : bowlingAvg) {
				if (playerBat.player.equals(playerBowler.player)) {
					bestList.add(playerBat.player);
				}
			}
		}
		return bestList;
	}

	public List<String> getAllRounderWithMostRunsAndWickets() {

		List<String> bestList = new ArrayList<>();

		List<CSVRuns> battingAvg = runList.stream()
				                   .sorted((playerA, playerB) -> 
				                   Double.compare(playerA.runs, playerB.runs))
				                   .collect(Collectors.toList());

		List<CSVWickets> bowlingAvg = wicketList.stream()
				                      .sorted((playerA, playerB) -> 
				                      Double.compare(playerA.wkts, playerB.wkts)).
				                      collect(Collectors.toList());

		for (CSVRuns playerBat : battingAvg) {
			for (CSVWickets playerBowler : bowlingAvg) {
				if (playerBat.player.equals(playerBowler.player)) {
					bestList.add(playerBat.player);
				}
			}
		}
		return bestList;
	}

	public String sortAccordingPlayerWithMaximum100WithGreatBattingAverages() throws WrongCSVException {
		if (runList == null || runList.size() == 0) {
			throw new WrongCSVException("File error", WrongCSVException.ExceptionType.WRONG_HEADER);
		}
		Comparator<CSVRuns> censusComparator = Comparator.comparing(ipl -> ipl.runs);
		this.sort(runList, censusComparator);
		runList.stream()
		.sorted(Comparator.comparing(ipl -> ipl.hundreds));
		String sortedBatting = new Gson().toJson(runList);
		return sortedBatting;
	}

	public List<CSVRuns> getPlayerWithZero100sOrZero50sButBestBattingAverage() throws WrongCSVException {
		if (runList == null || runList.size() == 0) {
			throw new WrongCSVException("File error", WrongCSVException.ExceptionType.WRONG_HEADER);
		}
		List<CSVRuns> list = runList.stream().
							 filter(player -> player.hundreds == 0 && player.fiftys == 0)
				             .sorted((playerA, playerB) -> Double.compare(playerA.getAvg(), playerB.getAvg()))
				             .collect(Collectors.toList());
		return list;
	}
	
	private <E> void sort(List<E> list, Comparator<E> censusComparator) {
		for (int i = 0; i < list.size() - 1; i++) {
			for (int j = 0; j < list.size() - 1 - i; j++) {
				E census1 = list.get(j);
				E census2 = list.get(j + 1);
				if (censusComparator.compare(census1, census2) > 0) {
					list.set(j, census2);
					list.set(j + 1, census1);
				}
			}
		}
	}


}
