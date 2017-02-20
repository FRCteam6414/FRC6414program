package org.usfirst.frc.team6414.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc.team6414.robot.Robot;

/**
 * Created by willson on 2017/2/16.
 *
 * @author willson
 *         published under GNU Protocol
 */
public class MonitoredSystem extends Subsystem {

    void threadInit(MonitorExe me) {
        new Thread(() -> {
            while (!Robot.staticIsEnabled()) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            while (Robot.staticIsEnabled()) {
                me.run();
                try {
                    Thread.sleep(200);
                } catch (Exception a) {
                    a.printStackTrace();
                }
            }
        }).start();
    }

    @Override
    protected void initDefaultCommand() {
    }

    /**
     * Created by willson on 2017/2/19.
     *
     * @author willson
     *         published under GNU Protocol
     */
    public interface MonitorExe {
        void run();
    }
}
