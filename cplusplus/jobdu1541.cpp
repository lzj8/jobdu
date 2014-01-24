/*
 * jobdu1541.cpp
 *
 *  Created on: Jan 23, 2014
 *      Author: xlh
 */
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

struct Node {
	int count;
	int index;
	Node *left;
	Node *right;
	Node *parent;
};

const int SIZE = 1000;
static Node nodes[SIZE];

void getCount(Node & node) {
	if (node.count == 0) {
		node.count = 1;
		if (node.left != NULL) {
			getCount(*node.left);
			node.count += node.left->count;
		}
		if (node.right != NULL) {
			getCount(*node.right);
			node.count += node.right->count;
		}
	}
}

void rotate(Node *node) {
	Node *p = node->parent;
	if (p == NULL) {
		return;
	}
	Node *pp = p->parent;
	node->parent = pp;
	p->parent = node;
	if (pp != NULL && pp->left == p) {
		pp->left = node;
	}
	if (pp != NULL && pp->right == p) {
		pp->right = node;
	}

	if (p->left == node) {
		p->left = node->right;
		if (node->right != NULL) {
			node->right->parent = p;
		}
		node->right = p;
	} else {
		p->right = node->left;
		if (node->left != NULL) {
			node->left->parent = p;
		}
		node->left = p;
	}
	p->count = 1;
	if (p->left != NULL) {
		p->count += p->left->count;
	}
	if (p->right != NULL) {
		p->count += p->right->count;
	}
	node->count = 1;
	if (node->left != NULL) {
		node->count += node->left->count;
	}
	if (node->right != NULL) {
		node->count += node->right->count;
	}
}

int main() {
	int n;
	while (scanf("%d", &n) != EOF) {
		for (int i = 0; i < n; ++i) {
			nodes[i].count = 0;
			nodes[i].left = NULL;
			nodes[i].right = NULL;
			nodes[i].parent = NULL;
			nodes[i].index = i;
		}
		for (int i = 0; i < n; ++i) {
			int x, y;
			scanf("%d %d", &x, &y);
			--x;
			--y;
			if (x >= 0) {
				nodes[i].left = &nodes[x];
				nodes[x].parent = &nodes[i];
			}
			if (y >= 0) {
				nodes[i].right = &nodes[y];
				nodes[y].parent = &nodes[i];
			}
		}

		for (int i = 0; i < n; ++i) {
			getCount(nodes[i]);
		}

		int t;
		char opt[20];
		int nodeIndex;
		scanf("%d", &t);
		while (t-- != 0) {
			scanf("%s %d", opt, &nodeIndex);
			--nodeIndex;
			if (opt[0] == 's') {
				printf("%d\n", nodes[nodeIndex].count);
			} else if (opt[0] == 'r') {
				rotate(&nodes[nodeIndex]);
			} else if (opt[0] == 'p') {
				if (nodes[nodeIndex].parent != NULL) {
					printf("%d\n", nodes[nodeIndex].parent->index + 1);
				} else {
					printf("-1\n");
				}
			}
		}
	}
	return 0;
}
