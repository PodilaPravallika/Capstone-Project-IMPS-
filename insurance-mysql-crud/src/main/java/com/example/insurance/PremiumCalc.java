package com.example.insurance;
public class PremiumCalc {
    public static double calculate(String type, int age, double coverage){
        double base = switch(type.toUpperCase()){
            case "LIFE" -> 1000;
            case "HEALTH" -> 800;
            case "VEHICLE" -> 600;
            default -> 700;
        };
        double ageFactor = (age <= 25) ? 0.9 : (age <= 40 ? 1.0 : (age <= 60 ? 1.3 : 1.6));
        double coverageFactor = Math.max(coverage / 100000.0, 0.5);
        return Math.round(base * ageFactor * coverageFactor * 100.0) / 100.0;
    }
}
