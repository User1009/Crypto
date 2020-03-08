package TP_D;// -*- coding: utf-8 -*-

import java.math.BigInteger;
import java.util.Random;


public class EPP
{

    public static BigInteger createBIGInteger(int taille){

        Random r = new Random();
        BigInteger x = new BigInteger(taille,r);
        BigInteger a = new BigInteger("2",10);
        a=a.pow(511);
        x=x.add(a);

        return x;

    }


    public static BigInteger make_n_primal(BigInteger n){

        BigInteger deux = new BigInteger("2",10);

        if(n.mod(deux).equals(new BigInteger("0",10))){
            n=n.add(new BigInteger("1",10));
        }

        int i=0;

        while(!est_probablement_premier(n)){

            n=n.add(new BigInteger("2",10));
            i++;

        }
        System.out.println("Nombre de tentatives :"+i);

        return n;


    }


    public static void main(String[] args)
    {
       // BigInteger n = new BigInteger("170141183460469231731687303715884105727", 10);

        BigInteger n = createBIGInteger(512);

        long debutRecherche = System.nanoTime();

        n = make_n_primal(n);

        long finRecherche = System.nanoTime();
        long dureeRechrche = (finRecherche - debutRecherche) / 1_000_000 ;


        System.out.println("obtenu en " + dureeRechrche + " millisecondes.") ;


        System.out.println("Nombre premier trouvé :"+n);

    }

    static boolean est_probablement_premier(BigInteger n)
    {
        return n.isProbablePrime(49);
    }
}

/*
  $ make
  javac *.java 
  $ java TP_D.EPP
  Le nombre 170141183460469231731687303715884105727 ...
*/

