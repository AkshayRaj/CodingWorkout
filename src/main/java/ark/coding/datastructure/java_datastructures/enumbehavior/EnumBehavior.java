package ark.coding.datastructure.java_datastructures.enumbehavior;

/**
 * Created by Akshayraj on 2/9/16.
 */
public class EnumBehavior {
    private enum SearchType{
        WITHIN_POLYGON("withinPolygon"),
        CENTER_POINT_DISTANCE("centerPointDistance"),
        DEFAULT("withinPolygon");

        private final String eKey;

        private SearchType(String s) {
            eKey = s;
        }

        public String toString(){
            return eKey;
        }
    }
    static SearchType enumTestWithinPolygon = SearchType.WITHIN_POLYGON;
    static SearchType enumTestCenterPointDistance = SearchType.CENTER_POINT_DISTANCE;
    static SearchType enumTestDefault = SearchType.DEFAULT;

    public static void main(String[] args){
        System.out.println("\nwithinPolygon: " + enumTestWithinPolygon.toString());
        System.out.println("\nDefault: " + enumTestDefault.toString());
        System.out.println("\nCenter_Point_Distance: " + enumTestCenterPointDistance.toString());
    }
}
