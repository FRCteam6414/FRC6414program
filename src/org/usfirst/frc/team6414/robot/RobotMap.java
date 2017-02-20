package org.usfirst.frc.team6414.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
    public static final int CHASSIS_ADJUST = 2;
    public static final int INTAKE_FWD = 3;
    public static final int MIXER_FWD = 4;
    public static final int INTAKE_BWD = 5;
    public static final int MIXER_BWD = 6;
    public static final int SET_SHOOTER_DEF = 7;
    static final int STICK = 0;

    public static final int RIGHT_MASTER = 1;
    public static final int RIGHT_SLAVE = 2;
	public static final int LEFT_MASTER = 3;
	public static final int LEFT_SLAVE = 4;
    public static final int INTAKE_MOTOR = 5;
	public static final int LEFT_SHOOTER = 6;
	public static final int RIGHT_SHOOTER = 7;
	public static final int MIXER_MOTOR = 8;

    public static final double INTAKE_DEF = 1;
    public static final double SHOOTER_DEFAULT = 0.7;
    public static final double MIXER_DEF =0.5;
}
