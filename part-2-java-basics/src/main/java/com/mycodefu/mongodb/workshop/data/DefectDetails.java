package com.mycodefu.mongodb.workshop.data;

public class DefectDetails {
    private String name;
    private Severity severity;

    public static DefectDetails of(String name, Severity severity) {
        DefectDetails defectDetails = new DefectDetails();
        defectDetails.name = name;
        defectDetails.severity = severity;
        return defectDetails;
    }

    public String getName() {
        return name;
    }

    public Severity getSeverity() {
        return severity;
    }

    @Override
    public String toString() {
        return "DefectDetails{" +
                "name='" + name + '\'' +
                ", severity=" + severity +
                '}';
    }
}
