package org.firstinspires.ftc.teamcode.oldteleops;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.GameController;
import org.firstinspires.ftc.teamcode.hardware.Claw;
import org.firstinspires.ftc.teamcode.hardware.ColorSensorRGB;
import org.firstinspires.ftc.teamcode.hardware.TankDrive;


// mech drive train tele op for BBC
@TeleOp(name="TestifThingsWork", group="Centerstage")
public class TestifThingsWork extends OpMode {

    // robot reference
    public TankDrive bot;
    public Claw claw;
    public String color;
    public ColorSensor cSensorHardware;
    public ColorSensorRGB csensor;

    // controller reference
    public GameController pad;

    public Telemetry tele;

    public DcMotor frontL;

    public DcMotor frontR;
    public double speedFactor;
    public DcMotor clawMotor;

    public Servo wrist, grabber;

    public DcMotor flywheel;
    public double clawPower;
    public int updateClawPower;
    public DcMotor.ZeroPowerBehavior powerBehavior;
    //telemetry


    // init, get robot and controller
    @Override
    public void init() {
        frontL = hardwareMap.dcMotor.get("motorLeft");
        frontR = hardwareMap.dcMotor.get("motorRight");
        clawMotor = hardwareMap.dcMotor.get("arm");
        clawMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        wrist = hardwareMap.servo.get("wrist");
        grabber = hardwareMap.servo.get("claw");
        cSensorHardware = hardwareMap.colorSensor.get("csensor");
        csensor = new ColorSensorRGB(cSensorHardware);
        flywheel = hardwareMap.dcMotor.get("flywheel");
        //bot = new TankDrive(frontL, frontR, csensor);
        claw = new Claw(clawMotor);
        speedFactor = 0.4;
        updateClawPower = 1;
        clawMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        //the last claw speed was "5"
        //claw.changeClawSpeed(0.39);
        claw.flipClaw();
        bot.flipMotor(frontL);

    }


    // loop every frame
    @Override
    public void loop() {
        // update game controller input/*
        telemetry.addData("Gamepad Stick LX:", gamepad1.left_stick_x);
        telemetry.addData("Gamepad Stick LY:", gamepad1.left_stick_y);
        telemetry.addData("Gamepad Stick RX:", gamepad1.right_stick_x);
        telemetry.addData("Gamepad Right Trigger:", gamepad1.right_trigger);
        telemetry.update();
        // set drive train power with controller x, y, and rotational input
        telemetry.addData("alpha", cSensorHardware.alpha());
        telemetry.addData("argb", cSensorHardware.argb());
        telemetry.addData("blue", cSensorHardware.blue());
        telemetry.addData("red", cSensorHardware.red());
        telemetry.addData("green", cSensorHardware.green());
        color = csensor.getColor();
        telemetry.addData("color read:", color);
        telemetry.update();
        //open claw
        if (gamepad2.right_bumper) {
            grabber.setPosition(1);
        }
        //close claw
        if (gamepad2.left_bumper) {
            grabber.setPosition(1);
        }
        //position 2 for wrist is ground pick up
        if (gamepad2.dpad_left) {
            wrist.setPosition(2);
        }
        //position 1 for wrist is board place, trial and error
        if (gamepad2.dpad_right) {
            wrist.setPosition(0);
        }
        if (gamepad2.dpad_down) {
            speedFactor -= 0.05;
        }
        if (gamepad2.dpad_up) {
            speedFactor += 0.05;
        }
        if (gamepad2.x) {
            updateClawPower *= -1;
        }
        if (updateClawPower > 0) {
            clawPower = gamepad2.left_stick_y;
        }
        clawMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        clawMotor.setPower(clawPower * speedFactor);

        flywheel.setPower(gamepad2.left_trigger);



        //bot.drive(-gamepad1.left_stick_y,gamepad1.left_stick_y,gamepad1.left_stick_x, gamepad1.right_trigger);
        claw.moveUp(gamepad2.right_stick_y * speedFactor);
    }
}

