package com.mycodefu.mongodb.workshop.data;

public class DefectDetails {
    public String name;
    public Severity severity;

    public static DefectDetails of(String name, Severity severity) {
        DefectDetails defectDetails = new DefectDetails();
        defectDetails.name = name;
        defectDetails.severity = severity;
        return defectDetails;
    }

    @Override
    public String toString() {
        return "DefectDetails{" +
                "name='" + name + '\'' +
                ", severity=" + severity +
                '}';
    }
}
