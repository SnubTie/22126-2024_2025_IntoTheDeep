package org.firstinspires.ftc.teamcode.oldteleops;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.GameController;
import org.firstinspires.ftc.teamcode.hardware.Claw;
import org.firstinspires.ftc.teamcode.hardware.ColorSensorRGB;
import org.firstinspires.ftc.teamcode.hardware.MechDrive;


// mech drive train tele op for BBC
@TeleOp(name="MMTeleMechD", group="Centerstage")
public class MMTeleMechD extends OpMode {

    // robot reference
    public MechDrive bot;
    public Servo airplane;
    public Claw claw;
    public String color;
    public ColorSensor cSensorHardware;
    public ColorSensorRGB csensor;

    // controller reference
    public GameController pad;

    public Telemetry tele;
    public DcMotorEx frontL;

    public DcMotorEx frontR;
    public DcMotorEx backL;
    public DcMotorEx backR;
    public DcMotorEx arm;

    public Servo clawServo;
    public Servo pushBot;
    public double speedFactor;
    public double clawPower;
    public boolean shouldAllowX;
    public int updateClawPower;
    public DcMotor.ZeroPowerBehavior powerBehavior;

    //telemetry


    // init, get robot and controller
    @Override
    public void init() {
        airplane = hardwareMap.servo.get("airplane");
        frontL = hardwareMap.get(DcMotorEx.class, "frontL");
        frontR = hardwareMap.get(DcMotorEx.class, "frontR");
        backL = hardwareMap.get(DcMotorEx.class, "backL");
        backR = hardwareMap.get(DcMotorEx.class, "backR");
        arm = hardwareMap.get(DcMotorEx.class, "arm");
        cSensorHardware = hardwareMap.colorSensor.get("csensor");
        color = "";
        clawServo = hardwareMap.servo.get("claw");
        pushBot = hardwareMap.servo.get("pushBot");
        cSensorHardware = hardwareMap.colorSensor.get("csensor");
        csensor = new ColorSensorRGB(cSensorHardware);
        //flywheel = hardwareMap.dcMotor.get("flywheel");
        bot = new MechDrive(frontL, frontR, backL, backR, telemetry);
        speedFactor = 0.6;
        updateClawPower = 1;
        shouldAllowX = true;
        arm.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        //claw.flipClaw();
        bot.flipMotor(frontL);
        bot.flipMotor(backL);

    }


    // loop every frame
    @Override
    public void loop() {
        // update game controller input
        /*pad.update();
        tele.addData("Gamepad Stick LX:", gamepad1.left_stick_x);
        tele.addData("Gamepad Stick LY:", gamepad1.left_stick_y);
        tele.addData("Gamepad Stick RX:", gamepad1.right_stick_x);
        tele.addData("Gamepad Right Trigger:", gamepad1.right_trigger);
        tele.update();*/
        // set drive train power with controller x, y, and rotational input
        telemetry.addData("alpha", cSensorHardware.alpha());
        telemetry.addData("argb", cSensorHardware.argb());
        telemetry.addData("blue", cSensorHardware.blue());
        telemetry.addData("red", cSensorHardware.red());
        telemetry.addData("green", cSensorHardware.green());
        color = csensor.getColor();
        telemetry.addData("color read:", color);
        telemetry.update();
        //close pushbot
        if (gamepad1.right_bumper) {
            pushBot.setPosition(0.25);
        }
        //open pushbot
        if (gamepad1.left_bumper) {
            pushBot.setPosition(0.75);
        }

        if (gamepad2.dpad_left) {
            clawServo.setPosition(2);
        }

        if (gamepad2.dpad_right) {
            clawServo.setPosition(0);
        }
        //launch
        if (gamepad1.y) {
            airplane.setPosition(0);
        }

        //not launch
        if (gamepad1.x) {
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
        arm.setPower(clawPower * speedFactor);


        bot.drive(gamepad1.left_stick_y, -gamepad1.left_stick_x, gamepad1.right_stick_x, gamepad1.right_trigger, true);
        //arm.setVelocity(gamepad2.right_stick_y);
    }
}

