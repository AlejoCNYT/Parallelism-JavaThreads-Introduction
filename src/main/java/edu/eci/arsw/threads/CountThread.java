/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.threads;

/**
 *
 * @author hcadavid
 */
<<<<<<< HEAD
public class CountThread extends Thread
{
    private int start;
    private int end;

    public CountThread(int start, int end)
    {
        this.start = start;
        this.end = end;
    }

    @Override
    public void run() {
        for (int i = start; i <= end; i++)
        {
            System.out.println(i);
        }
    }
=======
public class CountThread {
    
>>>>>>> 5042d342834489c8c281feb337c038fd344a8eec
}
