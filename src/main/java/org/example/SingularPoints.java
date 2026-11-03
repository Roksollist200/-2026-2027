package org.example;
/* * Аналитический поиск точки равновесия (I*, G*) для S = 0.
 *
 * Формулы:
 *   Зона 1: I* = 0, G* = G0 = 5.5
 *   Зона 2: I* = 0, G* = G0 = 5.5 (граница)
 *   Зона 3: G* = ((alpha*sigma/betta)*G0 + mu*Gcr)/((alpha*sigma/betta)+mu), I* = alpha*(G*-G0)/(beta*G*)
 */
public class SingularPoints {
   public final double I, G;

    public SingularPoints(double i, double g) {
        this.I = i;
        this.G = g;
    }

    public static SingularPoints find(PatientParameters pp) {
        double G_sing = (((pp.getAlpha() * pp.getSigma() / pp.getBetta()) * pp.getG0()) + pp.getMu() * pp.getGCritical()) / ((pp.getAlpha() * pp.getSigma() / pp.getBetta()) + pp.getMu());

        // Если G* >= Gcr (10), то равновесие в Зоне 3
        if (G_sing >= pp.getGCritical()) {
            double I_sing = (pp.getAlpha() * (G_sing - pp.getG0())) / pp.getBetta() * G_sing;
            return new SingularPoints(I_sing, G_sing);
        }
        return new SingularPoints(0.0, pp.getG0());
    }

    @Override
    public String toString() {
        return String.format("I* = %.5f, G* = %.5f", I, G);
    }
}
