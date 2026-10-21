package com.sheep_flock.geometry;

public class Geometry {

    /**
     * Calculates the distance between an oval and a rectangle (defined by its center).
     * 
     * @param ovalX   Center X of the oval
     * @param ovalY   Center Y of the oval
     * @param ovalRx  Horizontal radius of the oval
     * @param ovalRy  Vertical radius of the oval
     * @param rectX   Center X of the rectangle
     * @param rectY   Center Y of the rectangle
     * @param rectW   Width of the rectangle
     * @param rectH   Height of the rectangle
     * @return Minimum distance (0 if touching or overlapping)
     */
    public static float distanceOvalRect(float ovalX, float ovalY, float ovalRx, float ovalRy,
                                         float rectX, float rectY, float rectW, float rectH) {
        // Convert rectangle center to min/max bounds for easier calculation
        float rectMinX = rectX - rectW / 2.0f;
        float rectMaxX = rectX + rectW / 2.0f;
        float rectMinY = rectY - rectH / 2.0f;
        float rectMaxY = rectY + rectH / 2.0f;

        // Find closest point on rectangle to oval center
        float closestX = Math.max(rectMinX, Math.min(ovalX, rectMaxX));
        float closestY = Math.max(rectMinY, Math.min(ovalY, rectMaxY));

        float distX = ovalX - closestX;
        float distY = ovalY - closestY;
        
        // Approximate distance considering oval radii (simplified circular approximation or standard Euclidean)
        float distanceToClosestPoint = (float) Math.sqrt(distX * distX + distY * distY);
        
        // Using average radius or simple approximation for oval radius towards the point
        float effectiveRadius = (ovalRx + ovalRy) / 2.0f; 

        return Math.max(0.0f, distanceToClosestPoint - effectiveRadius);
    }

    /**
     * Calculates the minimum distance between two ovals (or circles).
     */
    public static float distanceOvalOval(float x1, float y1, float rx1, float ry1,
                                         float x2, float y2, float rx2, float ry2) {
        float distX = x2 - x1;
        float distY = y2 - y1;
        float centerDistance = (float) Math.sqrt(distX * distX + distY * distY);

        float r1 = (rx1 + ry1) / 2.0f;
        float r2 = (rx2 + ry2) / 2.0f;

        return Math.max(0.0f, centerDistance - (r1 + r2));
    }

    /**
     * Calculates the minimum distance between two rectangles (defined by their centers).
     */
    public static float distanceRectRect(float x1, float y1, float w1, float h1,
                                         float x2, float y2, float w2, float h2) {
        float minX1 = x1 - w1 / 2.0f;
        float maxX1 = x1 + w1 / 2.0f;
        float minY1 = y1 - h1 / 2.0f;
        float maxY1 = y1 + h1 / 2.0f;

        float minX2 = x2 - w2 / 2.0f;
        float maxX2 = x2 + w2 / 2.0f;
        float minY2 = y2 - h2 / 2.0f;
        float maxY2 = y2 + h2 / 2.0f;

        float gapX = Math.max(0.0f, Math.max(minX1, minX2) - Math.min(maxX1, maxX2));
        float gapY = Math.max(0.0f, Math.max(minY1, minY2) - Math.min(maxY1, maxY2));

        return (float) Math.sqrt(gapX * gapX + gapY * gapY);
    }
}