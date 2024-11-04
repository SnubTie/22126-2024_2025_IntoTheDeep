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
@TeleOp(name="MMTeleTankD", group="Centerstage")
public class MMTeleTankD extends OpMode {

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

    //public Servo wrist;
    public Servo grabber;
    //public DcMotor flywheel;
    public double clawPower;
    public boolean shouldAllowX;
    public int updateClawPower;
    public DcMotor.ZeroPowerBehavior powerBehavior;
    //telemetry
    public Servo airplane;


    // init, get robot and controller
    @Override
    public void init() {
        frontL = hardwareMap.dcMotor.get("motorLeft");
        frontR = hardwareMap.dcMotor.get("motorRight");
        clawMotor = hardwareMap.dcMotor.get("arm");
        clawMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        //wrist = hardwareMap.servo.get("wrist");
        grabber = hardwareMap.servo.get("claw");
        airplane = hardwareMap.servo.get("airplane");
        cSensorHardware = hardwareMap.colorSensor.get("csensor");
        csensor = new ColorSensorRGB(cSensorHardware);
        //flywheel = hardwareMap.dcMotor.get("flywheel");
        bot = new TankDrive(frontL, frontR, csensor);
        claw = new Claw(clawMotor);
        speedFactor = 0.6;
        updateClawPower = 1;
        shouldAllowX = true;
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
        //open claw
        if (gamepad2.right_bumper) {
            grabber.setPosition(2);
        }
        //close claw
        if (gamepad2.left_bumper) {
            grabber.setPosition(0);
        }
        //position 1 for wrist is ground pick up
        /*if (gamepad2.dpad_left) {
            wrist.setPosition(1);
        }
        if (gamepad2.dpad_right) {
            wrist.setPosition(0);
        }*/
        //reset launch
        if (gamepad1.left_bumper) {
            airplane.setPosition(0);
        }
        //launch
        if (gamepad1.right_bumper) {
            airplane.setPosition(1);
        }

        if (gamepad2.dpad_down) {
            speedFactor -= 0.05;
        }
        if (gamepad2.dpad_up) {
            speedFactor += 0.05;
        }
        if (gamepad2.x) updateClawPower = -1;
        if (gamepad2.y) updateClawPower = 1;
        if (updateClawPower > 0) {
            clawPower = gamepad2.right_stick_y;
        }
        telemetry.addData("update claw", updateClawPower);
        telemetry.addData("clawPower", clawPower);
        telemetry.update();
        clawMotor.setPower(clawPower * speedFactor);

        //flywheel.setPower(gamepad2.left_trigger);



        bot.drive(gamepad1.left_stick_y,gamepad1.left_stick_y,gamepad1.left_stick_x, gamepad1.right_trigger);
    }
}

