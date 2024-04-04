package core;

/**
 * This enum has two colors black and white
 * They have 2 variable colorName and symbol (which can be used in chess notation)
 */
public enum Colors {
    WHITE("white", 'w'),
    BLACK("black",'b');


    protected String colorName;
    protected char symbol;
    Colors(String colorName, char symbol){
        this.colorName = colorName;
        this.symbol = symbol;
    }
    public String getColorName()
    {
        return colorName;
    }

    public char getSymbol()
    {
        return symbol;
    }

    public String getSymbolAsString()
    {
        return String.valueOf(symbol);
    }

    public void setColorName(String colorName) {
        this.colorName = colorName;
    }

    public void setSymbol(char symbol) {
        this.symbol = symbol;
    }
}
