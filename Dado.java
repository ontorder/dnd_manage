
package personaggio;

/*** @author Ravibhai Patel ***/
public class Dado  {
private int MAX;  // maximum face value
private int faceValue;  // current value showing on the die
 
//  costruttore
public Dado (int max) {
MAX = max;
faceValue = 1;
}

//  tira il dado e restituisce il risultato.
 public int roll()  {
faceValue = (int)(Math.random() * MAX) + 1;
return faceValue;
}

public void setFaceValue (int value) {
faceValue = value;
 }

public int getFaceValue() {
return faceValue;
}

public int getMAX() {
return MAX;
}

public String getTipo () {
    String r="d"+MAX;
    return r;
}
/******* miei *************/


//main

public static void main(String[] args) {

    // Set dei Dadi
Dado d4 = new Dado(4);
Dado d6 = new Dado(6);
Dado d8 = new Dado(8);
Dado d10 = new Dado(10);
Dado d12 = new Dado(12);
Dado d20 = new Dado(20);

}    
}
