/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.mycompany.tvseriesapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author User
 */
public class SeriesTest {
    private Series series;

    @BeforeEach
    void setUp() {
        series = new Series();
        series.addSeries(new SeriesModel("101", "Extreme Sports", "12", "10"));
    }

    @Test
    void TestSearchSeries() {
        SeriesModel s = series.SearchSeries("101");
        assertNotNull(s);
        assertEquals("Extreme Sports", s.SeriesName);
        assertEquals("12", s.SeriesAge);
        assertEquals("10", s.SeriesNumberOfEpisodes);
    }

    @Test
    void TestSearchSeries_SeriesNotFound() {
        assertNull(series.SearchSeries("999"));
    }

    @Test
    void TestUpdateSeries() {
        assertTrue(series.UpdateSeries("101", "Extreme Sports 2026", "10", "12"));
        SeriesModel s = series.SearchSeries("101");
        assertEquals("Extreme Sports 2026", s.SeriesName);
        assertEquals("10", s.SeriesAge);
        assertEquals("12", s.SeriesNumberOfEpisodes);
    }

    @Test
    void TestDeleteSeries() {
        assertTrue(series.DeleteSeries("101"));
        assertNull(series.SearchSeries("101"));
    }

    @Test
    void TestDeleteSeries_SeriesNotFound() {
        assertFalse(series.DeleteSeries("999"));
        assertNotNull(series.SearchSeries("101")); // still there
    }

    @Test
    void TestSeriesAgeRestriction_AgeValid() {
        assertTrue(series.SeriesAgeRestriction("12"));
        assertTrue(series.SeriesAgeRestriction("2"));
        assertTrue(series.SeriesAgeRestriction("18"));
    }

    @Test
    void TestSeriesAgeRestriction_SeriesAgeInValid() {
        assertFalse(series.SeriesAgeRestriction("32"));
        assertFalse(series.SeriesAgeRestriction("Ten"));
        assertFalse(series.SeriesAgeRestriction("1"));
    }
}