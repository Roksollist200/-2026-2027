package org.example;

public class InsulinGlucoseZoneFirst {
    /* Случай когда человек ничего не ел и инсулин равен 0 G<5.5;
    Система уравнений имеет вид:
    * dI/dt = betta*I*G
    *dG/dt = gamma(G0-G)-sigma*I*G +S
    */
    public double dI_dt(double I, double G, double S, PatientParameters pp){
        return -pp.getBetta() * I * G ;
    }

    public double dG_dt(double I, double G, double S, PatientParameters pp){
        return pp.getGamma() * (pp.getG0()-G)-pp.getSigma() * I * G + S;
    }




}
