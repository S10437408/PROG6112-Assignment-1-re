/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.soccertournament;
import java.util.Scanner;
/**
 *
 * @author User
 */

public class Main {
    private static final Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        Tournament tournament = new Tournament("Soccer Tournament 2026", 8);
        boolean running = true;

        System.out.println("SOCCER TOURNAMENT MANAGER");
        System.out.println("*************************");

        while (running) {
            System.out.println();
            System.out.println("Please select one of the following menu items:");
            System.out.println("(1) Add a team");
            System.out.println("(2) Add a player to a team");
            System.out.println("(3) Record a match result");
            System.out.println("(4) Search for a team");
            System.out.println("(5) Print tournament report");
            System.out.println("(6) Exit");

            switch (readInt("Choice: ", 1, 6)) {
                case 1: addTeam(tournament);       break;
                case 2: addPlayer(tournament);     break;
                case 3: recordMatch(tournament);   break;
                case 4: searchTeam(tournament);    break;
                case 5: tournament.printReport();  break;
                default: running = false;
            }
        }
        System.out.println("Goodbye!");
    }

    private static void addTeam(Tournament t) {
        System.out.print("Team name: ");
        String name = in.nextLine().trim();
        System.out.print("Coach name: ");
        String coachName = in.nextLine().trim();
        int age = readInt("Coach age: ", 18, 90);
        int years = readInt("Coach years of experience: ", 0, 60);

        if (t.addTeam(new Team(name, new Coach(coachName, age, years)))) {
            System.out.println("Team added successfully!!!");
        } else {
            System.out.println("Could not add team (duplicate name or tournament full).");
        }
    }

    private static void addPlayer(Tournament t) {
        System.out.print("Team name: ");
        int idx = t.findTeamIndex(in.nextLine().trim());
        if (idx == -1) {
            System.out.println("Team not found.");
            return;
        }
        System.out.print("Player name: ");
        String name = in.nextLine().trim();
        int age = readInt("Player age: ", 15, 50);
        System.out.print("Position: ");
        String pos = in.nextLine().trim();

        if (t.getTeam(idx).addPlayer(new Player(name, age, pos))) {
            System.out.println("Player added successfully!!!");
        } else {
            System.out.println("Squad is full.");
        }
    }

    private static void recordMatch(Tournament t) {
        System.out.print("Home team: ");
        int home = t.findTeamIndex(in.nextLine().trim());
        System.out.print("Away team: ");
        int away = t.findTeamIndex(in.nextLine().trim());
        if (home == -1 || away == -1) {
            System.out.println("One of the teams was not found.");
            return;
        }
        int hg = readInt("Home goals: ", 0, 50);
        int ag = readInt("Away goals: ", 0, 50);

        if (!t.playMatch(home, away, hg, ag)) {
            System.out.println("Invalid match (same team, or these teams already played).");
            return;
        }
        System.out.println("Result saved!!!");

        // Credit goals to players
        creditGoals(t.getTeam(home), hg);
        creditGoals(t.getTeam(away), ag);
    }

    private static void creditGoals(Team team, int goals) {
        for (int i = 0; i < goals; i++) {
            System.out.print("Scorer for " + team.getName() + " goal " + (i + 1) + " (blank to skip): ");
            String scorer = in.nextLine().trim();
            if (scorer.isEmpty()) continue;
            Player p = team.findPlayer(scorer);
            if (p == null) System.out.println("Player not found, goal not credited.");
            else           p.addGoals(1);
        }
    }

    private static void searchTeam(Tournament t) {
        System.out.print("Enter the team name to search: ");
        int idx = t.findTeamIndex(in.nextLine().trim());
        System.out.println("----------------------------------------");
        if (idx == -1) {
            System.out.println("Team not found!");
        } else {
            Team team = t.getTeam(idx);
            System.out.println("TEAM: " + team.getName());
            System.out.println("COACH: " + team.getCoach().describe());
            System.out.println("PLAYERS: " + team.getPlayerCount());
            System.out.println("POINTS: " + team.getPoints());
        }
        System.out.println("----------------------------------------");
    }

    /** Keeps asking until the user enters a whole number in range. */
    private static int readInt(String prompt, int min, int max) {
        while (true) {
            System.out.print(prompt);
            try {
                int v = Integer.parseInt(in.nextLine().trim());
                if (v >= min && v <= max) return v;
            } catch (NumberFormatException ignored) { }
            System.out.println("Invalid input. Enter a number between " + min + " and " + max + ".");
        }
    }
}
