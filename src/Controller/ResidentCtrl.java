package Controller;

import java.sql.Date;
import java.util.ArrayList;

import Model.Activity;
import Model.Resident;
import SQLServer.DBQuery;

public class ResidentCtrl {
    public static boolean addActivity(Activity activity) {
        return DBQuery.addActivity(activity);
    }

    public static boolean addResident(Resident resident) {
        return DBQuery.addResident(resident);
    }

    public static boolean deleteResident(ArrayList<Long> selections) {
        for (Long residentId : selections) {
            if (!addActivity(new Activity(
                residentId,
                3,
                null,
                new Date(System.currentTimeMillis()),
                ""))) {
                    return false;
                }
        }

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

    public static ArrayList<Activity> getHistory(Long residentId) {
        return DBQuery.getHistory(residentId);
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