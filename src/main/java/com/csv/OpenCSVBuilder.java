package com.csv;
import com.analysis.dto.*;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.Reader;
import java.util.List;

import com.analysis.*;
public class OpenCSVBuilder<E> implements ICSVBuilder {
	@Override
	public List<E> getCSVList(Reader reader, Class csvClass) throws WrongCSVException  {
		return this.getCSVBean(reader, csvClass).parse();
	}

	private CsvToBean<E> getCSVBean(Reader reader, Class csvClass) throws WrongCSVException  {
		try {
			CsvToBeanBuilder<E> csvToBeanBuilder = new CsvToBeanBuilder<E>(reader);
			csvToBeanBuilder.withType(csvClass);
			csvToBeanBuilder.withIgnoreLeadingWhiteSpace(true);
			return csvToBeanBuilder.build();
		} catch (RuntimeException  e) {
			throw new WrongCSVException ("File internal data not valid", WrongCSVException.ExceptionType.WRONG_HEADER);
		}
	}

}