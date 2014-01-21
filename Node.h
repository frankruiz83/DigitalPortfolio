#pragma once
#include <string>
#include <iostream>

using namespace std;

class Node
{
public:
	string Name, Parent;	//Frank added Parent for Bellman-Ford.
	int Distance;
	bool checked;			//Frank added this for Bellman-Ford.
	Node(void);
	Node(string name, int d);
	~Node(void);
	void print();
	void setName(string name);
	string getName();
	void setDistance(int d);
	int getDistance();
};

