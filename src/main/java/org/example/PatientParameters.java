package org.example;

public class PatientParameters {
    //Общие константы для всех людей
    private static final double G_0 = 5.5;              //Нормальный уровень глюкозы
    private static final double G_critical = 10.0;      //Критический уровень глюкозы

    //индивидуальные константы

    private double alpha;                               //Чувствительность к глюкозе 1/ч;
    private double betta;                               //Утилизация инсулина глюкозой 1/Eд * ч
    private double gamma;                               //Коэффициент выхода глюкозы из печени 1/ч
    private double sigma;                               //Коэффициент влияния инсулина на утилизацию глюкозы 1/Ед * ч
    private double mu;                                  //Коэффициент почечного выведения глюкозы, если превышается критический уровень 1/ч

    /**
     * Конструктор для создания профиля пациента с индивидуальными параметрами.
     * @param alpha коэффициент отвечающий за чувствительность к глюкозе
     * @param betta коэффициент отвечающий за утилизацию инсулина глюкозой
     * @param gamma коэффициент отвечающий за выход глюкозы из печени
     * @param sigma коэффициент отвечающий за влияние инсулина на утилизацию глюкозы
     * @param mu коэффициент отвечающий за почечное выведение глюкозы
     */
    public PatientParameters(double alpha,double betta,double gamma,double sigma,double mu){
        this.alpha = alpha;
        this.betta = betta;
        this.sigma = sigma;
        this.gamma = gamma;
        this.mu = mu;
    }

    public double getAlpha() {
        return alpha;
    }

    public double getBetta() {
        return betta;
    }

    public double getSigma() {
        return sigma;
    }

    public double getGamma() {
        return gamma;
    }

    public double getMu() {
        return mu;
    }

    public  double getG_critical() {
        return G_critical;
    }

    public  double getG0() {
        return G_0;
    }
}
