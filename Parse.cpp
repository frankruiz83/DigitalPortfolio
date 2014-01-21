/*
UNDERGRAD PORTION OF PROJECT

Name: Francisco Ruiz

1. This program took me approximately 1 hour to code. Further revisions gave me about another hour tacked on.
2. Runtime is approximately 1.75 minutes (107 seconds).
3. Answer is 10657 unique IP addresses.
*/

#include <ctime>
#include <fstream>
#include <iostream>
#include <set>
#include <string>

using namespace std;

//By using a set instead of a vector (as I originally designed this program), I was able to cut down the 
//time exponentially. A set automatically orders the strings as the file is parsed, which enables the 
//program to determine if a IP address is unique without going through the entire list (as the set is sorted
//already).  

int main()
{
	fstream file;
	int time_passed;
	string temp, word;
	set<string> unique_ip;

	//This starts the clock so that I could have a time comparison function.
	clock_t start_time = clock();

	//I have hardcoded the path so as not to have to type it multiple times, but this part could easily be modified to take user input.
	file.open("httpd.log", ios::in);

	//Standard file opening error message. Boring.
	if(!file)
	{
		cout << "ERROR OPENING FILE!" << endl << endl;
		return 0;
	}

	do
	{
		//Pulls the first word from the line
		file >> word;

		//Checks to see if the first character is a digit, and if so, inserts it into our set.
		if(isdigit(word.at(0)))
		{
			unique_ip.insert(word);
		}

		//"Eats" the rest of the line so we can parse the beginning of the next line 
		getline(file, word, '\n');

	} while(!file.eof());

	cout << "There are " << unique_ip.size() << " unique IP addresses in the log file." << endl << endl;

	file.close();

	//End of time monitoring. 
	clock_t end_time = clock();

	time_passed = end_time - start_time;

	cout << "This program took " << time_passed / CLOCKS_PER_SEC << " seconds to run!" << endl;

	return 0;
}
