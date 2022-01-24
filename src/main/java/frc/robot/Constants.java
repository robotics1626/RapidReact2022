// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
    // Operator and Drive Controllers
    public static final int CONTROLLER_OPERATOR = 0;
    public static final int JOYSTICK_LEFT = 1;
    public static final int JOYSTICK_RIGHT = 2;

    // Drive Train
    public static final int DRIVE_FRONT_LEFT = 0;
    public static final int DRIVE_REAR_LEFT = 1;
    public static final int DRIVE_FRONT_RIGHT = 19;
    public static final int DRIVE_REAR_RIGHT = 18;

    // Compressor
    public static final int COMPRESSOR = 0;
    
    // Piston
    public static final int SOLENOID = 0;
    public static final int [] DOUBLE_SOLENOID = new int[] {1, 2};
}
