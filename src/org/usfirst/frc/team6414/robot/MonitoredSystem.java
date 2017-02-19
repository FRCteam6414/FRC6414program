package org.usfirst.frc.team6414.robot;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Created by willson on 2017/2/16.
 *
 * @author willson
 *         published under GNU Protocol
 */
public class MonitoredSystem extends Subsystem {

    protected Thread monitor;

    protected void threadInit(MoniterExe me) {
        monitor = new Thread(() -> {
            while (true) {
                me.run();
            }
        });
        monitor.start();
    }

    @Override
    protected void initDefaultCommand() {
    }
}
