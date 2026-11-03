package org.example;

public class EigenValues {

    /* a-lambda     b
           c         d-lambda

   (a-lambda)(d-lambda)-cb =ad -a *lambda - lambda*d +lambda^2 - cb =
   =lambda^2 - lambda (a+d) -cb + ad
   */
    private void calculateDeterminant(double a, double b, double c, double d) {
        double determinant = a * d - c * b;
        double A = 1.0;
        double B = -(a + d);
        double C = a * d - b * c;
        double discriminant = B * B - 4 * A * C;
        System.out.printf("Дискриминант: D = %.5f%n", discriminant);
        System.out.println("Собственные значения:");
        ;
        if (discriminant >= 0) {
            double sqrtD = Math.sqrt(discriminant);
            double lambda1 = (-B + sqrtD) / (2 * A);
            double lambda2 = (-B - sqrtD) / (2 * A);
            System.out.println("lambda1 " + lambda1);
            System.out.printf("lambda2 " + lambda2);
            System.out.println("\nИнтерпретация " + classifyReal(lambda1, lambda2));

        } else {
            double real = -B / (2 * A);
            double imag = Math.sqrt(-discriminant) / (2 * A);

            // Используем printf для красивого вывода (5 знаков после запятой)
            System.out.printf("lambda1 = %.5f + %.5fi%n", real, imag);
            System.out.printf("lambda2 = %.5f - %.5fi%n", real, imag); // Здесь МИНУС!

            System.out.println("\nИнтерпретация: " + classifyComplex(real));
        }
    }

    /*
     * Устойчивость в зоне 1 G<5.5
     * Матрица Якоби имеет вид
     * -betta*G |(0;5.5)       -betta *I|(0;5.5) )
     * -sigma *G |(0;5.5)      -gamma-sigma * I|(0;5.5))
     *
     * -5.5*betta       0
     * -5.5 * sigma     -gamma
     *

     */
    private void printMatrixJacoby(double a, double b, double c, double d) {
        System.out.println("Матрица Якоби  зоны:");
        System.out.printf("| %7.5f  %7.5f |%n", a, b);
        System.out.printf("| %7.5f  %7.5f |%n", c, d);
    }

    private String classifyReal(double lambda1, double lambda2) {
        if (lambda1 * lambda2 < 0) {
            return "Седло (неустойчиво). lambda1 и lambda2 имеют противоположные знаки";
        } else if (lambda1 * lambda2 > 0) {
            if (Math.abs(lambda1) < Math.abs(lambda2)) {
                return "Устойчивый узел |lambda1| < |lambda2| (касание оси ординат)";
            } else {
                return "Устойчивый узел |lambda1| > |lambda2| (касание оси абсцисс)";
            }
        } else if (lambda1 > 0 && lambda2 > 0) {
            if (Math.abs(lambda1) < Math.abs(lambda2)) {
                return "Неустойчивый узел |lambda1| < |lambda2|";
            } else {
                return "Неустойчивый узел |lambda1| > |lambda2|";
            }
        }
        return "Вырожденный случай (один из корней равен нулю)";
    }

    /*
     * Классификация для КОМПЛЕКСНЫХ корней (Случаи 3 и 4 из методички).
     */
    private String classifyComplex(double realPart) {
        if (Math.abs(realPart) < 1e-10) {
            return "Центр (устойчив по Ляпунову). alpha = 0. Замкнутые траектории";
        } else if (realPart < 0) {
            return "Устойчивый фокус (спираль закручивается) αlpha < 0";
        } else {
            return "Неустойчивый фокус (спираль раскручивается) αlpha > 0";
        }
    }

    public void analyzeZoneFirst(PatientParameters pp) {
        System.out.println("Человек ничего не ел(((((");
        double a = -pp.getBetta() * pp.getG0();
        double b = 0.0;
        double c = -pp.getSigma() * pp.getG0();
        double d = -pp.getGamma();
        printMatrixJacoby(a, b, c, d);
        calculateDeterminant(a, b, c, d);
    }

    /*
     * * Устойчивость в зоне 2 G0<G<G_CRIT
     * Матрица Якоби имеет вид
     * -betta*G |(0;5.5)       alpha - betta * I|(0;5.5) )
     * -sigma *G |(0;5.5)      -sigma*I(0;5.5))
     *
     * -5.5*betta       alpha
     * -5.5 * sigma     0
     * */
    public void analyzeZoneSecond(PatientParameters pp) {
        System.out.println("\n Скушал пончик");
        double a = -pp.getG0() * pp.getBetta();
        double b = pp.getAlpha();
        double c = -pp.getSigma() * pp.getG0();
        double d = 0.0;
        printMatrixJacoby(a, b, c, d);
        calculateDeterminant(a, b, c, d);
    }

    public void analizeZoneThird(PatientParameters pp) {
        System.out.println("\nСкушал тортик и три пончика");
        SingularPoints sp = SingularPoints.find(pp);
        if (sp.G < pp.getGCritical()) {
            System.out.printf("Точка равновесия G* = %.4f < Gcr = %.2f%n", sp.G, pp.getGCritical());
            System.out.println("Равновесие в Зоне 3 не существует при данных параметрах (система остается в норме).");
            return;
        }
        double I_sing = sp.I;
        double G_sing = sp.G;

        double a = -pp.getBetta() * G_sing;
        double b = pp.getAlpha() - pp.getBetta() * I_sing;
        double c = -pp.getSigma() * G_sing;
        double d = -pp.getSigma() * I_sing - pp.getMu();

        System.out.printf("\nТочка равновесия (I*, G*): (%.4f, %.4f)%n", I_sing, G_sing);
        calculateDeterminant(a, b, c, d);
    }
}


