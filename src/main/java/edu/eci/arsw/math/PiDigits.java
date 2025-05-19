package edu.eci.arsw.math;

/// <summary>
/// An implementation of the Bailey-Borwein-Plouffe formula for calculating hexadecimal
/// digits of pi.
/// https://en.wikipedia.org/wiki/Bailey%E2%80%93Borwein%E2%80%93Plouffe_formula
/// *** Translated from C# code: https://github.com/mmoroney/DigitsOfPi ***
/// </summary>
public class PiDigits {

    private static int DigitsPerSum = 8;
    private static double Epsilon = 1e-17;

    public static byte[] getDigits(int start, int count, int N) {
        if (start < 0 || count < 0 || N <= 0) {
            throw new RuntimeException("Parámetros inválidos.");
        }

        byte[] result = new byte[count];
        PiThread[] threads = new PiThread[N];

        int digitsPerThread = count / N;
        int remainder = count % N;

        for (int i = 0; i < N; i++) {
            int currentStart = start + i * digitsPerThread;
            int currentCount = (i == N - 1) ? digitsPerThread + remainder : digitsPerThread;
            int writeIndex = i * digitsPerThread;

            threads[i] = new PiThread(currentStart, currentCount, result, writeIndex);
            threads[i].start();
        }

        for (PiThread t : threads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        return result;
    }

    public static byte[] getDigits(int start, int count) {
        byte[] digits = new byte[count];

        for (int i = 0; i < count; i++) {
            double pi = 4 * (sum(1, start + i) - sum(4, start + i)
                    - sum(5, start + i) - sum(6, start + i));
            digits[i] = (byte) ((int) (pi) & 0xF);
        }

        return digits;
    }

    private static double sum(int m, int n) {
        double sum = 0;
        int d = m;
        int power = n;

        while (true) {
            double term;

            if (power > 0) {
                term = (double) hexExponentModulo(power, d) / d;
            } else {
                term = Math.pow(16, power) / d;
                if (term < Epsilon) {
                    break;
                }
            }

            sum += term;
            power--;
            d += 8;
        }

        return sum;
    }

    private static int hexExponentModulo(int p, int m) {
        int power = 1;
        while (power * 2 <= p) {
            power *= 2;
        }

        int result = 1;

        while (power > 0) {
            if (p >= power) {
                result *= 16;
                result %= m;
                p -= power;
            }

            power /= 2;

            if (power > 0) {
                result *= result;
                result %= m;
            }
        }

        return result;
    }
}
