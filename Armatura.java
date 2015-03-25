
package personaggio;

/*** Autor Kae ***/
public class Armatura {
    String Nome;
    int ValoreArmatura;
    int MaxDestrzza;
    int Penalità;
    
public Armatura (String nome, int valore, int maxd, int pen) {
    this.Nome = nome;
    this.ValoreArmatura = valore;
    this.MaxDestrzza = maxd;
    this.Penalità= pen;
}
// getter e setter
public String getNomeArmatura() {
return Nome;
}
public void setNomeArmatura(String nome) {
this.Nome = nome;
}

public int getValoreArmatura() {
return ValoreArmatura;
}
public void setValoreArmatura(int valore) {
this.ValoreArmatura = valore;
}

public int getMaxDestrzza() {
return MaxDestrzza;
}
public void setMaxDestrzza(int maxd) {
this.MaxDestrzza = maxd;
}

public int getPenalità() {
return Penalità;
}
public void setPenalità(int pen) {
this.Penalità = pen;
}
/*** Metodi ***/

public String ProfiloArmatura () {
    String r= Nome+": Bonus +"+ValoreArmatura+" - Bonu DES massima +"+MaxDestrzza+" - Penalatà di Armatura alla Prova -"+Penalità;
    return r;
}

/*** Main ***/
public static void main(String[] args) {
    
    Armatura Leggera = new Armatura ("Leggera", 2, 4, 1);
    Armatura Media = new Armatura ("Media", 4, 3, 2);
    Armatura Pesante = new Armatura ("Pesante", 8, 0, 4);
    
    System.out.println(Leggera.ProfiloArmatura());
    System.out.println(Media.ProfiloArmatura());
    System.out.println(Pesante.ProfiloArmatura());
    
    System.out.println(Pesante.getValoreArmatura());
    
    
}
}
