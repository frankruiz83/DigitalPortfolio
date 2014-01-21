#include "Node.h"
#include <vector>
#include <iostream>
#include <fstream>
#include <sstream>

using namespace std;

void short_Path();
void update(Node&, Node&, int);

vector<vector<Node>> Data;		//Frank made this global so that Bellman Ford can access it.

void main()
{
	vector<Node> temp;
	//File reading
	ifstream file;
	file.open("CSCI 3330 Project 3 file.txt");
	//Temporary variables to hold information
	string tmp;
	string tmpName;
	string tmpDistance;
	int hold;	
	//Loops through file
	while(!file.eof())
	{   
		getline(file, tmp); //Gets first line
		istringstream iss(tmp); //Converts to a stream
		//Gets first node which is the starting building that we look at its connections to other buildings
		getline(iss,tmpName, ' ');
		getline(iss,tmpDistance, ' ');
		hold = atoi(tmpDistance.c_str());
		//Adds node to inner vector
		Node element(tmpName, hold);//
		temp.push_back(element);
		
		do{  //Steps through string and gets information for each node
			getline(iss,tmpName, ' ');
			getline(iss,tmpDistance, ' ');
			hold = atoi(tmpDistance.c_str());
			Node element(tmpName, hold);
			temp.push_back(element);
		}while(!iss.eof());
		//Adds to outer vector which holds all the information
		Data.push_back(temp);
		temp.clear();
	}
	//Prints data in same format as the file for accuracy checking
	for(unsigned int i = 0; i < Data.size();  i++)
	{
		for(unsigned int j = 0; j < Data.at(i).size(); j++)
		{
			Data.at(i).at(j).print();
		}

		cout << endl;
	}

	file.close();

	cin.clear();
	cout << "Press return to continue...";
	cin.ignore(255, '\n');

	short_Path();
}

void update(Node &a, Node &b, int length)
{
	if(b.getDistance() > (a.getDistance() + length))
	{
		b.setDistance(a.getDistance() + length);
		b.Parent = a.Name;
	}
}

void short_Path()
{
	vector<vector<Node>> a;

	int children, temp;

	a = Data;

	//This should initialize the distances for all nodes to max value.
	for(unsigned int i=0; i<a.size(); i++)
	{
		a.at(i).at(0).Distance = 1000000;
		
	}

	//Sets starting node distance to 0
	a.at(5).at(0).setDistance(0);

	for(unsigned int i=0; i<a.size()-1; i++)
	{
		for(unsigned int j=0; j<a.size(); j++)
		{
			children = a.at(j).size() - 1;

			for(int k=0; k<children; k++)
			{
				//This for loop will find the node that matches the child node we are comparing it to.
				for(unsigned int l=0; l<a.size(); l++)
				{
					if(a.at(l).at(0).getName() == a.at(j).at(k+1).getName())
					{
						temp = l;
					}
				}

				update(a.at(j).at(0), a.at(temp).at(0), a.at(j).at(k+1).getDistance());
			}
		}		
	}

	//This will output the info relating to the nodes and their distances.
	for(unsigned int i=0; i<a.size(); i++)
	{
		cout << a.at(i).at(0).Name << ": " << a.at(i).at(0).Distance << endl;
	}

	for(unsigned int i=0; i<a.size(); i++)
	{
		cout << a.at(i).at(0).getName() << " <-- ";

		for(unsigned int j=0; j<a.size(); j++)
		{
			if(a.at(j).at(0).getName() == a.at(i).at(0).Parent)
			{
				temp = j;
			}

			while(a.at(temp).at(0).Parent != a.at(temp).at(0).getName())
			{
				cout << a.at(temp).at(0).getName() << " <-- ";

				for(unsigned int k=0; k<a.size(); k++)
				{
					if(a.at(k).at(0).getName() == a.at(temp).at(0).Parent)
					{
						temp = k;
					}
				}
			}
		}

		cout << "Math-Comp_Science" << endl << endl;
	}
}



