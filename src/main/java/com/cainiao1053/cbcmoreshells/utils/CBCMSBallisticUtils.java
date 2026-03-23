package com.cainiao1053.cbcmoreshells.utils;

import net.minecraft.util.Mth;
import net.minecraft.world.phys.Vec3;

public class CBCMSBallisticUtils {
    private static final double EULER = Math.E;
    private static final double MIN_Z = -1.0 / EULER;
    private static final int    MAX_ITERS = 40;
    private static final double TOL = 1e-14;

    public static double solveW(double dy, double theta, double g, double v, double cd) {
        double gS = 400.0 * g;
        double cdT = (1.0 - cd) / 0.05;
        double c = Math.cos(theta);
        if (Math.abs(c) < 1e-12) return Double.NaN;

        double denom = v / cdT;                // v / cdT
        double K1 = 1.0 / (c * denom);         // = cdT / (v c)
        double K0 = 1.0 + 5.0 / denom;         // = 1 + 5 cdT / v
        double a  = gS / (cdT * v * c) + Math.tan(theta);
        double b  = gS / (cdT * cdT);
        double d  = -(gS / cdT) * 5.0 / v + 2.0 - dy;

        if (Math.abs(a) < 1e-14) {
            double E = Math.exp(-d / b);
            double w = (K0 - E) / K1;
            return validateW(w, K0, K1) ? w : Double.NaN;
        }

        double A = K0 / K1;
        double C = -a / b;
        double E = Math.exp(-d / b);
        double B = -E / K1;

        double z = -C * B * Math.exp(C * A);

        double W0 = lambertW0(z);
        double w  = A - (1.0 / C) * W0;
        if (validateW(w, K0, K1)) return w;

        if (z >= -1.0 / EULER && z < 0.0) {
            double Wm1 = lambertWm1(z);
            double w2  = A - (1.0 / C) * Wm1;
            if (validateW(w2, K0, K1)) return w2;
        }

        return Double.NaN;
    }

    private static boolean validateW(double w, double K0, double K1) {
        if (!Double.isFinite(w)) return false;
        if (w < 0.0) return false;
        double inner = K0 - K1 * w;
        return inner > 0.0 && Double.isFinite(inner);
    }

    public static double lambertW0(double z) {
        if (z < MIN_Z) return Double.NaN;
        if (z == 0.0) return 0.0;
        double w = initialGuessW0(z);
        return halleyIter(z, w);
    }

    public static double lambertWm1(double z) {
        if (!(z >= MIN_Z && z < 0.0)) return Double.NaN;
        double w = initialGuessWm1(z);
        return halleyIter(z, w);
    }

    private static double halleyIter(double z, double w0) {
        double w = w0;
        for (int i = 0; i < MAX_ITERS; i++) {
            double ew = Math.exp(w);
            double wew = w * ew;
            double f = wew - z;
            double wp1 = w + 1.0;
            double denom = ew * wp1 - 0.5 * (wp1 + 1.0) * f / wp1;
            if (denom == 0.0) denom = ew * wp1;
            double dw = f / denom;
            double wNext = w - dw;

            if (!Double.isFinite(wNext)) {
                double f1 = ew * wp1;
                double f2 = ew * (w + 2.0);
                double halley = (2.0 * f * f1) / (2.0 * f1 * f1 - f * f2);
                wNext = w - halley;
            }

            if (Math.abs(wNext - w) <= TOL * Math.max(1.0, Math.abs(wNext))) return wNext;
            w = wNext;
        }
        return w;
    }

    private static double initialGuessW0(double z) {
        if (z >= 8.0) {
            return Math.log(z) - Math.log(Math.log(z)); // 渐近
        } else if (z > -0.3) {
            return z;
        } else {
            double q = z + MIN_Z * -1.0;
            q = z + 1.0 / EULER;
            double t = Math.sqrt(Math.max(0.0, 2.0 * EULER * q));
            return -1.0 + t - t * t / 3.0;
        }
    }


    private static double initialGuessWm1(double z) {
        double q = z + 1.0 / EULER;
        double t = Math.sqrt(Math.max(0.0, 2.0 * EULER * q));
        double w = -1.0 - t - t * t / 3.0;
        if (z > -1e-3) {
            w = Math.log(-z);
        }
        return w;
    }

    public static double cannonFunction(double theta, double w, double g, double v, double cd) { //v = 40*n
        g = 400*g;
        double cdT = (1-cd)/0.05;
        double c = Math.cos(theta);
        if (Math.abs(c) < 1e-12) {
            return Math.copySign(0, Math.tan(theta));
        }

        double term1 = ((g / cdT) / (v)) / c + Math.tan(theta);
        term1 *= w;

        double denom = (1.0 / cdT) * v;
        double inner = 1.0 - (w / c - 5) / denom; // k = 5

        if (inner <= 0.0) {
            return Math.copySign(0, -1.0);
        }

        double term2 = (g / (cdT * cdT)) * Math.log(inner);
        double term3 = -(g / cdT) * 5 / (v);
        double term4 = 2.0;

        return term1 + term2 + term3 + term4;
    }

    public static double vecToPitch(Vec3 v) {
        if (v == null) return 0;

        Vec3 n = v.normalize();
        if (Double.isNaN(n.x) || Double.isNaN(n.y) || Double.isNaN(n.z)) {
            return 0;
        }

        // Minecraft约定：
        // yaw: 围绕Y轴，0朝南(+Z)，90朝西(-X)，-90朝东(+X)
        // pitch: 抬头为负，低头为正
        //float yaw = (float) (Mth.atan2(n.z, n.x) * Mth.RAD_TO_DEG) - 90f;

        // 水平长度
        double xz = Math.sqrt(n.x * n.x + n.z * n.z);
        double pitch = (Mth.atan2(n.y, xz));

        // 按MC习惯：向上看为负
        //pitch = -pitch;

        return clampPitch(pitch);
    }

    private static float wrapDeg(float deg) {
        return Mth.wrapDegrees(deg);
    }

    private static double clampPitch(double pitch) {
        return Mth.clamp(pitch, -Math.PI/2, Math.PI/2);
    }
}
