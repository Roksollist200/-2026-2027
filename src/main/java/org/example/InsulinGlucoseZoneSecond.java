package org.example;

public class InsulinGlucoseZoneSecond {
    /*Когда человек поел и G в промежутке от (5,5; 10)
    Система уравнений имеет вид:
    * dI/dt = alpha * (G - G0) - beta * I * G
    * dG/dt = -sigma * I * G + S
    */

    public double dI_dt(double I, double G, double S, PatientParameters pp) {
        return pp.getAlpha()*(G - pp.getG0()) - pp.getBetta() * I * G;
    }

    public double dG_dt(double I, double G, double S, PatientParameters pp){
        return -pp.getSigma() * I * G + S;
    }
}
