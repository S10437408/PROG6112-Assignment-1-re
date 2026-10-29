/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.soccertournament;

/**
 *
 * @author User
 */

/** Inheritance: a Player IS a Person. */
public class Player extends Person {
    private final String position;
    private int goals;

    public Player(String name, int age, String position) {
        super(name, age);           // call the parent constructor
        this.position = position;
        this.goals = 0;
    }

    public String getPosition() { return position; }
    public int getGoals()       { return goals; }

    public void addGoals(int amount) {
        if (amount > 0) {
            goals += amount;
        }
    }

    @Override
    public String describe() {
        return super.describe() + " - " + position + " - " + goals + " goal(s)";
    }
}