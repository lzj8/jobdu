#include <cstdio>
#include <cstdlib>
#include <vector>
#include <iostream>
#include <queue>
#include <cstring>
#include <algorithm>
#include <limits>
#include <cmath>
using namespace std;
#define INT_MAX (1<<30)
#define INT_MIN (-(1<<30))
class Edge {
public:
	Edge() {
	}
	int v;
	int to;
};
class Node {
public:
	vector<int> adjs;
	int minMax;
};
struct Step {
	int node;
	int maxEdge;
};
const int M = 100005;
const int N = 10005;
int n, m;
Edge edges[2 * M];
Node nodes[N];
struct compare {
	bool operator()(const Step& l, const Step& r) {
		return l.maxEdge > r.maxEdge;
	}
};
int findmin() {
	priority_queue<Step, deque<Step> , compare> q;
	Step s;
	s.node = 0;
	s.maxEdge = INT_MIN;
	int min = INT_MAX;
	q.push(s);
	while (!q.empty()) {
		s = q.top();
		q.pop();
		int t = s.node;

		cout << "debug:" << nodes[t].minMax << endl;
		int currentMax = s.maxEdge;
		if (t == n - 1 && min > nodes[t].minMax) {
			min = nodes[t].minMax;
			continue;
		}
		vector<int>& adjs = nodes[t].adjs;
		vector<int>::iterator it = adjs.begin();
		while (it != adjs.end()) {
			int e = *it++;
			Edge &edge = edges[e];
			int maxEdge = max(currentMax, edge.v);
			if (nodes[edge.to].minMax > maxEdge) {
				nodes[edge.to].minMax = maxEdge;
				Step newStep;
				newStep.node = edge.to;
				newStep.maxEdge = maxEdge;
				q.push(newStep);
			}
		}
	}
	return min == INT_MAX ? -1 : min;
}
/*
 int main() {
 while (scanf("%d %d", &n, &m) != EOF) {
 for (int i = 0; i < n; ++i) {
 nodes[i].adjs.clear();
 nodes[i].minMax = INT_MAX;
 }
 int maxV = INT_MIN;
 int minV = INT_MAX;
 for (int i = 0; i < m; ++i) {
 int from, to, v;
 scanf("%d %d %d", &from, &to, &v);
 --from;
 --to;
 edges[i << 1].v = v;
 edges[(i << 1) + 1].v = v;
 edges[i << 1].to = to;
 edges[(i << 1) + 1].to = from;
 nodes[from].adjs.push_back(i << 1);
 nodes[to].adjs.push_back((i << 1) + 1);
 if (maxV < v)
 maxV = v;
 if (minV > v)
 minV = v;
 }
 cout << findmin() << endl;
 }
 return 0;
 }*/
