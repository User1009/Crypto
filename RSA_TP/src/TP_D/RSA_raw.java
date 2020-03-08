package TP_D;// -*- coding: utf-8 -*-

import java.math.BigInteger;
import java.util.Random;

public class RSA_raw {
    private static BigInteger code, codeChiffré, codeDéchiffré ;
    private static BigInteger n ;      // Le module de la clef publique
    private static BigInteger e ;      // L'exposant de la clef publique
    private static BigInteger d ;      // L'exposant de la clef privée

    static void fabrique() {           // Fabrique d'une paire de clefs RSA (A MODIFIER)
       // n = new BigInteger("196520034100071057065009920573", 10);

        //d = new BigInteger("56148581171448620129544540223", 10);

        //e = new BigInteger("7", 10);

        BigInteger p = EPP.createBIGInteger(1024);
        BigInteger q = EPP.createBIGInteger(1024);
        p = EPP.make_n_primal(p);
        q = EPP.make_n_primal(q);
        System.out.println("valeur de p : "+ p + "valeur de q :"+ q);

        n=p.multiply(q);

        BigInteger un = new BigInteger("1",10);

        BigInteger w = p.subtract(un).multiply(q.subtract(un));  //w = (p-1) * (q-1)


        System.out.println("Valeur de n :"+n);
        System.out.println("Valeur de w :"+w);

        Random r = new Random();
        d = new BigInteger(1024,r);
        while(!d.gcd(w).equals(un) || d.compareTo(w) >= 0){
            d = new BigInteger(1024,r);
        }

        System.out.println("Creation de d finie ");
        e = d.modInverse(w);
        System.out.println("Creation de e finie");

    }



    public static void main(String[] args) {
        code = new BigInteger("4b594f544f", 16);

        /* Affichage du code clair */
        System.out.println("Code clair        : " + code);

        fabrique();

        /* Affichage des clefs utilisées */
        System.out.println("Clef publique (n) : " + n);
        System.out.println("Clef publique (e) : " + e);
        System.out.println("Clef privée (d)   : " + d);

        /* On effectue d'abord le chiffrement RSA du code clair avec la clef publique */
        codeChiffré = code.modPow(e, n);
        System.out.println("Code chiffré      : " + codeChiffré);

        /* On déchiffre ensuite avec la clef privée */
        codeDéchiffré = codeChiffré.modPow(d, n);
        System.out.println("Code déchiffré    : " + codeDéchiffré);
    }
}