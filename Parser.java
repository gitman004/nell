package tlaprojet;



import java.util.List;

class Parser {
    private List<Token> tokens;
    private int pos;

    public Parser(List<Token> tokens) {
        this.tokens = tokens;
        this.pos = 0;
    }

    public ASTNode parse() {
        return parseExpr();
    }

    private ASTNode parseExpr() {
        ASTNode node = parseTerm();
        while (pos < tokens.size() && (tokens.get(pos).value.equals("+") || tokens.get(pos).value.equals("-"))) {
            Token operator = tokens.get(pos);
            pos++;
            ASTNode right = parseTerm();
            node = new ASTNode(operator.value, node, right);
        }
        return node;
    }

    private ASTNode parseTerm() {
        ASTNode node = parseFactor();
        while (pos < tokens.size() && (tokens.get(pos).value.equals("*") || tokens.get(pos).value.equals("/"))) {
            Token operator = tokens.get(pos);
            pos++;
            ASTNode right = parseFactor();
            node = new ASTNode(operator.value, node, right);
        }
        return node;
    }

    private ASTNode parseFactor() {
        ASTNode node = parseBase();
        while (pos < tokens.size() && tokens.get(pos).value.equals("^")) {
            Token operator = tokens.get(pos);
            pos++;
            ASTNode right = parseBase();
            node = new ASTNode(operator.value, node, right);
        }
        return node;
    }

    private ASTNode parseBase() {
        Token token = tokens.get(pos);
        pos++;
        if (token.type.equals("NUMBER")) {
            return new ASTNode("NUMBER", token.value);
        } else if (token.type.equals("VARIABLE")) {
            return new ASTNode("VARIABLE", token.value);
        } else if (token.value.equals("(")) {
            ASTNode node = parseExpr();
            pos++; // Skip closing parenthesis
            return node;
        } else {
            throw new IllegalArgumentException("Invalid token: " + token.value);
        }
    }
}

