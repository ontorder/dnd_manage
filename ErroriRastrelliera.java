package personaggio;

/*** @author Kae ***/
public class ErroriRastrelliera extends Exception {
    public ErroriRastrelliera() {
    }

    public String nonPosto (int posto) {
        return "non esiste uno slot arma <"+posto+">\nindica uno slot compreso tra 0 e 9";
    }
    public String postoVuoto (int posto) {
        return "non c'è nessuna arma nello slot <"+posto+">\nindica un altro slot";
    }
    public String postoPieno (int posto) {
        return "lo slot <"+posto+"> contiene già un arma\nindica un altro slot";
    }
    public String postozero () {
        return "Non puoi né sostituire né disequipaggiare le tue mani!";
    }   
}