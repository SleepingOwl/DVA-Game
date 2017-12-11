import java.awt.*;

/**
 * PROGRAM: A Java xeyes starter
 * PURPOSE: Show how to get the mouse cursor location (position)
 *          outside a Java JFrame.
 * DATE:    March 27, 2011
 * VERSION: 0.1
 * COPYRIGHT:
 * Copyright 2011, alvin j. alexander, http://devdaily.com
 * This work is licensed under a Creative Commons
 * Attribution-ShareAlike 3.0 Unported License;
 * see http://creativecommons.org/licenses/by-sa/3.0/
 * for more information.
 *
 */
public class Test
{
    public static void main(String[] args)
    {
        while (true)
        {
            PointerInfo info = MouseInfo.getPointerInfo();
            Point p = info.getLocation();
            System.out.format("LOC %d, %d\n", p.x, p.y);
            sleep();
        }
    }

    private static void sleep()
    {
        try
        {
            Thread.sleep(300);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }
}
