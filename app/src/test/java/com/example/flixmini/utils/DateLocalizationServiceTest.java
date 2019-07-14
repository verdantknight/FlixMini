package com.example.flixmini.utils;

import com.example.flixmini.utils.DateLocalizationService;

import org.junit.Test;

import static org.junit.Assert.*;

public class DateLocalizationServiceTest {
    @Test
    public void localizeDate() {
        DateLocalizationService dateLocalizationService = new DateLocalizationService();
        assertEquals("1 июня 2019", dateLocalizationService.localizeDate("2019-06-01"));
    }
}