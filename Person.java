/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rc;

/**
 *
 * @author RC_Student_lab
 */
// Base class for Person
public class Person {
    private String name;
    private int id;
    
    public Person(int id, String name) {
        this.id = id;
        this.name = name;
    }
    
    // Getters with information hiding
    public String getName() {
        return name;
    }
    
    public int getId() {
        return id;
    }
    
    // Setters with validation
    public void setName(String name) {
        if (name != null && !name.trim().isEmpty()) {
            this.name = name;
        }
    }
    
    @Override
    public String toString() {
        return "ID: " + id + ", Name: " + name;
    }
}
