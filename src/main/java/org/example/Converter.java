package org.example;

import javafx.collections.ObservableList;
import javafx.scene.control.TableView;

public class Converter {

    public static float fourScale(String grade) {

        float gpa;

        // Removes all spaces in the string and transforms it to uppercase
        // for string validation
        grade = grade.toUpperCase().replaceAll("\\s", "");

        // 4.0 GPA Scale as well as a default case that returns -1
        // to let the main program know of invalid entries to this method
        switch (grade) {
            case "A+":
                gpa = (float) 4.0;
                break;
            case "A":
            case "A-":
                gpa = (float) 3.8;
                break;
            case "B+":
                gpa = (float) 3.3;
                break;
            case "B":
            case "B-":
                gpa = (float) 3.0;
                break;
            case "C+":
                gpa = (float) 2.3;
                break;
            case "C":
            case "C-":
                gpa = (float) 2.0;
                break;
            case "D+":
                gpa = (float) 1.3;
                break;
            case "D":
            case "D-":
                gpa = (float) 1.0;
                break;
            case "E":
                gpa = (float) 0.7;
                break;
            case "F":
                gpa = (float) 0;
                break;
            default:
                gpa = (float) -1.0;
                break;
        }
        return gpa;
    }

    public static float yorkScale(String grade) {

        float gpa;

        // Removes all spaces in the string and transforms it to uppercase
        // for string validation
        grade = grade.toUpperCase().replaceAll("\\s", "");

        // York U GPA Scale as well as a default case that returns -1
        // to let the main program know of invalid entries to this method
        switch (grade) {
            case "A+":
                gpa = (float) 9.0;
                break;
            case "A":
            case "A-":
                gpa = (float) 8.0;
                break;
            case "B+":
                gpa = (float) 7.0;
                break;
            case "B":
            case "B-":
                gpa = (float) 6.0;
                break;
            case "C+":
                gpa = (float) 5.0;
                break;
            case "C":
            case "C-":
                gpa = (float) 4.0;
                break;
            case "D+":
                gpa = (float) 3.0;
                break;
            case "D":
            case "D-":
                gpa = (float) 2.0;
                break;
            case "E":
                gpa = (float) 1.0;
                break;
            case "F":
                gpa = (float) 0.0;
                break;
            default:
                gpa = (float) -1.0;
                break;
        }
        return gpa;
    }

    public static float ryersonScale(String grade) {

        float gpa;

        // Removes all spaces in the string and transforms it to uppercase
        // for string validation
        grade = grade.toUpperCase().replaceAll("\\s", "");

        // Ryerson GPA Scale as well as a default case that returns -1
        // to let the main program know of invalid entries to this method
        switch (grade) {
            case "A+":
                gpa = (float) 4.33;
                break;
            case "A":
                gpa = (float) 4.0;
                break;
            case "A-":
                gpa = (float) 3.67;
                break;
            case "B+":
                gpa = (float) 3.33;
                break;
            case "B":
                gpa = (float) 3.0;
                break;
            case "B-":
                gpa = (float) 2.67;
                break;
            case "C+":
                gpa = (float) 2.33;
                break;
            case "C":
                gpa = (float) 2.0;
                break;
            case "C-":
                gpa = (float) 1.67;
                break;
            case "D+":
                gpa = (float) 1.33;
                break;
            case "D":
                gpa = (float) 1.0;
                break;
            case "D-":
                gpa = (float) 0.67;
                break;
            case "E":
            case "F":
                gpa = (float) 0.0;
                break;
            default:
                gpa = (float) -1.0;
                break;
        }
        return gpa;
    }

    public static float uoftScale(String grade) {

        float gpa;

        // Removes all spaces in the string and transforms it to uppercase
        // for string validation
        grade = grade.toUpperCase().replaceAll("\\s", "");

        // UofT GPA Scale as well as a default case that returns -1
        // to let the main program know of invalid entries to this method
        switch (grade) {
            case "A+":
            case "A":
                gpa = (float) 4.0;
                break;
            case "A-":
                gpa = (float) 3.7;
                break;
            case "B+":
                gpa = (float) 3.3;
                break;
            case "B":
                gpa = (float) 3.0;
                break;
            case "B-":
                gpa = (float) 2.7;
                break;
            case "C+":
                gpa = (float) 2.3;
                break;
            case "C":
                gpa = (float) 2.0;
                break;
            case "C-":
                gpa = (float) 1.7;
                break;
            case "D+":
                gpa = (float) 1.3;
                break;
            case "D":
                gpa = (float) 1.0;
                break;
            case "D-":
                gpa = (float) 0.7;
                break;
            case "E":
            case "F":
                gpa = (float) 0.0;
                break;
            default:
                gpa = (float) -1.0;
                break;
        }
        return gpa;
    }

    public static Float cGPACalculator(ObservableList<Grade> grades, String scale){
        float weightedGPA = 0;
        float totalCredits = 0;

        // Adds credit multiple of gpa to weightedGPA to be able to do
        // weighted GPA calculation afterwards
        for (Grade grade : grades) {
            switch (scale) {
                case "four":
                    weightedGPA += grade.getFourGPA() * grade.getWeight();
                    totalCredits += grade.getWeight();
                    break;
                case "york":
                    weightedGPA += grade.getYorkGPA() * grade.getWeight();
                    totalCredits += grade.getWeight();
                    break;
                case "ryerson":
                    weightedGPA += grade.getRyersonGPA() * grade.getWeight();
                    totalCredits += grade.getWeight();
                    break;
                case "uoft":
                    weightedGPA += grade.getUoftGPA() * grade.getWeight();
                    totalCredits += grade.getWeight();
                    break;
            }
        }

        // Returns weighted GPA
        return (float) Math.round(100*(weightedGPA/totalCredits))/100;
    }

    public static String cGPALabel(TableView<Grade> table){
        // Updates Cumulative GPA and returns new label
        String fourCGPA = Converter.cGPACalculator(table.getItems(), "four").toString();
        String altScale = Columns.whichColumn(table);
        String altCGPA = Converter.cGPACalculator(table.getItems(), altScale).toString();
        return "Your cumulative 4.0 Scale GPA is: " + fourCGPA + " and your cumulative " +
                altScale.substring(0,1).toUpperCase() + altScale.substring(1) + " gpa is: " +  altCGPA;
    }
}
