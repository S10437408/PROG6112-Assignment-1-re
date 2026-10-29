/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.soccertournament;

/**
 *
 * @author User
 */

public class Tournament {
    private final String name;
    private final Team[] teams;          // 1D array of teams
    private final int[][] scores;        // 2D array: scores[home][away] = goals scored by home, -1 = not played
    private int teamCount = 0;

    public Tournament(String name, int maxTeams) {
        this.name = name;
        this.teams = new Team[maxTeams];
        this.scores = new int[maxTeams][maxTeams];
        for (int i = 0; i < maxTeams; i++) {
            for (int j = 0; j < maxTeams; j++) {
                scores[i][j] = -1;
            }
        }
    }

    public String getName()     { return name; }
    public int getTeamCount()   { return teamCount; }
    public Team getTeam(int i)  { return (i >= 0 && i < teamCount) ? teams[i] : null; }

    /** Adds a team. False if the tournament is full or the name is already used. */
    public boolean addTeam(Team t) {
        if (t == null || teamCount >= teams.length || findTeamIndex(t.getName()) != -1) {
            return false;
        }
        teams[teamCount++] = t;
        return true;
    }

    /** Returns the index of the team, or -1 if not found. */
    public int findTeamIndex(String teamName) {
        for (int i = 0; i < teamCount; i++) {
            if (teams[i].getName().equalsIgnoreCase(teamName)) {
                return i;
            }
        }
        return -1;
    }

    /** Records a match. False if invalid, same team, negative goals or already played. */
    public boolean playMatch(int home, int away, int homeGoals, int awayGoals) {
        if (home < 0 || away < 0 || home >= teamCount || away >= teamCount
                || home == away || homeGoals < 0 || awayGoals < 0
                || scores[home][away] != -1) {
            return false;
        }
        scores[home][away] = homeGoals;
        scores[away][home] = awayGoals;   // mirrored, so the pairing can't be played twice by mistake
        teams[home].recordResult(homeGoals, awayGoals);
        teams[away].recordResult(awayGoals, homeGoals);
        return true;
    }

    /** Returns a sorted copy of the teams: points, then goal difference, then goals scored. */
    public Team[] getStandings() {
        Team[] table = new Team[teamCount];
        for (int i = 0; i < teamCount; i++) {
            table[i] = teams[i];
        }
        // Bubble sort
        for (int i = 0; i < table.length - 1; i++) {
            for (int j = 0; j < table.length - 1 - i; j++) {
                if (isBetter(table[j + 1], table[j])) {
                    Team temp = table[j];
                    table[j] = table[j + 1];
                    table[j + 1] = temp;
                }
            }
        }
        return table;
    }

    private boolean isBetter(Team a, Team b) {
        if (a.getPoints() != b.getPoints())               return a.getPoints() > b.getPoints();
        if (a.getGoalDifference() != b.getGoalDifference()) return a.getGoalDifference() > b.getGoalDifference();
        return a.getGoalsFor() > b.getGoalsFor();
    }

    public void printReport() {
        System.out.println();
        System.out.println(name.toUpperCase() + " - TOURNAMENT REPORT");
        System.out.println("==============================================================");
        System.out.printf("%-3s %-16s %2s %2s %2s %2s %3s %3s %4s %4s%n",
                "#", "TEAM", "P", "W", "D", "L", "GF", "GA", "GD", "PTS");
        System.out.println("--------------------------------------------------------------");

        Team[] table = getStandings();
        for (int i = 0; i < table.length; i++) {
            Team t = table[i];
            System.out.printf("%-3d %-16s %2d %2d %2d %2d %3d %3d %4d %4d%n",
                    i + 1, t.getName(), t.getPlayed(), t.getWins(), t.getDraws(),
                    t.getLosses(), t.getGoalsFor(), t.getGoalsAgainst(),
                    t.getGoalDifference(), t.getPoints());
        }
        System.out.println("--------------------------------------------------------------");

        if (table.length > 0 && table[0].getPlayed() > 0) {
            System.out.println("Leader: " + table[0].getName() + " (" + table[0].getPoints() + " pts)");
        }

        System.out.println("Top scorers per team:");
        for (int i = 0; i < table.length; i++) {
            Player p = table[i].getTopScorer();
            String text = (p == null) ? "no players" : p.getName() + " (" + p.getGoals() + ")";
            System.out.println("  " + table[i].getName() + ": " + text);
        }
    }
}