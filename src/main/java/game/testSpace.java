package game;

public class testSpace {

    public static void main(String[] args) {
        System.out.println(Math.tan(Math.toRadians(30)));
        // double degrees = Math.toDegrees(Math.atan(dist_x/dist_y));
        String dir = "left";
        int a = 7;
        if ((a > 5 && dir.equals("right")) || (a < 2 && dir.equals("left"))){
            System.out.println("BEVEG");
        }
    }
    
}









