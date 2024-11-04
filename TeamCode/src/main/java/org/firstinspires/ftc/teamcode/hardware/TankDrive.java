package org.firstinspires.ftc.teamcode.hardware;

import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.external.Telemetry;



public class TankDrive {
    public static Telemetry tele;


    public DcMotor frontLWheel;
    public DcMotor frontRWheel;
    public int flflip = 1;
    public int frflip = 1;

    public double speedMod = 1;
    public long timeRan;
    public String color = "";
    public ColorSensorRGB csensor;
    /*
    public double a = Math.cos(240);
    public double b = Math.cos(240);
    public double c = Math.cos(240);
    public double d = Math.sin(240);
    public double e = Math.sin(240);
    public double f = Math.sin(240);
    public double g = 1;
    public double h = 1;
    double i = 1;
    double det = a*e*i + b*f*g + c*d*h - c*e*g - a*f*h;*/


    public TankDrive(DcMotor fl, DcMotor fr, ColorSensorRGB sensor) {
        frontLWheel = fl;
        frontRWheel = fr;
        csensor = sensor;
        color = "";

    }


    public void flipMotor(DcMotor motor) {
        if (motor == frontLWheel) {
            flflip *= -1;
        }
        else if (motor == frontRWheel) {
            flflip *= -1;
        }

    }

    public void drive(double forward, double strafe, double turn, double right_trigger) {

        if (right_trigger != 0) {
            speedMod = 0.5;
        }
        if (turn != 0) {
            frontLWheel.setPower(flflip *-1* turn * speedMod);
            frontRWheel.setPower(frflip * turn * speedMod);
            speedMod = 1;
        }
        else if (forward != 0) {
            frontLWheel.setPower(flflip * forward * speedMod);
            frontRWheel.setPower(frflip * forward * speedMod);
        }
        else {
            frontLWheel.setPower(0);
            frontRWheel.setPower(0);
        }
        /*
        else if (strafe != 0) {
            frontLWheel.setPower(flflip*Math.cos(30)*strafe*speedMod);
            frontRWheel.setPower(frflip*Math.cos(150)*strafe*speedMod);
        }
         */



            /*double r = Math.hypot(strafe, forward);
            double robotAngle = Math.atan2(forward, strafe) - Math.PI / 4;
            double rightX = turn;
            final double v1 = r * Math.cos(robotAngle) + rightX;
            final double v2 = r * Math.sin(robotAngle) - rightX;
            final double v3 = r * Math.sin(robotAngle) + rightX;
            final double v4 = r * Math.cos(robotAngle) - rightX;
            //tele.addData("fl", v1*speedMod);
            //tele.addData("fr", v2*speedMod);
            //tele.addData("bl", v3*speedMod);
            //tele.addData("br", v4*speedMod);
             back.setPower(v2 * speedMod);
            frontLWheel.setPower(v3 * speedMod);
            frontRWheel.setPower(v4 * speedMod);*/
        speedMod = 1;
        //tele.update();
    }
    public void driveUntil(double forward, double strafe, double turn, double rightTrigger, String untilColor) {
        while (!color.equals(untilColor)) {
            color = csensor.getColor();
            drive(forward, strafe, turn, rightTrigger);
        }
    }

    public void driveAuto(double forward1, double strafe1, double turn1, double rightTrigger, double time) {
        double time2 = time* 1000;
        //tele.addData("f",time2);
        long time1 = System.currentTimeMillis();
        //tele.update();

        while (timeRan<=time2) {
            drive(forward1, strafe1, turn1, rightTrigger);
            timeRan = System.currentTimeMillis()-time1;
            //tele.addData("a", timeRan);
            //tele.update();

        }
        timeRan = 0;


    }

}


