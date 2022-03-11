// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 *  This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
    /** Operator and Driver Controllers */
    public static final int CONTROLLER_OPERATOR = 0;
    public static final int JOYSTICK_LEFT = 1;
    public static final int JOYSTICK_RIGHT = 2;
    public static final double JOYSTICK_CURVE   = 5/3;
    //public static final double DRIVER_INPUT_CURVE = 3;

    /** Power Distribution Hub */
    public static final int PDH = 1;

    /** Pneumatic Hub */
    public static final int REVPH = 1;

    /** Compressor */
    public static final int COMPRESSOR = 0;

    /** Drive Train */
    public static final int DRIVE_FRONT_LEFT = 3;
    public static final int DRIVE_REAR_LEFT = 1;
    public static final int DRIVE_FRONT_RIGHT = 4;
    public static final int DRIVE_REAR_RIGHT = 2;
    
    /** Intake */
    public static final int INTAKE_MOTOR_LEFT = 5;
    public static final int INTAKE_MOTOR_RIGHT = 6;
    public static final double INTAKE_SPEED = 0.8;
    public static final int [] INTAKE_ARM_LEFT = new int[] {8, 9};
    public static final int [] INTAKE_ARM_RIGHT = new int[] {6, 7};

    /** Indexer */
    public static final int INDEXER_LEFT = 7;
    public static final int INDEXER_RIGHT = 8;

    /** Gatekeeper */
    public static final int GATEKEEPER = 9;

    /** Shooter */
    public static final int SHOOTER_LEFT = 10;
    public static final int SHOOTER_RIGHT = 11;

    /** Climber */
    public static final int CLIMBER_MOTOR = 12;
    public static final int [] CLIMBER_LEFT_UPPER = new int[] {10, 11};
    public static final int [] CLIMBER_LEFT_LOWER = new int[] {5, 4};
    public static final int [] CLIMBER_RIGHT_UPPER = new int[] {12, 13};
    public static final int [] CLIMBER_RIGHT_LOWER = new int[] {3, 2};
    
}
