package project;

public class Edge implements Comparable<Edge> {

	// Class Edge containing strings v1 and v2 which are the end vertices of an
	// edge

	// weight is an integer to store the weight of an edge

	public final String v1, v2;
	public final int weight;

	@Override
	public String toString() {
		return "Edge{" + "v1='" + v1 + '\'' + ", v2='" + v2 + '\''
				+ ", weight=" + weight + '}';
	}

	// A constructor to assign values to the final variables declared above
	public Edge(String v1, String v2, int weight) {
		this.v1 = v1;
		this.v2 = v2;
		this.weight = weight;
	}

	public String fromVertex() {
		return this.v1;
	}

	public String toVertex() {
		return this.v2;
	}

	@Override
	public int compareTo(Edge o) {
		return weight < o.weight ? -1 : (weight > o.weight ? 1 : 0);
	}
}