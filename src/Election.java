import java.util.*;

public class Election {
    private PriorityQueue<String> maxHeap;
    private Map<String, Integer> voteCount;
    private int totalVotes = 0;
    private int maxVotesAllowed;
    private Random rand;
    public Election(int p){
        voteCount = new HashMap<>();
        maxHeap = new PriorityQueue<>((a, b) -> voteCount.get(b) - voteCount.get(a));
        rand = new Random();
        this.maxVotesAllowed = p;
    }

    public void initializeCandidates(LinkedList<String> candidates) {
        for (String candidate : candidates) {
            voteCount.put(candidate, 0);
            maxHeap.add(candidate);
        }
    }
    private void rebuildHeap() {
        maxHeap.clear();
        maxHeap.addAll(voteCount.keySet());
    }
    public void castVote(String candidate) {
        if (!voteCount.containsKey(candidate) || totalVotes >= maxVotesAllowed)
            return;
        else {
            voteCount.put(candidate, voteCount.get(candidate) + 1);
            totalVotes++;
            rebuildHeap();
        }
    }

    public void castRandomVote() {
        List<String> candidates = new ArrayList<>(voteCount.keySet());
        String randomCandidate = candidates.get(rand.nextInt(candidates.size()));
        castVote(randomCandidate);
    }

    public void rigElection(String candidate) {
        if (!voteCount.containsKey(candidate)) {
            return;
        }else {
                voteCount.put(candidate, voteCount.get(candidate) + (maxVotesAllowed - totalVotes));
                totalVotes = maxVotesAllowed;
                rebuildHeap();
            }
    }

    public List<String> getTopKCandidates(int k) {
        PriorityQueue<String> tempHeap = new PriorityQueue<>(maxHeap);
        List<String> topK = new ArrayList<>();
        while (!tempHeap.isEmpty() && k-- > 0) {
            topK.add(tempHeap.poll());
        }
        return topK;
    }

    public void auditElection() {
        List<String> sortedCandidates = new ArrayList<>(voteCount.keySet());
        sortedCandidates.sort(((a, b) -> voteCount.get(b) - voteCount.get(a)));
        for (String candidate : sortedCandidates) {
            System.out.println(candidate + ": " + voteCount.get(candidate) + " votes");
        }
    }


}
