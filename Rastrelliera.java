package personaggio;

/*** @author Kae ***/
public class Rastrelliera {
    public static final int SLOT_della_RASTRELLIERA = 10;
    private Arma carico[]; 

    // Costruttore
    public Rastrelliera () {
        carico = new Arma[SLOT_della_RASTRELLIERA];
    }

    /***********************************************************************/

    public void controllaPosto (int posto) throws ErroriRastrelliera { //da errore se è stato inserito un valore non nell'array
        if ((posto<0)||(posto>=SLOT_della_RASTRELLIERA)) {
            throw new ErroriRastrelliera();
        }
    }
    public void postoVuoto (int posto) throws ErroriRastrelliera { //da errore se lo slot è vuoto
        if (carico[posto] == null) {
            throw new ErroriRastrelliera();
        }
    }  
    public void postoPieno (int posto) throws ErroriRastrelliera { //da errore se lo slot è pieno
        if (carico[posto] != null) {
            throw new ErroriRastrelliera();
        }
    }  

    public Arma getArma (int posto) { //da l'indirizzo di memoria dello slot
        try{
            controllaPosto(posto);
        }catch(personaggio.ErroriRastrelliera exception){
            System.out.println(exception.nonPosto(posto));
        }
        try{
            postoVuoto(posto);
        }catch(personaggio.ErroriRastrelliera exception){
            System.out.println(exception.postoVuoto(posto));
        }
        return carico[posto];
    }

    public void setArma (Arma arma, int posto) { //inserisce un arma in uno slot
        try{
            controllaPosto(posto);
        }catch(personaggio.ErroriRastrelliera exception){
            System.out.println(exception.nonPosto(posto));
        }
        try{
            postoPieno(posto);
        }catch(personaggio.ErroriRastrelliera exception) {
            System.out.println(exception.postoPieno(posto));
        }
        carico[posto] = arma;
    }

    public int ControllaCarico () { //dice quate armi ci sono nella rastelliera
        int posto, p=0;
        for (posto=0; posto<SLOT_della_RASTRELLIERA; posto++)
        if (carico[posto]!=null) p++;
        return p;
    }

    private int postominimo (int posto){ // evita che vada a pescare in valori negativi dell'array
        int a = ControllaCarico()-posto;
        if (a<0) {return 0;}
        return a;
    }

    /***********************************************************************/

    public static void main(String[] args) {
    }
}