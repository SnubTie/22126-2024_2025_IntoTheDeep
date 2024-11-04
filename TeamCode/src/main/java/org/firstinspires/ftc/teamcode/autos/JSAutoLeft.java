package org.firstinspires.ftc.teamcode.autos;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.hardware.MechDrive;


@Autonomous(name = "JSAutoLeft", group = "Drivetrain")
public class JSAutoLeft extends LinearOpMode {

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
        bot.driveAutoDifferent(0,-25,0,0,1.5);
        slide.setPower(0.5);
        arm.setPosition(0.5);
        claw.setPosition(1);
        sleep(5000);
        slide.setPower(0);
        bot.driveAutoDifferent(0,0,25,0,1);
        bot.driveAutoDifferent(15,0,0,0,0.5);
        arm.setPosition(0);
        claw.setPosition(0);
        bot.driveAutoDifferent(-15,0,0,0,0.5);
        bot.driveAutoDifferent(0,0,-25,0,1);
        slide.setPower(0.5);
        arm.setPosition(0.5);
        claw.setPosition(1);
        sleep(5000);
        slide.setPower(0);
        telemetry.update();


    }

}