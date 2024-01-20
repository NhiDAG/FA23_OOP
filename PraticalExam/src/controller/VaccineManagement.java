package controller;

import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Scanner;
import model.Vaccine;
import model.Vaccine;
import view.Menu;
import view.Validation;

public class VaccineManagement extends Menu{
SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    static String[] menu = {"Display all vaccines from file","Add new vaccine","Delete a vaccine by code","Sort vaccine by name","Save to File" , "Exit"};
    private VaccineManager com = new VaccineManager();
    private Validation val = new Validation();

    public VaccineManagement() {
        super("Vaccine Management System!!!", menu);
    }

    @Override
    public void execute(int n) {
        switch (n) {
            case 1:
                String path = Paths.get("").toAbsolutePath().toString();
                com.readFile(path + "/src/model/vaccines_input.txt");
                com.display();
                break;
            case 2:
                com.addVaccine(inputVaccine());
                break;
            case 3:
                com.deleteVaccine(getValue("Enter code: "));
                break;
            case 4:
                sortVaccineByName();
                break;
            case 5:
                String path1 = Paths.get("").toAbsolutePath().toString();
                if (com.saveFile(path1 + "/src/model/vaccines_output.txt")==true) System.out.println("Save File successfully");
                        else System.out.println("Save file unsucessfully");
                break;
            default:
                return;
        }
    }
//------------------------------------------------------------------------------
//VALIDATION
    public Vaccine inputVaccine() {
        String name = getValue("Enter VaccineName: ");       
        String code = getValue("Enter VaccineCode: ");
        int quantity = -1;
        try {
            while (quantity == -1) {
                quantity = val.checkInt(getValue("Enter vaccine quantity: "));
                if (quantity == -1) {
                    System.out.println("Please input again in the appropriate format: ");
                }
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        Date expreDate = null;
        try {
            while (expreDate == null) {
                expreDate = val.checkDate(getValue("Enter expirationDate  (dd/MM/yyyy): "));
                if (expreDate == null) {
                    System.out.println("Please input again in the appropriate format (dd/MM/yyyy): ");
                }
            }

        } catch (Exception ex) {
            ex.getMessage();
        }
         double price = -1;
        try {
            while (price == -1) {
                price = val.checkDouble(getValue("Enter price: "));
                if (price == -1) {
                    System.out.println("Please input again in the appropriate format: ");
                }
            }

        } catch (Exception ex) {
            ex.getMessage();
        }
        
        Date lasDate = null;
        try {
            while (lasDate == null) {
                lasDate = val.checkDate(getValue("Enter lastInjectedDate (dd/MM/yyyy): "));
                if (lasDate == null) {
                    System.out.println("Please input again in the appropriate format (dd/MM/yyyy): ");
                }
            }

        } catch (Exception ex) {
            ex.getMessage();
        }
        return new Vaccine(name, code, quantity, expreDate, price, lasDate);
    }
//-------------------------------------------------------------------------------
//INPUT DATA
    public String getValue(String s) {
        Scanner sc = new Scanner(System.in);
        System.out.print(s);
        return sc.nextLine();
    }
//-------------------------------------------------------------------------------
//SORT MENU
    public void sortVaccineByName() {
                        Collections.sort(com.vaccines, Comparator.comparing(Vaccine::getName));
                        com.display();
    }
    
        public static void main(String[] args) {
        VaccineManagement mgnObj = new VaccineManagement();
        mgnObj.run();
    }
    }

