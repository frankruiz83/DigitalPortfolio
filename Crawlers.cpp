/*
GRAD PORTION OF PROJECT

Name: Francisco Ruiz

1. This program took me approximately 3 hours to code. Further revisions gave me about another hour tacked on.
2. Runtime is approximately 2 minutes (121 seconds).
3. To process the web crawlers, I found the most prolific web crawlers and parsed though the file. When I came across 
   the keywords I was looking for (e.g., Mozilla/5.0 for Googlebot), I captured from there to the end of the line
   and inserted that into a vector.  If the same title was found multiple times, I incremented the counting vector
   entry that corresponded to said bot.
*/

#include <ctime>
#include <ctype.h>
#include <fstream>
#include <iostream>
#include <set>
#include <sstream>
#include <string>
#include <vector>

using namespace std;

//This program will not be able to use a set since I will need to be able to track the number of times a particular
//bot has accessed our server.  I will use two vectors to keep track of individual bots as well as the number of times
//each has visited.  

int main()
{
	fstream file_in, file_out;
	int time_passed;
	string temp, word, bot_name;
	vector<int> count;
	vector<string> bots;

	//This starts the clock so that I could have a time comparison function.
	clock_t start_time = clock();

	//I have hardcoded the path so as not to have to type it multiple times, but this part could easily be modified to take user input.
	file_in.open("httpd.log", ios::in);

	//This is the output file that will have our results.
	file_out.open("results.log", ios::out);

	//Standard file opening error message. Boring again.
	if(!file_in)
	{
		cout << "ERROR OPENING FILE!" << endl << endl;
		return 0;
	}

	while(!file_in.eof())
	{
		//This will get a line from the file at a time.
		while(getline(file_in, temp))
		{
			stringstream sstr(temp);

			//This line allows me to "pull" a word at a time from the line to check for popular bot ID's. If one is found,
			//we will then check to make sure we have not encountered this particular bot before and then take the 
			//appropriate action (either creating a new entry in our vector or updating the correct spot in the counting
			//vector.
			while(getline(sstr, word, ' '))
			{
				//Most crawlers will reques the robots.txt file since it contains the behavior requested from crawlers 
				//visiting the site. Some crawlers may not request the file, and in that case a 
				//keyword search would be in order.
				if(word == "/robots.txt")
				{
					//This series will make the bot_name the above "word" and then concatenate it to the end of the line.
					getline(sstr, bot_name, '\n'); 
				}
			}

			//This deals with the "first-case" scenario so that we don't get a bounds overflow. 
			if(bots.empty())
			{
				bots.push_back(bot_name);
				count.push_back(1);
			}
			else
			{
				//This will iterate through the existing bot list and check for repeats. If one is found, the corresponding 
				//counts entry is iterated. Otherwise, the entry will be tacked onto the end of the vector.
				for(unsigned int i=0; i<bots.size(); i++)
				{
					if(bot_name == bots[i])
					{
						count[i] += 1;
						break;
					}
					else
					{
						bots.push_back(bot_name);
						count.push_back(1);
					}
				}
			}			
		}
	}
	
	file_in.close();

	//This will display the results in the "results.log" file.
	for(unsigned int j=0; j<bots.size(); j++)
	{
		file_out << count[j] << " " << bots[j] << endl;
	}

	file_out.close();

	//End of time monitoring. 
	clock_t end_time = clock();

	time_passed = end_time - start_time;

	cout << "This program took " << time_passed / CLOCKS_PER_SEC << " seconds to run!" << endl;

	return 0;
}
