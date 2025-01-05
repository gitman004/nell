package tlaprojet;



import java.awt.*;
import java.util.List;

public class Plot {

    final static double STEPS = 1000;
    double range = 2;
    ASTNode functionAST;

    void setFunction(String function) {
        Lexer lexer = new Lexer(function);
        List<Token> tokens = lexer.tokenize();
        Parser parser = new Parser(tokens);
        this.functionAST = parser.parse();
    }

    void setRange(double range) {
        this.range = range;
    }

    void paint(Graphics2D g, double w, double h) {
        double step = range / STEPS;
        double centerX = w / 2;
        double centerY = h / 2;
        double halfMinSize = Math.max(w, h) / 2;

        g.setColor(Color.GRAY);
        g.drawLine((int)centerX, 0, (int)centerX, (int)h);
        g.drawLine(0, (int)centerY, (int)w, (int)centerY);

        g.setColor(Color.BLACK);
        for (double x = -range; x <= range; x += step) {
            double y = functionAST.evaluate(x);
            if (Double.isFinite(y)) {
                g.drawRect(
                    (int) (centerX + x * halfMinSize / range),
                    (int) (centerY + -y * halfMinSize / range),
                    1,
                    1
                );
            }
        }
    }
}
