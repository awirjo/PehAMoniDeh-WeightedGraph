package PehAMoniDeh.Graph;

public class DistanceParent {
    public int distance;
    public int parentVert;

    public DistanceParent(int parentVert, int distance){
        this.parentVert = parentVert;
        this.distance = distance;
    }

    @Override
    public String toString() {
        return "DistanceParent{" +
                "distance=" + distance +
                ", parentVert=" + parentVert +
                '}';
    }
}
