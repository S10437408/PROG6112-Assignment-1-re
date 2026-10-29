/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.soccertournament;

/**
 *
 * @author User
 */

public class Team {
    public static final int MAX_PLAYERS = 15;

    private final String name;
    private final Coach coach;
    private final Player[] players = new Player[MAX_PLAYERS];   // array of players
    private int playerCount = 0;

    private int played, wins, draws, losses, goalsFor, goalsAgainst;

    public Team(String name, Coach coach) {
        this.name = name;
        this.coach = coach;
    }

    public String getName()        { return name; }
    public Coach getCoach()        { return coach; }
    public int getPlayerCount()    { return playerCount; }
    public int getPlayed()         { return played; }
    public int getWins()           { return wins; }
    public int getDraws()          { return draws; }
    public int getLosses()         { return losses; }
    public int getGoalsFor()       { return goalsFor; }
    public int getGoalsAgainst()   { return goalsAgainst; }
    public int getPoints()         { return wins * 3 + draws; }
    public int getGoalDifference() { return goalsFor - goalsAgainst; }

    /** Adds a player. Returns false if the squad is full. */
    public boolean addPlayer(Player p) {
        if (p == null || playerCount >= MAX_PLAYERS) {
            return false;
        }
        players[playerCount++] = p;
        return true;
    }

    /** Loops through the squad to find a player by name (null if not found). */
    public Player findPlayer(String playerName) {
        for (int i = 0; i < playerCount; i++) {
            if (players[i].getName().equalsIgnoreCase(playerName)) {
                return players[i];
            }
        }
        return null;
    }

    /** Updates the table statistics after a match. */
    public void recordResult(int scored, int conceded) {
        played++;
        goalsFor += scored;
        goalsAgainst += conceded;
        if (scored > conceded)       wins++;
        else if (scored == conceded) draws++;
        else                         losses++;
    }

    /** Returns the player with the most goals (null if no players). */
    public Player getTopScorer() {
        Player best = null;
        for (int i = 0; i < playerCount; i++) {
            if (best == null || players[i].getGoals() > best.getGoals()) {
                best = players[i];
            }
        }
        return best;
    }
}
