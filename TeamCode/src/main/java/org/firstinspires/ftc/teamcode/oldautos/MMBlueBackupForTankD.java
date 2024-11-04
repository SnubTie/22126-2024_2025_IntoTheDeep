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


@Autonomous(name = "MMBlueBackupTankD", group = "Drivetrain")
public class MMBlueBackupForTankD extends LinearOpMode {

    // robot reference
    public TankDrive bot;
    public Claw claw;
    public ColorSensorRGB csensor;


    public DcMotor frontL;
    public DcMotor frontR;
    public ColorSensor cSensorHardware;
    public DcMotor arm;
    public String color;

    //public Servo wrist;
    public Servo grabber;






    public void runOpMode() {
        frontL = hardwareMap.dcMotor.get("motorLeft");
        frontR = hardwareMap.dcMotor.get("motorRight");
        arm = hardwareMap.dcMotor.get("arm");
        cSensorHardware = hardwareMap.colorSensor.get("csensor");
        //wrist = hardwareMap.servo.get("wrist");
        grabber = hardwareMap.servo.get("claw");

        Telemetry tele = null;

        waitForStart();
        csensor = new ColorSensorRGB(cSensorHardware);

        bot = new TankDrive(frontL, frontR, csensor);
        claw = new Claw(arm);
        //the last speed of the claw "5"
        claw.changeClawSpeed(0.39);
        claw.flipClaw();
        bot.flipMotor(frontR);
        bot.driveUntil(0.29, 0,0,0, "blue");
        claw.moveAuto(0.5, 0.09);
        bot.driveAuto(-0.3, 0, 0, 0, 0.7);
        //turn approx. 45 degrees
        bot.driveAuto(-0, -0, -0.25, -0, 0.8);
        bot.driveAuto(-0.4, -0, -0, -0, 2.55);
        bot.driveAuto(-0, -0, 0.25, -0, 0.7);
        bot.driveAuto(0.4, -0, -0, -0, 1.79);
        bot.driveAuto(-0.1, -0, -0, -0, 0.285);
        bot.driveAuto(-0, -0, -0, -0, 0.5);
        claw.moveAuto(-1,1.7);
        bot.driveAuto(-0, -0, -0, -0, 1);
        grabber.setPosition(2);
        bot.driveAuto(-0, -0, -0, -0, 1);
        claw.moveAuto(0.6,4.5);



        telemetry.update();
/*
        frontL = hardwareMap.dcMotor.get("motorLeft");
        frontR = hardwareMap.dcMotor.get("motorRight");
        arm = hardwareMap.dcMotor.get("arm");
        cSensorHardware = hardwareMap.colorSensor.get("csensor");
        //wrist = hardwareMap.servo.get("wrist");
        grabber = hardwareMap.servo.get("claw");

        Telemetry tele = null;


        waitForStart();

        bot = new TankDrive(frontL, frontR, csensor);
        claw = new Claw(arm);
        //the last speed of the claw "5"
        claw.changeClawSpeed(0.39);
        claw.flipClaw();
        bot.flipMotor(frontR);
        bot.driveUntil(0.29, 0,0,0, "blue");
        //set wrist to the normal position for picking up and placing
        claw.moveAuto(0.5, 0.09);
        bot.driveAuto(0.3, 0, 0, 0, 0.7);
        */
        /*while (!color.equals("blue")) {
            color = csensor.getColor();
            bot.driveAuto(-0.3,0,0,0,0.1);
        }*/
        /*bot.driveAuto(-0.45, 0,0,0,2.25);
        bot.driveAuto(0, 0,0,0,0.2);
        bot.driveAuto(0.45, 0,0,0,1.8);
        bot.driveAuto(0, 0,0,0,0.2);
        bot.driveAuto(0, 0,0.5,0,0.63);
        bot.driveAuto(0, 0,0,0,0.2);*/
        //bot.driveAuto(0.6, 0,0,0,0.5);
        //set wrist to the normal position for picking up and placing
        //wrist.setPosition(2);



        telemetry.update();


    }

}