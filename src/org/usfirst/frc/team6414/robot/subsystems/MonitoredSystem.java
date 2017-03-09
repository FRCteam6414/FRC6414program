package org.usfirst.frc.team6414.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Created by willson on 2017/2/16.
 *
 * @author willson
 *         published under GNU Protocol
 */
public abstract class MonitoredSystem extends Subsystem {

    private Thread moniter;
    private boolean isRunning = true;

    void threadInit(Runnable me) {
        moniter = new Thread(() -> {
            while (isRunning) {
                me.run();
                try {
                    Thread.sleep(200);
                } catch (Exception a) {
                    a.printStackTrace();
                }
            }
        });
    }

    public void startMonitor() {
        moniter.start();
    }

    public void stopMonitor() {
        isRunning = false;
    }

    @Override
    protected void initDefaultCommand() {
    }
}
