/*
GRAD PORTION OF PROJECT

Name: Francisco Ruiz

1. This program took me approximately 3 hours to code. Further revisions gave me about another hour tacked on.
2. Runtime is approximately 4 minutes (243 seconds).
3. To process the web crawlers, I searched for the filename "robots.txt".  All polite crawlers will ask for this file,
   so this code may miss some, but all mainstream crawlers in the log should be found in the list.  If the same title 
   was found multiple times, I incremented the counting vector entry that corresponded to said bot.
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

/*
  This program will not be able to use a set since I will need to be able to track the number of times a particular
  bot has accessed our server.  I will use two vectors to keep track of individual bots as well as the number of times
  each has visited. In this program, I have parsed through the file once and moved all bots to a separate file, where 
  they are processed again to check for unique visits.  This method may multi-count "inter-laced" crawler visits, but 
  these cases should be few and far between.  
*/

//This process will be the second parsing, which operates on the already processed file.  It will tally up unique 
//visit counts for each unique crawler visit.
void final_process(string file_name)
{
	fstream f, g;
	string temp, temp_address, ip_address, temp_id;
	vector<string> all_ip;
	vector<int> ip_count;

	f.open(file_name, ios::in);

	g.open("final_results.log", ios::out);

	if(!f)
	{
		cout << "ERROR OPENING FILE FOR INPUT!" << endl;
	}
	else
	{
		while(!f.eof())
		{
			while(getline(f, temp))
			{
				stringstream sstr(temp);
				
				//This will get the ip address we need to check against the list of crawlers we have accumulated.
				getline(sstr, ip_address, ' ');

				//First case scenario
				if(all_ip.empty())
				{
					all_ip.push_back(temp);
					ip_count.push_back(1);
				}
				else
				{
					for(unsigned int i=0; i<all_ip.size(); i++)
					{
						//This second stringstream is necessary to pull the first word from the IP list we have
						//accumulated, which will be the IP address.
						stringstream sstr2(all_ip[i]);

						getline(sstr2, temp_address, ' ');
									
						//If this IP address is found in the list already, it will increment the corresponding count
						//entry in the counting vector and break from the loop, as it does not need to continue
						//checking the rest of the list.
						if(ip_address == temp_address)
						{
							ip_count[i]++;
							break;
						}
						//If the IP address is not found in the list, it is considered unique and it is added to both vectors.
						else if(i == all_ip.size() - 1)
						{
							all_ip.push_back(temp);
							ip_count.push_back(1);
						}
					}
				}
			}
		}
	}

	f.close();

	for(unsigned int j=0; j<all_ip.size(); j++)
	{
		g << ip_count[j] - 1 << "  " << all_ip[j] << endl;
	}

	g.close();
}


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

			getline(sstr, word, ' ');

			//This line allows me to "pull" a word at a time from the line to check for the "robots.txt" filename. 
			//If one is found, we will then port the rest of the log text to a separate file for further processing.
			while(getline(sstr, word, ' '))
			{
				//Most legitimate crawlers will request the robots.txt file since it contains the behavior 
				//requested from crawlers visiting the site. 
				if(word == "/robots.txt")
				{
					file_out << temp << endl;
					break;
				}
			}
		}
	}
	
	file_in.close();

	file_out.close();

	//Call to above function for processing of output file.
	final_process("results.log");

	//End of time monitoring. 
	clock_t end_time = clock();

	time_passed = end_time - start_time;

	cout << "This program took " << time_passed / CLOCKS_PER_SEC << " seconds to run!" << endl;

	return 0;
}
