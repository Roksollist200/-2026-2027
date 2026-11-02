package org.example;

public class Рarameters {
    //Общие константы для всех людей
    private static final double G_0 = 5.5;              //Нормальный уровень глюкозы
    private static final double G_critical = 10.0;      //Критический уровень глюкозы

    //индивидуальные константы

    private double alpha;                               //Чувствительность к глюкозе 1/ч;
    private double betta;                               //Чувствительность к глюкозе 1/Eд * ч
    private double gamma;                               //Коэффициент выхода глюкозы из печени 1/ч
    private double sigma;                               //Коэффициент влияния инсулина на утилизацию глюкозы 1/Ед * ч
    private double mu;                                  //Коэффициент почечного выведения глюкозы, если превышается критический уровень 1/ч

    public Parameters(double alpha,double betta,double gamma,double sigma,double mu){

    }

}
