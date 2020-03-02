package project;

import java.util.*;

class Graph {
	private final Map<String, Vertex> graph; // mapping of vertex names to
												// Vertex objects, built from a
												// set of Edges

	// This method builds a graph from set of vertices that we passed from the
	// main function
	public Graph(Edge[] edges, boolean undirected) {
		graph = new HashMap<>(edges.length);

		// putting the vertices in the hash map "graph"
		for (Edge edge : edges) {
			if (!graph.containsKey(edge.v1))
				graph.put(edge.v1, new Vertex(edge.v1));
			if (!graph.containsKey(edge.v2))
				graph.put(edge.v2, new Vertex(edge.v2));
		}

		// setting the vertices by checking neighboring vertices
		for (Edge e : edges) {
			// directed graph
			graph.get(e.v1).neighbours.put(graph.get(e.v2), e.weight);
			if (undirected) {
				graph.get(e.v2).neighbours.put(graph.get(e.v1), e.weight);
				// we need do this also for an undirected graph
			}
		}
	}

	// Dijkstra to run on starting vertex
	public void dijkstra(String startSource) {
		if (!graph.containsKey(startSource)) {
			System.err.printf("Graph doesn't contain start vertex \"%s\"\n",
					startSource);
			return;
		}
		final Vertex source = graph.get(startSource);

		// Priority Queue to store vertices
		PriorityQueue<Vertex> q = new PriorityQueue<>();

		// set-up vertices
		for (Vertex v : graph.values()) {
			v.previous = (v == source) ? source : null;
			v.cost = (v == source) ? 0 : Integer.MAX_VALUE; // setting to
															// infinite if it is
															// not source
			q.add(v);
			// adding v to priority queue one by one
		}

		implementDijkstra(q); // implementing Dijkstra algorithm on the priority
								// queue which consists of all the vertices
	}

	// / Implementation of dijkstra's algorithm using a queue
	private void implementDijkstra(final PriorityQueue<Vertex> q) {
		Vertex u, v;
		// Checking till queue is not empty
		while (!q.isEmpty()) {

			// first iteration will return us the source node/vertex that is the
			// starting vertex

			u = q.poll(); // vertex with shortest cost will be polled out of the
							// queue, for first time it will be start
			if (u.cost == Integer.MAX_VALUE) {
				break; // we can ignore u (and any other remaining vertices)
						// since they are unreachable
			}

			// checking cost of each neighboring vertices
			for (Map.Entry<Vertex, Integer> a : u.neighbours.entrySet()) {
				v = a.getKey();

				final int tempDist = u.cost + a.getValue();
				if (tempDist < v.cost) { // if shortest path found to
											// neighboring vertex
					q.remove(v); // remove v
					v.cost = tempDist;
					v.previous = u;
					q.add(v); // add new v
				}
			}
		}
	}

	// prints a path from the vertex (source) to the end vertex
	// This method was used for testing purpose only
	public void printPath(String last) {
		if (!graph.containsKey(last)) {
			System.err
					.printf("Graph doesn't contain end vertex \"%s\"\n", last);
			return;
		}

		graph.get(last).print();
		System.out.println();
	}

	// prints all the path from the source vertex to other vertices
	public void printAllPaths() {
		int cost = 0;
		for (Vertex v : graph.values()) {
			cost += v.print();
			System.out.println();
		}
		//System.out.println("Cost: " + cost);
	}

}
