/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hw4kevincai_coursesimulator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Kevin
 */
public class HW4KevinCai_CourseSimulator {

    final static int CLASSMINSIZE = 10;
    final static int CLASSUMAXSIZE = 20;
    final static int CLASSGMAXSIZE = 10;
    static int indexOfCurrAvailGSection = 0;
    static int indexOfCurrAvailUSection = 1;
    static final int MAX_STUDENTS = 300;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        int userSelection, courseToFind, studentToFind;
        ArrayList<Hw3Course> theCourses = new ArrayList<>();
        System.out.println("------------------------------------------------------\n"
                + "Please select from the following options:\n"
                + "1. Simulate\n"
                + "2. View available courses\n"
                + "3. View roster for a course\n"
                + "4. Drop student and adjust roster for a course\n"
                + "5. Exit from system");
        userSelection = console.nextInt();
        while(userSelection!=5){
        if (userSelection == 1) {
            theCourses = simulate();
            System.out.println("-------------------------------------------------------------\nPlease select from the following options:\n"
                    + "1. Simulate\n"
                    + "2. View available courses\n"
                    + "3. View roster for a course\n"
                    + "4. Drop student and adjust roster for a course\n"
                    + "5. Exit from system\n" + "-------------------------------------------------------------");
            userSelection = console.nextInt();
        }
        if (userSelection == 2) {
            printCourseInfo(theCourses);
            System.out.println("-------------------------------------------------------------\nPlease select another option:\n"
                    + "1. Simulate\n"
                    + "2. View available courses\n"
                    + "3. View roster for a course\n"
                    + "4. Drop student and adjust roster for a course\n"
                    + "5. Exit from system\n" + "-------------------------------------------------------------");
            userSelection = console.nextInt();
        }
        if (userSelection == 3) {
            System.out.println("Please input either course 4415 or 6615");
            courseToFind = console.nextInt();
            findAndPrintRosterInfo(courseToFind, theCourses);
            System.out.println("-------------------------------------------------------------\nPlease select another option:\n"
                    + "1. Simulate\n"
                    + "2. View available courses\n"
                    + "3. View roster for a course\n"
                    + "4. Drop student and adjust roster for a course\n"
                    + "5. Exit from system\n" + "-------------------------------------------------------------");
            userSelection = console.nextInt();
        }
        if (userSelection == 4) {
            System.out.println("Please enter a student ID to drop:");
            studentToFind = console.nextInt();
            dropAndAdjustRoster(theCourses, studentToFind);
            System.out.println("-------------------------------------------------------------\nPlease select from the following options:\n"
                    + "1. Simulate\n"
                    + "2. View available courses\n"
                    + "3. View roster for a course\n"
                    + "4. Drop student and adjust roster for a course\n"
                    + "5. Exit from system\n" + "-------------------------------------------------------------");
            userSelection = console.nextInt();
        }
        else if (userSelection == 5){
            System.out.println("Bye");
        }
        }
        //System.out.println(userSelection);

    }

    public static ArrayList<Hw3Course> simulate() {
        //create list of variables
        Hw3Course newCourse;
        Hw3Student newGStudent, newUStudent;
        //creates random numbers for GPA and Major
        Random selectGPA = new Random();
        Random selectMajor = new Random();
        //created variable for student ID
        int sID = 1;
        //Variable for the different major UGRAD and GRAD
        Classification major;
        //Variable for deciding between UGRAD and GRAD
        float cutoff = (float) 0.75;
        //Variable for rejecting students
        float gradCutoff = (float) 3.2;
        float ugradCutoff = (float) 2.8;

        float gpa = 0, decideMajor;
        int gStudent = 0, uStudent = 0;
        int undergradClass = 0, gradClass = 0;
        int rejectedGStud = 0, rejectedUStud = 0;
        int gradWait = 0, underGradWait = 0;
        int addedGrad = 0, addedUGrad = 0;

        //create ArrayList and objects to go into the ArrayList
        ArrayList<Hw3Course> courseArray = new ArrayList<>();
        newCourse = new Hw3Course(6615, "Grad Java", CLASSGMAXSIZE);
        courseArray.add(newCourse);
        newCourse = new Hw3Course(4415, "Java-1", CLASSUMAXSIZE);
        courseArray.add(newCourse);
        
        /*
        Extra unnecessary stuff
        ArrayList<Hw3Student> waitListU = new ArrayList<>();
        newUStudent = new Hw3Student(sID, Classification.UGRAD, gpa);
        waitListU.add(newUStudent);

        ArrayList<Hw3Student> waitListG = new ArrayList<>();
        newGStudent = new Hw3Student(sID, Classification.GRAD, gpa);
        waitListU.add(newGStudent);
        */
                
        //create a loop that will simulate the MAX # of students (300)
        for (int i = 0; i < MAX_STUDENTS; i++) {
            //increment the student id by 1
            sID++;
            //take the randomized gpa, multiply for 4 cause gpa ranges from 0-4, put into variable gpa
            gpa = selectGPA.nextFloat() * 4;
            //take the randomized major and put it in the variable decideMajor
            decideMajor = selectMajor.nextFloat();

            //this determines if the major is grad or undergrad based on the cutoff variable above
            if (decideMajor > cutoff) {
                major = Classification.GRAD;
                //this counts how many is major = grad
                gradClass++;
                //nested expression that in addition to decideMajor, gpa also determines whether student is rejected or not
                if (gpa > gradCutoff) {
                    newGStudent = new Hw3Student(sID, major, gpa);
                    //by using courseArray this allows me to pull in methods from the Hw3Course class
                    if (courseArray.get(indexOfCurrAvailGSection).isFull()) {
                        //see if there is space and adds the student to the roster
                        courseArray.get(indexOfCurrAvailGSection).addStudentToRoster(newGStudent);
                        addedGrad++;
                    } else {
                        courseArray.get(indexOfCurrAvailGSection).addStudentTowaitList(newGStudent);
                        gradWait++;
                    }
                } else {
                    rejectedGStud++;
                }

            } else {
                major = Classification.UGRAD;
                undergradClass++;
                if (gpa > ugradCutoff) {
                    newUStudent = new Hw3Student(sID, major, gpa);
                    if (courseArray.get(indexOfCurrAvailUSection).isFull()) {
                        //see if there is space and adds the student to the roster
                        courseArray.get(indexOfCurrAvailUSection).addStudentToRoster(newUStudent);
                        addedUGrad++;
                    } else {
                        courseArray.get(indexOfCurrAvailUSection).addStudentTowaitList(newUStudent);
                        underGradWait++;
                    }
                } else {
                    rejectedUStud++;
                }
            }
        }
        System.out.println("Grads = " + gradClass + "; rejected = " + rejectedGStud + " and added = " + addedGrad
                + " and waitlisted = " + gradWait + "\n" + "Undergrads = " + undergradClass + "; rejected = "
                + rejectedUStud + " and added = " + addedUGrad + " and waitlisted = " + gradWait);

        return courseArray;

    }

    public static void printCourseInfo(ArrayList<Hw3Course> allCourses) {
        Hw3Course Course;
        Iterator<Hw3Course> itC = allCourses.iterator();
        while (itC.hasNext()) {
            Course = itC.next();
            System.out.println("Listing courses available in the system. \n "
                    + Course.getNumber() + "\t" + Course.getTitle() + "\t" + Course.getRosterSize() + "\t" + Course.getCapacity());

        }
        
    }

    public static void findAndPrintRosterInfo(int courseToFind, ArrayList<Hw3Course> allCourses) {
        Hw3Course Course;
        Hw3Student Student;
        boolean found=false;
        Iterator<Hw3Course> itC = allCourses.iterator();
        Iterator<Hw3Student> itS;
        
        while (itC.hasNext()) {
            Course = itC.next();
            if (Course.getNumber() == courseToFind) {
                found=true;
                itS = Course.getRoster().iterator();
                while (itS.hasNext()){
                    Student = itS.next();
                    System.out.println("" + Student.getSInfoisFull());
                }
            }
        }
        if (found == false){
            System.out.println("The Class you want is not found");
        }
    }

    public static void dropAndAdjustRoster(ArrayList<Hw3Course> allCourses, int studentToFind) {
        Hw3Course Course;
        Hw3Student Student;
        boolean found = false;
        Iterator<Hw3Course> itC = allCourses.iterator();
        Iterator<Hw3Student> itS;
        ArrayList<Hw3Student> waitList;
        System.out.println("\n******************trying to drop a Student and move a Student from waitlister****************\n");
        while (itC.hasNext() && found == false) {
            Course = itC.next();
            waitList = Course.getwaitList();
            itS = waitList.iterator();
            while (itS.hasNext() && found == false) {
                Student = itS.next();
                if (Student.getID() == studentToFind) {
                    found = true;
                    Course.addStudentToRoster(Student);
                    waitList.remove(Student);
                }
            }
            if (found == true) {
                System.out.println("Student was found in " +Course.getTitle()+", and dropped, and the waitlst is now of size: " +Course.getwaitListSize());
            }
        }
        if (found == false) {
            System.out.println("The Student ID you want is not found");
        }

    }

}
