package com.practise101;

import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;

/**
 * Hello world!
 *
 */

public class App {
    static Scanner input = new Scanner(System.in);
    // store record in arrayList
    static ArrayList<String> fNames = new ArrayList<>();
    static ArrayList<String> lNames = new ArrayList<>();
    static ArrayList<Integer> sNumbers = new ArrayList<>();
    static ArrayList<String> cCodes = new ArrayList<>();

    public static void main(String[] args) {
        int exitMain = 1;

        // add demo data
        addDemoData();

        // Create menu
        while (exitMain == 1) {
            // clear console
            System.out.print("\033[H\033[2J");

            System.out.println("1. Add Records");
            System.out.println("2. Update Record");
            System.out.println("3. Delete record");
            System.out.println("4. Search Record");
            System.out.println("5. View All");
            System.out.println("6. Delete All");
            System.out.println("7. Exit");

            System.out.print("Choose option: ");
            int option = input.nextInt();

            switch (option) {
                case 1: // add record
                    addRecord();
                    break;
                case 2: // update record
                    updateRecord();

                    break;
                case 3: // delete
                    deleteRecord();
                    break;
                case 4: // search
                    searchRecord();
                    break;
                case 5: // view all
                    viewRecord();
                    break;
                case 6: // delete all
                    deleteAll();
                    break;
                case 7: // exit
                    System.out.println("Exiting...");
                    return;

                default:
                    break;
            }

            System.out.print("\nwould you like to continue? (y/n): ");
            String cont = input.next();

            switch (cont) {
                case "n":
                    exitMain = 0;
                    break;
                case "N":
                    exitMain = 0;
                    break;
                default:
                    exitMain = 1;
                    break;
            }

        }
    }

    private static void addDemoData() {
        String FNAMES[] = { "John", "Jane", "Mark", "Mary", "Peter", "Paul", "Luke", "James", "Jude", "Simon" };
        String LNAMES[] = { "Doe", "Doe", "Smith", "Smith", "Parker", "Parker", "Skywalker", "Bond", "Bond", "Bond" };
        int SNUMBERS[] = { 123456, 234567, 345678, 456789, 567890, 678901, 789012, 890123, 901234, 123456 };
        String CODES[] = { "CSC101", "CSC102", "CSC103", "CSC104", "CSC105", "CSC106", "CSC107", "CSC108", "CSC109",
                "CSC110" };

        // ADD THESE TO THE ARRAY LISTS
        for (int i = 0; i < FNAMES.length; i++) {
            fNames.add(FNAMES[i]);
            lNames.add(LNAMES[i]);
            sNumbers.add(SNUMBERS[i]);
            cCodes.add(CODES[i]);
        }
    }

    // private static void searchRecord() {
    // // search record using stundent number
    // System.out.print("\nEnter student number: ");
    // int sNumber = input.nextInt();

    // // get index of sNumber
    // int index = sNumbers.indexOf(sNumber);

    // // print record
    // System.out.println("\n--------------------------------------------------");
    // System.out.println("First Name: " + fNames.get(index));
    // System.out.println("Last Name: " + lNames.get(index));
    // System.out.println("Student Number: " + sNumbers.get(index));
    // System.out.println("Course Code: " + cCodes.get(index));
    // }

    private static void searchRecord() {
        // search record using student number
        System.out.print("\nEnter student number: ");
        int sNumber = input.nextInt();

        // convert arrayLists to arrays
        String[] fNamesArr = fNames.toArray(new String[fNames.size()]);
        String[] lNamesArr = lNames.toArray(new String[lNames.size()]);
        Integer[] sNumbersArr = sNumbers.toArray(new Integer[sNumbers.size()]);
        String[] cCodesArr = cCodes.toArray(new String[cCodes.size()]);

        // sort the arrays
        for (int i = 0; i < sNumbersArr.length; i++) {// loopinh multiple times
            for (int j = 0; j < sNumbersArr.length - 1; j++) { // looping once through the array to compare each element
                if (sNumbersArr[j] > sNumbersArr[j + 1]) {
                    // swap sNumbersArr
                    int temp = sNumbersArr[j];
                    sNumbersArr[j] = sNumbersArr[j + 1];
                    sNumbersArr[j + 1] = temp;

                    // swap fNamesArr
                    String temp2 = fNamesArr[j];
                    fNamesArr[j] = fNamesArr[j + 1];
                    fNamesArr[j + 1] = temp2;

                    // swap lNamesArr
                    String temp3 = lNamesArr[j];
                    lNamesArr[j] = lNamesArr[j + 1];
                    lNamesArr[j + 1] = temp3;

                    // swap cCodesArr
                    String temp4 = cCodesArr[j];
                    cCodesArr[j] = cCodesArr[j + 1];
                    cCodesArr[j + 1] = temp4;
                }
            }
        }

        // get index of sNumber using binary search
        int upperBound = sNumbersArr.length - 1;
        int lowerBound = 0;
        int index = -1;
        int midPoint = sNumbersArr.length / 2;

        for (int i = 0; i < sNumbersArr.length; i++) {
            if (sNumber == sNumbersArr[midPoint]) {
                index = midPoint;
                break;
            } else if (sNumber > sNumbersArr[midPoint]) {
                lowerBound = midPoint + 1; // ignores the lower half
                midPoint = (upperBound + lowerBound) / 2; // set a new midpoint
            } else if (sNumber < sNumbersArr[midPoint]) {
                upperBound = midPoint - 1; // ignores the upper half
                midPoint = (upperBound + lowerBound) / 2; // set a new midpoint
            }
        }

        // if index is -1, record not found
        if (index == -1) {
            System.out.println("Record not found");
            return;
        } else {
            // print record
            System.out.println("\n--------------------------------------------------");
            System.out.println("First Name: " + fNamesArr[index]);
            System.out.println("Last Name: " + lNamesArr[index]);
            System.out.println("Student Number: " + sNumbersArr[index]);
            System.out.println("Course Code: " + cCodesArr[index]);
            return;
        }
    }

    private static void deleteRecord() {
        // search record using stundent number
        System.out.print("\nEnter student number: ");
        int sNumber = input.nextInt();

        // get index of sNumber
        int index = sNumbers.indexOf(sNumber);

        // delete record
        fNames.remove(index);
        lNames.remove(index);
        sNumbers.remove(index);
        cCodes.remove(index);

        // update file
        fileOps(fNames, lNames, sNumbers, cCodes);
    }

    private static void deleteAll() {
        // clear all arraylists
        fNames.clear();
        lNames.clear();
        sNumbers.clear();
        cCodes.clear();

        // clear file
        File myFile = new File("src\\main\\java\\com\\practise101\\records.txt");
        try {
            if (!myFile.exists()) {
                myFile.createNewFile();
            }

            FileWriter fw = new FileWriter(myFile, false);
            BufferedWriter bw = new BufferedWriter(fw);

            // overwrite file data with new data
            bw.write("");
            bw.close();
            fw.close();

            System.out.println("All records deleted successfully");

        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }

    public static void fileOps(ArrayList<String> fnames, ArrayList<String> lnames, ArrayList<Integer> snumbers,
            ArrayList<String> ccodes) {

        String recordData = "";

        // create file
        File myFile = new File("src\\main\\java\\com\\practise101\\records.txt");
        try {
            if (!myFile.exists()) {
                myFile.createNewFile();
            }

            FileWriter fw = new FileWriter(myFile, true);
            BufferedWriter bw = new BufferedWriter(fw);

            // write to file
            for (int i = 0; i < fNames.size(); i++) {
                recordData = "First Name: " + fnames.get(i) + "\nLast Name: " + lnames.get(i) + "\nStudent Number: "
                        + snumbers.get(i) + "\nCourse Code: " + ccodes.get(i) + "\n\n";
            }

            System.out.println(recordData);

            // overwrite file data with new data
            bw.append(recordData);
            bw.close();
            fw.close();

            System.out.println("Record added successfully");

        } catch (Exception e) {
            System.out.println("Error: " + e);
        }

    }

    public static void printRecords(ArrayList<String> fnames, ArrayList<String> lnames, ArrayList<Integer> snumbers,
            ArrayList<String> ccodes) {

        // Print all data
        for (int i = 0; i < fnames.size(); i++) {
            System.out.println("\n--------------------------------------------------");
            System.out.println("First Name: " + fnames.get(i));
            System.out.println("Last Name: " + lnames.get(i));
            System.out.println("Student Number: " + snumbers.get(i));
            System.out.println("Course Code: " + ccodes.get(i));
        }
    }

    private static void addRecord() {
        int exit = 1;

        while (exit == 1) {
            // get record data
            System.out.print("\nFirst Name: ");
            String fName = input.next();

            System.out.println("\nLast Name: ");
            String lName = input.next();

            System.out.println("\nStudent Number: ");
            int sNumber = input.nextInt();

            System.out.println("\nCourse Code: ");
            String cCode = input.next();

            // add data to arraylists
            fNames.add(fName);
            lNames.add(lName);
            sNumbers.add(sNumber);
            cCodes.add(cCode);

            System.out.print("\nwould you like to continue? (y/n): ");
            String cont = input.next();

            switch (cont) {
                case "n":
                    exit = 0;
                    break;
                case "N":
                    exit = 0;
                    break;
                default:
                    exit = 1;
                    break;
            }

        }

        // Store record in Files (.txt)
        fileOps(fNames, lNames, sNumbers, cCodes);
    }

    private static void updateRecord() {

        // search record using stundent number
        System.out.print("\nEnter student number: ");
        int sNumber = input.nextInt();

        // get index of sNumber
        int index = sNumbers.indexOf(sNumber);

        // get record data
        System.out.print("\nFirst Name: ");
        String fName = input.next();

        System.out.println("\nLast Name: ");
        String lName = input.next();

        System.out.println("\nCourse Code: ");
        String cCode = input.next();

        // update record
        fNames.set(index, fName);
        lNames.set(index, lName);
        cCodes.set(index, cCode);

        // update file
        fileOps(fNames, lNames, sNumbers, cCodes);
    }

    private static void viewRecord() {
        printRecords(fNames, lNames, sNumbers, cCodes);
    }
}
