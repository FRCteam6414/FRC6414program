package org.usfirst.frc.team6414.robot.subsystems;

import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team6414.robot.RobotMap;

/**
 * Created by willson on 2017/3/11.
 *
 * @author willson
 *         published under GNU Protocol
 */
public class USensor extends MonitoredSystem {

    private DigitalOutput leftPulse;
    private Counter left;
    private double leftDistant = -1;

    private DigitalOutput rightPulse;
    private Counter right;
    private double rightDistant = -1;

    public USensor() {
        leftPulse = new DigitalOutput(RobotMap.LEFT_PULSE);
        left = new Counter(RobotMap.LEFT_ECHO);
        left.setMaxPeriod(1);
        left.setSemiPeriodMode(true);
        left.reset();

        rightPulse = new DigitalOutput(RobotMap.RIGHT_PULSE);
        right = new Counter(RobotMap.RIGHT_ECHO);
        right.setMaxPeriod(1);
        right.setSemiPeriodMode(true);
        right.reset();

        threadInit(() -> {
            leftPulse.pulse(RobotMap.US_PULSE);
            rightPulse.pulse(RobotMap.US_PULSE);

            do {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } while (left.get() < 2 || right.get() < 2);

            leftDistant = left.getPeriod() * 0.5 * RobotMap.SPEED_OF_SOUND;
            rightDistant = right.getPeriod() * 0.5 * RobotMap.SPEED_OF_SOUND;

            SmartDashboard.putNumber("left dis", leftDistant);
            SmartDashboard.putNumber("right dis", rightDistant);

            left.reset();
            right.reset();
        });
    }

    private double square(double in) {
        return in * in;
    }

    public double getLeftDistant() {
        return leftDistant;
    }

    public double getRightDistant() {
        return rightDistant;
    }

    public double getDistant() {
        return (0.5 * RobotMap.SENSOR_DIST * (leftDistant + rightDistant)) / Math.sqrt(square(RobotMap.SENSOR_DIST) + square(leftDistant - rightDistant));
    }

    @Override
    protected void initDefaultCommand() {
//        setDefaultCommand(new HangGear());
    }
}

