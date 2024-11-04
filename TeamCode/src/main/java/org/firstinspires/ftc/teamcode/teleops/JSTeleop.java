package org.firstinspires.ftc.teamcode.teleops;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.GameController;
import org.firstinspires.ftc.teamcode.hardware.MechDrive;


// mech drive train tele op for BBC
@TeleOp(name="JSTeleop", group="Centerstage")
public class JSTeleop extends OpMode {

    // robot reference
    public MechDrive bot;
    // controller reference
    public GameController pad;
    public Telemetry tele;
    public DcMotorEx frontL;
    public DcMotorEx frontR;
    public DcMotorEx backL;
    public DcMotorEx backR;
    public Servo claw;
    public Servo arm;
    public DcMotorEx slide;

    //telemetry


    // init, get robot and controller
    @Override
    public void init() {
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
    }


    // loop every frame
    @Override
    public void loop() {
        //close claw
        if (gamepad2.right_bumper) {
            claw.setPosition(0);
        }
        //open claw
        if (gamepad2.left_bumper) {
            claw.setPosition(1);
        }
        //pickup pos
        if (gamepad2.dpad_right) {
            arm.setPosition(0);
        }
        //top pos
        if (gamepad2.dpad_left) {
            arm.setPosition(1);
        }
        //bucket pos
        if (gamepad2.dpad_up){
            arm.setPosition(0.5);
        }
        //submersible pos
        if (gamepad2.dpad_down){
            arm.setPosition(0.25);
        }
        bot.drive(gamepad1.left_stick_y, -gamepad1.left_stick_x, gamepad1.right_stick_x, gamepad1.right_trigger, true);
        slide.setPower(gamepad2.right_stick_y);
    }
}

