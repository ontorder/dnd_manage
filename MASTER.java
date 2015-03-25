package personaggio;

/*** Autor Kae ***/

public class MASTER {
    private String Nome;

/*** Costruttore ***/

public MASTER (String nome) {
    this.Nome = nome;
}

/*** robe del master ***/

// tira 4d6 e scarta il più basso
public int ValoreProfilo () {
    int risultato, a, b, c, d, x, y, z;
    a = (int)(Math.random() * 6) + 1;
    b = (int)(Math.random() * 6) + 1;
    c = (int)(Math.random() * 6) + 1;
    d = (int)(Math.random() * 6) + 1;
    // il piu piccolo tra i primi 2
    if (a<b) {x=a;} 
    else  {x=b;}
    // il piu piccolo tra i secondi 2
    if (c<d) {y=c;} 
    else  {y=d;}
    // il piu piccolo tra i 2 più piccoli
    if (x<y) {z=x;} 
    else  {z=y;}
    risultato=a+b+c+d-z;
    
    return risultato;
}
// assegna valori casuali ad un profilo
public Profilo RandomizzaProfilo (Profilo profilo) {
    profilo.setForza(ValoreProfilo ());
    profilo.setDestrezza(ValoreProfilo ());
    profilo.setCostituzione(ValoreProfilo ());
    profilo.setIntelligenza(ValoreProfilo ());
    profilo.setSaggezza(ValoreProfilo ());
    profilo.setCarisma(ValoreProfilo ());
    
    return profilo;
}

public String StampaScheda (Personaggio personaggio) {
    String r= personaggio.StampaScheda();
    return r;
}

//crea un nuovo personaggio base dai con gli stessi valori inziali del primo
public Personaggio ClonaPersonaggio (Personaggio personaggio) {
    Personaggio Clone = new Personaggio ("clone di "+personaggio.getNome(), personaggio.getClasse(), personaggio.getLivello(), personaggio.getProfilo());
    return Clone;
}

public static void main(String[] args) {

MASTER Kae = new MASTER ("Kae");

// Set dei Dadi
Dado d4  = new Dado(4);
Dado d6  = new Dado(6);
Dado d8  = new Dado(8);
Dado d10 = new Dado(10);
Dado d12 = new Dado(12);
Dado d20 = new Dado(20);

// Classi
Classe class1 = new Classe ("Guerriero", "V", d10, "A", "B", "B");

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
Personaggio PNG1 = new Personaggio ("PNG1", class1, 4, Pro1);

System.out.println(PNG1.StampaScheda());

System.out.println(Kae.StampaScheda(Kae.ClonaPersonaggio(PNG1)));

}
}
