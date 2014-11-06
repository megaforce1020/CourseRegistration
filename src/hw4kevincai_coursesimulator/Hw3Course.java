/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hw4kevincai_coursesimulator;

import java.util.ArrayList;

/**
 *
 * @author Kevin
 */
public class Hw3Course {

    private int number = 0;
    private String title = "";
    private int capacity = 0;
    private ArrayList<Hw3Student> roster = new ArrayList<>();
    private ArrayList<Hw3Student> waitlist = new ArrayList<>();
    private boolean classFull = false;
    private int classSize = 0;
    private int totalWaitStudents=0;
    

    /**
     *
     * @param number
     * @param name
     * @param maxSize
     */
    public Hw3Course(int number, String name, int maxSize) {
        this.number = number;
        title = name;
        capacity = maxSize;
    }

    public int getNumber() {
        return number;
    }

    public String getTitle() {
        return title;
    }   //getter that returns private field title

    public int getTotalWaitStudents() {
        return totalWaitStudents;
    }

    public int getCapacity() {
        return capacity;
    }  //getter returns private field capacity

    public int getRosterSize() {
        return roster.size();
    }   //getter returns the size of the private ArrayList roster

    public int getwaitListSize() {
        return waitlist.size();
    }   //getter returns the size of the private ArrayList waitList

    public ArrayList<Hw3Student> getRoster() {
        return roster;
    } // getter that returns the private ArrayList roster

    public ArrayList<Hw3Student> getwaitList() {
        return waitlist;
    } // getter that returns the private ArrayList waitList

    public void addStudentToRoster(Hw3Student student) {
        if (!classFull){
            roster.add(student);
            classSize++;
        }
        
    } // adds student ArrayList of String objects (roster)  

    public void addStudentTowaitList(Hw3Student student) {
        waitlist.add(student);
        totalWaitStudents++;
    } // adds student ArrayList of String objects (roster)  

    public boolean isFull() {
        if (classSize<=capacity){
            return true;
        }return false;
    }  //determines whether the course is full, returns true if yes and false if not

}
