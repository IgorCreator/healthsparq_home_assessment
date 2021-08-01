package org.demo;

import java.util.Objects;

public class CSVObject {

    private final String name;
    private final String state;
    private final String motto;
    private final String mayor;

    private static final String DELIMITER = ",";

    public CSVObject(String line) {
        String[] entry = line.split(DELIMITER);
        if(entry.length != 4) throw new IllegalArgumentException("Incorrect line format: " + line);

        this.name = entry[0];
        this.state = entry[1];
        this.motto = entry[2];
        this.mayor = entry[3];
    }

    public String getName() {
        return name;
    }

    public String getState() {
        return state;
    }

    public String getMotto() {
        return motto;
    }

    public String getMayor() {
        return mayor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CSVObject csvObject = (CSVObject) o;
        return Objects.equals(name, csvObject.name) && Objects.equals(state, csvObject.state) && Objects.equals(motto, csvObject.motto) && Objects.equals(mayor, csvObject.mayor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, state, motto, mayor);
    }

    @Override
    public String toString() {
        return name
                + DELIMITER + state
                + DELIMITER + motto
                + DELIMITER + mayor;
    }
}