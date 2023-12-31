package Controller;

import java.util.ArrayList;

import Model.Apartment;
import Model.Resident;
import SQLServer.DBQuery;

public class ApartmentCtrl {
    public static boolean addNewApartment(int apartmentId, int ownerId) {
        return DBQuery.addNewApartment(apartmentId, ownerId);
    }

    public static boolean deleteApartment(ArrayList<Integer> selections) {
        return DBQuery.deleteApartment(selections);
    }

    public static Apartment getApartment(Integer id) {
        return DBQuery.getApartment(id);
    }

    public static ArrayList<Apartment> getApartmentList() {
        return DBQuery.getApartmentList();
    }

    public static ArrayList<Resident> getMembers(int id) {
        return DBQuery.getMembers(id);
    }

    public static Resident getOwner(int floor, int room) {
        return DBQuery.getOwner(floor, room);
    }
}
