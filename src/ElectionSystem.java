import java.util.LinkedList;
import java.util.List;

public class ElectionSystem {
    public static void main(String[] args) {
        LinkedList<String> candidates = new LinkedList<>();
        candidates.add("Marcus Fenix");
        candidates.add("Dominic Santiago");
        candidates.add("Damon Baird");
        candidates.add("Cole Train");
        candidates.add("Anya Stroud");

        int p = 5;

        Election election = new Election(p);

        election.initializeCandidates(candidates);

        election.castVote("Cole Train");
        election.castVote("Cole Train");
        election.castVote("Marcus Fenix");
        election.castVote("Anya Stroud");
        election.castVote("Anya Stroud");

        System.out.println("Top 3 candidates after 5 votes: " + election.getTopKCandidates(3));

        election.rigElection("Marcus Fenix");

        System.out.println("Top 3 candidates after rigging the election: " + election.getTopKCandidates(3));

        System.out.println("Audit Results:");
        election.auditElection();
    }
}
