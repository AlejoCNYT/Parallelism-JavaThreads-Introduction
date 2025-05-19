package edu.eci.arsw.math;

public class PiExperiments
{

    public static void main(String[] args)
    {
        final int start = 0;
        final int count = 1_000_000;
        int cores = Runtime.getRuntime().availableProcessors();

        System.out.println("Número de núcleos disponibles: " + cores);

        // runExperiment("Un solo hilo", start, count, 1);
        // runExperiment("Hilos = núcleos", start, count, cores);
        // runExperiment("Hilos = 2 x núcleos", start, count, cores * 2);
        // runExperiment("200 hilos", start, count, 200);
        // runExperiment("500 hilos", start, count, 500);
    }

    public static void runExperiment(String name, int start, int count, int numThreads)
    {
        System.out.println("\n--- " + name + " ---");
        long startTime = System.currentTimeMillis();

        PiDigits.getDigits(start, count, numThreads);

        long endTime = System.currentTimeMillis();
        System.out.println("Tiempo con " + numThreads + " hilos: " + (endTime - startTime) + " ms");
    }
}
