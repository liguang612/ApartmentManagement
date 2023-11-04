package Controller;

import java.util.ArrayList;

import Model.Apartment;
import SQLServer.DBQuery;

public class ApartmentCtrl {
    public static boolean addNewApartment(int apartmentId, int ownerId) {
        return DBQuery.addNewApartment(apartmentId, ownerId);
    }

    public static boolean deleteApartment(ArrayList<Integer> selections) {
        return DBQuery.deleteApartment(selections);
    }

    public static ArrayList<Apartment> getApartmentList(int abId) {
        return DBQuery.getApartmentList();
    }
}
