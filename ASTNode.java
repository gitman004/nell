package tlaprojet;



class ASTNode {
    String type;
    String value;
    ASTNode left;
    ASTNode right;

    public ASTNode(String type, String value) {
        this.type = type;
        this.value = value;
    }

    public ASTNode(String type, ASTNode left, ASTNode right) {
        this.type = type;
        this.left = left;
        this.right = right;
    }

    public double evaluate(double x) {
        switch (type) {
            case "NUMBER":
                return Double.parseDouble(value);
            case "VARIABLE":
                return x;
            case "+":
                return left.evaluate(x) + right.evaluate(x);
            case "-":
                return left.evaluate(x) - right.evaluate(x);
            case "*":
                return left.evaluate(x) * right.evaluate(x);
            case "/":
                return left.evaluate(x) / right.evaluate(x);
            case "^":
                return Math.pow(left.evaluate(x), right.evaluate(x));
            default:
                throw new IllegalArgumentException("Unknown node type: " + type);
        }
    }
}
