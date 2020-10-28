package com.csv;

import com.analysis.dto.*;
import com.analysis.*;

public class CSVBuilderFactory {
	public static ICSVBuilder createCSVBuilder() {
		return new OpenCSVBuilder();
	}
}
