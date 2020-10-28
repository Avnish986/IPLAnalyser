package com.csv;

import com.analysis.dto.*;

import java.io.Reader;
import java.util.List;

import com.analysis.*;

public interface ICSVBuilder<E> {
	public List<E> getCSVList(Reader reader, Class csvClass) throws WrongCSVException;
}
