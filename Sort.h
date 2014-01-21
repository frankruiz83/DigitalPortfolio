#ifndef SORT_H
#define SORT_H

#include <fstream>
#include <iostream>
#include <string>
#include <vector>

using namespace std;

class Data
{
private:
	int temp, place, count;
	vector<vector<int>> rankings;
	int inversions[3];

public:
	//Default Constructor
	Data()
	{
		place = count = 0;

		for(unsigned int i = 0; i < 3; i++)
		{
			inversions[i] = 0;
		}
	}
	
	//Setters and Getters for inversion count and vector 
	int* get_Inversions()
	{
		return inversions;
	}
	
	vector<vector<int>> get_Rankings()
	{
		return rankings;
	}

	int set_Rankings()
	{
		fstream f;
		string path;
		int temp2 = 0;

		//Allows user to manually enter file name -- Can be hardcoded if necessary
		cout << "What is the filepath for the data set? Please enter here: ";
		cin >> path;

		f.open(path, ios::in);

		//Error Checking
		if(!f)
		{
			cout << "Invalid path name!" << endl;
		}
		else
		{
			rankings.push_back(vector<int>());
			//This will read all numbers from the file into the vector. 
			while(!f.eof())
			{
				f >> temp;
				rankings[place].push_back(temp);
			}
			
			place++;
		}

		f.close();
		count++;

		return count;
	}

	//This is the function that actually merges the two sides into a sorted series.
	vector<int> merge(vector<int> left_side, vector<int> right_side)
	{
		unsigned int l, r, i;
		vector<int> sorted;

		sorted.resize(left_side.size() + right_side.size());
		l = r = i = 0;

		while(i<(left_side.size() + right_side.size()))
		{
			if((r > right_side.size()) || ((l <= left_side.size()) && (left_side[l] <= right_side[r])))
			{
				sorted[i] = left_side[l];
				l++;
			}
			else
			{
				sorted[i] = right_side[r];
				r++;
				inversions[0] += 1;
			}

			i++;

			if(l > (left_side.size() - 1) || r > (right_side.size() - 1))
				break;
		}

		if(l > (left_side.size() - 1))
		{
			sorted[i] = right_side[r];
		}
		if(r > (right_side.size() - 1))
		{
			sorted[i] = left_side[l];
		}

		return sorted;
	}

	//This is the function that splits the two groups in half for merge-sorting.
	vector<int> merge_Sort(vector<int> unsorted)
	{
		//Pointers allow for dynamic memory allocation depending on size of data to be sorted
		vector<int> left, right, left_final, right_final;
		int size_left, size_right;

		if(unsorted.size() == 1)
		{
			return unsorted;
		}
		else
		{
			//Finds sizes for loops to add values into vectors
			size_left = (unsorted.size() / 2);
			size_right = (unsorted.size() - size_left);
		
			for(int i=0; i<size_left; i++)
			{
				left.push_back(unsorted[i]);
			}

			for(int j=0; j<size_right; j++)
			{
				right.push_back(unsorted[size_left + j]);
			}

			left_final.resize(size_left);
			right_final.resize(size_right);
			left_final = merge_Sort(left);
			right_final = merge_Sort(right);

			return merge(left_final, right_final);
		}
	}

	vector<int> quick_Sort(vector<int> unsorted)
	{
		int pivot;
		vector<int> left, middle, right, sorted_left, sorted_right, final_sorted;

		if(unsorted.size() <= 1)
		{
			return unsorted;
		}

		//Using first number as pivot to make counting inversions easier
		pivot = unsorted[0];

		//Sorts unsorted data into three vectors(left, right, middle)
		for(unsigned int i=1; i<unsorted.size(); i++)
		{
			if(unsorted[i] < pivot)
			{
				left.push_back(unsorted[i]);
				inversions[1] += 1;
			}
			else if(unsorted[i] == pivot)
			{
				middle.push_back(unsorted[i]);
			}
			else
			{
				right.push_back(unsorted[i]);
			}
		}

		//Recursive call to sort left and right sides
		sorted_left = quick_Sort(left);
		sorted_right = quick_Sort(right);

		//Following code concatenates the three sections into a final sorted vector
		for(unsigned int j=0; j<sorted_left.size(); j++)
		{
			final_sorted.push_back(sorted_left[j]);
		}
	
		for(unsigned int k=0; k<middle.size(); k++)
		{
			final_sorted.push_back(middle[k]);
		}

		for(unsigned int l=0; l<sorted_right.size(); l++)
		{
			final_sorted.push_back(sorted_right[l]);
		}

		cout << final_sorted.front();
		return final_sorted;
	}

	//This is the O(n^2) algorithm - Selection Sort
	vector<int> selection_Sort(vector<int> unsorted)
	{
		int temp, position;
		unsigned int size;
		vector<int> sorted;

		size = unsorted.size();

		//Cycles through all unsorted elements multiple times
		for(unsigned int i = 0; i<size; i++)
		{
			for(unsigned int j=0; j<unsorted.size(); j++)
			{
				temp = unsorted[0];
				position = 0;

				if(temp > unsorted[j])
				{
					temp = unsorted[j];
					position = j;
					inversions[2] += 1;
				}

				sorted.push_back(temp);
				unsorted.erase(unsorted.begin() + position);
			}
		}

		return sorted;
	}

	vector<int> sum_sources()
	{
		vector<int> summed_total;
		int sum = 0;

		summed_total.resize(rankings[0].size());

		for(unsigned int j=0; j < rankings[0].size(); j++)
		{
			for(unsigned int i=0; i < 4; i++)
			{
				sum += rankings[i][j];
			}
			summed_total[j] = sum;
			sum = 0;
		}

		return summed_total;
	}
	
	void run()
	{
		int c;
		vector<int> unsorted_list;
		vector<int> sorted_list;
		vector<int> summed_sources;

		c = 0;

		//Fills in rankings list from unsorted data
		while(c <= 4)
		{
			c = set_Rankings();
		}

		summed_sources = sum_sources();

		for(unsigned int i=0; i<rankings[0].size(); i++)
		{
			unsorted_list.push_back(rankings[0][i] + rankings[1][i] + rankings[2][i] + rankings[3][i] + rankings[4][i]);
		}
	
		sorted_list = merge_Sort(unsorted_list);
		sorted_list = quick_Sort(unsorted_list);
		sorted_list = selection_Sort(unsorted_list);
				
		cout << endl;
		cout << "Number of inversions from merge sort on all 5 sources combined: " << inversions[0] << endl;
		cout << "Number of inversions from quick sort on all 5 sources combined: " << inversions[1] << endl;
		cout << "Number of inversions from selection sort on all 5 sources combined: " << inversions[2] << endl;

		for(unsigned int i = 0; i < 3; i++)
		{
			inversions[i] = 0;
		}

		for(unsigned int i = 0; i < 5; i++)
		{
			cout << endl;
			merge_Sort(rankings[i]);
			quick_Sort(rankings[i]);
			selection_Sort(rankings[i]);
			cout << "Number of inversions from merge sort on source " << (i+1) << ": " << inversions[0] << endl;
			cout << "Number of inversions from quick sort on source " << (i+1) << ": " << inversions[1] << endl;
			cout << "Number of inversions from selection sort on source " << (i+1) << ": " << inversions[2] << endl;
			for(unsigned int j = 0; j < 3; j++)
			{
				inversions[j] = 0;
			}
		}
	}
};



#endif
