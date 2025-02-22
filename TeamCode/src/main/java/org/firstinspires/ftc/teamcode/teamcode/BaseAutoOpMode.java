package org.firstinspires.ftc.teamcode.teamcode;//package org.firstinspires.ftc.teamcode;
//
//import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
//import com.qualcomm.robotcore.eventloop.opmode.Disabled;
//import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
//import com.qualcomm.robotcore.hardware.DcMotor;
//
//import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
//import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
//import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
//import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
//
//// 2.4 rotations for top, 1/2 rotation for safe zone
//@Disabled
//@Autonomous( name="BaseAutoOpMode", group="AutoOpModes")
//public class BaseAutoOpMode extends LinearOpMode {
//    private Orientation lastAngles = new Orientation();
//    private double currAngle = 0.0;
//    private int allowedAngleDiff = 3;
//    public static final double SERVO_DOWN = 0.48;
//    public static final double SERVO_UP = -0.03;
//    public static final double SERVO_AUTON_UP = 0.08;
//
//    public static final double SERVO_LIFTED = 0.62;
//    public static final double SERVO_UNLIFT = 1;
//    protected DejaVuBot robot = new DejaVuBot();
//
//    public double getAbsoluteAngle() {
//        return robot.imu.getAngularOrientation(
//                AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES
//        ).firstAngle;
//    }
//
//    public void turnPID(double degrees, DejaVuBot bot) {
//        turnToPID(degrees + getAbsoluteAngle(),bot);
//    }
//
//    public void turnToPID(double targetAngle, DejaVuBot bot) {
//        bot.gyroInit();
//        bot.chassisEncoderOff();
//        if(targetAngle < 0){
//            turnToPIDForNegativeAngle(targetAngle);
//        }else {
//            PIDUtils pid = new PIDUtils(targetAngle, 0.0015, 0, 0.003);
//            telemetry.setMsTransmissionInterval(50);
//            // Checking lastSlope to make sure that it's not "oscillating" when it quits
//            telemetry.addData(" turnToPID start abs angle = ", getAbsoluteAngle());
//            telemetry.addData(" turnToPID start diff = ", Math.abs(targetAngle - Math.abs(getAbsoluteAngle())));
//            telemetry.addData(" turnToPID start slope = ", pid.getLastSlope());
//            telemetry.update();
//
//            while (opModeIsActive() &&
//                    (Math.abs(targetAngle - Math.abs(getAbsoluteAngle())) > allowedAngleDiff || pid.getLastSlope() > 0.75)) {
//                double motorPower = pid.update(getAbsoluteAngle());
//
//                bot.leftFrontMotor.setPower(-motorPower);
//                bot.leftBackMotor.setPower(-motorPower);
//                bot.rightFrontMotor.setPower(motorPower);
//                bot.rightBackMotor.setPower(motorPower);
//
//                telemetry.addData(" turnToPID loop abs angle = ", getAbsoluteAngle());
//                telemetry.addData(" turnToPID angle difference = ", Math.abs(targetAngle - Math.abs(getAbsoluteAngle())));
//                telemetry.addData(" turnToPID slope = ", pid.getLastSlope());
//
//                telemetry.update();
//            }
//
//            bot.setPowerToAllMotors(0);
//        }
//
//    }
//    public void driveForwardByInches(int distance, DejaVuBot bot, double driveVelocity) {
//        bot.stopRobot();
//        int targetInput = (int) ((48/41)*(distance * DejaVuBot.COUNT_PER_INCH));
//        telemetry.addData("Target Position Set to:", targetInput);
//        telemetry.update();
//        bot.leftFrontMotor.setTargetPosition(targetInput);
//        bot.rightFrontMotor.setTargetPosition(targetInput);
//        bot.leftBackMotor.setTargetPosition(targetInput);
//        bot.rightBackMotor.setTargetPosition(targetInput);
//        bot.chassisEncoderOn();
//        // Send telemetry message to signify robot waiting;
//        telemetry.addData("Status", "Ready for run");
//        telemetry.update();
//        bot.setVelocityToAllMotors(driveVelocity);
//        while (opModeIsActive() && bot.leftFrontMotor.isBusy()) {
//            telemetry.addData("left", bot.leftFrontMotor.getCurrentPosition());
//            telemetry.addData("right", bot.rightFrontMotor.getCurrentPosition());
//            telemetry.update();
//        }
//    }
//
//    @Override
//    public void runOpMode() throws InterruptedException {
//        telemetry.addData("Status", "Initialized");
//        telemetry.update();
//        // Wait for the game to start (driver presses PLAY)
//        waitForStart();
//
//        // run until the end of the match (driver presses STOP)
//        while (opModeIsActive()) {
//            telemetry.addData("Status", "Running");
//            telemetry.update();
//        }
//    }
//
//    private void turnToPIDForNegativeAngle(double targetAngle) {
//        PIDUtils pid = new PIDUtils(targetAngle, 0.0015, 0, 0.003);
//        telemetry.setMsTransmissionInterval(50);
//        // Checking lastSlope to make sure that it's not "oscillating" when it quits
//        telemetry.addData(" turnToPIDForNegativeAngle s abs angle = ", getAbsoluteAngle());
//        telemetry.addData("  turnToPIDForNegativeAngle s  diff = ", Math.abs(Math.abs(targetAngle) - getAbsoluteAngle()) );
//        telemetry.addData("  turnToPIDForNegativeAngle  start slope = ", pid.getLastSlope());
//        telemetry.update();
//
//        while (opModeIsActive()
//                && (Math.abs(Math.abs(targetAngle) - getAbsoluteAngle()) > allowedAngleDiff
//                || pid.getLastSlope() > 0.75)) {
//            double motorPower = pid.update(getAbsoluteAngle());
//
//            robot.leftFrontMotor.setPower(-motorPower);
//            robot.leftBackMotor.setPower(-motorPower);
//            robot.rightFrontMotor.setPower(motorPower);
//            robot.rightBackMotor.setPower(motorPower);
//
//            telemetry.addData(" turnToPIDForNegativeAngle loop abs angle = ", getAbsoluteAngle());
//            telemetry.addData(" turnToPIDForNegativeAngle angle difference = ", Math.abs(Math.abs(targetAngle) - getAbsoluteAngle()) );
//            telemetry.addData(" turnToPIDForNegativeAngle slope = ", pid.getLastSlope());
//            telemetry.update();
//        }
//
//        robot.setPowerToAllMotors(0);
//    }
//
//
//
//    public void strafeDirection(DejaVuBot bot, boolean left, int milliseconds) {
//        bot.setModeForAllMotors(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
//        if(left) {
//            bot.leftFrontMotor.setPower(-0.85);
//            bot.leftBackMotor.setPower(0.85);
//            bot.rightFrontMotor.setPower(0.85);
//            bot.rightBackMotor.setPower(-0.85);
//        } else {
//            bot.leftFrontMotor.setPower(0.85);
//            bot.leftBackMotor.setPower(-0.85);
//            bot.rightFrontMotor.setPower(-0.85);
//            bot.rightBackMotor.setPower(0.85);
//        }
//        sleep(milliseconds);
//        bot.setModeForAllMotors(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//        bot.setModeForAllMotors(DcMotor.RunMode.RUN_TO_POSITION);
//    }
//    public void strafe45Direction(DejaVuBot bot, boolean left, boolean directionForwards, int milliseconds) {
//        bot.setModeForAllMotors(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
//        int direction = 0;
//        if(left) {
//            if (directionForwards) {
//                direction = 1;
//            } else {
//                direction = -1;
//            }
//            bot.leftFrontMotor.setPower(direction * 1.0);
//            bot.leftBackMotor.setPower(0);
//            bot.rightFrontMotor.setPower(0);
//            bot.rightBackMotor.setPower(direction * 1.0);
//        } else {
//            if (directionForwards) {
//                direction = 1;
//            } else {
//                direction = -1;
//            }
//            bot.leftFrontMotor.setPower(0);
//            bot.leftBackMotor.setPower(direction * 1.0);
//            bot.rightFrontMotor.setPower(direction * 1.0);
//            bot.rightBackMotor.setPower(0);
//        }
//        sleep(milliseconds);
//        bot.setModeForAllMotors(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//        bot.setModeForAllMotors(DcMotor.RunMode.RUN_TO_POSITION);
//    }
//}
//
