package org.firstinspires.ftc.teamcode.oldteleops;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;


// mech drive train tele op for BBC
@TeleOp(name="MMBotLift", group="Centerstage")
public class MMBotLift extends OpMode {


    public DcMotor clawMotor;
    public double speedFactor;
    public double clawPower;
    public int updateClawPower;
    public DcMotor.ZeroPowerBehavior powerBehavior;


    //telemetry


    // init, get robot and controller
    @Override
    public void init() {
        clawMotor = hardwareMap.dcMotor.get("arm");
        speedFactor = 0.4;
        updateClawPower = 1;

        clawMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }


    // loop every frame
    @Override
    public void loop() {
        // update game controller input/*
        telemetry.addData("Gamepad Stick LX:", gamepad1.left_stick_x);
        telemetry.addData("Gamepad Stick LY:", gamepad1.left_stick_y);
        telemetry.addData("Gamepad Stick RX:", gamepad1.right_stick_x);
        telemetry.addData("Gamepad Right Trigger:", gamepad1.right_trigger);
        telemetry.addData("Arm Speed Factor", speedFactor);
        telemetry.update();

        if (gamepad1.dpad_up) {
            speedFactor += 0.001;
        }
        if (gamepad1.dpad_down) {
            speedFactor -= 0.001;
        }
        if (gamepad1.x) {
            updateClawPower *= -1;
        }
        if (updateClawPower > 0) {
            clawPower = gamepad1.left_stick_y;
        }
        clawMotor.setPower(clawPower * speedFactor);
        clawMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

    }
}

