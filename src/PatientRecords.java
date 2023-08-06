import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.InputMismatchException;
import java.io.FileNotFoundException;

/** This program was designed to read, add, update, and output patient
 * information from txt files using 5 different functions/methods.
 * 
 * @author Riya Gunda
 * @version 1.0
 */
public class PatientRecords {

    /**
     * Variable for the constant integer value 2
     */
    public static final int VALUE_TWO = 2;

    /**
     * Variable for the constant integer value 3
     */
    public static final int VALUE_THREE = 3;

    /**
     * Variable for the constant integer value 4
     */
    public static final int VALUE_FOUR = 4;

    /**
     * Variable for the constant integer value 5
     */
    public static final int VALUE_FIVE = 5;

    /**
     * Variable for the constant integer value 6
     */
    public static final int VALUE_SIX = 6;

    /**
     * Variable for the constant integer value 7
     */
    public static final int VALUE_SEVEN = 7;

    /**
     * Variable for the starting ID number for any patient
     */
    public static final int STARTING_ID = 1001;
    
    /**
     * Program starts 
     * @param args from command line
     */   
    public static void main(String[] args){
        System.out.println();
        if(args.length != VALUE_THREE) {
            System.out.println("Usage: java -cp bin PatientRecords infile outfile max_patients");
            System.exit(1);
        }

        if(Integer.parseInt(args[VALUE_TWO]) < 0) {
            System.out.println("Usage: java -cp bin PatientRecords infile outfile max_patients");
            System.exit(1);
        }

        int maxValue;
        try {
            maxValue = Integer.parseInt(args[VALUE_TWO]);
        } catch (NumberFormatException e) {
            System.out.println("Usage: java - cp bin PatientRecords infile outfile max_patients");
            System.exit(1);
            return;
        }

        if(maxValue <= 0){
            System.out.println("Usage: java - cp bin PatientRecords infile outfile max_patients");
            System.exit(1);
            return;
        }
        String inputFileName = args[0];
        String outputFileName = args[1];
        File inFile = new File(inputFileName);
        File outFile = new File(outputFileName);
        PrintWriter out = null;

        if(!inFile.exists() || !inFile.canRead()) {
            System.out.println("Unable to access input files: " + inFile);
            System.exit(1);
            return;
        }

        Scanner fileReader = null;
        try {
            fileReader = new Scanner(inFile);
        } catch (FileNotFoundException e){
            System.out.println("Unable to access input file: " + inFile);
            System.exit(1);
        }

        if(outFile.exists()) {
            System.out.print(outFile.getName() + " exists - OK to overwrite? (y/n)?: ");
            Scanner console = new Scanner(System.in);
            String userChoice = console.nextLine();
            if(userChoice.toLowerCase().equals("y") || userChoice.toLowerCase().equals("yes")) {
                outFile.delete();
            } else {
                System.exit(1);
            }
        }

        try {
            out = new PrintWriter(outFile);
        } catch (FileNotFoundException e) {
            System.out.println("Cannot create output file");
            System.exit(1);
        }

        int counter = 0;
        int[] ids = new int[maxValue];
        String[] names = new String[maxValue];
        String[] birthdates = new String[maxValue];
        int[] heights = new int[maxValue];
        int[] weights = new int[maxValue];
        double[] temperatures = new double[maxValue];
        String[] bloodPressures = new String[maxValue];

        int id, ht, wt;
        double temp;
        String name, bd, bp;

        try {
            while(fileReader.hasNextLine() && counter < maxValue) {
                String[] holder = fileReader.nextLine().split(",");
                id = Integer.parseInt(holder[0]);
                if((id != STARTING_ID + counter) || (holder.length != VALUE_SEVEN) || (counter >= maxValue) || !inFile.exists()) {
                    System.out.println();
                    System.out.println("Invalid input file");
                    return;
                }

                id = Integer.parseInt(holder[0]);
                name = holder[1];
                bd = holder[VALUE_TWO];
                ht = Integer.parseInt(holder[VALUE_THREE]);
                wt = Integer.parseInt(holder[VALUE_FOUR]);
                temp = Double.parseDouble(holder[VALUE_FIVE]);
                bp = holder[VALUE_SIX];

                ids[counter] = id;
                names[counter] = name;
                birthdates[counter] = bd;
                heights[counter] = ht;
                weights[counter] = wt;
                temperatures[counter] = temp;
                bloodPressures[counter] = bp;

                counter++;
            }
        } catch (NumberFormatException e) {
            System.out.println();
            System.out.println("Invalid input file");
            System.exit(1);
        }

        fileReader.close();

        try {
            boolean programRunning = true;
            while(programRunning) {
                System.out.println();
                System.out.println("Patient records program - Please choose an option.");
                System.out.println();
                System.out.println("L - List patients");
                System.out.println("U - Update patients");
                System.out.println("A - Add patient");
                System.out.println("Q - Quit");

                System.out.println();
                System.out.print("Option: ");
                Scanner console = new Scanner(System.in);
                String userChoice = console.next();
                String compare = userChoice.toLowerCase();
                System.out.println();
                if(compare.equals("l")){
                    System.out.println(" ID         NAME          BIRTHDATE HT  WT    T      BP");
                    String listed = getPatientList(ids, names, birthdates, heights, weights, 
                        temperatures, bloodPressures);
                    System.out.println(listed);
                    continue;
                } else if(compare.equals("u")) {
                    String updated = updatePatient(console, ids, names, birthdates, heights, 
                        weights, temperatures, bloodPressures);
                    System.out.println(updated);
                    continue;
                } else if(compare.equals("a")) {
                    String added = addPatient(console, ids, names, birthdates, heights, 
                        weights, temperatures, bloodPressures);
                    System.out.println(added);
                    continue;
                } else if(compare.equals("q")) {
                    PrintWriter printWriter = new PrintWriter(new FileOutputStream(outputFileName));
                    outputPatients(printWriter, ids, names, birthdates, heights, 
                        weights, temperatures, bloodPressures);
                    printWriter.close();
                    System.exit(1);
                } else {
                    System.out.println("Invalid option");
                    continue;
                }

            } 
        } catch (Exception e) {
            System.out.println();
        }        
    }

    /**
     * Reads each line of the input file, and stores the patient id, name, birthdate,
     * height, weight, temperature, and blood pressure
     * in the appropriate array parameter
     * Returns true, if successful
     * Return false, if
     *   (a) a line of the file does not contain seven items on each line as described above,
     *   (b) the patient ids do not start with 1001 and increase by 1 for each patient,
     *   (c) the height and/or weight are not integer values,
     *   (d) the temperatures is not a double value (note that all integer values are also double
     *       values),
     *   (e) or the file contains more lines than the maximum number of patients 
     *       (note that the length of each array corresponds to the maximum number 
     *       of patients)
     *
     * @param in object for the Scanner class
     * @param ids patient's unique number
     * @param names patient's first and last name
     * @param birthdates patient's date of birth
     * @param heights patient's height
     * @param weights patient's weight
     * @param temperatures patient's current body temperature
     * @param bloodPressures patient's current blood pressure
     * 
     * @return boolean value based on the validity of the inputted data in the file
     * 
     * @throws IllegalArgumentException with the message "Null file" if it is null
     * @throws IllegalArgumentException with the message "Null file" if ids, names, birthdates,
     *         heights, weights, temperatures, and/or bloodPressure are/is null
     * @throws IllegalArgumentException with the message "Invalid array length" if 
     *         the lengths of the arrays parameters are not the same and/or < 1
     */
    public static boolean inputPatients(Scanner in, int[] ids, String[] names, String[] birthdates,
                                        int[] heights, int[] weights, double[] temperatures,
                                        String[] bloodPressures) {
        // check for invalid number of items in line
        // check for invalid patients ID
        // check for invalid ht and wt
        // check for invalid temp
        // check for invalid bp format
        // store all patient info in arrays
        if(in == null) {
            throw new IllegalArgumentException("Null file");
        }

        if(ids == null || names == null || birthdates == null || heights == null || 
            weights == null || temperatures == null || bloodPressures == null) {
            throw new IllegalArgumentException("Null array");
        }

        if(ids.length != names.length || ids.length != birthdates.length || 
            ids.length != heights.length || ids.length != weights.length || 
            ids.length != temperatures.length || ids.length != bloodPressures.length ||
            ids.length < 1) {
            throw new IllegalArgumentException("Invalid array length");
        }

        int counter = 0;
        int expected = STARTING_ID;
        int id, ht, wt;
        double temp;
        String bp;

        while(in.hasNextLine()) {
            if (counter == ids.length) {
                return false;
            }

            String reader = in.nextLine();
            String[] rowLine = reader.split(",");

            if(rowLine.length < VALUE_SEVEN || rowLine.length > VALUE_SEVEN) {
                return false;
            }

            try {
                id = Integer.parseInt(rowLine[0]);

                if (Integer.parseInt(rowLine[0]) != expected + counter) {
                    return false;
                }
            } catch (NumberFormatException e) {
                return false;
            }

            int first = Integer.parseInt(rowLine[0]);
            if(first != STARTING_ID && counter == 0){
                return false;
            }

            try {
                ht = Integer.parseInt(rowLine[VALUE_THREE]);
                wt = Integer.parseInt(rowLine[VALUE_FOUR]);
                temp = Double.parseDouble(rowLine[VALUE_FIVE]);
                bp = rowLine[VALUE_SIX];
            } catch (NumberFormatException e) {
                return false;
            }

            ids[counter] = id;
            names[counter] = rowLine[1];
            birthdates[counter] = rowLine[VALUE_TWO];
            heights[counter] = ht;
            weights[counter] = wt;
            temperatures[counter] = temp;
            bloodPressures[counter] = bp;   
            counter++;     
        }
        return true;
    }

    /**
     * Returns a String with each patient id, name, birthdate, height, weight, temperature, and
     * blood pressure with a newline character after the String for each patient. Only include
     * a String for the patients, if any, for which there is an id (1001, 1002, etc.)
     * in the ids array. These should be at the beginning of the ids array potentially followed
     * 0s for patients that have not been added.
     * Uses the toString() method to correctly format each line
     *
     * @param ids patient's unique number
     * @param names patient's first and last name
     * @param birthdates patient's date of birth
     * @param heights patient's height
     * @param weights patient's weight
     * @param temperatures patient's current body temperature
     * @param bloodPressures patient's current blood pressure
     * 
     * @return a String with patient details
     * 
     * @throws IllegalArgumentException with the message "Null array" if the array parameter is null
     * @throws IllegalArgumentException with the message "Invalid array length" if the lengths 
     * of the array parameters are not the same
     */
    public static String getPatientList(int[] ids, String[] names, String[] birthdates,
                                        int[] heights, int[] weights, double[] temperatures,
                                        String[] bloodPressures) {

        if(ids == null || names == null || birthdates == null || heights == null || weights == null 
            || temperatures == null || bloodPressures == null) {
            throw new IllegalArgumentException("Null array");
        }

        if(ids.length != names.length || ids.length != birthdates.length || ids.length != 
            heights.length || ids.length != weights.length || ids.length != temperatures.length || 
            ids.length != bloodPressures.length || ids.length < 1) {
            throw new IllegalArgumentException("Invalid array length");
        }

        String patients = "";
        int num = ids.length;
        for(int i = 0; i < num; i++) {
            if(ids[i] == 0) {
                continue;
            }
            if(ids[i] == (STARTING_ID + i)) {
                patients += toString((STARTING_ID + i), names[i], birthdates[i], heights[i], 
                weights[i], temperatures[i], bloodPressures[i]) + "\n";
            }
        }

        return patients;
    }

    /**
     * Returns the data in a properly formatted string
     * @param id patient's unique number
     * @param name patient's first and last name
     * @param birthdate patient's date of birth
     * @param height patient's height
     * @param weight patient's weight
     * @param temperature patient's current body temperature
     * @param bloodPressure patient's current blood pressure
     * 
     * @return properly formatted string for patient
     */
    public static String toString(int id, String name, String birthdate, int height, int weight,
                                  double temperature, String bloodPressure) {
        return String.format("%4d  %-18s %10s %2d %3d %6.2f %7s", id, name, birthdate, 
            height, weight, temperature, bloodPressure );
    }

    /**
     * Prompts the user for the id of the patient to be updated.
     * If the id does not exist, "Invalid id" is returned.
     *
     * Prompts the user for the patient's height, weight, temperature, and blood pressure.
     * If the height or weight are not integer values or the temperature is not a double value,
     * "Invalid value" is returned as soon as one invalid value is entered.
     *
     * If valid values are entered, the patient's height, weight, temperature, and blood pressure
     * are updated and "Successful update" is returned.
     *
     * @param scnr object for the Scanner class
     * @param ids patient's unique number
     * @param names patient's first and last name
     * @param birthdates patient's date of birth
     * @param heights patient's height
     * @param weights patient's weight
     * @param temperatures patient's current body temperature
     * @param bloodPressures patient's current blood pressure
     * 
     * @return a String with a certain message based on whether the updates were successful
     * 
     * @throws IllegalArgumentException with the message "Null array" if ids, names, birthdates,
     * heights, weights, temperatures, and/or bloodPressures are/is null     
     * @throws IllegalArgumentException with the message "Invalid array length" if the lengths
     * of the array parameters are not the same and/or < 1
     */
    public static String updatePatient(Scanner scnr, int[] ids, String[] names,
                                       String[] birthdates, int[] heights, int[] weights,
                                       double[] temperatures, String[] bloodPressures) {

        if(ids == null || names == null || birthdates == null || heights == null || 
            weights == null || temperatures == null || bloodPressures == null) {
            throw new IllegalArgumentException("Null array");
        }

        if(ids.length != names.length || ids.length != birthdates.length || 
            ids.length != heights.length || ids.length != weights.length ||
            ids.length != temperatures.length || ids.length != bloodPressures.length 
            || ids.length < 1) {
            throw new IllegalArgumentException("Invalid array length");
        } 

        int patientID = -1;
        try {
            System.out.print("Patient id: ");
            patientID = scnr.nextInt();   
        } catch (InputMismatchException e) {
            System.out.println();
            return "Invalid id";
        }
  
        boolean flag = false;
        int temp = 0;                            
        for(int i = 0; i < ids.length; i++){
            if(patientID == ids[i]) {
                flag = true;
                temp = i;
                break;
            }
        }

        if(flag == false) {
            System.out.println();
            return "Invalid id";
        }


        int ht, wt;
        double temperature;
        String bp;
        try {
            System.out.print("Height: ");
            ht = scnr.nextInt();
            if(ht < 0){
                System.out.println();
                return "Invalid value";
            }
            scnr.nextLine();

            System.out.print("Weight: ");
            wt = scnr.nextInt();
            if(wt < 0){
                System.out.println();
                return "Invalid value";
            }
            scnr.nextLine();

            System.out.print("Temperature: ");
            temperature = scnr.nextDouble();
            if(temperature < 0.0) {
                System.out.println();
                return "Invalid value";
            }
            scnr.nextLine();

            System.out.print("Blood pressure: ");
            bp = scnr.next();

            heights[temp] = ht;
            weights[temp] = wt;
            temperatures[temp] = temperature;
            bloodPressures[temp] = bp;

            System.out.println();

            return "Successful update";
        } catch (InputMismatchException e) {
            System.out.println();
            return "Invalid value";
        }
    }

    /**
     * If the maximum number of patients has been reached, i.e., none of the values in the ids
     * array are 0, "Cannot add new patient" is returned
     *
     * Prompts the user for the patient's name, birthdate, height, weight, temperature, and blood
     * pressure. If the height or weight are not integer values or the temperature is not a
     * double value, "Invalid value" is returned as soon as one invalid value is entered.
     *
     * If valid values are entered, the patient is assigned the next available id,
     * the patient’s information is added, and "Successful addition" is returned.
     *
     * @param scnr object for the Scanner class
     * @param ids patient's unique number
     * @param names patient's first and last name
     * @param birthdates patient's date of birth
     * @param heights patient's height
     * @param weights patient's weight
     * @param temperatures patient's current body temperature
     * @param bloodPressures patient's current blood pressure
     * 
     * @return a String with a certain message based on whether the addition was successful
     * 
     * @throws IllegalArgumentException with the message "Null array" if ids, names, birthdates,
     * heights, weights, temperatures, and/or bloodPressures are/is null     
     * @throws IllegalArgumentException with the message "Invalid array length" if the lengths of
     * the array parameters are not the same and/or < 1
     */
    public static String addPatient(Scanner scnr, int[] ids, String[] names,
                                    String[] birthdates, int[] heights, int[] weights,
                                    double[] temperatures, String[] bloodPressures) {

        if(ids == null || names == null || birthdates == null || heights == null || 
            weights == null || temperatures == null || bloodPressures == null) {
            throw new IllegalArgumentException("Null array");
        }

        if(ids.length != names.length || ids.length != birthdates.length || 
            ids.length != heights.length || ids.length != weights.length ||
            ids.length != temperatures.length || ids.length != bloodPressures.length 
            || ids.length < 1) {
            throw new IllegalArgumentException("Invalid array length");
        }

        //check if new input exceeds max length of the arrays
        boolean flag = false;   
        int temp = 0;                       
        for(int i = 0; i < ids.length; i++){
            if(ids[i] == 0) {
                flag = true;
                temp = i;
                break;
            }
        }

        if(flag == false){
            return "Cannot add new patient";
        }

        int id, ht, wt;
        String name, bp, bd;
        double temperature;
        int idIndex = temp - 1; 
        try {
            id = ids[idIndex] + 1;
            scnr.nextLine();
            System.out.print("Name: ");
            name = scnr.nextLine();

            System.out.print("Birthdate: ");
            bd = scnr.next();
            scnr.nextLine();

            System.out.print("Height: ");
            ht = scnr.nextInt();
            if(ht < 0){
                System.out.println();
                return "Invalid value";
            }
            scnr.nextLine();

            System.out.print("Weight: ");
            wt = scnr.nextInt();
            if(wt < 0){
                System.out.println();
                return "Invalid value";
            }
            scnr.nextLine();

            System.out.print("Temperature: ");
            temperature = scnr.nextDouble();
            if(temperature < 0.0){
                System.out.println();
                return "Invalid value";
            }
            scnr.nextLine();

            System.out.print("Blood pressure: ");
            bp = scnr.next();
        } catch (InputMismatchException e) {
            System.out.println();
            return "Invalid value";
        }   

        ids[temp] = id;
        names[temp] = name; 
        birthdates[temp] = bd; 
        heights[temp] = ht;
        weights[temp] = wt;
        temperatures[temp] = temperature;
        bloodPressures[temp] = bp;

        System.out.println();
        return "Successful addition";
    }

    /**
     * Outputs the information for each patient to the file in CSV format as described above.
     *
     * @param out object of type PrintWriter
     * @param ids patient's unique number
     * @param names patient's first and last name
     * @param birthdates patient's date of birth
     * @param heights patient's height
     * @param weights patient's weight
     * @param temperatures patient's current body temperature     * 
     * @param bloodPressures patient's current blood pressure
     * 
     * @throws IllegalArgumentException with the message "Null file" if out is null
     * @throws IllegalArgumentException with the message "Null array" if any array
     * parameter is null
     * @throws IllegalArgumentException with the message "Invalid array length" if the
     * lengths of the array parameters are not the same and/or < 1
     */
    public static void outputPatients(PrintWriter out, int[] ids, String[] names, 
                                      String[] birthdates, int[] heights, int[] weights, 
                                      double[] temperatures, String[] bloodPressures) {
        if(out == null) {
            throw new IllegalArgumentException("Null file");
        }

        if(ids == null || names == null || birthdates == null || heights == null || 
            weights == null || temperatures == null || bloodPressures == null) {
            throw new IllegalArgumentException("Null array");
        }

        if(ids.length != names.length || ids.length != birthdates.length || 
            ids.length != heights.length || ids.length != weights.length ||
            ids.length != temperatures.length || ids.length != bloodPressures.length 
            || ids.length < 1) {
            throw new IllegalArgumentException("Invalid array length");
        }   

        int temp = 0;
        for(int i = 0; i < ids.length; i++) {
            if(ids[i] == 0){
                temp = i - 1;
                break;
            }
        }     

        String outputString = "";
        for(int i = 0; i <= temp; i++) {
            if(i == 0) {
                outputString = ids[i] + "," + names[i] + "," + birthdates[i] + "," + 
                heights[i] + "," + weights[i] + "," + temperatures[i] + "," + bloodPressures[i];
                out.print(outputString);
            } else {
                outputString = ids[i] + "," + names[i] + "," + birthdates[i] + "," + 
                heights[i] + "," + weights[i] + "," + temperatures[i] + "," + bloodPressures[i];
                out.print("\n" + outputString);
            }
        }                       
    }
}
