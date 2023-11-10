package Controller;

import java.util.ArrayList;

import Model.Resident;
import SQLServer.DBQuery;

public class ResidentCtrl {
    public static boolean addResident(Resident resident) {
        return DBQuery.addResident(resident);
    }

    public static boolean deleteResident(ArrayList<Long> selections) {
        return DBQuery.deleteResident(selections);
    }

    public static boolean editResident(Resident resident, Long oldId) {
        return DBQuery.editResident(resident, oldId);
    }

    public static boolean existsPhoneNumber(int phoneNumber) {
        return DBQuery.existsPhoneNumber(phoneNumber);
    }
    public static boolean existsResident(long id) {
        return DBQuery.existsResident(id);
    }

    public static Resident getResident(Long residentId) {
        return DBQuery.getResident(residentId);
    }
    public static ArrayList<Resident> getResidentList() {
        return DBQuery.getResidentList();
    }
    public static ArrayList<Resident> getResidentList(int floor, int room) {
        return DBQuery.getResidentList(floor, room);
    }
}
