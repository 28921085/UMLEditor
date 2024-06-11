package Model;

public enum ModeType {
    SELECT,
    ASSOCIATION_LINE,
    GENERALIZATION_LINE,
    COMPOSITION_LINE,
    CLASS,
    USE_CASE,
    NONE;
    public String getFileName(){
        String res=System.getProperty("user.dir");
        switch (this){
            case SELECT:
                res+="\\src\\assets\\select.png";
                break;
            case ASSOCIATION_LINE:
                res+="\\src\\assets\\association line.png";
                break;
            case GENERALIZATION_LINE:
                res+="\\src\\assets\\generalization line.png";
                break;
            case COMPOSITION_LINE:
                res+="\\src\\assets\\composition line.png";
                break;
            case CLASS:
                res+="\\src\\assets\\class.png";
                break;
            case USE_CASE:
                res+="\\src\\assets\\use case.png";
                break;
            default:
                return "";
        }
        return res;
    }
    public boolean isLine(){
        return (this==ASSOCIATION_LINE||this==GENERALIZATION_LINE||this==COMPOSITION_LINE);
    }
}