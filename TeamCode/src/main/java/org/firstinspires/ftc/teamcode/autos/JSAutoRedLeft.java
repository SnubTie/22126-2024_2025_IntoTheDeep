package org.firstinspires.ftc.teamcode.autos;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotorEx;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.hardware.MechDrive;


@Autonomous(name = "JSAutoRedLeft", group = "Drivetrain")
public class JSAutoRedLeft extends LinearOpMode {

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
        bot.driveAutoDifferent(0,10
                ,0,0,5);
        telemetry.update();


    }

}