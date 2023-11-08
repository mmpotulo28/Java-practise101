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

        // Create menu
        while (exitMain == 1) {

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

                    break;
                case 4: // search

                    break;
                case 5: // view all
                    viewRecord();
                    break;
                case 6: // delete all

                    break;
                case 7: // exit

                    break;
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
            bw.write(recordData);
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
