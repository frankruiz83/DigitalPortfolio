#include "Node.h"

using namespace std;

Node::Node(string name, int d)
{
	Name = Parent = name;
	Distance = d;
	checked = false;
}
Node::Node(void)
{
	Name = Parent = "None";
	Distance = 0;
	checked = false;
}


Node::~Node(void)
{
}
//Some get and set functions
string Node::getName()
{
	return Name;
}

void Node::setName(string name)
{
	Name = name;
}

int Node::getDistance()
{
	return Distance;
}

void Node::setDistance(int d)
{
	Distance = d;
}
//Prints node
void Node::print()
{
	cout << Name << " " << Distance << '\n';
}
