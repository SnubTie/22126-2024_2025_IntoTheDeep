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

;


@Autonomous(name = "MMRedBackupForTankD", group = "Drivetrain")
public class MMRedBackupForTankD extends LinearOpMode {

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
        bot.driveAuto(-0.45, 0,0,0,1.8);
        bot.driveAuto(0, 0,0,0,0.2);
        bot.driveAuto(0.45, 0,0,0,1.55);
        bot.driveAuto(0, 0,0,0,0.2);
        bot.driveAuto(0, 0,-0.5,0,0.6);
        bot.driveAuto(0, 0,0,0,0.2);
        //bot.driveAuto(0.6, 0,0,0,0.5);
        //set wrist to the normal position for picking up and placing
        //wrist.setPosition(2);
        bot.driveUntil(0.29, 0,0,0, "red");
        bot.driveAuto(0.15,0,0,0,0.1);
        /*while (!color.equals("red")) {
            color = csensor.getColor();
            bot.driveAuto(-0.3,0,0,0,0.1);
        }*/
        //set wrist to the normal position for picking up and placing





        telemetry.update();


    }

}