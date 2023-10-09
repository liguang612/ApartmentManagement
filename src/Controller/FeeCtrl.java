package Controller;

import java.util.ArrayList;

import Model.Fee;
import SQL.DBQuery;

public class FeeCtrl {
    public static Boolean addNewFee(int abId, String name, int cost, int mandatory, int cycle, String expirationDate) {
        return DBQuery.addNewFee(abId, name, cost, mandatory, cycle, expirationDate);
    }

    public static ArrayList<Fee> getFeeList(int abId) {
        return DBQuery.getFeeList(abId);
    }
}
