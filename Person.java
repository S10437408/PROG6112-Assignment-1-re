/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.soccertournament;

/**
 *
 * @author User
 */

/** Parent class: shared details for everyone in the tournament. */
public class Person {
    // Information hiding: fields are private, accessed through getters
    private final String name;
    private final int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() { return name; }
    public int getAge()     { return age; }

    public String describe() {
        return name + " (age " + age + ")";
    }
}
