package org.firstinspires.ftc.teamcode.autos;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotorEx;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.hardware.MechDrive;


@Autonomous(name = "JSAutoRed", group = "Drivetrain")
public class JSAutoRed extends LinearOpMode {

    // robot reference
    public MechDrive bot;
    public DcMotorEx frontL;
    public DcMotorEx frontR;
    public DcMotorEx backL;
    public DcMotorEx backR;


    public void runOpMode() {
        frontL = hardwareMap.get(DcMotorEx.class, "frontL");
        frontR = hardwareMap.get(DcMotorEx.class, "frontR");
        backL = hardwareMap.get(DcMotorEx.class, "backL");
        backR = hardwareMap.get(DcMotorEx.class, "backR");
        bot = new MechDrive(frontL, frontR, backL, backR, telemetry);

        Telemetry tele = null;
        waitForStart();
        bot.driveAutoDifferent(15,0,0,0,0.25);
        bot.driveAutoDifferent(-15,0,0,0,0.25);
        bot.driveAutoDifferent(15,0,0,0,0.75);
        bot.driveAutoDifferent(0,-20,0,0,0.75);
        bot.driveAutoDifferent(0,0,-10,0,0.25);
        telemetry.update();


    }

}