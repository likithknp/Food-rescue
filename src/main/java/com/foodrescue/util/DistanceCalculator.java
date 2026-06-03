package com.foodrescue.util;

public class DistanceCalculator {

    public static double calculateDistance(
            double lat1,
            double lon1,
            double lat2,
            double lon2
    ) {

        double theta = lon1 - lon2;

        double distance = Math.sin(Math.toRadians(lat1))
                * Math.sin(Math.toRadians(lat2))
                + Math.cos(Math.toRadians(lat1))
                * Math.cos(Math.toRadians(lat2))
                * Math.cos(Math.toRadians(theta));

        distance = Math.acos(distance);
        distance = Math.toDegrees(distance);

        return distance * 111.1896;
    }
}