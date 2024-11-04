package org.firstinspires.ftc.teamcode.oldautos;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.hardware.Claw;
import org.firstinspires.ftc.teamcode.hardware.ColorSensorRGB;
import org.firstinspires.ftc.teamcode.hardware.MechDrive;
import org.firstinspires.ftc.teamcode.hardware.VisionCamera;


@Autonomous(name = "MMMechRedVision2", group = "Drivetrain")
public class MMMechRedVision2 extends LinearOpMode {

    // robot reference
    public MechDrive bot;
    public Claw claw;
    public ColorSensorRGB csensor;
    public VisionCamera detector;
    public Servo pushBot;
    public DcMotorEx frontL;
    public DcMotorEx frontR;
    public DcMotorEx backL;
    public DcMotorEx backR;
    public ColorSensor cSensorHardware;
    public DcMotorEx arm;
    public String color;
    public Servo clawServo;
    public String side;


    public void runOpMode() {
        frontL = hardwareMap.get(DcMotorEx.class, "frontL");
        frontR = hardwareMap.get(DcMotorEx.class, "frontR");
        backL = hardwareMap.get(DcMotorEx.class, "backL");
        backR = hardwareMap.get(DcMotorEx.class, "backR");
        arm = hardwareMap.get(DcMotorEx.class, "arm");
        bot = new MechDrive(frontL, frontR, backL, backR, telemetry);
        cSensorHardware = hardwareMap.colorSensor.get("csensor");
        pushBot = hardwareMap.servo.get("pushBot");
        clawServo = hardwareMap.servo.get("claw");
        detector = new VisionCamera(hardwareMap, telemetry);
        detector.init();
        detector.telemetryTfod();

        Telemetry tele = null;
        while(!isStarted()) {
            detector.telemetryTfod();
            telemetry.addData("Detected Side: ", side);
            telemetry.update();
            if (gamepad1.dpad_left) side = "left";
            else if (gamepad1.dpad_up || gamepad1.dpad_down) side = "middle";
            else if (gamepad1.dpad_right) side = "right";
            else side = detector.getSide();
        }
        waitForStart();
        csensor = new ColorSensorRGB(cSensorHardware);
        detector.telemetryTfod();
        claw = new Claw(arm);
        //the last speed of the claw "5"
        claw.changeClawSpeed(0.39);
        claw.flipClaw();
        bot.flipMotor(frontR);
        bot.flipMotor(backR);
        //close claw
        clawServo.setPosition(0);
        switch(side) {
            case "left":
                //pushBot.setPosition(0.625);
                bot.driveAutoDifferent(-0,-0,-0,-0,0.5);
                bot.driveAutoDifferent(-0,-75,-0,-0,0.425);
                clawServo.setPosition(0);
                bot.driveAutoDifferent(-0,7.5,-0,-0,0.2);
                bot.driveAutoDifferent(-0,-0,-0,-0,0.5);
                clawServo.setPosition(0);
                bot.driveAutoDifferent(-25,-0,-0,-0,1);
                pushBot.setPosition(0.625);
                //0.55
                bot.driveAutoDifferent(50,-0,-0,-0,0.6);
                bot.driveAutoDifferent(-5,-0,-0,-0,0.6);
                clawServo.setPosition(0);
                bot.driveAutoDifferent(-25,-0,-0,-0,0.5);
                break;

            case "middle":
                pushBot.setPosition(0.625);
                bot.driveAutoDifferent(50,-0,-0,-0,1.175);
                clawServo.setPosition(0);
                bot.driveAutoDifferent(-5,-0,-0,-0,0.2);
                bot.driveAutoDifferent(-40,-0,-0,-0,0.35);
                clawServo.setPosition(0);
                break;

            case "right":
                pushBot.setPosition(0.625);
                bot.driveAutoDifferent(50,-0,-0,-0,0.9);
                bot.driveAutoDifferent(-40,-0,-0,-0,0.2);
                clawServo.setPosition(0);
                bot.driveAutoDifferent(-0,-0,-2.5,-0,0.5);
                bot.driveAutoDifferent(-0,-0,-0,-0,0.5);
                clawServo.setPosition(0);
                bot.driveAutoDifferent(-0,-5,-0,-0,0.05);
                bot.driveAutoDifferent(-0,-0,-0,-0,1);
                clawServo.setPosition(0);
                while(bot.backRWheel.isBusy() || bot.backLWheel.isBusy());
                bot.driveAutoDifferent(15,-0,-0,-0,0.5);
                clawServo.setPosition(0);
                break;
        }

        /*color = csensor.getColor();
        bot.driveUntil(0.29, 0,0,0, "red");
        while (!color.equals("red")) {
            color = csensor.getColor();
            bot.driveAuto(-0.3,0,0,0,0.1);
        }*/





        telemetry.update();


    }

}