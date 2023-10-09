package Controller;

import java.util.ArrayList;

import Model.Apartment;
import SQL.DBQuery;

public class ApartmentCtrl {
    public static ArrayList<Apartment> getApartmentList(int abId) {
        return DBQuery.getApartmentList(abId);
    }
}
