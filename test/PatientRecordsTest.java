import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

/**
 * Program to test PatientRecords methods
 *
 * @author Suzanne Balik
 * @author Michelle Glatz
 * @author Riya Gunda
 */
public class PatientRecordsTest {                                               

    /**
     * Test toString
     */
    @Test
    public void testToString() {
        String expected = "1001  Amy Apple          01/11/1111 50 150  98.50  100/80";
        String actual = PatientRecords.toString(1001, "Amy Apple", "01/11/1111", 
                50, 150, 98.5, "100/80");
        assertEquals(expected, actual, "Tests that using given toString method");
    }
                                                   

    /**
     * Test getPatientList - arrays not full
     */
    @Test
    public void testGetPatientList1() {
        
        int[] ids = {1001, 1002, 1003, 0, 0};
        String[] names = {"Amy Apple","Bill Brook","Carly Cook", null, null};
        String[] birthdates = {"01/11/1111", "02/22/2222", "03/33/3333", null, null}; 
        int[] heights = {50, 60, 70, 0, 0};
        int[] weights = {150, 160, 170, 0, 0};
        double[] temperatures = {98.5, 99.0, 100.2, 0.0, 0.0};
        String[] bloodPressures = {"100/80", "120/90", "130/95", null, null};
        int[] idsCopy = {1001, 1002, 1003, 0, 0};
        String[] namesCopy = {"Amy Apple","Bill Brook","Carly Cook", null, null }; 
        String[] birthdatesCopy = {"01/11/1111", "02/22/2222", "03/33/3333", null, null}; 
        int[] heightsCopy = {50, 60, 70, 0, 0};
        int[] weightsCopy = {150, 160, 170, 0, 0};
        double[] temperaturesCopy = {98.5, 99.0, 100.2, 0.0, 0.0};
        String[] bloodPressuresCopy = {"100/80", "120/90", "130/95", null, null};       
        
        String expected = "1001  Amy Apple          01/11/1111 50 150  98.50  100/80\n" +
                          "1002  Bill Brook         02/22/2222 60 160  99.00  120/90\n" +
                          "1003  Carly Cook         03/33/3333 70 170 100.20  130/95\n";

        String actual = PatientRecords.getPatientList(ids, names, birthdates, heights, weights, 
                                                      temperatures, bloodPressures);
        assertEquals(expected, actual, 
                     "PatientRecords.getPatientList when arrays not full");

        // Check that PatientRecords.getPatientList does not modify arrays
        assertArrayEquals(idsCopy, ids, 
                "ids modified in PatientRecords.getPatientList when it shouldn't be");
        assertArrayEquals(namesCopy, names, 
                "names modified in PatientRecords.getPatientList when it shouldn't be");
        assertArrayEquals(birthdatesCopy, birthdates,
                "birthdates modified in PatientRecords.getPatientList when it shouldn't be");
        assertArrayEquals(heightsCopy, heights, 
                "heights modified in PatientRecords.getPatientList when it shouldn't be");  
        assertArrayEquals(weightsCopy, weights, 
                "weights modified in PatientRecords.getPatientList when it shouldn't be");
        assertArrayEquals(temperaturesCopy, temperatures, 
                "temperatures modified in PatientRecords.getPatientList when it shouldn't be");
    }

    /**
     * Test getPatientList no patients
     */
    @Test
    public void testGetPatientList2() {
        
        String expected = "";

        int[] noInts = new int[5];
        String[] noStrings = new String[5];
        double[] noDoubles = new double[5];
        String actual = PatientRecords.getPatientList(noInts, noStrings, noStrings, noInts, 
                                                      noInts, noDoubles, noStrings);
        assertEquals(expected, actual, 
                     "PatientRecords.getPatientList(noInts, noStrings, noStrings, " +
                     "noInts, noInts, noDoubles, noStrings);");

        // Check that PatientRecords.getList does not modify arrays
        int[] noIntsCopy = new int[5];
        String[] noStringsCopy = new String[5];
        double[] noDoublesCopy = new double[5];
        assertArrayEquals(noIntsCopy, noInts,
                "noInts modified in PatientRecords.getPatientList when it shouldn't be");
        assertArrayEquals(noStringsCopy, noStrings,
                "noStrings modified in PatientRecords.getPatientList when it shouldn't be");
        assertArrayEquals(noDoublesCopy, noDoubles,
                "noDoubles modified in PatientRecords.getPatientList when it shouldn't be");
        
    }

    /**
     * Test getPatientList full arrays
     */
    @Test
    public void testGetPatientList3() {
        
        int[] ids = {1001, 1002, 1003};
        String[] names = {"Amy Apple","Bill Brook","Carly Cook"};
        String[] birthdates = {"01/11/1111", "02/22/2222", "03/33/3333"}; 
        int[] heights = {50, 60, 70};
        int[] weights = {150, 160, 170};
        double[] temperatures = {98.5, 99.0, 100.2};
        String[] bloodPressures = {"100/80", "120/90", "130/95"};
        int[] idsCopy = {1001, 1002, 1003};
        String[] namesCopy = {"Amy Apple","Bill Brook","Carly Cook"}; 
        String[] birthdatesCopy = {"01/11/1111", "02/22/2222", "03/33/3333"}; 
        int[] heightsCopy = {50, 60, 70};
        int[] weightsCopy = {150, 160, 170};
        double[] temperaturesCopy = {98.5, 99.0, 100.2};
        String[] bloodPressuresCopy = {"100/80", "120/90", "130/95"};       
        
        String expected = "1001  Amy Apple          01/11/1111 50 150  98.50  100/80\n" +
                          "1002  Bill Brook         02/22/2222 60 160  99.00  120/90\n" +
                          "1003  Carly Cook         03/33/3333 70 170 100.20  130/95\n";

        String actual = PatientRecords.getPatientList(ids, names, birthdates, heights, weights, 
                                                      temperatures, bloodPressures);
        assertEquals(expected, actual, 
                     "PatientRecords.getPatientList when arrays are full");

        // Check that PatientRecords.getPatientList does not modify arrays
        assertArrayEquals(idsCopy, ids, 
                "ids modified in PatientRecords.getPatientList when it shouldn't be");
        assertArrayEquals(namesCopy, names, 
                "names modified in PatientRecords.getPatientList when it shouldn't be");
        assertArrayEquals(birthdatesCopy, birthdates,
                "birthdates modified in PatientRecords.getPatientList when it shouldn't be");
        assertArrayEquals(heightsCopy, heights, 
                "heights modified in PatientRecords.getPatientList when it shouldn't be");  
        assertArrayEquals(weightsCopy, weights, 
                "weights modified in PatientRecords.getPatientList when it shouldn't be");
        assertArrayEquals(temperaturesCopy, temperatures, 
                "temperatures modified in PatientRecords.getPatientList when it shouldn't be");
        
    }

    /**
     * Test getPatientList - only one patient added
     */
    @Test
    public void testGetPatientList4() {
        
        int[] ids = {1001};
        String[] names = {"Amy Apple"};
        String[] birthdates = {"01/11/1111"}; 
        int[] heights = {50};
        int[] weights = {150};
        double[] temperatures = {98.5};
        String[] bloodPressures = {"100/80"};
        int[] idsCopy = {1001};
        String[] namesCopy = {"Amy Apple"}; 
        String[] birthdatesCopy = {"01/11/1111"}; 
        int[] heightsCopy = {50};
        int[] weightsCopy = {150};
        double[] temperaturesCopy = {98.5};
        String[] bloodPressuresCopy = {"100/80"};       
        
        String expected = "1001  Amy Apple          01/11/1111 50 150  98.50  100/80\n";

        String actual = PatientRecords.getPatientList(ids, names, birthdates, heights, weights, 
                                                      temperatures, bloodPressures);
        assertEquals(expected, actual, 
                     "PatientRecords.getPatientList when arrays not full");

        // Check that PatientRecords.getPatientList does not modify arrays
        assertArrayEquals(idsCopy, ids, 
                "ids modified in PatientRecords.getPatientList when it shouldn't be");
        assertArrayEquals(namesCopy, names, 
                "names modified in PatientRecords.getPatientList when it shouldn't be");
        assertArrayEquals(birthdatesCopy, birthdates,
                "birthdates modified in PatientRecords.getPatientList when it shouldn't be");
        assertArrayEquals(heightsCopy, heights, 
                "heights modified in PatientRecords.getPatientList when it shouldn't be");  
        assertArrayEquals(weightsCopy, weights, 
                "weights modified in PatientRecords.getPatientList when it shouldn't be");
        assertArrayEquals(temperaturesCopy, temperatures, 
                "temperatures modified in PatientRecords.getPatientList when it shouldn't be");
    }

    /**
     * Test updatePatient - successful update
     */
    @Test
    public void testupdatePatient1() {
        String input = "1002\n 76\n 189\n 99.2\n 140/80\n";
        Scanner scan = new Scanner(input); //Simulate keyboard input
        int[] ids = {1001, 1002, 1003};
        String[] names = {"Amy Apple","Bill Brook","Carly Cook" }; 
        String[] birthdates = {"01/11/1111", "02/22/2222", "03/33/3333"}; 
        int[] heights = {50, 60, 70};
        int[] weights = {150, 160, 170};
        double[] temperatures = {98.5, 99.0, 100.2};
        String[] bloodPressures = {"100/80", "120/90", "130/95"};
        int[] idsCopy = {1001, 1002, 1003};
        String[] namesCopy = {"Amy Apple","Bill Brook","Carly Cook" }; 
        String[] birthdatesCopy = {"01/11/1111", "02/22/2222", "03/33/3333"};
        int[] heightsUpdate = {50, 76, 70};
        int[] weightsUpdate = {150, 189, 170};
        double[] temperaturesUpdate = {98.5, 99.2, 100.2};
        String[] bloodPressuresUpdate = {"100/80", "140/80", "130/95"};
        String actual = PatientRecords.updatePatient(scan, ids, names, birthdates, heights,
                                                       weights, temperatures,
                                                       bloodPressures);
        assertEquals("Successful update", actual,
                     "PatientRecords.updatePatient(scan, ids, names, heights, weights, " +
                     "temperatures, bloodPressues);");        
        
        assertArrayEquals(idsCopy, ids, 
            "ids Array modified in PatientRecords.updatePatient when it shouldn't be"); 
        assertArrayEquals(namesCopy, names, 
            "names Array modified in PatientRecords.updatePatient when it shouldn't be"); 
        assertArrayEquals(birthdatesCopy, birthdates, 
            "birthdates Array modified in PatientRecords.updatePatient when it shouldn't be"); 
        assertArrayEquals(heightsUpdate, heights, 
            "heights Array incorrectly modified in PatientRecords.updatePatient"); 
        assertArrayEquals(weightsUpdate, weights, 
            "weights Array incorrectly modified in PatientRecords.updatePatient"); 
        assertArrayEquals(temperaturesUpdate, temperatures, 
            "temperatures Array incorrectly modified in PatientRecords.updatePatient"); 
        assertArrayEquals(bloodPressuresUpdate, bloodPressures, 
            "bloodpressures Array modified incorrectly in PatientRecords.updatePatient"); 
    }
 
    /**
     * Test updatePatient - Invalid id - non-integer
     */ 
    @Test
    public void testupdatePatient2() {
        String input = "id\n 76\n 189\n 99.2\n 140/80\n";
        Scanner scan = new Scanner(input); //Simulate keyboard input
        int[] ids = {1001, 1002, 1003};
        String[] names = {"Amy Apple","Bill Brook","Carly Cook" }; 
        String[] birthdates = {"01/11/1111", "02/22/2222", "03/33/3333"}; 
        int[] heights = {50, 60, 70};
        int[] weights = {150, 160, 170};
        double[] temperatures = {98.5, 99.0, 100.2};
        String[] bloodPressures = {"100/80", "120/90", "130/95"};
        int[] idsCopy = {1001, 1002, 1003};
        String[] namesCopy = {"Amy Apple","Bill Brook","Carly Cook" }; 
        String[] birthdatesCopy = {"01/11/1111", "02/22/2222", "03/33/3333"};
        int[] heightsCopy = {50, 60, 70};
        int[] weightsCopy = {150, 160, 170};
        double[] temperaturesCopy = {98.5, 99.0, 100.2};
        String[] bloodPressuresCopy = {"100/80", "120/90", "130/95"};        

        String actual = PatientRecords.updatePatient(scan, ids, names, birthdates, heights,
                                                       weights, temperatures,
                                                       bloodPressures);
        assertEquals("Invalid id", actual,
                     "PatientRecords.updatePatient with non-integer id");        
        
        assertArrayEquals(idsCopy, ids, 
            "ids Array modified in PatientRecords.updatePatient when it shouldn't be"); 
        assertArrayEquals(namesCopy, names, 
            "names Array modified in PatientRecords.updatePatient when it shouldn't be"); 
        assertArrayEquals(birthdatesCopy, birthdates,
            "birthdates Array modified in PatientRecords.updatePatient when it shouldn't be"); 
        assertArrayEquals(heightsCopy, heights,
            "heights Array modified in PatientRecords.updatePatient when it shouldn't be"); 
        assertArrayEquals(weightsCopy, weights,
            "weights Array modified in PatientRecords.updatePatient when it shouldn't be"); 
        assertArrayEquals(temperaturesCopy, temperatures, 
            "temperatures Array modified in PatientRecords.updatePatient when it shouldn't be"); 
        assertArrayEquals(bloodPressuresCopy, bloodPressures,
            "bloodPressures Array modified in PatientRecords.updatePatient when it shouldn't be"); 
    }

    /**
     * Test updatePatient - Invalid id - 0
     */
    @Test
    public void testupdatePatient3() {
        String input = "0\n 76\n 189\n 99.2\n 140/80\n";
        Scanner scan = new Scanner(input); //Simulate keyboard input
        int[] ids = {1001, 1002, 1003};
        String[] names = {"Amy Apple","Bill Brook","Carly Cook" }; 
        String[] birthdates = {"01/11/1111", "02/22/2222", "03/33/3333"}; 
        int[] heights = {50, 60, 70};
        int[] weights = {150, 160, 170};
        double[] temperatures = {98.5, 99.0, 100.2};
        String[] bloodPressures = {"100/80", "120/90", "130/95"};
        int[] idsCopy = {1001, 1002, 1003};
        String[] namesCopy = {"Amy Apple","Bill Brook","Carly Cook" }; 
        String[] birthdatesCopy = {"01/11/1111", "02/22/2222", "03/33/3333"};
        int[] heightsCopy = {50, 60, 70};
        int[] weightsCopy = {150, 160, 170};
        double[] temperaturesCopy = {98.5, 99.0, 100.2};
        String[] bloodPressuresCopy = {"100/80", "120/90", "130/95"};        

        String actual = PatientRecords.updatePatient(scan, ids, names, birthdates, heights,
                                                       weights, temperatures,
                                                       bloodPressures);
        assertEquals("Invalid id", actual,
                     "PatientRecords.updatePatient with 0 id");        
        
        assertArrayEquals(idsCopy, ids, 
            "ids Array modified in PatientRecords.updatePatient when it shouldn't be"); 
        assertArrayEquals(namesCopy, names, 
            "names Array modified in PatientRecords.updatePatient when it shouldn't be"); 
        assertArrayEquals(birthdatesCopy, birthdates,
            "birthdates Array modified in PatientRecords.updatePatient when it shouldn't be"); 
        assertArrayEquals(heightsCopy, heights,
            "heights Array modified in PatientRecords.updatePatient when it shouldn't be"); 
        assertArrayEquals(weightsCopy, weights,
            "weights Array modified in PatientRecords.updatePatient when it shouldn't be"); 
        assertArrayEquals(temperaturesCopy, temperatures, 
            "temperatures Array modified in PatientRecords.updatePatient when it shouldn't be"); 
        assertArrayEquals(bloodPressuresCopy, bloodPressures,
            "bloodPressures Array modified in PatientRecords.updatePatient when it shouldn't be"); 
    } 


    /**
     * Test updatePatient - non-integer height (Invalid value)
     */
    @Test
    public void testupdatePatient4() {
        String input = "1002\n 65.5\n 189\n 99.2\n 140/80\n";
        Scanner scan = new Scanner(input); //Simulate keyboard input
        int[] ids = {1001, 1002, 1003};
        String[] names = {"Amy Apple","Bill Brook","Carly Cook" }; 
        String[] birthdates = {"01/11/1111", "02/22/2222", "03/33/3333"}; 
        int[] heights = {50, 60, 70};
        int[] weights = {150, 160, 170};
        double[] temperatures = {98.5, 99.0, 100.2};
        String[] bloodPressures = {"100/80", "120/90", "130/95"};
        int[] idsCopy = {1001, 1002, 1003};
        String[] namesCopy = {"Amy Apple","Bill Brook","Carly Cook" }; 
        String[] birthdatesCopy = {"01/11/1111", "02/22/2222", "03/33/3333"};
        int[] heightsCopy = {50, 60, 70};
        int[] weightsCopy = {150, 160, 170};
        double[] temperaturesCopy = {98.5, 99.0, 100.2};
        String[] bloodPressuresCopy = {"100/80", "120/90", "130/95"};        

        String actual = PatientRecords.updatePatient(scan, ids, names, birthdates, heights,
                                                       weights, temperatures,
                                                       bloodPressures);
        assertEquals("Invalid value", actual,
                     "PatientRecords.updatePatient with non-integer height");        
        
        assertArrayEquals(idsCopy, ids, 
            "ids Array modified in PatientRecords.updatePatient when it shouldn't be"); 
        assertArrayEquals(namesCopy, names, 
            "names Array modified in PatientRecords.updatePatient when it shouldn't be"); 
        assertArrayEquals(birthdatesCopy, birthdates,
            "birthdates Array modified in PatientRecords.updatePatient when it shouldn't be"); 
        assertArrayEquals(heightsCopy, heights,
            "heights Array modified in PatientRecords.updatePatient when it shouldn't be"); 
        assertArrayEquals(weightsCopy, weights,
            "weights Array modified in PatientRecords.updatePatient when it shouldn't be"); 
        assertArrayEquals(temperaturesCopy, temperatures, 
            "temperatures Array modified in PatientRecords.updatePatient when it shouldn't be"); 
        assertArrayEquals(bloodPressuresCopy, bloodPressures,
            "bloodPressures Array modified in PatientRecords.updatePatient when it shouldn't be");  
    } 


    /**
     * Test updatePatient - Invalid value - non-numeric temperature
     */
    @Test
    public void testupdatePatient5() {
        String input = "1002\n 76\n 189\n ?\n 140/80\n";
        Scanner scan = new Scanner(input); //Simulate keyboard input
        int[] ids = {1001, 1002, 1003};
        String[] names = {"Amy Apple","Bill Brook","Carly Cook" }; 
        String[] birthdates = {"01/11/1111", "02/22/2222", "03/33/3333"}; 
        int[] heights = {50, 60, 70};
        int[] weights = {150, 160, 170};
        double[] temperatures = {98.5, 99.0, 100.2};
        String[] bloodPressures = {"100/80", "120/90", "130/95"};
        int[] idsCopy = {1001, 1002, 1003};
        String[] namesCopy = {"Amy Apple","Bill Brook","Carly Cook" }; 
        String[] birthdatesCopy = {"01/11/1111", "02/22/2222", "03/33/3333"};
        int[] heightsCopy = {50, 60, 70};
        int[] weightsCopy = {150, 160, 170};
        double[] temperaturesCopy = {98.5, 99.0, 100.2};
        String[] bloodPressuresCopy = {"100/80", "120/90", "130/95"};        

        String actual = PatientRecords.updatePatient(scan, ids, names, birthdates, heights,
                                                       weights, temperatures,
                                                       bloodPressures);
        assertEquals("Invalid value", actual,
                     "PatientRecords.updatePatient with non-numeric temperature");        
        
        assertArrayEquals(idsCopy, ids, 
            "ids Array modified in PatientRecords.updatePatient when it shouldn't be"); 
        assertArrayEquals(namesCopy, names, 
            "names Array modified in PatientRecords.updatePatient when it shouldn't be"); 
        assertArrayEquals(birthdatesCopy, birthdates,
            "birthdates Array modified in PatientRecords.updatePatient when it shouldn't be"); 
        assertArrayEquals(heightsCopy, heights,
            "heights Array modified in PatientRecords.updatePatient when it shouldn't be"); 
        assertArrayEquals(weightsCopy, weights,
            "weights Array modified in PatientRecords.updatePatient when it shouldn't be"); 
        assertArrayEquals(temperaturesCopy, temperatures, 
            "temperatures Array modified in PatientRecords.updatePatient when it shouldn't be"); 
        assertArrayEquals(bloodPressuresCopy, bloodPressures,
            "bloodPressures Array modified in PatientRecords.updatePatient when it shouldn't be");  
    }

    /**
     * Test updatePatient - Invalid value - patient ID that does not exist
     */
    @Test
    public void testupdatePatient6() {
        String input = "567\n 76\n 189\n 98.7\n 140/80\n";
        Scanner scan = new Scanner(input); //Simulate keyboard input
        int[] ids = {1001, 1002, 1003};
        String[] names = {"Amy Apple","Bill Brook","Carly Cook" }; 
        String[] birthdates = {"01/11/1111", "02/22/2222", "03/33/3333"}; 
        int[] heights = {50, 60, 70};
        int[] weights = {150, 160, 170};
        double[] temperatures = {98.5, 99.0, 100.2};
        String[] bloodPressures = {"100/80", "120/90", "130/95"};
        int[] idsCopy = {1001, 1002, 1003};
        String[] namesCopy = {"Amy Apple","Bill Brook","Carly Cook" }; 
        String[] birthdatesCopy = {"01/11/1111", "02/22/2222", "03/33/3333"};
        int[] heightsCopy = {50, 60, 70};
        int[] weightsCopy = {150, 160, 170};
        double[] temperaturesCopy = {98.5, 99.0, 100.2};
        String[] bloodPressuresCopy = {"100/80", "120/90", "130/95"};        

        String actual = PatientRecords.updatePatient(scan, ids, names, birthdates, heights,
                                                       weights, temperatures,
                                                       bloodPressures);
        assertEquals("Invalid id", actual,
                     "PatientRecords.updatePatient with non-numeric temperature");        
        
        assertArrayEquals(idsCopy, ids, 
            "ids Array modified in PatientRecords.updatePatient when it shouldn't be"); 
        assertArrayEquals(namesCopy, names, 
            "names Array modified in PatientRecords.updatePatient when it shouldn't be"); 
        assertArrayEquals(birthdatesCopy, birthdates,
            "birthdates Array modified in PatientRecords.updatePatient when it shouldn't be"); 
        assertArrayEquals(heightsCopy, heights,
            "heights Array modified in PatientRecords.updatePatient when it shouldn't be"); 
        assertArrayEquals(weightsCopy, weights,
            "weights Array modified in PatientRecords.updatePatient when it shouldn't be"); 
        assertArrayEquals(temperaturesCopy, temperatures, 
            "temperatures Array modified in PatientRecords.updatePatient when it shouldn't be"); 
        assertArrayEquals(bloodPressuresCopy, bloodPressures,
            "bloodPressures Array modified in PatientRecords.updatePatient when it shouldn't be");  
    }    

    /**
     * Test updatePatient - Invalid value - non-integer weight
     */
    @Test
    public void testupdatePatient7() {
        String input = "1002\n 76\n abc\n 98.7\n 140/80\n";
        Scanner scan = new Scanner(input); //Simulate keyboard input
        int[] ids = {1001, 1002, 1003};
        String[] names = {"Amy Apple","Bill Brook","Carly Cook" }; 
        String[] birthdates = {"01/11/1111", "02/22/2222", "03/33/3333"}; 
        int[] heights = {50, 60, 70};
        int[] weights = {150, 160, 170};
        double[] temperatures = {98.5, 99.0, 100.2};
        String[] bloodPressures = {"100/80", "120/90", "130/95"};
        int[] idsCopy = {1001, 1002, 1003};
        String[] namesCopy = {"Amy Apple","Bill Brook","Carly Cook" }; 
        String[] birthdatesCopy = {"01/11/1111", "02/22/2222", "03/33/3333"};
        int[] heightsCopy = {50, 60, 70};
        int[] weightsCopy = {150, 160, 170};
        double[] temperaturesCopy = {98.5, 99.0, 100.2};
        String[] bloodPressuresCopy = {"100/80", "120/90", "130/95"};        

        String actual = PatientRecords.updatePatient(scan, ids, names, birthdates, heights,
                                                       weights, temperatures,
                                                       bloodPressures);
        assertEquals("Invalid value", actual,
                     "PatientRecords.updatePatient with non-numeric temperature");        
        
        assertArrayEquals(idsCopy, ids, 
            "ids Array modified in PatientRecords.updatePatient when it shouldn't be"); 
        assertArrayEquals(namesCopy, names, 
            "names Array modified in PatientRecords.updatePatient when it shouldn't be"); 
        assertArrayEquals(birthdatesCopy, birthdates,
            "birthdates Array modified in PatientRecords.updatePatient when it shouldn't be"); 
        assertArrayEquals(heightsCopy, heights,
            "heights Array modified in PatientRecords.updatePatient when it shouldn't be"); 
        assertArrayEquals(weightsCopy, weights,
            "weights Array modified in PatientRecords.updatePatient when it shouldn't be"); 
        assertArrayEquals(temperaturesCopy, temperatures, 
            "temperatures Array modified in PatientRecords.updatePatient when it shouldn't be"); 
        assertArrayEquals(bloodPressuresCopy, bloodPressures,
            "bloodPressures Array modified in PatientRecords.updatePatient when it shouldn't be");  
    }

    /**
     * Test addPatient - successful
     */
    @Test
    public void testAddPatient1() {
        String input = "\nDonald Duck\n 04/44/4444\n 76\n 189\n 99.2\n 140/80\n";
        Scanner scan = new Scanner(input); //Simulate keyboard input
        int[] ids = {1001, 1002, 1003, 0, 0};
        String[] names = {"Amy Apple","Bill Brook","Carly Cook", null, null }; 
        String[] birthdates = {"01/11/1111", "02/22/2222", "03/33/3333", null, null}; 
        int[] heights = {50, 60, 70, 0, 0};
        int[] weights = {150, 160, 170, 0, 0};
        double[] temperatures = {98.5, 99.0, 100.2, 0.0, 0.0};
        String[] bloodPressures = {"100/80", "120/90", "130/95", null, null};
        int[] idsUpdate = {1001, 1002, 1003, 1004, 0};
        String[] namesUpdate = {"Amy Apple","Bill Brook","Carly Cook", "Donald Duck", null}; 
        String[] birthdatesUpdate = {"01/11/1111", "02/22/2222", "03/33/3333", "04/44/4444", null}; 
        int[] heightsUpdate = {50, 60, 70, 76, 0};
        int[] weightsUpdate = {150, 160, 170, 189, 0};
        double[] temperaturesUpdate = {98.5, 99.0, 100.2, 99.2, 0.0};
        String[] bloodPressuresUpdate = {"100/80", "120/90", "130/95", "140/80", null};
        String actual = PatientRecords.addPatient(scan, ids, names, birthdates, heights,
                                                     weights, temperatures,
                                                     bloodPressures);
        assertEquals("Successful addition", actual,
                     "PatientRecords.addPatient(scan, ids, names, heights, weights, " +
                     "temperatures, bloodPressues);");        
        
        assertArrayEquals(idsUpdate, ids,
            "ids Array incorrectly modified in PatientRecords.addPatient"); 
        assertArrayEquals(namesUpdate, names, 
            "names Array incorrectly modified in PatientRecords.addPatient"); 
        assertArrayEquals(birthdatesUpdate, birthdates, 
            "birthdates Array incorrectly modified in PatientRecords.addPatient"); 
        assertArrayEquals(heightsUpdate, heights, 
            "heights Array incorrectly modified in PatientRecords.addPatient"); 
        assertArrayEquals(weightsUpdate, weights, 
            "weights Array incorrectly modified in PatientRecords.addPatient"); 
        assertArrayEquals(temperaturesUpdate, temperatures, 
            "temperatures Array incorrectly modified in PatientRecords.addPatient"); 
        assertArrayEquals(bloodPressuresUpdate, bloodPressures, 
            "bloodpressures Array modified incorrectly in PatientRecords.addPatient"); 
    }

    /**
     * Test addPatient - non-integer weight (Invalid value)
     */    
    @Test
    public void testAddPatient2() {
        String input = "\nDonald Duck\n 04/44/4444\n 76\n NA\n 99.2\n 140/80\n";
        Scanner scan = new Scanner(input); //Simulate keyboard input
        int[] ids = {1001, 1002, 1003, 0, 0};
        String[] names = {"Amy Apple","Bill Brook","Carly Cook", null, null }; 
        String[] birthdates = {"01/11/1111", "02/22/2222", "03/33/3333", null, null}; 
        int[] heights = {50, 60, 70, 0, 0};
        int[] weights = {150, 160, 170, 0, 0};
        double[] temperatures = {98.5, 99.0, 100.2, 0.0, 0.0};
        String[] bloodPressures = {"100/80", "120/90", "130/95", null, null};
        int[] idsCopy = {1001, 1002, 1003, 0, 0};
        String[] namesCopy = {"Amy Apple","Bill Brook","Carly Cook", null, null }; 
        String[] birthdatesCopy = {"01/11/1111", "02/22/2222", "03/33/3333", null, null}; 
        int[] heightsCopy = {50, 60, 70, 0, 0};
        int[] weightsCopy = {150, 160, 170, 0, 0};
        double[] temperaturesCopy = {98.5, 99.0, 100.2, 0.0, 0.0};
        String[] bloodPressuresCopy = {"100/80", "120/90", "130/95", null, null};
        String actual = PatientRecords.addPatient(scan, ids, names, birthdates, heights,
                                                     weights, temperatures,
                                                     bloodPressures);
        assertEquals("Invalid value", actual,
                     "PatientRecords.addPatient with non-integer weight");        
        
        assertArrayEquals(idsCopy, ids, 
            "ids Array modified in PatientRecords.addPatient when it shouldn't be"); 
        assertArrayEquals(namesCopy, names, 
            "names Array modified in PatientRecords.addPatient when it shouldn't be"); 
        assertArrayEquals(birthdatesCopy, birthdates,
            "birthdates Array modified in PatientRecords.addPatient when it shouldn't be"); 
        assertArrayEquals(heightsCopy, heights,
            "heights Array modified in PatientRecords.addPatient when it shouldn't be"); 
        assertArrayEquals(weightsCopy, weights,
            "weights Array modified in PatientRecords.addPatient when it shouldn't be"); 
        assertArrayEquals(temperaturesCopy, temperatures, 
            "temperatures Array modified in PatientRecords.addPatient when it shouldn't be"); 
        assertArrayEquals(bloodPressuresCopy, bloodPressures,
            "bloodPressures Array modified in PatientRecords.addPatient when it shouldn't be");  
    }

    /**
     * Test addPatient - non-numeric temperature (Invalid value)
     */ 
    @Test
    public void testAddPatient3() {
        String input = "\nDonald Duck\n 04/44/4444\n 76\n 189\n fever\n 140/80\n";
        Scanner scan = new Scanner(input); //Simulate keyboard input
        int[] ids = {1001, 1002, 1003, 0, 0};
        String[] names = {"Amy Apple","Bill Brook","Carly Cook", null, null }; 
        String[] birthdates = {"01/11/1111", "02/22/2222", "03/33/3333", null, null}; 
        int[] heights = {50, 60, 70, 0, 0};
        int[] weights = {150, 160, 170, 0, 0};
        double[] temperatures = {98.5, 99.0, 100.2, 0.0, 0.0};
        String[] bloodPressures = {"100/80", "120/90", "130/95", null, null};
        int[] idsCopy = {1001, 1002, 1003, 0, 0};
        String[] namesCopy = {"Amy Apple","Bill Brook","Carly Cook", null, null }; 
        String[] birthdatesCopy = {"01/11/1111", "02/22/2222", "03/33/3333", null, null}; 
        int[] heightsCopy = {50, 60, 70, 0, 0};
        int[] weightsCopy = {150, 160, 170, 0, 0};
        double[] temperaturesCopy = {98.5, 99.0, 100.2, 0.0, 0.0};
        String[] bloodPressuresCopy = {"100/80", "120/90", "130/95", null, null};
        String actual = PatientRecords.addPatient(scan, ids, names, birthdates, heights,
                                                     weights, temperatures,
                                                     bloodPressures);
        assertEquals("Invalid value", actual,
                     "PatientRecords.addPatient with non-numeric temperature");        
        
        assertArrayEquals(idsCopy, ids, 
            "ids Array modified in PatientRecords.addPatient when it shouldn't be"); 
        assertArrayEquals(namesCopy, names, 
            "names Array modified in PatientRecords.addPatient when it shouldn't be"); 
        assertArrayEquals(birthdatesCopy, birthdates,
            "birthdates Array modified in PatientRecords.addPatient when it shouldn't be"); 
        assertArrayEquals(heightsCopy, heights,
            "heights Array modified in PatientRecords.addPatient when it shouldn't be"); 
        assertArrayEquals(weightsCopy, weights,
            "weights Array modified in PatientRecords.addPatient when it shouldn't be"); 
        assertArrayEquals(temperaturesCopy, temperatures, 
            "temperatures Array modified in PatientRecords.addPatient when it shouldn't be"); 
        assertArrayEquals(bloodPressuresCopy, bloodPressures,
            "bloodPressures Array modified in PatientRecords.addPatient when it shouldn't be"); 
    }  

    /**
     * Test addPatient - full array, cannot create a new patient
     */ 
    @Test
    public void testAddPatient4() {
        String input = "\nDonald Duck\n 04/44/4444\n 76\n 189\n fever\n 140/80\n";
        Scanner scan = new Scanner(input); //Simulate keyboard input
        int[] ids = {1001, 1002, 1003};
        String[] names = {"Amy Apple","Bill Brook","Carly Cook"}; 
        String[] birthdates = {"01/11/1111", "02/22/2222", "03/33/3333"}; 
        int[] heights = {50, 60, 70};
        int[] weights = {150, 160, 170};
        double[] temperatures = {98.5, 99.0, 100.2};
        String[] bloodPressures = {"100/80", "120/90", "130/95"};
        int[] idsCopy = {1001, 1002, 1003};
        String[] namesCopy = {"Amy Apple","Bill Brook","Carly Cook"}; 
        String[] birthdatesCopy = {"01/11/1111", "02/22/2222", "03/33/3333"}; 
        int[] heightsCopy = {50, 60, 70};
        int[] weightsCopy = {150, 160, 170};
        double[] temperaturesCopy = {98.5, 99.0, 100.2};
        String[] bloodPressuresCopy = {"100/80", "120/90", "130/95"};
        String actual = PatientRecords.addPatient(scan, ids, names, birthdates, heights,
                                                     weights, temperatures,
                                                     bloodPressures);
        assertEquals("Cannot add new patient", actual,
                     "PatientRecords.addPatient with non-numeric temperature");        
        
        assertArrayEquals(idsCopy, ids, 
            "ids Array modified in PatientRecords.addPatient when it shouldn't be"); 
        assertArrayEquals(namesCopy, names, 
            "names Array modified in PatientRecords.addPatient when it shouldn't be"); 
        assertArrayEquals(birthdatesCopy, birthdates,
            "birthdates Array modified in PatientRecords.addPatient when it shouldn't be"); 
        assertArrayEquals(heightsCopy, heights,
            "heights Array modified in PatientRecords.addPatient when it shouldn't be"); 
        assertArrayEquals(weightsCopy, weights,
            "weights Array modified in PatientRecords.addPatient when it shouldn't be"); 
        assertArrayEquals(temperaturesCopy, temperatures, 
            "temperatures Array modified in PatientRecords.addPatient when it shouldn't be"); 
        assertArrayEquals(bloodPressuresCopy, bloodPressures,
            "bloodPressures Array modified in PatientRecords.addPatient when it shouldn't be"); 
    }  
    
    /**
     * Test addPatient - non-integer height value
     */ 
    @Test
    public void testAddPatient5() {
        String input = "\nDonald Duck\n 04/44/4444\n rg\n 189\n 101.2\n 140/80\n";
        Scanner scan = new Scanner(input); //Simulate keyboard input
        int[] ids = {1001, 1002, 1003, 0, 0};
        String[] names = {"Amy Apple","Bill Brook","Carly Cook", null, null }; 
        String[] birthdates = {"01/11/1111", "02/22/2222", "03/33/3333", null, null}; 
        int[] heights = {50, 60, 70, 0, 0};
        int[] weights = {150, 160, 170, 0, 0};
        double[] temperatures = {98.5, 99.0, 100.2, 0.0, 0.0};
        String[] bloodPressures = {"100/80", "120/90", "130/95", null, null};
        int[] idsCopy = {1001, 1002, 1003, 0, 0};
        String[] namesCopy = {"Amy Apple","Bill Brook","Carly Cook", null, null }; 
        String[] birthdatesCopy = {"01/11/1111", "02/22/2222", "03/33/3333", null, null}; 
        int[] heightsCopy = {50, 60, 70, 0, 0};
        int[] weightsCopy = {150, 160, 170, 0, 0};
        double[] temperaturesCopy = {98.5, 99.0, 100.2, 0.0, 0.0};
        String[] bloodPressuresCopy = {"100/80", "120/90", "130/95", null, null};
        String actual = PatientRecords.addPatient(scan, ids, names, birthdates, heights,
                                                     weights, temperatures,
                                                     bloodPressures);
        assertEquals("Invalid value", actual,
                     "PatientRecords.addPatient with non-numeric temperature");        
        
        assertArrayEquals(idsCopy, ids, 
            "ids Array modified in PatientRecords.addPatient when it shouldn't be"); 
        assertArrayEquals(namesCopy, names, 
            "names Array modified in PatientRecords.addPatient when it shouldn't be"); 
        assertArrayEquals(birthdatesCopy, birthdates,
            "birthdates Array modified in PatientRecords.addPatient when it shouldn't be"); 
        assertArrayEquals(heightsCopy, heights,
            "heights Array modified in PatientRecords.addPatient when it shouldn't be"); 
        assertArrayEquals(weightsCopy, weights,
            "weights Array modified in PatientRecords.addPatient when it shouldn't be"); 
        assertArrayEquals(temperaturesCopy, temperatures, 
            "temperatures Array modified in PatientRecords.addPatient when it shouldn't be"); 
        assertArrayEquals(bloodPressuresCopy, bloodPressures,
            "bloodPressures Array modified in PatientRecords.addPatient when it shouldn't be"); 
    }
     
  
    /**
     * Test the PatientRecords methods with invalid values
     */
    @Test
    public void testInvalidMethods() {

        // Invalid test cases are provided for you below - You do NOT
        // need to add additional invalid tests. Just make sure these
        // pass!
       
        int[] ids = {1001, 1002, 1003};
        String[] names = {"Amy Apple","Bill Brook","Carly Cook"}; 
        String[] birthdates = {"01/11/1111", "02/22/2222", "03/33/3333"}; 
        int[] heights = {50, 60, 70};
        int[] weights = {150, 160, 170};
        double[] temperatures = {98.5, 99.0, 100.2};
        String[] bloodPressures = {"100/80", "120/90", "130/95"};
        int[] idsCopy = {1001, 1002, 1003};
        String[] namesCopy = {"Amy Apple","Bill Brook","Carly Cook" }; 
        String[] birthdatesCopy = {"01/11/1111", "02/22/2222", "03/33/3333"}; 
        int[] heightsCopy = {50, 60, 70};
        int[] weightsCopy = {150, 160, 170};
        double[] temperaturesCopy = {98.5, 99.0, 100.2};
        String[] bloodPressuresCopy = {"100/80", "120/90", "130/95"};  
        String input = "1002\n 76\n 189\n 99.2\n 140/80\n";
        Scanner scan = new Scanner(input); //Simulate keyboard input

        int[] idsLengthBad = new int[2];
        String[] namesLengthBad = {"", "", "", ""}; 
        String[] birthdatesLengthBad = {"", "", "", "", "", ""}; 
        int[] heightsLengthBad = new int[4];
        int[] weightsLengthBad = new int[5];
        double[] temperaturesLengthBad = new double[0];
        String[] bloodPressuresLengthBad = {""};        
        int[] idsLengthBadCopy = new int[2];
        String[] namesLengthBadCopy = {"", "", "", ""};  
        String[] birthdatesLengthBadCopy = {"", "", "", "", "", ""};  
        int[] heightsLengthBadCopy = new int[4];
        int[] weightsLengthBadCopy = new int[5];
        double[] temperaturesLengthBadCopy = new double[0];
        String[] bloodPressuresLengthBadCopy = {""};          

        // Testing null arrays getPatientList
        Exception exception = assertThrows(IllegalArgumentException.class,
            () -> PatientRecords.getPatientList(null, names, birthdates, heights, weights, 
                                                temperatures, bloodPressures),
                "PatientRecords.getPatientList(null, names, birthdates, heights, weights, " +
                                               "temperatures, bloodPressures);");
        assertEquals("Null array", exception.getMessage(),
                "Testing PatientRecords.getPatientList(null, names, birthdates, heights, " + 
                             "weights, temperatures, bloodPressures); - exception message");

        // Test null names array
        exception = assertThrows(IllegalArgumentException.class,
            () -> PatientRecords.getPatientList(ids, null, birthdates, heights, weights, 
                                                temperatures, bloodPressures),
                "PatientRecords.getPatientList(ids, null, birthdates, heights, weights, " +
                                               "temperatures, bloodPressures);");
        assertEquals("Null array", exception.getMessage(),
                "Testing PatientRecords.getPatientList(ids, null, birthdates, heights, " + 
                             "weights, temperatures, bloodPressures); - exception message");
                             
        // Test null birthdates array
        exception = assertThrows(IllegalArgumentException.class,
            () -> PatientRecords.getPatientList(ids, names, null, heights, weights, 
                                                temperatures, bloodPressures),
                "PatientRecords.getPatientList(ids, names, null, heights, weights, " +
                                               "temperatures, bloodPressures);");
        assertEquals("Null array", exception.getMessage(),
                "Testing PatientRecords.getPatientList(ids, names, null, heights, " + 
                             "weights, temperatures, bloodPressures); - exception message");
                             
        // Test null heights array
        exception = assertThrows(IllegalArgumentException.class,
            () -> PatientRecords.getPatientList(ids, names, birthdates, null, weights, 
                                                temperatures, bloodPressures),
                "PatientRecords.getPatientList(ids, names, birthdates, null, weights, " +
                                               "temperatures, bloodPressures);");
        assertEquals("Null array", exception.getMessage(),
                "Testing PatientRecords.getPatientList(ids, names, birthdates, null " + 
                             "weights, temperatures, bloodPressures); - exception message");  

        // Test null weights array
        exception = assertThrows(IllegalArgumentException.class,
            () -> PatientRecords.getPatientList(ids, names, birthdates, heights, null, 
                                                temperatures, bloodPressures),
                "PatientRecords.getPatientList(ids, names, birthdates, heights, null, " +
                                               "temperatures, bloodPressures);");
        assertEquals("Null array", exception.getMessage(),
                "Testing PatientRecords.getPatientList(ids, names, birthdates, heights " + 
                             "null, temperatures, bloodPressures); - exception message");   

        // Test null temperatures array
        exception = assertThrows(IllegalArgumentException.class,
            () -> PatientRecords.getPatientList(ids, names, birthdates, heights, weights, 
                                                null, bloodPressures),
                "PatientRecords.getPatientList(ids, names, birthdates, heights, weights, " +
                                               "null, bloodPressures);");
        assertEquals("Null array", exception.getMessage(),
                "Testing PatientRecords.getPatientList(ids, names, birthdates, heights " + 
                             "weights, null, bloodPressures); - exception message");  
 
        // Test null bloodPressures array
        exception = assertThrows(IllegalArgumentException.class,
            () -> PatientRecords.getPatientList(ids, names, birthdates, heights, weights, 
                                                temperatures, null),
                "PatientRecords.getPatientList(ids, names, birthdates, heights, weights, " +
                                               "temperatures, null);");
        assertEquals("Null array", exception.getMessage(),
                "Testing PatientRecords.getPatientList(ids, names, birthdates, heights " + 
                             "weights, temperatures, null); - exception message");  
                             
        assertArrayEquals(idsCopy, ids,
                "ids Array modified in PatientRecords.getPatientList when it shouldn't be");  
        assertArrayEquals(namesCopy, names,
                "names Array modified in PatientRecords.getPatientList when it shouldn't be");
        assertArrayEquals(birthdatesCopy, birthdates,
               "birthdates Array modified in PatientRecords.getPatientList when it shouldn't be");
        assertArrayEquals(heightsCopy, heights,
                "heights Array modified in PatientRecords.getPatientList when it shouldn't be");
        assertArrayEquals(weightsCopy, weights,
                "weights Array modified in PatientRecords.getPatientList when it shouldn't be"); 
        assertArrayEquals(temperaturesCopy, temperatures,
              "temperatures Array modified in PatientRecords.getPatientList when it shouldn't be");
        assertArrayEquals(bloodPressuresCopy, bloodPressures,
            "bloodPressures Array modified in PatientRecords.getPatientList when it shouldn't be"); 

        // test null arrays updatePatient

        exception = assertThrows(IllegalArgumentException.class,
            () -> PatientRecords.updatePatient(scan, null, names, birthdates, heights, weights, 
                                                temperatures, bloodPressures),
                "PatientRecords.updatePatient(scan, null, names, birthdates, heights, weights, " +
                                               "temperatures, bloodPressures);");
        assertEquals("Null array", exception.getMessage(),
                "Testing PatientRecords.updatePatient(scan, null, names, birthdates, heights, " + 
                             "weights, temperatures, bloodPressures); - exception message"); 

        // Test null names array
        exception = assertThrows(IllegalArgumentException.class,
            () -> PatientRecords.updatePatient(scan, ids, null, birthdates, heights, weights, 
                                                temperatures, bloodPressures),
                "PatientRecords.updatePatient(scan, ids, null, birthdates, heights, weights, " +
                                               "temperatures, bloodPressures);");
        assertEquals("Null array", exception.getMessage(),
                "Testing PatientRecords.updatePatient(scan, ids, null, birthdates, heights, " + 
                             "weights, temperatures, bloodPressures); - exception message");
                             
        // Test null birthdates array
        exception = assertThrows(IllegalArgumentException.class,
            () -> PatientRecords.updatePatient(scan, ids, names, null, heights, weights, 
                                                temperatures, bloodPressures),
                "PatientRecords.updatePatient(scan, ids, names, null, heights, weights, " +
                                               "temperatures, bloodPressures);");
        assertEquals("Null array", exception.getMessage(),
                "Testing PatientRecords.updatePatient(scan, ids, names, null, heights, " + 
                             "weights, temperatures, bloodPressures); - exception message");
                             
        // Test null heights array
        exception = assertThrows(IllegalArgumentException.class,
            () -> PatientRecords.updatePatient(scan, ids, names, birthdates, null, weights, 
                                                temperatures, bloodPressures),
                "PatientRecords.updatePatient(scan, ids, names, birthdates, null, weights, " +
                                               "temperatures, bloodPressures);");
        assertEquals("Null array", exception.getMessage(),
                "Testing PatientRecords.updatePatient(scan, ids, names, birthdates, null " + 
                             "weights, temperatures, bloodPressures); - exception message");  

        // Test null weights array
        exception = assertThrows(IllegalArgumentException.class,
            () -> PatientRecords.updatePatient(scan, ids, names, birthdates, heights, null, 
                                                temperatures, bloodPressures),
                "PatientRecords.updatePatient(scan, ids, names, birthdates, heights, null, " +
                                               "temperatures, bloodPressures);");
        assertEquals("Null array", exception.getMessage(),
                "Testing PatientRecords.updatePatient(scan, ids, names, birthdates, heights " + 
                             "null, temperatures, bloodPressures); - exception message");   

        // Test null temperatures array
        exception = assertThrows(IllegalArgumentException.class,
            () -> PatientRecords.updatePatient(scan, ids, names, birthdates, heights, weights, 
                                                null, bloodPressures),
                "PatientRecords.updatePatient(scan, ids, names, birthdates, heights, weights, " +
                                               "null, bloodPressures);");
        assertEquals("Null array", exception.getMessage(),
                "Testing PatientRecords.updatePatient(scan, ids, names, birthdates, heights " + 
                             "weights, null, bloodPressures); - exception message"); 
                                   
 
        // Test null bloodPressures array
        exception = assertThrows(IllegalArgumentException.class,
            () -> PatientRecords.updatePatient(scan, ids, names, birthdates, heights, weights, 
                                                temperatures, null),
                "PatientRecords.updatePatient(scan, ids, names, birthdates, heights, weights, " +
                                               "temperatures, null);");
        assertEquals("Null array", exception.getMessage(),
                "Testing PatientRecords.updatePatient(scan, ids, names, birthdates, heights " + 
                             "weights, temperatures, null); - exception message"); 
                             
        assertArrayEquals(idsCopy, ids,
                "ids Array modified in PatientRecords.updatePatient when it shouldn't be"); 
        assertArrayEquals(namesCopy, names,
                "names Array modified in PatientRecords.updatePatient when it shouldn't be");
        assertArrayEquals(birthdatesCopy, birthdates,
               "birthdates Array modified in PatientRecords.updatePatient when it shouldn't be");
        assertArrayEquals(heightsCopy, heights,
                "heights Array modified in PatientRecords.updatePatient when it shouldn't be");
        assertArrayEquals(weightsCopy, weights,
                "weights Array modified in PatientRecords.updatePatient when it shouldn't be"); 
        assertArrayEquals(temperaturesCopy, temperatures,
              "temperatures Array modified in PatientRecords.updatePatient when it shouldn't be");
        assertArrayEquals(bloodPressuresCopy, bloodPressures,
            "bloodPressures Array modified in PatientRecords.updatePatient when it shouldn't be");
                             
                             
        // test null arrays addPatient                     
        exception = assertThrows(IllegalArgumentException.class,
            () -> PatientRecords.addPatient(scan, null, names, birthdates, heights, weights, 
                                                temperatures, bloodPressures),
                "PatientRecords.addPatient(scan, null, names, birthdates, heights, weights, " +
                                               "temperatures, bloodPressures);");
        assertEquals("Null array", exception.getMessage(),
                "Testing PatientRecords.addPatient(scan, null, names, birthdates, heights, " + 
                             "weights, temperatures, bloodPressures); - exception message"); 
                             
        // Test null names array
        exception = assertThrows(IllegalArgumentException.class,
            () -> PatientRecords.addPatient(scan, ids, null, birthdates, heights, weights, 
                                                temperatures, bloodPressures),
                "PatientRecords.addPatient(scan, ids, null, birthdates, heights, weights, " +
                                               "temperatures, bloodPressures);");
        assertEquals("Null array", exception.getMessage(),
                "Testing PatientRecords.addPatient(scan, ids, null, birthdates, heights, " + 
                             "weights, temperatures, bloodPressures); - exception message");
                             
        // Test null birthdates array
        exception = assertThrows(IllegalArgumentException.class,
            () -> PatientRecords.addPatient(scan, ids, names, null, heights, weights, 
                                                temperatures, bloodPressures),
                "PatientRecords.addPatient(scan, ids, names, null, heights, weights, " +
                                               "temperatures, bloodPressures);");
        assertEquals("Null array", exception.getMessage(),
                "Testing PatientRecords.addPatient(scan, ids, names, null, heights, " + 
                             "weights, temperatures, bloodPressures); - exception message");
                             
        // Test null heights array
        exception = assertThrows(IllegalArgumentException.class,
            () -> PatientRecords.addPatient(scan, ids, names, birthdates, null, weights, 
                                                temperatures, bloodPressures),
                "PatientRecords.addPatient(scan, ids, names, birthdates, null, weights, " +
                                               "temperatures, bloodPressures);");
        assertEquals("Null array", exception.getMessage(),
                "Testing PatientRecords.addPatient(scan, ids, names, birthdates, null " + 
                             "weights, temperatures, bloodPressures); - exception message");  

        // Test null weights array
        exception = assertThrows(IllegalArgumentException.class,
            () -> PatientRecords.addPatient(scan, ids, names, birthdates, heights, null, 
                                                temperatures, bloodPressures),
                "PatientRecords.addPatient(scan, ids, names, birthdates, heights, null, " +
                                               "temperatures, bloodPressures);");
        assertEquals("Null array", exception.getMessage(), 
                "Testing PatientRecords.addPatient(scan, ids, names, birthdates, heights " + 
                             "null, temperatures, bloodPressures); - exception message");   

        // Test null temperatures array
        exception = assertThrows(IllegalArgumentException.class,
            () -> PatientRecords.addPatient(scan, ids, names, birthdates, heights, weights, 
                                                null, bloodPressures),
                "PatientRecords.addPatient(scan, ids, names, birthdates, heights, weights, " +
                                               "null, bloodPressures);");
        assertEquals("Null array", exception.getMessage(),
                "Testing PatientRecords.addPatient(scan, ids, names, birthdates, heights " + 
                             "weights, null, bloodPressures); - exception message"); 
                             
        // Test null bloodPressures array
        exception = assertThrows(IllegalArgumentException.class,
            () -> PatientRecords.addPatient(scan, ids, names, birthdates, heights, weights, 
                                                temperatures, null),
                "PatientRecords.addPatient(scan, ids, names, birthdates, heights, weights, " +
                                               "temperatures, null);");
        assertEquals("Null array", exception.getMessage(),
                "Testing PatientRecords.addPatient(scan, ids, names, birthdates, heights " + 
                             "weights, temperatures, null); - exception message");  

        assertArrayEquals(idsCopy, ids,
                "ids Array modified in PatientRecords.addPatient when it shouldn't be"); 
        assertArrayEquals(namesCopy, names,
                "names Array modified in PatientRecords.addPatient when it shouldn't be");
        assertArrayEquals(birthdatesCopy, birthdates,
               "birthdates Array modified in PatientRecords.addPatient when it shouldn't be");
        assertArrayEquals(heightsCopy, heights,
                "heights Array modified in PatientRecords.addPatient when it shouldn't be");
        assertArrayEquals(weightsCopy, weights,
                "weights Array modified in PatientRecords.addPatient when it shouldn't be"); 
        assertArrayEquals(temperaturesCopy, temperatures,
              "temperatures Array modified in PatientRecords.addPatient when it shouldn't be");
        assertArrayEquals(bloodPressuresCopy, bloodPressures,
             "bloodPressures Array modified in PatientRecords.addPatient when it shouldn't be"); 

        // Test invalid array length getPatientList 
        exception = assertThrows(IllegalArgumentException.class,
            () -> PatientRecords.getPatientList(idsLengthBad, names, birthdates, heights, weights, 
                                                temperatures, bloodPressures),
               "PatientRecords.getPatientList(idsLengthBad, names, birthdates, heights, weights," +
                                               " temperatures, bloodPressures);");
        assertEquals("Invalid array length", exception.getMessage(),
               "Testing PatientRecords.getPatientList(idsLengthBad, names, birthdates, heights, " + 
                             "weights, temperatures, bloodPressures); - exception message");  

        exception = assertThrows(IllegalArgumentException.class,
            () -> PatientRecords.getPatientList(ids, namesLengthBad, birthdates, heights, weights, 
                                                temperatures, bloodPressures),
               "PatientRecords.getPatientList(ids, namesLengthBad, birthdates, heights, weights," +
                                               " temperatures, bloodPressures);");
        assertEquals("Invalid array length", exception.getMessage(),
               "Testing PatientRecords.getPatientList(ids, namesLengthBad, birthdates, heights, " + 
                             "weights, temperatures, bloodPressures); - exception message");  

        exception = assertThrows(IllegalArgumentException.class,
            () -> PatientRecords.getPatientList(ids, names, birthdatesLengthBad, heights, weights, 
                                                temperatures, bloodPressures),
               "PatientRecords.getPatientList(ids, names, birthdatesLengthBad, heights, weights," +
                                               " temperatures, bloodPressures);");
        assertEquals("Invalid array length", exception.getMessage(),
               "Testing PatientRecords.getPatientList(ids, names, birthdatesLengthBad, heights, " + 
                             "weights, temperatures, bloodPressures); - exception message"); 

        exception = assertThrows(IllegalArgumentException.class,
            () -> PatientRecords.getPatientList(ids, names, birthdates, heightsLengthBad, weights, 
                                                temperatures, bloodPressures),
               "PatientRecords.getPatientList(ids, names, birthdates, heightsLengthBad, weights," +
                                               " temperatures, bloodPressures);");
        assertEquals("Invalid array length", exception.getMessage(),
                "Testing PatientRecords.getPatientList(ids, names, birthdates, heightsLengthBad," + 
                             " weights, temperatures, bloodPressures); - exception message"); 

        exception = assertThrows(IllegalArgumentException.class,
            () -> PatientRecords.getPatientList(ids, names, birthdates, heights, weightsLengthBad, 
                                                temperatures, bloodPressures),
              "PatientRecords.getPatientList(ids, names, birthdates, heights, weightsLengthBad," +
                                               " temperatures, bloodPressures);");
        assertEquals("Invalid array length", exception.getMessage(),
                     "Testing PatientRecords.getPatientList(ids, names, birthdates, heights, " + 
                     "weightsLengthBad, temperatures, bloodPressures); - exception message");

        exception = assertThrows(IllegalArgumentException.class,
            () -> PatientRecords.getPatientList(ids, names, birthdates, heights, weights, 
                                                temperaturesLengthBad, bloodPressures),
                "PatientRecords.getPatientList(ids, names, birthdates, heights, weights, " +
                                               "temperaturesLengthBad, bloodPressures);");
        assertEquals("Invalid array length", exception.getMessage(),
                     "Testing PatientRecords.getPatientList(ids, names, birthdates, heights, " + 
                     "weights, temperaturesLengthBad, bloodPressures); - exception message"); 

        exception = assertThrows(IllegalArgumentException.class,
            () -> PatientRecords.getPatientList(ids, names, birthdates, heights, weights, 
                                                temperatures, bloodPressuresLengthBad),
                "PatientRecords.getPatientList(ids, names, birthdates, heights, weights, " +
                                               "temperatures, bloodPressuresLengthBad);");
        assertEquals("Invalid array length", exception.getMessage(),
                     "Testing PatientRecords.getPatientList(ids, names, birthdates, heights, " + 
                     "weights, temperatures, bloodPressuresLengthBad); - exception message"); 

        assertArrayEquals(idsCopy, ids,
                "ids Array modified in PatientRecords.getPatientList when it shouldn't be");  
        assertArrayEquals(namesCopy, names,
                "names Array modified in PatientRecords.getPatientList when it shouldn't be");
        assertArrayEquals(birthdatesCopy, birthdates,
               "birthdates Array modified in PatientRecords.getPatientList when it shouldn't be");
        assertArrayEquals(heightsCopy, heights,
                "heights Array modified in PatientRecords.getPatientList when it shouldn't be");
        assertArrayEquals(weightsCopy, weights,
                "weights Array modified in PatientRecords.getPatientList when it shouldn't be"); 
        assertArrayEquals(temperaturesCopy, temperatures,
              "temperatures Array modified in PatientRecords.getPatientList when it shouldn't be");
        assertArrayEquals(bloodPressuresCopy, bloodPressures,
            "bloodPressures Array modified in PatientRecords.getPatientList when it shouldn't be");

        // Test invalid array length updatePatient
        exception = assertThrows(IllegalArgumentException.class,
            () -> PatientRecords.updatePatient(scan, idsLengthBad, names, birthdates, heights,  
                                               weights, temperatures, bloodPressures),
          "PatientRecords.updatePatient(scan, idsLengthBad, names, birthdates, heights, weights," +
                                               " temperatures, bloodPressures);");
        assertEquals("Invalid array length", exception.getMessage(),
                    "Testing PatientRecords.updatePatient(scan, idsLengthBad, names, birthdates," +
                     " heights, weights, temperatures, bloodPressures); - exception message");
        
        exception = assertThrows(IllegalArgumentException.class,
            () -> PatientRecords.updatePatient(scan, ids, namesLengthBad, birthdates, heights,  
                                                weights, temperatures, bloodPressures),
           "PatientRecords.updatePatient(scan, ids, namesLengthBad, birthdates, heights, weights," +
                                               " temperatures, bloodPressures);");
        assertEquals("Invalid array length", exception.getMessage(),
                    "Testing PatientRecords.updatePatient(scan, ids, namesLengthBad, birthdates," +
                     " heights, weights, temperatures, bloodPressures); - exception message");  

        exception = assertThrows(IllegalArgumentException.class,
            () -> PatientRecords.updatePatient(scan, ids, names, birthdatesLengthBad, heights, 
                                                weights, temperatures, bloodPressures),
                 "PatientRecords.updatePatient(scan, ids, names, birthdatesLengthBad, heights," +
                                               " weights, temperatures, bloodPressures);");
        assertEquals("Invalid array length", exception.getMessage(),
                    "Testing PatientRecords.updatePatient(scan, ids, names, birthdatesLengthBad," +
                     " heights, weights, temperatures, bloodPressures); - exception message"); 

        exception = assertThrows(IllegalArgumentException.class,
            () -> PatientRecords.updatePatient(scan, ids, names, birthdates, heightsLengthBad, 
                                                 weights, temperatures, bloodPressures),
                 "PatientRecords.updatePatient(scan, ids, names, birthdates, heightsLengthBad," +
                                               " weights, temperatures, bloodPressures);");
        assertEquals("Invalid array length", exception.getMessage(),
                     "Testing PatientRecords.updatePatient(scan, ids, names, birthdates, " + 
                     "heightsLengthBad,weights,temperatures,bloodPressures); - exception message");

        exception = assertThrows(IllegalArgumentException.class,
            () -> PatientRecords.updatePatient(scan, ids, names, birthdates, heights, 
                                                weightsLengthBad, temperatures, bloodPressures),
                  "PatientRecords.updatePatient(scan, ids, names, birthdates, heights, " +
                                      " weightsLengthBad,temperatures, bloodPressures);");
        assertEquals("Invalid array length", exception.getMessage(),
                   "Testing PatientRecords.updatePatient(scan, ids, names, birthdates, heights," +
                     " weightsLengthBad, temperatures, bloodPressures); - exception message");

        exception = assertThrows(IllegalArgumentException.class,
            () -> PatientRecords.updatePatient(scan, ids, names, birthdates, heights, weights, 
                                                temperaturesLengthBad, bloodPressures),
                "PatientRecords.updatePatient(scan, ids, names, birthdates, heights, weights, " +
                                               "temperaturesLengthBad, bloodPressures);");
        assertEquals("Invalid array length", exception.getMessage(),
                   "Testing PatientRecords.updatePatient(scan, ids, names, birthdates, heights," +
                     " weights, temperaturesLengthBad, bloodPressures); - exception message"); 

        exception = assertThrows(IllegalArgumentException.class,
            () -> PatientRecords.updatePatient(scan, ids, names, birthdates, heights, weights, 
                                                temperatures, bloodPressuresLengthBad),
                "PatientRecords.updatePatient(scan, ids, names, birthdates, heights, weights, " +
                                               "temperatures, bloodPressuresLengthBad);");
        assertEquals("Invalid array length", exception.getMessage(),
                   "Testing PatientRecords.updatePatient(scan, ids, names, birthdates, heights," + 
                     " weights, temperatures, bloodPressuresLengthBad); - exception message");

        assertArrayEquals(idsCopy, ids,
                "ids Array modified in PatientRecords.updatePatient when it shouldn't be");
        assertArrayEquals(namesCopy, names,
                "names Array modified in PatientRecords.updatePatient when it shouldn't be");
        assertArrayEquals(birthdatesCopy, birthdates,
               "birthdates Array modified in PatientRecords.updatePatient when it shouldn't be");
        assertArrayEquals(heightsCopy, heights,
                "heights Array modified in PatientRecords.updatePatient when it shouldn't be");
        assertArrayEquals(weightsCopy, weights,
                "weights Array modified in PatientRecords.updatePatient when it shouldn't be"); 
        assertArrayEquals(temperaturesCopy, temperatures,
              "temperatures Array modified in PatientRecords.updatePatient when it shouldn't be");
        assertArrayEquals(bloodPressuresCopy, bloodPressures,
             "bloodPressures Array modified in PatientRecords.updatePatient when it shouldn't be"); 
             
        // Test invalid array length addPatient                            
        exception = assertThrows(IllegalArgumentException.class,
            () -> PatientRecords.addPatient(scan, idsLengthBad, names, birthdates, heights,
                                            weights,temperatures, bloodPressures),
               "PatientRecords.addPatient(scan, idsLengthBad, names, birthdates, heights, " +
                                               "weights,temperatures, bloodPressures);");
        assertEquals("Invalid array length", exception.getMessage(),
                   "Testing PatientRecords.addPatient(scan, idsLengthBad, names, birthdates, " + 
                      "heights, weights, temperatures, bloodPressures); - exception message");
        
        exception = assertThrows(IllegalArgumentException.class,
            () -> PatientRecords.addPatient(scan, ids, namesLengthBad, birthdates, heights, 
                                            weights,temperatures, bloodPressures),
               "PatientRecords.addPatient(scan, ids, namesLengthBad, birthdates, heights, " +
                                             "weights, temperatures, bloodPressures);");
        assertEquals("Invalid array length", exception.getMessage(),
                    "Testing PatientRecords.addPatient(scan, ids, namesLengthBad, birthdates, " + 
                     "heights, weights, temperatures, bloodPressures); - exception message");

        exception = assertThrows(IllegalArgumentException.class,
            () -> PatientRecords.addPatient(scan, ids, names, birthdatesLengthBad, heights, 
                                            weights, temperatures, bloodPressures),
               "PatientRecords.addPatient(scan, ids, names, birthdatesLengthBad, heights, " +
                                               "weights, temperatures, bloodPressures);");
        assertEquals("Invalid array length", exception.getMessage(),
                   "Testing PatientRecords.addPatient(scan, ids, names, birthdatesLengthBad, " + 
                      "heights, weights, temperatures, bloodPressures); - exception message"); 

        exception = assertThrows(IllegalArgumentException.class,
            () -> PatientRecords.addPatient(scan, ids, names, birthdates, heightsLengthBad, 
                                            weights,temperatures, bloodPressures),
               "PatientRecords.addPatient(scan, ids, names, birthdates, heightsLengthBad," +
                                          " weights, temperatures, bloodPressures);");
        assertEquals("Invalid array length", exception.getMessage(),
              "Testing PatientRecords.addPatient(scan, ids, names, birthdates, heightsLengthBad," +
                             " weights, temperatures, bloodPressures); - exception message"); 

        exception = assertThrows(IllegalArgumentException.class,
            () -> PatientRecords.addPatient(scan, ids, names, birthdates, heights, 
                                            weightsLengthBad, temperatures, bloodPressures),
                  "PatientRecords.addPatient(scan, ids, names, birthdates, heights, " +
                                         " weightsLengthBad,temperatures, bloodPressures);");
        assertEquals("Invalid array length", exception.getMessage(),
                     "Testing PatientRecords.addPatient(scan, ids, names, birthdates, heights, " + 
                     "weightsLengthBad, temperatures, bloodPressures); - exception message");

        exception = assertThrows(IllegalArgumentException.class,
            () -> PatientRecords.addPatient(scan, ids, names, birthdates, heights, weights, 
                                                temperaturesLengthBad, bloodPressures),
                "PatientRecords.addPatient(scan, ids, names, birthdates, heights, weights, " +
                                               "temperaturesLengthBad, bloodPressures);");
        assertEquals("Invalid array length", exception.getMessage(),
                     "Testing PatientRecords.addPatient(scan, ids, names, birthdates, heights, " + 
                     "weights, temperaturesLengthBad, bloodPressures); - exception message"); 

        exception = assertThrows(IllegalArgumentException.class,
            () -> PatientRecords.addPatient(scan, ids, names, birthdates, heights, weights, 
                                                temperatures, bloodPressuresLengthBad),
                "PatientRecords.addPatient(scan, ids, names, birthdates, heights, weights, " +
                                               "temperatures, bloodPressuresLengthBad);");
        assertEquals("Invalid array length", exception.getMessage(),
                     "Testing PatientRecords.addPatient(scan, ids, names, birthdates, heights, " + 
                     "weights, temperatures, bloodPressuresLengthBad); - exception message"); 

        assertArrayEquals(idsCopy, ids,
                "ids Array modified in PatientRecords.addPatient when it shouldn't be"); 
        assertArrayEquals(namesCopy, names,
                "names Array modified in PatientRecords.addPatient when it shouldn't be");
        assertArrayEquals(birthdatesCopy, birthdates,
               "birthdates Array modified in PatientRecords.addPatient when it shouldn't be");
        assertArrayEquals(heightsCopy, heights,
                "heights Array modified in PatientRecords.addPatient when it shouldn't be");
        assertArrayEquals(weightsCopy, weights,
                "weights Array modified in PatientRecords.addPatient when it shouldn't be"); 
        assertArrayEquals(temperaturesCopy, temperatures,
              "temperatures Array modified in PatientRecords.addPatient when it shouldn't be");
        assertArrayEquals(bloodPressuresCopy, bloodPressures,
             "bloodPressures Array modified in PatientRecords.addPatient when it shouldn't be");


    }

    /**
     * Tests inputPatientss
     */
    @Test
    public void testInputPatients() {

        // You do NOT need to add additional inputPatientss tests. Just make sure these
        // pass!
        
        int[] expIds = {1001, 1002, 1003, 0, 0};
        String[] expNames = {"Amy Apple","Bill Brook","Carly Cook", null, null};  
        String[] expBirthdates = {"01/11/1111", "02/22/2222", "03/33/3333", null, null}; 
        int[] expHeights = {50, 60, 70, 0, 0};
        int[] expWeights = {150, 160, 170, 0, 0};
        double[] expTemperatures = {98.5, 99.0, 100.2, 0.0, 0.0};
        String[] expBloodPressures = {"100/80", "120/90", "130/95", null, null};
        
        int[] ids1 = new int[5];
        String[] names1 = new String[5];  
        String[] birthdates1 = new String[5]; 
        int[] heights1 = new int[5];
        int[] weights1 = new int[5];
        double[] temperatures1 = new double[5];
        String[] bloodPressures1 = new String[5];
        
        String simulatedFile = "1001,Amy Apple,01/11/1111,50,150,98.5,100/80\n" + 
                               "1002,Bill Brook,02/22/2222,60,160,99.0,120/90\n" +
                               "1003,Carly Cook,03/33/3333,70,170,100.2,130/95\n";
        String simulatedFileE1 = "\n" + 
                               "1002,Bill Brook,02/22/2222,60,160,99.0,120/90\n" +
                               "1003,Carly Cook,03/33/3333,70,170,100.2,130/95\n";    
        String simulatedFileE2 = "One,Amy Apple,01/11/1111,50,150,98.5,100/80\n" +
                               "1002,Bill Brook,02/22/2222,60,160,99.0,120/90\n" +
                               "1003,Carly Cook,03/33/3333,70,170,100.2,130/95\n";
        String simulatedFileE3 = "One,Amy Apple,01/11/1111,50,150,98.5,100/80\n" +
                               "1002,Bill Brook,02/22/2222,Sixty,160,99.0,120/90\n" +
                               "1003,Carly Cook,03/33/3333,70,170,100.2,130/95\n"; 
        String simulatedFileE4 =  "1001,Amy Apple,01/11/1111,50,150,98.5,100/80\n" + 
                                 "1002,Bill Brook,02/22/2222,60,160,99.0,120/90\n" +
                                 "1003,Carly Cook,03/33/3333,70,missing,100.2,130/95\n"; 
        String simulatedFileE5 = "1001,Amy Apple,01/11/1111,50,150,98.5,100/80\n" + 
                                "1002,Bill Brook,02/22/2222,60,160,F,120/90\n" +
                                "1003,Carly Cook,03/33/3333,70,170,100.2,130/95\n"; 
        String simulatedFileE6 = "1001,Amy Apple,01/11/1111,50,150,98.5,100/80\n" + 
                                "1002,Bill Brook,02/22/2222,60,160,99.0,120/90\n" +
                                "1003,Carly Cook,03/33/3333,70,170,100.2,130/95,extra\n"; 
                               
        String simulatedFileE7 =  "1001,Amy Apple,01/11/1111,50,150,98.5,100/80\n" + 
                                 "1002,Bill Brook,02/22/2222,60,160,99.0,120/90\n" +
                                 "1004,Carly Cook,03/33/3333,70,170,100.2,130/95\n"; 
                               
        String simulatedFileE8 = "1000,Amy Apple,01/11/1111,50,150,98.5,100/80\n" + 
                                "1001,Bill Brook,02/22/2222,60,160,99.0,120/90\n" +
                                "1002,Carly Cook,03/33/3333,70,170,100.2,130/95\n";  

        String simulatedFileE9 = "1001,Amy Apple,01/11/1111,50,150,98.5,100/80\n" + 
                                 "1002,Bill Brook,02/22/2222,60,160,99.0,120/90\n" +
                                 "1003,Bill Two,03/33/3333,60,160,99.0,120/90\n" +
                                 "1004,Bill Three,01/11/1111,60,160,99.0,120/90\n" +
                                 "1005,Bill Four,02/22/2222,60,160,99.0,120/90\n" +
                                 "1006,Carly Cook,03/33/3333,70,170,100.2,130/95\n"; 
         
        
        int[] ids = new int[5];
        String[] names = new String[5]; 
        String[] birthdates = new String[5]; 
        int[] heights = new int[5];
        int[] weights = new int[5];
        double[] temperatures = new double[5];
        String[] bloodPressures = new String[5];  
        int[] idsCopy = new int[5];
        String[] namesCopy = new String[5]; 
        String[] birthdatesCopy = new String[5]; 
        int[] heightsCopy = new int[5];
        int[] weightsCopy = new int[5];
        double[] temperaturesCopy = new double[5];
        String[] bloodPressuresCopy = new String[5]; 

        int[] idsLengthBad = new int[2];
        String[] namesLengthBad = new String[3];   
        String[] birthdatesLengthBad = new String[6]; 
        int[] heightsLengthBad = new int[4];
        int[] weightsLengthBad = new int[7];
        double[] temperaturesLengthBad = new double[0];
        String[] bloodPressuresLengthBad = new String[1];
        
        String input = "1002,76,189,99.2,140/80\n"; 
                
 
        assertTrue(PatientRecords.inputPatients(new Scanner(simulatedFile), ids1, names1, 
                                             birthdates1, heights1, weights1, temperatures1, 
                                             bloodPressures1),
                      "test PatientRecords.inputPatients() method with valid inputs");
        assertArrayEquals(expIds, ids1,
                "ids correct after PatientRecords.inputPatients(new Scanner(simulatedFile),"
                        + " ids1, names1, birthdates1, heights1, weights1, temperatures1, " +
                                             "bloodPressures1);");
                                             
        assertArrayEquals(expNames, names1,
                "names correct after PatientRecords.inputPatients(new Scanner(simulatedFile),"
                        + " ids1, names1, birthdates1, heights1, weights1, temperatures1, " +
                                            "bloodPressures1);");  
      
        assertFalse(
                PatientRecords.inputPatients(new Scanner(simulatedFileE1), ids1, names1, 
                                             birthdates1, heights1, weights1, temperatures1, 
                                             bloodPressures1),
                "test PatientRecords.inputPatients() method with too few tokens on a line");

        assertFalse(
                PatientRecords.inputPatients(new Scanner(simulatedFileE2), ids1, names1, 
                                             birthdates1, heights1, weights1, temperatures1, 
                                             bloodPressures1),
                "test PatientRecords.inputPatients() method with non-integer id");

        assertFalse(
                PatientRecords.inputPatients(new Scanner(simulatedFileE3), ids1, names1, 
                                             birthdates1, heights1, weights1, temperatures1, 
                                             bloodPressures1),
                "test PatientRecords.inputPatients() method with non-integer height");

        assertFalse(
                PatientRecords.inputPatients(new Scanner(simulatedFileE4), ids1, names1, 
                                             birthdates1, heights1, weights1, temperatures1, 
                                             bloodPressures1),
                "test PatientRecords.inputPatients() method with non-integer weight"); 

        assertFalse(
                PatientRecords.inputPatients(new Scanner(simulatedFileE5), ids1, names1, 
                                             birthdates1, heights1, weights1, temperatures1, 
                                             bloodPressures1),
                "test PatientRecords.inputPatients() method with non-numerical temperature"); 

        assertFalse(
                PatientRecords.inputPatients(new Scanner(simulatedFileE6), ids1, names1, 
                                             birthdates1, heights1, weights1, temperatures1, 
                                             bloodPressures1),
                "test PatientRecords.inputPatients() method with too many tokens on a line");
                

        assertFalse(
                PatientRecords.inputPatients(new Scanner(simulatedFileE7), ids1, names1, 
                                             birthdates1, heights1, weights1, temperatures1, 
                                             bloodPressures1),
                "test PatientRecords.inputPatients() method with non-sequential ids");
                
 
        assertFalse(
                PatientRecords.inputPatients(new Scanner(simulatedFileE8), ids1, names1, 
                                             birthdates1, heights1, weights1, temperatures1, 
                                             bloodPressures1),
                "test PatientRecords.inputPatients() method first id not 1001"); 

 
        assertFalse(
                PatientRecords.inputPatients(new Scanner(simulatedFileE9), ids1, names1, 
                                             birthdates1, heights1, weights1, temperatures1, 
                                             bloodPressures1),
                "test PatientRecords.inputPatients() method too many lines in file");
                
               
        //test null input file 
        Exception exception = assertThrows(IllegalArgumentException.class,
            () -> PatientRecords.inputPatients(null, ids, names, birthdates, heights, 
                                                weights, temperatures, bloodPressures),
                "PatientRecords.inputPatients(null, ids, names, birthdates, heights, " + 
                                                "weights, temperatures, bloodPressures);");
        assertEquals("Null file", exception.getMessage(),
                "Testing PatientRecords.inputPatients(null, ids, names, birthdates, heights, " +
                         "weights, temperatures, bloodPressures); - exception message");

 
        //test null ids array
        exception = assertThrows(IllegalArgumentException.class,
            () -> PatientRecords.inputPatients(new Scanner(input), null, names, birthdates,  
                                               heights, weights, temperatures, bloodPressures),
                "PatientRecords.inputPatients(new Scanner(input), null, names, birthdates," +
                                              " heights, weights,temperatures, bloodPressures);");
        assertEquals("Null array", exception.getMessage(),
              "Testing PatientRecords.inputPatients(new Scanner(input), null, names, birthdates," +
                      " heights, weights, temperatures, bloodPressures); - exception message");
        
        //test null names array
        exception = assertThrows(IllegalArgumentException.class,
            () -> PatientRecords.inputPatients(new Scanner(input), ids, null, birthdates,  
                                               heights, weights, temperatures, bloodPressures),
                "PatientRecords.inputPatients(new Scanner(input), ids, null, birthdates," +
                                              " heights, weights,temperatures, bloodPressures);");
        assertEquals("Null array", exception.getMessage(),
              "Testing PatientRecords.inputPatients(new Scanner(input), ids, null, birthdates," + 
                      " heights, weights, temperatures, bloodPressures); - exception message");

        //test null birthdates array
        exception = assertThrows(IllegalArgumentException.class,
            () -> PatientRecords.inputPatients(new Scanner(input), ids, names, null,  
                                               heights, weights, temperatures, bloodPressures),
                "PatientRecords.inputPatients(new Scanner(input), ids, names, null," +
                                              " heights, weights,temperatures, bloodPressures);");
        assertEquals("Null array", exception.getMessage(),
              "Testing PatientRecords.inputPatients(new Scanner(input), ids, names, null," + 
                      " heights, weights, temperatures, bloodPressures); - exception message"); 

        //test null heights array
        exception = assertThrows(IllegalArgumentException.class,
            () -> PatientRecords.inputPatients(new Scanner(input), ids, names, birthdates,  
                                               null, weights, temperatures, bloodPressures),
                "PatientRecords.inputPatients(new Scanner(input), ids, names, birthdates," +
                                              " null, weights,temperatures, bloodPressures);");
        assertEquals("Null array", exception.getMessage(),
              "Testing PatientRecords.inputPatients(new Scanner(input), ids, names, birthdates," +
                      " null, weights, temperatures, bloodPressures); - exception message"); 

        //test null weights array
        exception = assertThrows(IllegalArgumentException.class,
            () -> PatientRecords.inputPatients(new Scanner(input), ids, names, birthdates,  
                                               heights, null, temperatures, bloodPressures),
                "PatientRecords.inputPatients(new Scanner(input), ids, names, birthdates," +
                                              " heights, null, temperatures, bloodPressures);");
        assertEquals("Null array", exception.getMessage(),
             "Testing PatientRecords.inputPatients(new Scanner(input), ids, names, birthdates," +
                      " heights, null, temperatures, bloodPressures); - exception message"); 
                      
        //test null temperatures array
        exception = assertThrows(IllegalArgumentException.class,
            () -> PatientRecords.inputPatients(new Scanner(input), ids, names, birthdates,  
                                               heights, weights, null, bloodPressures),
                "PatientRecords.inputPatients(new Scanner(input), ids, names, birthdates," +
                                              " heights, weights, null, bloodPressures);");
        assertEquals("Null array", exception.getMessage(),
             "Testing PatientRecords.inputPatients(new Scanner(input), ids, names, birthdates," + 
                      " heights, weights, null, bloodPressures); - exception message");
                      
        //test null bloodPressures array
        exception = assertThrows(IllegalArgumentException.class,
            () -> PatientRecords.inputPatients(new Scanner(input), ids, names, birthdates,  
                                               heights, weights, temperatures, null),
                "PatientRecords.inputPatients(new Scanner(input), ids, names, birthdates," +
                                              " heights, weights, temperatures, null);");
        assertEquals("Null array", exception.getMessage(),
             "Testing PatientRecords.inputPatients(new Scanner(input), ids, names, birthdates," + 
                      " heights, weights, temperatures, null); - exception message");
                      
        // Test invalid array length ids bad                            
        exception = assertThrows(IllegalArgumentException.class,
            () -> PatientRecords.inputPatients(new Scanner(input), idsLengthBad, names, birthdates,
                                               heights, weights, temperatures, bloodPressures),
              "PatientRecords.inputPatients(new Scanner(input), idsLengthBad, names, birthdates," +
                                             " heights, weights, temperatures, bloodPressures);");
        assertEquals("Invalid array length", exception.getMessage(),
               "Testing PatientRecords.inputPatients(new Scanner(input), idsLengthBad, names, " + 
               "birthdates, heights, weights, temperatures, bloodPressures); - exception message");

        // Test invalid array length names bad                            
        exception = assertThrows(IllegalArgumentException.class,
            () -> PatientRecords.inputPatients(new Scanner(input), ids, namesLengthBad, birthdates,
                                               heights, weights, temperatures, bloodPressures),
             "PatientRecords.inputPatients(new Scanner(input), ids, namesLengthBad, birthdates," +
                                             " heights, weights, temperatures, bloodPressures);");
        assertEquals("Invalid array length", exception.getMessage(),
               "Testing PatientRecords.inputPatients(new Scanner(input), ids, namesLengthBad, " + 
               "birthdates, heights, weights, temperatures, bloodPressures); - exception message");
        
        // Test invalid array length birthdates bad     
        exception = assertThrows(IllegalArgumentException.class,
            () -> PatientRecords.inputPatients(new Scanner(input), ids, names, birthdatesLengthBad,
                                               heights, weights, temperatures, bloodPressures),
             "PatientRecords.inputPatients(new Scanner(input), ids, names, birthdatesLengthBad," +
                                             " heights, weights, temperatures, bloodPressures);");
        assertEquals("Invalid array length", exception.getMessage(),
               "Testing PatientRecords.inputPatients(new Scanner(input), ids, names, " + 
               "birthdatesLengthBad, heights, weights, temperatures, bloodPressures); - " +
               "exception message");    

        // Test invalid array length heights bad 
        exception = assertThrows(IllegalArgumentException.class,
            () -> PatientRecords.inputPatients(new Scanner(input), ids, names, birthdates,
                                               heightsLengthBad, weights, temperatures, 
                                               bloodPressures),
               "PatientRecords.inputPatients(new Scanner(input), ids, names, birthdates, " +
                                               "heightsLengthBad, weights, temperatures, " +
                                               "bloodPressures);");
        assertEquals("Invalid array length", exception.getMessage(),
               "Testing PatientRecords.inputPatients(new Scanner(input), ids, names, " + 
               "birthdates, heightsLengthBad, weights, temperatures, bloodPressures); - " +
               "exception message");                  
         
        // Test invalid array length weights bad 
        exception = assertThrows(IllegalArgumentException.class,
            () -> PatientRecords.inputPatients(new Scanner(input), ids, names, birthdates,
                                               heights, weightsLengthBad, temperatures, 
                                               bloodPressures),
               "PatientRecords.inputPatients(new Scanner(input), ids, names, birthdates, " +
                                               "heights, weightsLengthBad, temperatures, " +
                                               "bloodPressures);");
        assertEquals("Invalid array length", exception.getMessage(),
               "Testing PatientRecords.inputPatients(new Scanner(input), ids, names, " + 
               "birthdates, heights, weightsLengthBad, temperatures, bloodPressures); - " +
               "exception message");               
 
        // Test invalid array length temperatures bad 
        exception = assertThrows(IllegalArgumentException.class,
            () -> PatientRecords.inputPatients(new Scanner(input), ids, names, birthdates,
                                               heights, weights, temperaturesLengthBad, 
                                               bloodPressures),
               "PatientRecords.inputPatients(new Scanner(input), ids, names, birthdates, " +
                                               "heights, weights, temperaturesLengthBad, " +
                                               "bloodPressures);");
        assertEquals("Invalid array length", exception.getMessage(),
               "Testing PatientRecords.inputPatients(new Scanner(input), ids, names, " + 
               "birthdates, heights, weights, temperaturesLengthBad, bloodPressures); - " +
               "exception message");  

        // Test invalid array length bloodPressures bad 
        exception = assertThrows(IllegalArgumentException.class,
            () -> PatientRecords.inputPatients(new Scanner(input), ids, names, birthdates,
                                               heights, weights, temperatures, 
                                               bloodPressuresLengthBad),
               "PatientRecords.inputPatients(new Scanner(input), ids, names, birthdates, " +
                                               "heights, weights, temperatures, " +
                                               "bloodPressuresLengthBad);");
        assertEquals("Invalid array length", exception.getMessage(),
               "Testing PatientRecords.inputPatients(new Scanner(input), ids, names, " + 
               "birthdates, heights, weights, temperatures, bloodPressuresLengthBad); - " +
               "exception message");  
                     
                      
        assertArrayEquals(idsCopy, ids,
                "ids Array modified in PatientRecords.inputPatients when it shouldn't be");
        assertArrayEquals(namesCopy, names,
                "names Array modified in PatientRecords.inputPatients when it shouldn't be");
        assertArrayEquals(birthdatesCopy, birthdates,
               "birthdates Array modified in PatientRecords.inputPatients when it shouldn't be");
        assertArrayEquals(heightsCopy, heights,
                "heights Array modified in PatientRecords.inputPatients when it shouldn't be");
        assertArrayEquals(weightsCopy, weights,
                "weights Array modified in PatientRecords.inputPatients when it shouldn't be"); 
        assertArrayEquals(temperaturesCopy, temperatures,
              "temperatures Array modified in PatientRecords.inputPatients when it shouldn't be");
        assertArrayEquals(bloodPressuresCopy, bloodPressures,
             "bloodPressures Array modified in PatientRecords.inputPatients when it shouldn't be");

    }

 

    /**
     * Tests outputPatients
     * 
     * @throws FileNotFoundException if unable to construct PrintWriter
     */

    @Test
    public void testOutputPatients() throws FileNotFoundException {

        // You do NOT need to add additional outputPatients tests. Just make sure these
        // pass!
    
        
        int[] ids = {1001, 1002, 1003, 0, 0};
        String[] names = {"Mary Beth Jones","Thomas A. Berry","Tricia Patel", null, null};
        String[] birthdates = {"04/21/1972", "06/19/1967", "11/18/1956", null, null}; 
        int[] heights = {62, 73, 64, 0, 0};
        int[] weights = {135,198, 105, 0, 0};
        double[] temperatures = {102.4, 98.6, 100.2, 0.0, 0.0};
        String[] bloodPressures = {"120/90", "130/80", "120/80", null, null}; 

        int[] idsCopy = {1001, 1002, 1003, 0, 0};
        String[] namesCopy = {"Mary Beth Jones","Thomas A. Berry","Tricia Patel", null, null};
        String[] birthdatesCopy = {"04/21/1972", "06/19/1967", "11/18/1956", null, null}; 
        int[] heightsCopy = {62, 73, 64, 0, 0};
        int[] weightsCopy = {135,198, 105, 0, 0};
        double[] temperaturesCopy = {102.4, 98.6, 100.2, 0.0, 0.0};
        String[] bloodPressuresCopy = {"120/90", "130/80", "120/80", null, null};        
        
        
        String expected = "test-files/patients.txt"; 
        String actual = "test-files/act_UpdatedPatient.txt"; 
        // Delete file if it already exists
        Path path = Path.of(actual);
        try {
            Files.deleteIfExists(path);
        } catch (IOException e) {
            // Nothing needs to be done
            e.printStackTrace();
        }
        PrintWriter out = new PrintWriter(new FileOutputStream(actual));
        PatientRecords.outputPatients(out, ids, names, birthdates,
                                               heights, weights, temperatures, 
                                               bloodPressures);
        out.close();
        assertFilesEqual(expected, actual, "outputPatients");


        

        Exception exception = assertThrows(IllegalArgumentException.class,
            () -> PatientRecords.outputPatients(null, ids, names, birthdates,
                                               heights, weights, temperatures, 
                                               bloodPressures),
                "PatientRecords.outputPatients(null, ids, names, birthdates, " +
                                               "heights, weights, temperatures, " +
                                               "bloodPressures)");
        assertEquals("Null file", exception.getMessage(), 
                     "Testing Null output PrintWriter - exception message");
        
        //test null ids array
        exception = assertThrows(IllegalArgumentException.class,
            () -> PatientRecords.outputPatients(out, null, names, birthdates,  
                                               heights, weights, temperatures, bloodPressures),
                "PatientRecords.outputPatients(out, null, names, birthdates," +
                                              " heights, weights,temperatures, bloodPressures);");
        assertEquals("Null array", exception.getMessage(),
              "Testing PatientRecords.outputPatients(out, null, names, birthdates," + 
                      " heights, weights, temperatures, bloodPressures); - exception message");
        
        //test null names array
        exception = assertThrows(IllegalArgumentException.class,
            () -> PatientRecords.outputPatients(out, ids, null, birthdates,  
                                               heights, weights, temperatures, bloodPressures),
                "PatientRecords.outputPatients(out, ids, null, birthdates," +
                                              " heights, weights,temperatures, bloodPressures);");
        assertEquals("Null array", exception.getMessage(),
              "Testing PatientRecords.outputPatients(out, ids, null, birthdates," + 
                      " heights, weights, temperatures, bloodPressures); - exception message");

        //test null birthdates array
        exception = assertThrows(IllegalArgumentException.class,
            () -> PatientRecords.outputPatients(out, ids, names, null,  
                                               heights, weights, temperatures, bloodPressures),
                "PatientRecords.outputPatients(out, ids, names, null," +
                                              " heights, weights,temperatures, bloodPressures);");
        assertEquals("Null array", exception.getMessage(),
              "Testing PatientRecords.outputPatients(new Scanner(input), ids, names, null," + 
                      " heights, weights, temperatures, bloodPressures); - exception message"); 

        //test null heights array
        exception = assertThrows(IllegalArgumentException.class,
            () -> PatientRecords.outputPatients(out, ids, names, birthdates,  
                                               null, weights, temperatures, bloodPressures),
                "PatientRecords.outputPatients(out, ids, names, birthdates," +
                                              " null, weights,temperatures, bloodPressures);");
        assertEquals("Null array", exception.getMessage(),
              "Testing PatientRecords.outputPatients(out, ids, names, birthdates," + 
                      " null, weights, temperatures, bloodPressures); - exception message"); 

        //test null weights array
        exception = assertThrows(IllegalArgumentException.class,
            () -> PatientRecords.outputPatients(out, ids, names, birthdates,  
                                               heights, null, temperatures, bloodPressures),
                "PatientRecords.outputPatients(out, ids, names, birthdates," +
                                              " heights, null, temperatures, bloodPressures);");
        assertEquals("Null array", exception.getMessage(),
             "Testing PatientRecords.outputPatients(out, ids, names, birthdates," + 
                      " heights, null, temperatures, bloodPressures); - exception message"); 
                      
        //test null temperatures array
        exception = assertThrows(IllegalArgumentException.class,
            () -> PatientRecords.outputPatients(out, ids, names, birthdates,  
                                               heights, weights, null, bloodPressures),
                "PatientRecords.outputPatients(out, ids, names, birthdates," +
                                              " heights, weights, null, bloodPressures);");
        assertEquals("Null array", exception.getMessage(),
             "Testing PatientRecords.outputPatients(out, ids, names, birthdates," + 
                      " heights, weights, null, bloodPressures); - exception message");
                      
        //test null bloodPressures array
        exception = assertThrows(IllegalArgumentException.class,
            () -> PatientRecords.outputPatients(out, ids, names, birthdates,  
                                               heights, weights, temperatures, null),
                "PatientRecords.outputPatients(out, ids, names, birthdates," +
                                              " heights, weights, temperatures, null);");
        assertEquals("Null array", exception.getMessage(),
             "Testing PatientRecords.outputPatients(out, ids, names, birthdates," + 
                      " heights, weights, temperatures, null); - exception message");

        

        int[] idsLengthBad = new int[2];
        String[] namesLengthBad = new String[3];                                     
        String[] birthdatesLengthBad = new String[6]; 
        int[] heightsLengthBad = new int[4];
        int[] weightsLengthBad = new int[7];
        double[] temperaturesLengthBad = new double[0];
        String[] bloodPressuresLengthBad = new String[1];
        
        // Test invalid array length ids bad                            
        exception = assertThrows(IllegalArgumentException.class,
            () -> PatientRecords.outputPatients(out, idsLengthBad, names, birthdates,
                                               heights, weights, temperatures, bloodPressures),
               "PatientRecords.outputPatients(out, idsLengthBad, names, birthdates, " +
                                               "heights, weights, temperatures, bloodPressures);");
        assertEquals("Invalid array length", exception.getMessage(),
               "Testing PatientRecords.outputPatients(out, idsLengthBad, names, " + 
               "birthdates, heights, weights, temperatures, bloodPressures); - exception message");

        // Test invalid array length names bad                            
        exception = assertThrows(IllegalArgumentException.class,
            () -> PatientRecords.outputPatients(out, ids, namesLengthBad, birthdates,
                                               heights, weights, temperatures, bloodPressures),
               "PatientRecords.outputPatients(out, ids, namesLengthBad, birthdates, " +
                                               "heights, weights, temperatures, bloodPressures);");
        assertEquals("Invalid array length", exception.getMessage(),
               "Testing PatientRecords.outputPatients(out, ids, namesLengthBad, " + 
               "birthdates, heights, weights, temperatures, bloodPressures); - exception message");
        
        // Test invalid array length birthdates bad                            
        exception = assertThrows(IllegalArgumentException.class,
            () -> PatientRecords.outputPatients(out, ids, names, birthdatesLengthBad,
                                               heights, weights, temperatures, bloodPressures),
               "PatientRecords.outputPatients(out, ids, names, birthdatesLengthBad, " +
                                               "heights, weights, temperatures, bloodPressures);");
        assertEquals("Invalid array length", exception.getMessage(),
               "Testing PatientRecords.outputPatients(out, ids, names, " + 
               "birthdatesLengthBad, heights, weights, temperatures, bloodPressures); - " +
               "exception message");    

        // Test invalid array length heights bad 
        exception = assertThrows(IllegalArgumentException.class,
            () -> PatientRecords.outputPatients(out, ids, names, birthdates,
                                               heightsLengthBad, weights, temperatures, 
                                               bloodPressures),
               "PatientRecords.outputPatients(out, ids, names, birthdates, " +
                                               "heightsLengthBad, weights, temperatures, " +
                                               "bloodPressures);");
        assertEquals("Invalid array length", exception.getMessage(),
               "Testing PatientRecords.outputPatients(out, ids, names, " + 
               "birthdates, heightsLengthBad, weights, temperatures, bloodPressures); - " +
               "exception message");                  
         
        // Test invalid array length weights bad 
        exception = assertThrows(IllegalArgumentException.class,
            () -> PatientRecords.outputPatients(out, ids, names, birthdates,
                                               heights, weightsLengthBad, temperatures, 
                                               bloodPressures),
               "PatientRecords.outputPatients(out, ids, names, birthdates, " +
                                               "heights, weightsLengthBad, temperatures, " +
                                               "bloodPressures);");
        assertEquals("Invalid array length", exception.getMessage(),
               "Testing PatientRecords.outputPatients(out, ids, names, " + 
               "birthdates, heights, weightsLengthBad, temperatures, bloodPressures); - " +
               "exception message");               
 
        // Test invalid array length temperatures bad 
        exception = assertThrows(IllegalArgumentException.class,
            () -> PatientRecords.outputPatients(out, ids, names, birthdates,
                                               heights, weights, temperaturesLengthBad, 
                                               bloodPressures),
               "PatientRecords.outputPatients(out, ids, names, birthdates, " +
                                               "heights, weights, temperaturesLengthBad, " +
                                               "bloodPressures);");
        assertEquals("Invalid array length", exception.getMessage(),
               "Testing PatientRecords.outputPatients(out, ids, names, " + 
               "birthdates, heights, weights, temperaturesLengthBad, bloodPressures); - " +
               "exception message");  

        // Test invalid array length bloodPressures bad 
        exception = assertThrows(IllegalArgumentException.class,
            () -> PatientRecords.outputPatients(out, ids, names, birthdates,
                                               heights, weights, temperatures, 
                                               bloodPressuresLengthBad),
               "PatientRecords.outputPatients(out, ids, names, birthdates, " +
                                               "heights, weights, temperatures, " +
                                               "bloodPressuresLengthBad);");
        assertEquals("Invalid array length", exception.getMessage(),
               "Testing PatientRecords.outputPatients(out, ids, names, " + 
               "birthdates, heights, weights, temperatures, bloodPressuresLengthBad); - " +
               "exception message");  
                     
                      
        assertArrayEquals(idsCopy, ids,
                "ids Array modified in PatientRecords.outputPatients when it shouldn't be");
        assertArrayEquals(namesCopy, names,
                "names Array modified in PatientRecords.outputPatient when it shouldn't be");
        assertArrayEquals(birthdatesCopy, birthdates,
               "birthdates Array modified in PatientRecords.outputPatient when it shouldn't be");
        assertArrayEquals(heightsCopy, heights,
                "heights Array modified in PatientRecords.outputPatient when it shouldn't be");
        assertArrayEquals(weightsCopy, weights,
                "weights Array modified in PatientRecords.outputPatient when it shouldn't be"); 
        assertArrayEquals(temperaturesCopy, temperatures,
              "temperatures Array modified in PatientRecords.outputPatient when it shouldn't be");
        assertArrayEquals(bloodPressuresCopy, bloodPressures,
             "bloodPressures Array modified in PatientRecords.outputPatient when it shouldn't be");
  
    }

    /**
     * Tests whether files contain the same contents
     * 
     * @param pathToExpected path to file with expected contents
     * @param pathToActual path to file with actual content
     * @param message message for test
     * @throws FileNotFoundException if Scanner cannot be constructed with file
     */
    private void assertFilesEqual(String pathToExpected, String pathToActual, String message)
            throws FileNotFoundException {
        try (Scanner expected = new Scanner(new FileInputStream(pathToExpected));
                Scanner actual = new Scanner(new FileInputStream(pathToActual));) {
            while (expected.hasNextLine()) {
                if (!actual.hasNextLine()) { // checks that actual has line as well
                    fail(message + ": Actual missing line(s)");
                } else { // both have another line
                    assertEquals(expected.nextLine(), actual.nextLine(),
                            message + ": Checking line equality");
                }
            }

            if (actual.hasNextLine()) {
                fail(message + ": Actual has extra line(s)");
            }
        }
    }
}