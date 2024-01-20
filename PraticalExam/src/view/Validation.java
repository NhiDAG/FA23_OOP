package view;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import model.Vaccine;

public class Validation {

    public Validation() {
    }

    public String checkPhone(String input) throws Exception {
        while (true) {
            String temp = input.trim();
            if (temp == null || temp.length() != 10) {
                return null;
            }
            if (!temp.startsWith("09")) {
                return null;
            }

            for (int i = 2; i < temp.length(); i++) {
                if (((temp.charAt(i)) > '9') || ((temp.charAt(i)) < '0')) {
                    return null;
                }
            }

            return temp;
        }
    }

    public String checkID(String input) throws Exception {
        while (true) {
            String temp = input.trim();
            if (temp == null || temp.length() != 4) {
                return null;
            }
            for (int i = 2; i < 4; i++) {
                if (((temp.charAt(i)) > '9') || ((temp.charAt(i)) < '0')) {
                    return null;
                }
            }
            if (temp.equals("KH00")) return null;
            
            if (temp.substring(0, 2).equals("KH")) {
                return temp;
            }
            return null;
        }

    }

    public Date checkDate(String input) throws Exception {
        String temp = input.trim();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        dateFormat.setLenient(false);

        try {
            Date date1 = dateFormat.parse(temp);
            return date1;
        } catch (ParseException e) {
            return null;

        }
    }

    public String checkDuplicateId(String input, ArrayList<Vaccine> arr) throws Exception {
        for (Vaccine vaccine : arr) {
            if (vaccine.getCode().equals(input)) {
                return null;
            }
        }
        return input;
    }

    public int checkInt(String input) throws Exception {
        String temp = input.trim();
        if (temp == null) {
            return -1;
        }
        try {
            int dapAn = Integer.parseInt(temp);
            return dapAn;
        } catch (NumberFormatException e) {
            return -1;
        }
    }
    
    public double checkDouble(String input) throws Exception {
        String temp = input.trim();
        if (temp == null) {
            return -1;
        }
        try {
            double dapAn = Double.parseDouble(temp);
            return dapAn;
        } catch (NumberFormatException e) {
            return -1;
        }
    }


}
