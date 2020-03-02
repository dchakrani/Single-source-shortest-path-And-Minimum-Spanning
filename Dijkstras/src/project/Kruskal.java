package project;

import java.util.*;

public class Kruskal {

	private static Map<String, String> PARENT; // To store parent
	private static Map<String, Integer> DEPTH; // to store ranks

	public static void initialize(String[] universe) {
		PARENT = new HashMap<String, String>();
		DEPTH = new HashMap<>();
		for (String x : universe) {
			PARENT.put(x, x);
			DEPTH.put(x, 1);
		}
	}

	public static String FindCloud(String item) {
		String parent = PARENT.get(item);
		if (parent == item)
			return item;
		else
			return FindCloud(parent);
	}

	public static void Union(String setA, String setB) {
		String parentA, parentB;
		while ((parentA = PARENT.get(setA)) != setA) {
			setA = parentA;
		}
		while ((parentB = PARENT.get(setB)) != setB) {
			setB = parentB;
		}

		int depthFirst = DEPTH.get(setA), depthSecond = DEPTH.get(setB);
		if (depthFirst > depthSecond) {
			PARENT.put(setB, setA);
			updateDepthUp(setB);
		} else if (depthSecond > depthFirst) {
			PARENT.put(setA, setB);
			updateDepthUp(setA);
		} else {
			PARENT.put(setB, setA);
			updateDepthUp(setB);
		}
	}

	public static void updateDepthUp(String current) {
		int tempDepth = DEPTH.get(current); // we will store current depth into
											// temp variable depth
		String currentsParent = PARENT.get(current);
		int parentsDepth = DEPTH.get(currentsParent);
		if (!(tempDepth < parentsDepth || currentsParent == current)) {
			DEPTH.put(currentsParent, tempDepth + 1);
			updateDepthUp(currentsParent);
		}
	}

	public ArrayList<Edge> KruskalAlgo(String[] vertices, Edge[] edges) {
		
		int cost = 0;
		// AT start A = empty set
		ArrayList<Edge> minimum = new ArrayList<>();

		// for each vertex v belongs to graph we will make set
		initialize(vertices);

		// sort edges into increasing order by cost
		Arrays.sort(edges);

		// For each edge from u to v in increasing order by cost
		for (Edge e : edges) {
			// If (find-set(u)!=find-set(v)
			if (FindCloud(e.v1) != FindCloud(e.v2)) {
				// add edge to the minimum tree constructed so far
				minimum.add(e);
				//System.out.print("test" + e.weight);
				cost += e.weight;
				// UNION of vertices u & v
				Union(e.v1, e.v2);
			}
		}
		// Display our MST
		System.out.println("M.S.T. has the edges: " + minimum);
		System.out.println(cost);
		return minimum;
	}
}