package DataWorker;

import parsers.*;

import java.util.*;

public class Worker {
    private BiblioDeck biblioDeck;

    public Worker(BiblioDeck biblioDeck){
        this.biblioDeck = biblioDeck;
    }

    public SortedSet<Integer> DeckTranslate (List<String> deck){
        SortedSet<Integer> deckForSPMF = new TreeSet<>(); //On rend un set qui automatiquement trié, cela permet d'éviter les carte en double
        Map<Integer, String> bib = biblioDeck.getBib();
        for (String card : deck) {
            if (!bib.containsValue(card)){
                System.out.println("La carte est inconnue, peu t'etre une erreur dans la creation de la bibliodeck.");
                return null;
            }
            for (int i = 1; i <= bib.size(); i++){
                String bibCard = bib.get(i);
                if (bibCard.equals(card)){
                    deckForSPMF.add(i);
                }
            }
        }
        return deckForSPMF;
    }
}
