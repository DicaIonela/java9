package com.example.lab9;

import java.io.*;
import java.util.LinkedList;

import java.util.Queue;
public class ArboreBinarString {
    private Nod<String> radacina; // radacina arborelui
    private BufferedReader r;
    private StringBuilder sb;
    private boolean tastatura;
    private static String txtRadacina="Radacina";
    private String traversare;
    public ArboreBinarString(InputStream is)
    {
        tastatura = is == System.in;
        r = new BufferedReader( new InputStreamReader(is) );
        radacina = constArbore( creeazaNod(txtRadacina, null) );
    }
    public ArboreBinarString(File fisier) {
        try {
            r = new BufferedReader(new FileReader(fisier));
            citesteArboreDinFisier(fisier); // Citim arborele din fișier
        } catch (IOException e) {
            System.out.println("Eroare la deschiderea fișierului: " + e.getMessage());
        }
    }
    private Nod<String> constArbore (Nod<String> x)
    {
        if(((String)x.getInfo()).equals("")) return null;
        x.setStg( constArbore( creeazaNod("stang",x) ));
        x.setDr( constArbore( creeazaNod("drept",x) ));
        return x;
    }
    private Nod<String> creeazaNod(String s,Nod<String> tata)
    {
        if(!s.equals(txtRadacina))
            s="Introduceti fiul "+ s + " al nodului "+ tata;
        return new Nod<String>(citesteInfo(s));
    }
    private String citesteInfo(String text)
    {
        String info;
        if(tastatura) // daca se citeste de la tastatura -> mesaj prompter
            System.out.print("\n" + text + ":");
        try {
            info= r.readLine().trim();
        } catch(IOException e) {
            info="";
            System.out.println(e);
        }
        return info;
    }
    public String toString()
    {
        traversare="Traversare SRD: ";
        traversareSRD(radacina,sb);
        return traversare;
    }


    public String traversareRSD() {
        StringBuilder sb = new StringBuilder();
        traversareRSD(radacina, sb);
        return sb.toString();
    }

    private void traversareRSD(Nod<String> nod, StringBuilder sb) {
        if (nod == null) return;
        sb.append(nod.getInfo()).append(" "); // Vizitează nodul
        traversareRSD(nod.getStg(), sb);     // Traversare stângă
        traversareRSD(nod.getDr(), sb);      // Traversare dreaptă
    }

    // Traversare Inordine (SRD)
    public String traversareSRD() {
        StringBuilder sb = new StringBuilder();
        traversareSRD(radacina, sb);
        return sb.toString();
    }

    private void traversareSRD(Nod<String> nod, StringBuilder sb) {
        if (nod == null) return;
        traversareSRD(nod.getStg(), sb);     // Traversare stângă
        sb.append(nod.getInfo()).append(" "); // Vizitează nodul
        traversareSRD(nod.getDr(), sb);      // Traversare dreaptă
    }

    // Traversare Postordine (SDR)
    public String traversareSDR() {
        StringBuilder sb = new StringBuilder();
        traversareSDR(radacina, sb);
        return sb.toString();
    }

    private void traversareSDR(Nod<String> nod, StringBuilder sb) {
        if (nod == null) return;
        traversareSDR(nod.getStg(), sb);     // Traversare stângă
        traversareSDR(nod.getDr(), sb);      // Traversare dreaptă
        sb.append(nod.getInfo()).append(" "); // Vizitează nodul
    }
    public void citesteArboreDinFisier(File fisier) {
        try (BufferedReader br = new BufferedReader(new FileReader(fisier))) {
            String linie;
            Queue<Nod<String>> coada = new LinkedList<>();

            // Citim prima linie (radacina)
            if ((linie = br.readLine()) != null) {
                radacina = new Nod<>(linie.trim());
                coada.add(radacina); // Adăugăm rădăcina în coadă
            }

            // Citirea nodurilor pentru arbore
            while ((linie = br.readLine()) != null) {
                String[] copii = linie.trim().split("\\s+");
                Nod<String> parinte = coada.poll();

                if (copii.length > 0 && !copii[0].isEmpty()) {
                    Nod<String> stang = new Nod<>(copii[0]);
                    parinte.setStg(stang);
                    coada.add(stang);  // Adăugăm fiul stâng în coadă
                }

                if (copii.length > 1 && !copii[1].isEmpty()) {
                    Nod<String> drept = new Nod<>(copii[1]);
                    parinte.setDr(drept);
                    coada.add(drept);  // Adăugăm fiul drept în coadă
                }
            }

            System.out.println("Arborele a fost citit din fișier.");
        } catch (IOException e) {
            System.out.println("Eroare la citirea fișierului: " + e.getMessage());
        }
    }
}