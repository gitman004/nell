package tlaprojet;


import java.util.ArrayList;
import java.util.List;

class Token {
    String type;
    String value;

    public Token(String type, String value) {
        this.type = type;
        this.value = value;
    }
}

class Lexer {
    private String input;
    private int pos;

    public Lexer(String input) {
        this.input = input;
        this.pos = 0;
    }

    public List<Token> tokenize() {
        List<Token> tokens = new ArrayList<>();
        while (pos < input.length()) {
            char currentChar = input.charAt(pos);
            if (Character.isDigit(currentChar)) {
                tokens.add(new Token("NUMBER", readNumber()));
            } else if (currentChar == 'x') {
                tokens.add(new Token("VARIABLE", "x"));
                pos++;
            } else if (currentChar == '+' || currentChar == '-' || currentChar == '*' || currentChar == '/' || currentChar == '^') {
                tokens.add(new Token("OPERATOR", String.valueOf(currentChar)));
                pos++;
            } else if (currentChar == '(' || currentChar == ')') {
                tokens.add(new Token("PARENTHESIS", String.valueOf(currentChar)));
                pos++;
            } else if (currentChar == ' ') {
                pos++;
            } else {
                throw new IllegalArgumentException("Invalid character: " + currentChar);
            }
        }
        return tokens;
    }

    private String readNumber() {
        StringBuilder number = new StringBuilder();
        while (pos < input.length() && (Character.isDigit(input.charAt(pos)) || input.charAt(pos) == '.')) {
            number.append(input.charAt(pos));
            pos++;
        }
        return number.toString();
    }
}

