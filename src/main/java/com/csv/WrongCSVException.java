package com.csv;

import com.analysis.dto.*;
import com.analysis.*;



	public class WrongCSVException extends Exception {

		public enum ExceptionType {
			WRONG_CSV, WRONG_TYPE, WRONG_HEADER
		}

		ExceptionType type;

		public WrongCSVException(String message, ExceptionType type) {
			super(message);
			this.type = type;
		}

	}