abstract class Space {
    public abstract boolean describesInfinityWell();
}

class Infinity extends Space {
    private final boolean isInfinite;
    private final boolean isMeaningful;
    private final boolean isInteresting;

    public Infinity(boolean isInfinite, boolean isMeaningful, boolean isInteresting) {
        this.isInfinite = isInfinite;
        this.isMeaningful = isMeaningful;
        this.isInteresting = isInteresting;
    }

    @Override
    public boolean describesInfinityWell() {
        return false;
    }

    public boolean isInfinity() {
        return isInfinite && !isMeaningful && !isInteresting;
    }

//    public boolean isInfinite() {
//        return isInfinite;
//    }
//
//    public boolean isMeaningful() {
//        return isMeaningful;
//    }
//
//    public boolean isInteresting() {
//        return isInteresting;
//    }
}

class Room extends Space {
    private final double width;
    private final double height;
    private final double length;
    private final boolean isHuge;

    private static final double MIN_HUGE_SIZE= 1000.0;

    public Room(double width, double height, double length) {
        validateDimensions(width, height, length);
        this.width = width;
        this.height = height;
        this.length = length;
        this.isHuge = calculateIsHuge();
    }

    @Override
    public boolean describesInfinityWell() {
        return isHuge;
    }

    public boolean isFinite() {
        return Double.isFinite(width) &&
                Double.isFinite(height) &&
                Double.isFinite(length);
    }


    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public double getLength() {
        return length;
    }

    private void validateDimensions(double width, double height, double length) {
        if (width <= 0 || height <= 0 || length <= 0) {
            throw new IllegalArgumentException("Dimensions must be positive and non-zero");
        }
    }

    private boolean calculateIsHuge() {
        return width > MIN_HUGE_SIZE &&
                height > MIN_HUGE_SIZE &&
                length > MIN_HUGE_SIZE && isFinite();
    }

    public boolean isHuge() {
        return isHuge;
    }
}

class Aeromobile {
    private final double width;
    private final double height;
    private final double length;

    public Aeromobile(double width, double height, double length) {
        validateSize(width, height, length);
        this.width = width;
        this.height = height;
        this.length = length;
    }

    public boolean canFitInRoom(Room room) {
        return width < room.getWidth() &&
                height < room.getHeight() &&
                length < room.getLength();
    }

    private void validateSize(double width, double height, double length) {
        if (width <= 0 || height <= 0 || length <= 0) {
            throw new IllegalArgumentException("Size must be positive and non-zero");
        }
    }


}

class Distance {
    private final double distance;

    public Distance(double distance) {
        if (Double.isNaN(distance)) {
            throw new IllegalArgumentException("Distance cannot be NaN");
        }
        this.distance = distance;
    }


    public boolean isComprehensible() {
        return Double.isFinite(distance);
    }

    public boolean isMeaningful() {
        return isComprehensible();
    }
}

class Sky {
    private final Distance distance;

    public Sky(Distance distance) {
        this.distance = distance;
    }

    public boolean isVisible() {
        return !distance.isComprehensible() && !distance.isMeaningful();
    }

}