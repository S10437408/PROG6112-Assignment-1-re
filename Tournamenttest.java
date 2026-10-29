/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.soccertournament;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
/**
 *
 * @author User
 */

public class TournamentTest {
    private Tournament tournament;

    @BeforeEach
    void setUp() {
        tournament = new Tournament("Test Cup", 4);
        tournament.addTeam(new Team("Lions", new Coach("Sam", 45, 10)));
        tournament.addTeam(new Team("Tigers", new Coach("Lee", 50, 15)));
        tournament.addTeam(new Team("Bears", new Coach("Ayo", 38, 5)));
    }

    @Test
    void testAddTeam_DuplicateRejected() {
        assertFalse(tournament.addTeam(new Team("lions", new Coach("X", 40, 1))));
        assertEquals(3, tournament.getTeamCount());
    }

    @Test
    void testFindTeamIndex() {
        assertEquals(1, tournament.findTeamIndex("tigers"));
        assertEquals(-1, tournament.findTeamIndex("Wolves"));
    }

    @Test
    void testPlayMatch_UpdatesStats() {
        assertTrue(tournament.playMatch(0, 1, 3, 1));
        Team lions = tournament.getTeam(0);
        Team tigers = tournament.getTeam(1);
        assertEquals(3, lions.getPoints());
        assertEquals(0, tigers.getPoints());
        assertEquals(2, lions.getGoalDifference());
        assertEquals(1, lions.getWins());
        assertEquals(1, tigers.getLosses());
    }

    @Test
    void testPlayMatch_Draw() {
        tournament.playMatch(0, 2, 2, 2);
        assertEquals(1, tournament.getTeam(0).getPoints());
        assertEquals(1, tournament.getTeam(2).getPoints());
    }

    @Test
    void testPlayMatch_InvalidAndDuplicate() {
        assertFalse(tournament.playMatch(0, 0, 1, 1));   // same team
        assertFalse(tournament.playMatch(0, 1, -1, 2));  // negative goals
        assertTrue(tournament.playMatch(0, 1, 1, 0));
        assertFalse(tournament.playMatch(0, 1, 2, 2));   // already played
        assertFalse(tournament.playMatch(1, 0, 2, 2));   // already played (reverse)
    }

    @Test
    void testStandings_Sorted() {
        tournament.playMatch(0, 1, 1, 0);   // Lions 3 pts
        tournament.playMatch(2, 1, 4, 0);   // Bears 3 pts, better GD
        Team[] table = tournament.getStandings();
        assertEquals("Bears", table[0].getName());
        assertEquals("Lions", table[1].getName());
        assertEquals("Tigers", table[2].getName());
    }

    @Test
    void testAddPlayer_SquadLimit() {
        Team t = tournament.getTeam(0);
        for (int i = 0; i < Team.MAX_PLAYERS; i++) {
            assertTrue(t.addPlayer(new Player("P" + i, 20, "MID")));
        }
        assertFalse(t.addPlayer(new Player("Extra", 20, "MID")));
    }

    @Test
    void testTopScorer() {
        Team t = tournament.getTeam(0);
        assertNull(t.getTopScorer());
        Player a = new Player("Ann", 22, "FWD");
        Player b = new Player("Bo", 24, "MID");
        t.addPlayer(a);
        t.addPlayer(b);
        b.addGoals(3);
        a.addGoals(1);
        assertEquals("Bo", t.getTopScorer().getName());
    }

    @Test
    void testInheritance_PlayerIsPerson() {
        Person p = new Player("Ann", 22, "FWD");
        assertEquals("Ann", p.getName());
        assertTrue(p.describe().contains("FWD"));
    }
}
