package org.usfirst.frc.team6414.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Created by willson on 2017/2/16.
 *
 * @author willson
 *         published under GNU Protocol
 */
public abstract class MonitoredSystem extends Subsystem {

    private Thread monitor;                 //This thread is used to monitor the whole subsystem
    private boolean isRunning = true;       //run state indicator.
    private long sleepTime = 200;           //How many milliseconds between 2 inspect

    /**
     * This method is to initialize the monitor thread or replace the exiting thread.
     * WARNING! STOP THE THREAD BEFORE REPLACING IT!
     *
     * @param me Usually a lambda expression which decide what to monitor or customized functions
     */
    void threadInit(Runnable me) {
        monitor = new Thread(() -> {
            while (isRunning) {
                me.run();
                try {
                    Thread.sleep(sleepTime);        //control the speed to reduce CPU usage & network usage
                } catch (Exception a) {
                    a.printStackTrace();
                }
            }
        });
    }

    /**
     * Sleep time setter. used to set interval between two monitoring actions.
     * @param sleepTime interval between two monitoring actions.
     */
    public void setSleepTime(long sleepTime) {
        this.sleepTime = sleepTime;
    }

    /**
     * Start monitoring. Usually called in robotInit();
     */
    public void startMonitor() {
        monitor.start();
    }

    /**
     * Stop monitoring, The thread will break the while() and stop naturally.
     * Usually called in disableInit();
     * IMPORTANT: EVERY THREAD CREATED SHOULD BE END THIS WAY.
     * IF THE THREAD WAS NOT STOPPED, IT WILL PREVENT SOME SINGLETON FORM BEING gc().
     * NEXT TIME ENABLING THE ROBOT WILL CAUSE ERROR FOR TRYING TO CREATE THE SECOND INSTANCE OF A SINGLETON.
     */
    public void stopMonitor() {
        isRunning = false;
    }

    /**
     * Empty. Make sure to override it in subsystems.
     */
    @Override
    protected abstract void initDefaultCommand();
}
