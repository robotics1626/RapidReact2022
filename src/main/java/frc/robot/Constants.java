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
    public static final double JOYSTICK_CURVE = 5/3;

    // Power Distribution Hub
    public static final int PDH = 1;
    public static final int PDH_DRIVE_REAR_RIGHT = 0;
    public static final int PDH_DRIVE_FRONT_RIGHT = 1;
    public static final int PDH_DRIVE_FRONT_LEFT = 18;
    public static final int PDH_DRIVE_REAR_LEFT = 19;
    public static final int PDH_INTAKE_BELT_LEFT = 2;

    // Pneumatic Hub
    public static final int PNEUMATIC_HUB = 1;

    // Drive Train
    public static final int DRIVE_FRONT_LEFT = 3;
    public static final int DRIVE_REAR_LEFT = 1;
    public static final int DRIVE_FRONT_RIGHT = 4;
    public static final int DRIVE_REAR_RIGHT = 2;

    // Compressor
    public static final int COMPRESSOR = 0;
    
    // Intake
    public static final int INTAKE_BELT_LEFT = 5;
    public static final int INTAKE_BELT_RIGHT = 6;
    public static final int [] INTAKE_ARM_LEFT = new int[] {0, 1};
    public static final int [] INTAKE_ARM_RIGHT = new int[] {2, 3};

    // Indexer
    public static final int INDEXER = 7;

    // Shooter
    public static final int SHOOTER = 8;
    public static final double SHOOTER_RPM = 3000;
   
}
