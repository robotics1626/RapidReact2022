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
    /** Operator and Driver Controllers */
    public static final class Controller {
        public static final int OPERATOR = 0;
        public static final class Driver {
            public static final int LEFT = 1;
            public static final int RIGHT = 2;
            public static final double CURVE = 3;
        }
    }

    /** Power Distribution Hub */
    public static final int PDH = 1;

    /** Pneumatic Hub */
    public static final int REVPH = 1;

    /** Compressor */
    public static final int COMPRESSOR = 0;

    /** Drive Train */
    public static final class Drivetrain {
        public static final class Motor {
            public static final int LEFT_FRONT = 3;
            public static final int LEFT_REAR = 1;
            public static final int RIGHT_FRONT = 4;
            public static final int RIGHT_REAR = 2;
        }
    }

    /** Intake */
    public static final class Intake {
        public static final class Motor {
            public static final int LEFT = 5;
            public static final int RIGHT = 6;
        }
        public static final class Piston {
            public static final int [] LEFT = new int[] {8, 9};
            public static final int [] RIGHT = new int[] {6, 7};
        }
    }

    /** Indexer */
    public static final class Indexer {
        public static final class Motor {
            public static final int LEFT = 7;
            public static final int RIGHT = 8;
        }
    }

    /** Gatekeeper */
    public static final class Gatekeeper {
        public static final int MOTOR = 9;
        
    }

    /** Shooter */
    public static final class Shooter {
        public static final class Motor {
            public static final int LEFT = 10;
            public static final int RIGHT = 11;
        }
    }

    /** Climber */
    public static final class Climber {
        public static final int MOTOR = 12;
    }
}
