/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tvseriesapp;
import java.util.ArrayList;
import java.util.Scanner;


/**
 *
 * @author User
 */

public class Series {
    private final ArrayList<SeriesModel> seriesList = new ArrayList<>();
    private final Scanner scanner = new Scanner(System.in);

    // ---------- Validation ----------
    // Valid age = a whole number between 2 and 18
    public boolean SeriesAgeRestriction(String age) {
        try {
            int a = Integer.parseInt(age.trim());
            return a >= 2 && a <= 18;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private String readValidAge(String prompt) {
        System.out.print(prompt);
        String age = scanner.nextLine();
        while (!SeriesAgeRestriction(age)) {
            System.out.println("You have entered a incorrect series age!!!");
            System.out.print("Please re-enter the series age >> ");
            age = scanner.nextLine();
        }
        return age.trim();
    }

    // ---------- 1.2 Capture ----------
    public void CaptureSeries() {
        System.out.println("CAPTURE A NEW SERIES");
        System.out.println("************************");
        System.out.print("Enter the series id: ");
        String id = scanner.nextLine();
        System.out.print("Enter the series name: ");
        String name = scanner.nextLine();
        String age = readValidAge("Enter the series age restriction: ");
        System.out.print("Enter the number of episodes for " + name + ": ");
        String eps = scanner.nextLine();

        seriesList.add(new SeriesModel(id, name, age, eps));
        System.out.println("Series processed successfully!!!");
    }

    // Used by unit tests (no console input needed)
    public void addSeries(SeriesModel s) {
        seriesList.add(s);
    }

    // ---------- Search ----------
    public SeriesModel SearchSeries(String id) {
        for (SeriesModel s : seriesList) {
            if (s.SeriesId.equals(id)) return s;
        }
        return null;
    }

    public void SearchSeriesMenu() {
        System.out.print("Enter the series id to search: ");
        String id = scanner.nextLine();
        SeriesModel s = SearchSeries(id);
        System.out.println("----------------------------------------");
        if (s == null) {
            System.out.println("Series with Series Id: " + id + " was not found!");
        } else {
            printSeries(s);
        }
        System.out.println("----------------------------------------");
    }

    // ---------- Update ----------
    public boolean UpdateSeries(String id, String name, String age, String episodes) {
        SeriesModel s = SearchSeries(id);
        if (s == null || !SeriesAgeRestriction(age)) return false;
        s.SeriesName = name;
        s.SeriesAge = age;
        s.SeriesNumberOfEpisodes = episodes;
        return true;
    }

    public void UpdateSeriesMenu() {
        System.out.print("Enter the series id to update: ");
        String id = scanner.nextLine();
        if (SearchSeries(id) == null) {
            System.out.println("Series with Series Id: " + id + " was not found!");
            return;
        }
        System.out.print("Enter the series name: ");
        String name = scanner.nextLine();
        String age = readValidAge("Enter the age restriction: ");
        System.out.print("Enter the number of episodes: ");
        String eps = scanner.nextLine();
        UpdateSeries(id, name, age, eps);
        System.out.println("Series updated successfully!!!");
    }

    // ---------- Delete ----------
    public boolean DeleteSeries(String id) {
        SeriesModel s = SearchSeries(id);
        if (s == null) return false;
        return seriesList.remove(s);
    }

    public void DeleteSeriesMenu() {
        System.out.print("Enter the series id to delete: ");
        String id = scanner.nextLine();
        if (SearchSeries(id) == null) {
            System.out.println("Series with Series Id: " + id + " was not found!");
            return;
        }
        System.out.print("Are you sure you want to delete series " + id + " from the system? Yes (y) to delete: ");
        if (scanner.nextLine().equalsIgnoreCase("y")) {
            DeleteSeries(id);
            System.out.println("Series with Series Id: " + id + " WAS deleted!");
        }
    }

    // ---------- Report ----------
    public void SeriesReport() {
        System.out.println("SERIES REPORT - 2025");
        System.out.println("************************");
        if (seriesList.isEmpty()) {
            System.out.println("No series captured yet.");
            return;
        }
        int n = 1;
        for (SeriesModel s : seriesList) {
            System.out.println("Series " + n++);
            System.out.println("----------------------------------------");
            printSeries(s);
            System.out.println("----------------------------------------");
        }
    }

    private void printSeries(SeriesModel s) {
        System.out.println("SERIES ID: " + s.SeriesId);
        System.out.println("SERIES NAME: " + s.SeriesName);
        System.out.println("SERIES AGE RESTRICTION: " + s.SeriesAge);
        System.out.println("SERIES NUMBER OF EPISODES: " + s.SeriesNumberOfEpisodes);
    }
}