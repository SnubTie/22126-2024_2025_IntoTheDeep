package org.firstinspires.ftc.teamcode.hardware;

import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.external.Telemetry;


public class Claw {
    //public CRServo Claw;
    public DcMotor upClaw;
    public Telemetry tele;
    public int clawFlip = 1;
    public double speedFactor = 1;
    public double timeRan;

    public Claw(DcMotor up) {
        //Claw = claw;
        upClaw = up;
        //tele = Tele;
    }
    /*
    public void move(float power) {
        if (power != 0) {
            tele.addData("Power", power);
            tele.update();
            Claw.setPower(power);

        }
        else {
            Claw.setPower(0);
        }
    }*/
    public void changeClawSpeed(double factor) {
        speedFactor = factor;
    }
    public void flipClaw() {
        clawFlip *= -1;
    }


    public void moveUp(double moveup) {
        upClaw.setPower(clawFlip*moveup*speedFactor);


    }
    public void moveAuto(double power, double time) {
        double time2 = time* 1000;
        //tele.addData("f",time2);
        long time1 = System.currentTimeMillis();
        //tele.update();

        while (timeRan<=time2) {
            moveUp(power);
            timeRan = System.currentTimeMillis()-time1;
            //tele.addData("a", timeRan);
            //tele.update();

        }
        timeRan = 0;


    }

}