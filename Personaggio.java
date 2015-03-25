
package personaggio;

/*** Autor Kae ***/
public class Personaggio {
    private String Nome;
    private Classe Clas;
    private int Livello;
    private Profilo Profi;
    private int MaxFerite;
    private int PuntiFerita;
    private Armatura Armatura;
    private int ClasseArmatura;
    private int BonusAttaccoBase;
    private int BonusManovraCombattimento;
    private int DifesaManovraCombattimento;
    private int Tempra;
    private int Riflessi;
    private int Volontà;
    
    
public Personaggio (String nome, Classe classe, int livello, Profilo profilo){
    int tiri, pf=0, dest;
    this.Nome = nome;
    this.Clas = classe;
    this.Livello = livello;
    this.Profi = profilo;
        // calcolo dei pf
    for (tiri=0; tiri<livello; tiri++) {
        pf= pf+(classe.getDadoVita().roll())+profilo.Bonus(profilo.getCostituzione());
                }
    this.MaxFerite = pf+1;
    this.PuntiFerita = pf;
        // l'armatura iniziale è nulla + calcolo della classe armatura
    this.Armatura = new Armatura ("nessuna", 0, 100, 0);
        if (Profi.Bonus(Profi.getDestrezza()) > Armatura.getMaxDestrzza() ) {dest = Armatura.getMaxDestrzza();}
        else {dest = Profi.Bonus(Profi.getDestrezza());}
    this.ClasseArmatura = 10+(Armatura.getValoreArmatura())+dest;
        // valori derivati del personaggio
    this.BonusAttaccoBase = (classe.getModAttacco(livello));
    this.BonusManovraCombattimento = BonusAttaccoBase+profilo.Bonus(profilo.getForza());
    this.DifesaManovraCombattimento = 10+profilo.Bonus(profilo.getForza())+profilo.Bonus(profilo.getDestrezza());
        // saves
    this.Tempra = classe.getTempra(livello)+profilo.Bonus(profilo.getCostituzione());      
    this.Riflessi = classe.getRiflessi(livello)+profilo.Bonus(profilo.getDestrezza());           
    this.Volontà = classe.getVolontà(livello)+profilo.Bonus(profilo.getSaggezza());
}

/************************************************************/

// getter e setter
public String getNome () {
    return Nome;
}
public Classe getClasse () {
    return Clas;
}
public int getLivello () {
    return Livello;
}
public Profilo getProfilo () {
    return Profi;
}
public int getDifesaManovra () {
    return DifesaManovraCombattimento;
}

/************************************************************/

public int Tira (int quanti, Dado dado) { //per tirare dadi e basta
  int risultato=0, giro;
    for (giro=0; giro==quanti; giro++)
        risultato=(risultato+dado.roll());
  return risultato;
}

// SAVES (naturalmente non hanno setter)
public int getTempra () {
    return Tempra;
}
public int getRiflessi () {
    return Riflessi;
}
public int getVolontà () {
    return Volontà;
}
public int SalvezzaSullaTempra () {  // esegue il tiro salvezza sulla tempra
    int save, tiro;
    tiro = (int)(Math.random() * 20) + 1;
    save= Tempra+tiro;
    return save;
}
public int SalvezzaSuiRiflessi () {  // esegue il tiro salvezza sui riflessi
    int save, tiro;
    tiro = (int)(Math.random() * 20) + 1;
    save= Riflessi+tiro;
    return save;
}
public int SalvezzaSullaVolontà () {  // esegue il tiro salvezza sulla volontà
    int save, tiro;
    tiro = (int)(Math.random() * 20) + 1;
    save= Volontà+tiro;
    return save;
}
// comandi dell'armatura
public int getClasseArmatura () {
    return ClasseArmatura;
}
public String getArmatura () {
    return Armatura.ProfiloArmatura();
}
public void setArmatura (Armatura armatura) {
    int dest;
    if (Profi.Bonus(Profi.getDestrezza()) > armatura.getMaxDestrzza() ) {dest = armatura.getMaxDestrzza();}
    else {dest = Profi.Bonus(Profi.getDestrezza());}
    this.Armatura = armatura;
    this.ClasseArmatura = 10+(Armatura.getValoreArmatura())+dest;
}
public int ProvaArmatura (Armatura armatura) { //dice quale sarebbe la CA se si indossasse quell'armatura
    int tot, dest;
    if (Profi.Bonus(Profi.getDestrezza()) > armatura.getMaxDestrzza() ) {dest = armatura.getMaxDestrzza();}
    else {dest = Profi.Bonus(Profi.getDestrezza());}
    tot = 10+armatura.getValoreArmatura()+dest;
    return tot;
}

// gestione ferite 
public int getPuntiFerita () {
    return PuntiFerita;
}
public void Ferito (int danni) {
    int pf = PuntiFerita;
    this.PuntiFerita = pf-danni; // nuove ferite
}
public void Curato (int danni) {
    int pf = PuntiFerita;
    if (pf+danni < MaxFerite) {
        this.PuntiFerita = pf+danni;} // nuove ferite
    else {this.PuntiFerita = MaxFerite-1;}
}

 // Comandi per il combattimento
public int TiroD20 () {
    int tiro;
    tiro = (int)(Math.random() * 20) + 1;
    return tiro;
}
/*** nemico.Ferito(arma.Danni()+Profi.Bonus(Profi.getForza())) ***/
public int AttaccoInMischia (Personaggio nemico, Arma arma) {
    int attacco, tiro, x =0;
    tiro = TiroD20 ();
    attacco = tiro+Profi.Bonus(Profi.getForza())+BonusAttaccoBase;
        if (tiro < 2) {x= -1;} // maldestro -1
        else if (attacco < nemico.getClasseArmatura()) // mancare 1
            {x= 1;}
        else if (attacco >= nemico.getClasseArmatura()) {
            if (tiro < arma.getCritico()){x= 2;}// colpo normale 2
            else {x= -2;} // colpo critico -2 
            }
        else if (tiro > 19) {x= -2;} // colpo critico -2
    return x;
}
public int AttaccoInLotta (Personaggio nemico) {
    int attacco, tiro, x =0;
    tiro = TiroD20 ();
    attacco = tiro+Profi.Bonus(Profi.getForza())+BonusAttaccoBase;
        if (tiro < 2) {x= -1;} // maldestro -1
        else if (attacco < nemico.getDifesaManovra()) // mancare 1
            {x= 1;}
        else if (attacco >= nemico.getDifesaManovra()) {x= 2;}// colpo normale 2
        else if (tiro > 19) {x= -2;} // critico -2
    return x;
}
public int DannoMinimo (int danno) {
    if (danno<1) { return 1 ;}
    else return danno;
}
public int DannoInMischia (Arma arma) {
    int danno;
    danno = DannoMinimo (arma.Danni()+Profi.Bonus(Profi.getForza()));
    return danno;
}
public int CriticoInMischia (Arma arma) {
    int danno;
    danno = DannoMinimo (arma.DanniCritici()+(Profi.Bonus(Profi.getForza())* arma.getMoltiplicatore()));
    return danno;
}
public int FerireInMischia (Personaggio nemico, Arma arma) {
    int x, conferma, danno=0;
    x = AttaccoInMischia (nemico, arma);
        if (x == 1) {danno=0;} // mancato
        else if (x == 2) {danno= DannoInMischia(arma);} //ferito
        else if (x == -2) { // critico
            conferma = AttaccoInMischia (nemico, arma);
                if (conferma == 1 || conferma == -1) {danno= DannoInMischia(arma);} // non confermato
                else if (conferma == 2) {danno= CriticoInMischia(arma);} // confermato
            }
    return danno;   
}
public String Ferire (int danno, Personaggio nemico) {
    String s="";
    nemico.Ferito(danno);
        if (nemico.getPuntiFerita()>0) {s=nemico.getNome()+" ha perso "+danno+" punti ferita";}
        else if (nemico.getPuntiFerita()==0) {s=nemico.getNome()+" barcolla esausto";}
        else {s=nemico.getNome()+" è a terra morente";}
    return s;

}

public String Lottare (int lotta, Personaggio nemico) {
    String s="";
        if (lotta <= 1) {s= Nome+" ha preso in lotta "+nemico.getNome();}
        else {s= Nome+" non è riuscito ad afferrare "+nemico.getNome();}
    return s;
}


public String StampaScheda () {
    String r="------------------------------\n"
            + " Nome: "+Nome+" ("+Clas.getNomeClasse()+" di "+Livello+"°)\n"
            + " Punti Ferita: "+(MaxFerite-1)+"\n"
            + " Ferite Attuali: "+PuntiFerita+"\n"
            + " Classe Armatura:"+ClasseArmatura+" (Des "+Profi.Bonus(Profi.getDestrezza())+")\n"
            + "| FOR "+Profi.getForza()+" "+Profi.Bonus(Profi.getForza())+" DES "+Profi.getDestrezza()+" "+Profi.Bonus(Profi.getDestrezza())+" COS "+Profi.getCostituzione()+" "+Profi.Bonus(Profi.getCostituzione())+" |\n"
            + "| INT "+Profi.getIntelligenza()+" "+Profi.Bonus(Profi.getIntelligenza())+" SAG "+Profi.getSaggezza()+" "+Profi.Bonus(Profi.getSaggezza())+" CAR "+Profi.getCarisma()+" "+Profi.Bonus(Profi.getCarisma())+" |\n"
            + "| BAB :"+BonusAttaccoBase+"   BMC :"+BonusManovraCombattimento+"   DMC :"+DifesaManovraCombattimento+"  |\n"
            + "| T-> "+Tempra+"    R->  "+Riflessi+"   V->  "+Volontà+"  |\n"
            + "------------------------------";
    return r;
}
   
    
    public static void main(String[] args) {
        
// Set dei Dadi
Dado d4  = new Dado(4);
Dado d6  = new Dado(6);
Dado d8  = new Dado(8);
Dado d10 = new Dado(10);
Dado d12 = new Dado(12);
Dado d20 = new Dado(20);

// Classi
Classe class1 = new Classe ("Guerriero", "V", d10, "A", "B", "B");
Classe class2 = new Classe ("Barbaro", "V", d12, "A", "B", "B");
// Profili
Profilo Pro1 = new Profilo (15, 13, 12, 10, 9, 8);
Profilo Pro2 = new Profilo (13, 15, 10, 9, 12, 8);

// Armature
Armatura Leggera = new Armatura ("Leggera", 2, 4, 1);
Armatura Media = new Armatura ("Media", 4, 3, 2);
Armatura Pesante = new Armatura ("Pesante", 8, 0, 4);

//Armi
Arma Spada = new Arma ("Spada",d6, 19, 2, 1, 20);
Arma Martello = new Arma ("Martello",d8, 20, 3, 1, 30);
Arma Pistola = new Arma ("Pistola",d10, 18, 4, 45, 100);

// PERSONAGGI
Personaggio PNG1 = new Personaggio ("PNG1", class1, 6, Pro1);
PNG1.setArmatura(Pesante);
System.out.println(PNG1.StampaScheda());
Personaggio PNG2 = new Personaggio ("PNG2", class2, 6, Pro2);
PNG2.setArmatura(Media);
System.out.println(PNG2.StampaScheda());

System.out.println(PNG1.Ferire(PNG1.FerireInMischia(PNG2, Spada), PNG2));
System.out.println(PNG2.Ferire(PNG2.FerireInMischia(PNG1, Martello), PNG1));
System.out.println("-- "+PNG1.getPuntiFerita()+" -- "+PNG2.getPuntiFerita()+" --");
System.out.println(PNG1.Lottare(PNG1.AttaccoInLotta(PNG2), PNG2));
System.out.println(PNG1.Lottare(PNG1.AttaccoInLotta(PNG2), PNG2));


}    
}
