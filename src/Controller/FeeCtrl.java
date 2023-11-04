package Controller;

import java.util.ArrayList;

import Model.Fee;
import SQLServer.DBQuery;

public class FeeCtrl {
    public static Boolean addNewFee(String name, int cost, boolean mandatory, int cycle, String expirationDate) {
        return DBQuery.addNewFee(name, cost, mandatory, cycle, expirationDate);
    }

    public static boolean deleteFee(ArrayList<Integer> selections) {
        return DBQuery.deleteFee(selections);
    }

    public static ArrayList<Fee> getFeeList(int cycle) {
        return DBQuery.getFeeList(cycle);
    }
}
