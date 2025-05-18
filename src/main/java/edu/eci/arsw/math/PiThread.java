package edu.eci.arsw.math;

public class PiThread extends Thread
{

    private int start;
    private int count;
    private byte[] result;

    public PiThread(int start, int count, byte[] result)
    {
        this.start = start;
        this.count = count;
        this.result = result;
    }

    @Override
    public void run()
    {
        byte[] partialResult = PiDigits.getDigits(start, count);
        System.arraycopy(partialResult, 0, result, start, count);
    }
}
