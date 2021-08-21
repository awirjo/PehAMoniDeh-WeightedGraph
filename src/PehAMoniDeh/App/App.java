package PehAMoniDeh.App;

import PehAMoniDeh.Datastructure.OneEdgeQueue;
import PehAMoniDeh.Datastructure.QueObject;
import PehAMoniDeh.Datastructure.TestQueue;
import PehAMoniDeh.Graph;
import PehAMoniDeh.entity.Abanies;

public class App {
    public static void main(String[] args) {
        Abanies fouta = new Abanies("Fouta", "Mastermind Villain", 99);
        Abanies chantaki = new Abanies("Chantaki", "Pres for Nep(otisme)", 75);
        Abanies melissa = new Abanies("Melissa", "GT Gyal", 65);
        Abanies domswijk = new Abanies("Domswijk", "Vieze Pres", 85);
        Abanies leoswijk = new Abanies("Leoswijk", "Postman", 90);
        Abanies dekleurBordo = new Abanies("deKleurBordo", "Money Hond", 70);
        Abanies kirpa = new Abanies("Kirpa", "Not for better and cheaper", 55);
        Abanies kanhaai = new Abanies("KanHaai", "Alles Legaal", 25);
        Abanies vanTrickster = new Abanies("VanTrickster", "Cellblok 008", 80);
        Abanies kromosroto = new Abanies("Kromosroto", "Domme javaan", 65);
        Abanies achaiber = new Abanies("AchaiBer", "Stijgende koers afdeling", 35);
        Abanies badjalala = new Abanies("Badjalala", "Tjoekoe Gang", 45);
        Abanies ramdinho = new Abanies("Ramdinho", "Buitenlandse Bezwaar", 25);
        Abanies koeldipsang = new Abanies("KoeldipSang", "Zware Materiaal", 55);
        Abanies boefdraad = new Abanies("Boefdraad", "Underground Boss", 98);

        Graph graph = new Graph();

        graph.addVertex(fouta);
        graph.addVertex(chantaki);
        graph.addVertex(melissa);
        graph.addVertex(domswijk);
        graph.addVertex(leoswijk);
        graph.addVertex(dekleurBordo);
        graph.addVertex(kirpa);
        graph.addVertex(kanhaai);
        graph.addVertex(vanTrickster);
        graph.addVertex(kromosroto);
        graph.addVertex(achaiber);
        graph.addVertex(badjalala);
        graph.addVertex(ramdinho);
        graph.addVertex(koeldipsang);
        graph.addVertex(boefdraad);

        graph.addEdge(fouta, kanhaai, 55);
        graph.addEdge(fouta, ramdinho, 30);
        graph.addEdge(kanhaai, chantaki, 25);
        graph.addEdge(kanhaai, domswijk, 75);
        graph.addEdge(achaiber, domswijk, 20);
        graph.addEdge(chantaki, achaiber, 45);
        graph.addEdge(domswijk, dekleurBordo, 65);
        graph.addEdge(dekleurBordo, leoswijk, 60);
        graph.addEdge(dekleurBordo, koeldipsang, 45);
        graph.addEdge(leoswijk, fouta, 40);
        graph.addEdge(koeldipsang, fouta, 35);
        graph.addEdge(koeldipsang, vanTrickster, 25);
        graph.addEdge(ramdinho, melissa, 25);
        graph.addEdge(melissa, chantaki, 20);
        graph.addEdge(melissa, badjalala, 30);
        graph.addEdge(badjalala, kirpa, 50);
        graph.addEdge(kirpa, kromosroto, 35);
        graph.addEdge(kromosroto, boefdraad, 75);
        graph.addEdge(kromosroto, vanTrickster, 50);
        graph.addEdge(vanTrickster, boefdraad, 80);
        graph.addEdge(boefdraad, kirpa, 60);
        graph.addEdge(boefdraad, fouta, 90);

        //graph.visualizeAdjMatrix();
        //graph.displayVertexAbaniesName(10);
        graph.bfsVertex1EdgeSeparated(domswijk);
        //graph.displayAllVertices();
        OneEdgeQueue oneEdgeQueue = new OneEdgeQueue();
        oneEdgeQueue.showQueue();







    }
}
