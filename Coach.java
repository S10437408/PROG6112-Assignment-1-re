/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.soccertournament;

/**
 *
 * @author User
 */

/** Inheritance: a Coach IS a Person. */
public class Coach extends Person {
    private final int yearsExperience;

    public Coach(String name, int age, int yearsExperience) {
        super(name, age);
        this.yearsExperience = yearsExperience;
    }

    public int getYearsExperience() { return yearsExperience; }

    @Override
    public String describe() {
        return super.describe() + " - " + yearsExperience + " yrs experience";
    }
}
