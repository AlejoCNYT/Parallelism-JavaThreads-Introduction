package edu.eci.arsw.math;

public class PiThread extends Thread {

    private int computeStart;
    private int count;
    private byte[] result;
    private int writeIndex;

    public PiThread(int computeStart, int count, byte[] result, int writeIndex) {
        this.computeStart = computeStart;
        this.count = count;
        this.result = result;
        this.writeIndex = writeIndex;
    }

    @Override
    public void run() {
        byte[] partialResult = PiDigits.getDigits(computeStart, count);
        System.arraycopy(partialResult, 0, result, writeIndex, count);
    }
}
