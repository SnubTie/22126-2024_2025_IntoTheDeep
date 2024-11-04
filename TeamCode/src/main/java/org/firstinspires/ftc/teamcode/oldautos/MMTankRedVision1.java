package org.firstinspires.ftc.teamcode.oldautos;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.hardware.Claw;
import org.firstinspires.ftc.teamcode.hardware.ColorSensorRGB;
import org.firstinspires.ftc.teamcode.hardware.TankDrive;
import org.firstinspires.ftc.teamcode.hardware.VisionCamera;


@Autonomous(name = "MMTankRedVision1", group = "Drivetrain")
public class MMTankRedVision1 extends LinearOpMode {

    // robot reference
    public TankDrive bot;
    public Claw claw;
    public ColorSensorRGB csensor;
    public VisionCamera detector;

    public DcMotor frontL;
    public DcMotor frontR;
    public ColorSensor cSensorHardware;
    public DcMotor arm;
    public String color;
    //public Servo wrist, grabber;
    public Servo grabber;
    public String side;


    public void runOpMode() {
        frontL = hardwareMap.dcMotor.get("motorLeft");
        frontR = hardwareMap.dcMotor.get("motorRight");
        arm = hardwareMap.dcMotor.get("arm");
        cSensorHardware = hardwareMap.colorSensor.get("csensor");
        //wrist = hardwareMap.servo.get("wrist");
        grabber = hardwareMap.servo.get("claw");
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
        bot = new TankDrive(frontL, frontR, csensor);
        claw = new Claw(arm);
        //the last speed of the claw "5"
        claw.changeClawSpeed(0.39);
        claw.flipClaw();
        bot.flipMotor(frontR);
        //close claw
        grabber.setPosition(0);
        switch(side) {
            case "right":
                //start with this side
                bot.driveAuto(-0.15, -0,-0,-0,0.5);
                bot.driveAuto(-0, -0,0.25,-0,0.4);
                bot.driveAuto(-0.5,-0,-0,-0, 1.67);
                bot.driveAuto(0.25, -0,-0,-0, 1.1);
                bot.driveAuto(-0, -0,-0.5,-0,0.825);
                bot.driveAuto(0.8, -0,-0,-0,0.655);
                bot.driveAuto(0,0,0,0,0.99);
                claw.moveAuto(-1,0.7);
                bot.driveAuto(-0,-0,-0,-0,0.65);
                grabber.setPosition(2);
                grabber.setPosition(2);
                grabber.setPosition(2);
                grabber.setPosition(2);
                grabber.setPosition(2);
                bot.driveAuto(-0,-0,-0,-0,0.9);
                claw.moveAuto(0.7,4);
                break;

            case "middle":
                bot.driveAuto(-0.75,-0,-0,-0,1);
                bot.driveAuto(-0,-0,-0,-0,0.3);
                bot.driveAuto(0.75,-0,-0,-0,0.17);
                bot.driveAuto(-0,-0,-0,-0,0.2);
                bot.driveAuto(-0, -0,-0.5,-0,0.525);
                bot.driveAuto(0.75,-0,-0,-0,0.85);
                bot.driveAuto(-0,-0,-0,-0,0.65);
                claw.moveAuto(-0.5,0.5);
                bot.driveAuto(-0,-0,-0,-0,5);
                //open claw
                grabber.setPosition(1);
                bot.driveAuto(-0,-0,-0,-0,0.9);
                claw.moveAuto(0.5,2.4);
                break;

            case "left":
                bot.driveAuto(-0.5,-0,-0,-0,1.75);
                bot.driveAuto(-0, -0,-0.5,-0,0.4);
                bot.driveAuto(-0.75,-0,-0,-0,0.2);
                bot.driveAuto(0.75,-0,-0,-0,1);
                bot.driveAuto(-0,-0,-0,-0,1.9);
                claw.moveAuto(-0.5,0.5);
                bot.driveAuto(-0,-0,-0,-0,5);
                //open claw
                grabber.setPosition(2);
                grabber.setPosition(2);
                grabber.setPosition(2);
                grabber.setPosition(2);
                grabber.setPosition(2);
                bot.driveAuto(-0,-0,-0,-0,1.3);
                claw.moveAuto(0.5,2.3);
                grabber.setPosition(-0);
                grabber.setPosition(-0);
                grabber.setPosition(-0);
                grabber.setPosition(-0);
                grabber.setPosition(-0);
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