package org.example;

public class InsulinGlucoseZoneThird {
    /*критический уровень глюкозы G>=10
    Система уравнений имеет вид:
    * dI/dt = alpha * (G - G0) - beta * I * G
    * dG/dt = -sigma * I * G - mu(G - G_cr) + S

    */

    public double dI_dt(double I, double G, double S, PatientParameters pp) {
        return pp.getAlpha()*(G-pp.getG0()) - pp.getBetta() * I *G;
    }

    public double dG_dt(double I, double G, double S, PatientParameters pp){
        return -pp.getSigma() * I * G -pp.getMu() *(G- pp.getGCritical())  + S;
    }

}
