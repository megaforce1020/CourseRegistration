/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hw4kevincai_coursesimulator;

import static hw4kevincai_coursesimulator.Classification.UGRAD;

/**
 *
 * @author Kevin
 */
public class Hw3Student {

    //list fields

    int studentID;
    private Classification studentClass;
    private float studentGPA;

    public Hw3Student(int ID, Classification Major, float GPA) {
        studentID = ID;
        studentGPA = GPA;
        studentClass = Major;
    }

    public float getGPA() {
        return studentGPA;
    }

    public float getID() {
        return studentID;
    }

    public String getSInfoisFull() {
        return studentGPA + "\t" + studentID + "\t" + studentID + "\t";
    }
}

