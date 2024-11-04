package org.firstinspires.ftc.teamcode.autos;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.hardware.MechDrive;


@Autonomous(name = "JSAutoRight", group = "Drivetrain")
public class JSAutoRight extends LinearOpMode {

    // robot reference
    public MechDrive bot;
    public DcMotorEx frontL;
    public DcMotorEx frontR;
    public DcMotorEx backL;
    public DcMotorEx backR;
    public DcMotorEx slide;
    public Servo claw;
    public Servo arm;


    public void runOpMode() {
        frontL = hardwareMap.get(DcMotorEx.class, "frontL");
        frontR = hardwareMap.get(DcMotorEx.class, "frontR");
        backL = hardwareMap.get(DcMotorEx.class, "backL");
        backR = hardwareMap.get(DcMotorEx.class, "backR");
        slide = hardwareMap.get(DcMotorEx.class, "slide");
        bot = new MechDrive(frontL, frontR, backL, backR, telemetry);
        claw = hardwareMap.servo.get("claw");
        arm = hardwareMap.servo.get("arm");
        bot.flipMotor(frontR);
        slide.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        Telemetry tele = null;
        waitForStart();
        bot.driveAutoDifferent(-25,0,0,0,1);
        bot.driveAutoDifferent(0,0,0,0,1);
        arm.setPosition(0.425);
        telemetry.update();


    }

}