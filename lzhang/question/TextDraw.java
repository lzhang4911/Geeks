package lzhang.question;

/**
 * The trick of this question is that 
 * (1) there ought not be any gap between 2 dots;
 * (2) every dot should be as close to the idea position as possible.
 * 
 * TODO: not done yet
 * 
 * @author lzhang
 *
 */
public class TextDraw {

    public static void test() {
        drawCircle(10);
    }
    
    private static void drawCircle(int r) {
        System.out.println("Radius of circle: " + r);
        
        for(int x = -r; x <= r; x++) {
            for(int y = r; y >= -r; y--) {
                if(x == 0 && y == 0) {
                    // draw origin
                    System.out.print("+");
                } else if(x*x + y*y >= r*r) {
                    // shade the outside
                    System.out.print("^");
                } else {
                    // hollow inside
                    System.out.print(" ");
                }
            }
            
            // line break
            System.out.println("");
        }
    }
}
