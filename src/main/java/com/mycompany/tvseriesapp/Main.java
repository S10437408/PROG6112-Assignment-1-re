/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tvseriesapp;
import java.util.Scanner;

/**
 *
 * @author User
 */

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Series series = new Series();

        System.out.println("LATEST SERIES - 2025");
        System.out.println("*************************************");
        System.out.print("Enter (1) to launch menu or any other key to exit: ");
        String start = in.nextLine();

        while (start.equals("1")) {
            System.out.println("\nPlease select one of the following menu items:");
            System.out.println("(1) Capture a new series.");
            System.out.println("(2) Search for a series.");
            System.out.println("(3) Update series age restriction");
            System.out.println("(4) Delete a series.");
            System.out.println("(5) Print series report - 2025");
            System.out.println("(6) Exit Application.");
            String choice = in.nextLine();

            switch (choice) {
                case "1": series.CaptureSeries(); break;
                case "2": series.SearchSeriesMenu(); break;
                case "3": series.UpdateSeriesMenu(); break;
                case "4": series.DeleteSeriesMenu(); break;
                case "5": series.SeriesReport(); break;
                case "6": System.out.println("Goodbye!"); return;
                default:  System.out.println("Invalid option, try again.");
            }
            System.out.print("\nEnter (1) to launch menu or any other key to exit: ");
            start = in.nextLine();
        }
    }
}