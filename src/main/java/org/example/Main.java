package org.example;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

import java.util.Scanner;

public class Main {

    enum ZoneType {
        HUNGER,       // Натощак
        NOT_HUNGER,   // После еды
        CRITICAL      // Критическая
    }

    private static ZoneType getZoneType(double G,PatientParameters pp) {
        if (G < pp.getG0()) {
            return ZoneType.HUNGER;
        }
        else if(pp.getG0()<G && G<pp.getGCritical()){
            return  ZoneType.NOT_HUNGER;
        }
        else{
            return  ZoneType.CRITICAL;
        }

    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println(" Модель I-G: Симуляция (S = 0)");

        //Ввод параметров пациента
        System.out.println("\nИндивидуальные параметры");
        System.out.print("Alpha (1/ч): ");     double alpha = scanner.nextDouble();
        System.out.print("Betta (1/Ед*ч): "); double betta = scanner.nextDouble();
        System.out.print("Gamma (1/ч): ");     double gamma = scanner.nextDouble();
        System.out.print("Sigma (1/Ед*ч): "); double sigma = scanner.nextDouble();
        System.out.print("Mu (1/ч): ");        double mu = scanner.nextDouble();

        PatientParameters pp = new PatientParameters(alpha, betta, gamma, sigma, mu);

        System.out.println("\nНачальное состояние");
        System.out.print("Начальная глюкоза G: "); double G = scanner.nextDouble();
        System.out.print("Начальный инсулин I: ");   double I = scanner.nextDouble();


        //InsulinGlucoseZoneFirst  zone1 = new InsulinGlucoseZoneFirst();
        //InsulinGlucoseZoneSecond zone2 = new InsulinGlucoseZoneSecond();
        //InsulinGlucoseZoneThird  zone3 = new InsulinGlucoseZoneThird();

        //Аналитический поиск точки равновесия
        System.out.println("\nТочка равновесия (S = 0)");
        SingularPoints eq = SingularPoints.find(pp);
        System.out.println(eq);


         System.out.println("\nАнализ устойчивости");
         EigenValues eigen = new EigenValues();
         eigen.analyzeZoneFirst(pp);
         eigen.analyzeZoneSecond(pp);
         eigen.analizeZoneThird(pp);

        scanner.close();
    }
}