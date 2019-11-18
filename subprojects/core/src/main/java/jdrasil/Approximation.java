package jdrasil;

import java.io.*;
import jdrasil.graph.*;
import jdrasil.utilities.*;
import jdrasil.algorithms.lowerbounds.*;

public class Approximation {

    public static void main(String[] args) throws Exception {
    Approximation app = new Approximation();
    app.run();
    }

    public void run() throws Exception {

    // some algorithms are randomized, set a seed to have deterministic results
    RandomNumberGenerator.seed(5368);
    
    // read graph from stdin
    Graph<Integer> g = GraphFactory.graphFromStdin();

    // simple an fast
    DegeneracyLowerbound<Integer> degeneracyLB = new DegeneracyLowerbound<>(g);
    System.out.println(String.format("degeneracy lower bound:      %4d", degeneracyLB.call()));

    // better
    MinorMinWidthLowerbound<Integer> mmwLB = new MinorMinWidthLowerbound<>(g);
    System.out.println(String.format("minor-min-width lower bound: %4d", mmwLB.call()));
        
    // best and slowest
    ImprovedGraphLowerbound<Integer> improvedLB = new ImprovedGraphLowerbound<>(g);
    improvedLB.setContraction(true);
    System.out.println(String.format("improved-graph lower bound:  %4d", improvedLB.call()));

    // Compute an improved path-graph (rather than an improved neighbor-graph). Expensive!
    // most likly you do not want this ...
    improvedLB.setPath(true);
    System.out.println(String.format("improved-path lower bound:   %4d", improvedLB.call()));
    }

}
