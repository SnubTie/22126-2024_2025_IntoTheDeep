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


@Autonomous(name = "MMBlueForwardParkTankD", group = "Drivetrain")
public class MMBlueForwardParkTankD extends LinearOpMode {

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
        bot.driveUntil(-0.15, 0,0,0, "blue");
        bot.driveAuto(0.5, 0,0,0,0.19);
        bot.driveAuto(0,0,-0.2,0,0.1);




        telemetry.update();


    }

}