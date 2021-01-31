package com.soluware.song;

/**
 * Created by Luciano on 01/06/2016.
 */
public class UserRec {

    private static int pointsRecord = 0;
    private static int points = 0;

    public static int getPointsRecord() {
        return pointsRecord;
    }

    /*
    public static void setPointsRecord(int pointsRecord) {
        UserRec.pointsRecord = pointsRecord;
    }*/

    public static int getPoints() {
        return points;
    }

    public static void setPoints(int points) {
        UserRec.points = points;
    }
}
