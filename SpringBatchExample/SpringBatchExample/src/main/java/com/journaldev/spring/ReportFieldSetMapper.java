package com.journaldev.spring;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

import com.journaldev.spring.model.Report;

public class ReportFieldSetMapper implements FieldSetMapper<Report> {

	private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

	public Report mapFieldSet(FieldSet fieldSet) throws BindException {

		Report report = new Report();
		report.setId(fieldSet.readInt(0));
		report.setFirstName(fieldSet.readString(1));
		report.setLastName(fieldSet.readString(2));

		// default format yyyy-MM-dd
		// fieldSet.readDate(4);
		String date = fieldSet.readString(3);
		try {
			report.setDob(dateFormat.parse(date));
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return report;

	}

}