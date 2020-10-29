package com.analysis.dto;

import com.analysis.*;
import com.csv.*;
import com.opencsv.bean.CsvBindByName;

public class CSVWickets {
	@CsvBindByName(column = "POS")
	public int pos;

	@CsvBindByName(column = "PLAYER")
	public String player;

	@CsvBindByName(column = "Mat")
	public int mat;

	@CsvBindByName(column = "Inns")
	public int inns;

	@CsvBindByName(column = "Ov")
	public double ov;

	@CsvBindByName(column = "Runs")
	public int runs;

	@CsvBindByName(column = "Wkts")
	public int wkts;

	@CsvBindByName(column = "BBI")
	public int bbi;

	@CsvBindByName(column = "Avg")
	public String avg;

	@CsvBindByName(column = "Econ")
	public double econ;

	@CsvBindByName(column = "SR")
	public String sr;

	@CsvBindByName(column = "4w")
	public int four;

	@CsvBindByName(column = "5w")
	public int five;

	public double getAvg() {
		if (avg.equals("-")) {
			return 0;
		}
		return Double.parseDouble(avg);
	}

	public double getSR() {
		if (sr.equals("-")) {
			return 0;
		}
		return Double.parseDouble(sr);
	}
}
