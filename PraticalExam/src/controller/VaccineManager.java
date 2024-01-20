package controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.function.Predicate;
import model.Vaccine;
import view.Validation;

public class VaccineManager {
  Validation val = new Validation();
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    //--------------------------------------------------
    //List of Vaccine
    public static ArrayList<Vaccine> vaccines;

    public VaccineManager(ArrayList<Vaccine> vaccines) {
        this.vaccines = vaccines;
    }

    public VaccineManager() {
        this.vaccines = new ArrayList<Vaccine>();
    }

    //-----------------------------------------------
    //Read text file to load data
    public void readFile(String path) {
        try {
            FileInputStream fis = new FileInputStream(path);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            String line = br.readLine();
            while (line != null) {
                String arr[] = line.split(",");
                int count = 0;
                if (arr.length == 6) {
                    arr[0] = arr[0].substring(5, arr[0].length());
                    arr[1] = arr[1].substring(6, arr[1].length());
                    arr[2] = arr[2].substring(10, arr[2].length());
                    arr[3] = arr[3].substring(16, arr[3].length());
                    arr[4] = arr[4].substring(7, arr[4].length());
                    arr[5] = arr[5].substring(19, arr[5].length());
//
                    if (val.checkDate(arr[3]) == null || val.checkDate(arr[5]) == null || val.checkDouble(arr[4]) == -1 || val.checkInt(arr[2])==-1 || val.checkDuplicateId(arr[0], vaccines)==null) { //validation file
                    } else {
                        Vaccine s = new Vaccine(arr[1], arr[0], Integer.parseInt(arr[2]), val.checkDate(arr[3]),Double.parseDouble(arr[4]),val.checkDate(arr[5]));
                        vaccines.add(s);
                    }
                }
                line = br.readLine();
            }
            br.close();
            isr.close();
            fis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //-------------------------------------------------------
    //Add new customer 
    public void addVaccine(Vaccine c) {
        this.vaccines.add(c);
    }

    //---------------------------------------------------------
    //Search customer by different criteria 
    public ArrayList<Vaccine> search(Predicate<Vaccine> c) {
        ArrayList<Vaccine> rs = new ArrayList<Vaccine>();
        for (Vaccine customer : vaccines) {
            if (c.test(customer)) {
                rs.add(customer);
            }
        }
        return rs;
    }

    //------------------------------------------------------------
    //Write file
    public boolean saveFile(String path) {
        try {
            FileOutputStream fos = new FileOutputStream(path);
            OutputStreamWriter osw = new OutputStreamWriter(fos);
            BufferedWriter bw = new BufferedWriter(osw);
            for (Vaccine t : this.vaccines) {
                String line = "name=" + t.getName() + ", code=" + t.getCode() + ", quantity=" + t.getQuantity() + ", expirationDate=" + dateFormat.format(t.getExpirationDate()) + ", lastInjecDate=" +", price="+t.getPrice()+ dateFormat.format(t.getLastInjecDate());
                bw.write(line);
                bw.newLine();
            }
            bw.close();
            osw.close();
            fos.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    //------------------------------------------------------------------------
    //Print any List of customers 

    public void display() {
        if (vaccines.isEmpty()) {
            System.out.println("Not found data");
        }
        for (Vaccine customer : vaccines) {
            System.out.println(customer);
        }
    }

    //----------------------------------------------------------
    public void deleteVaccine(String code) {
        // Find the index of the customer with the matching ID
        int index = -1;
        for (int i = 0; i < vaccines.size(); i++) {
            if (vaccines.get(i).getCode().equals(code)) {
                index = i;
                break;
            }
        }
        // If the customer was found, remove them from the list
        if (index != -1) {
            vaccines.remove(index);
            System.out.println("Vaccine deleted successfully.");
        } else {
            System.out.println("Vaccine not found.");
        }
    }
}
