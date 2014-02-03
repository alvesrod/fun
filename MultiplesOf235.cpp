/*
 * Multiples of 2, 3, 5
 * Consider a series in ascending order that only consists of numbers
 * that can be factored by any combination of 2, 3 and 5.
 * e.g. 1, 2, 3, 4, 5, 6, 8, 9, 10, 12, 15....
 *
 * This algorithm finds the number that occupies the Nth position in this series.
 */
#include <iostream>
using namespace std;

#define NOT_FOUND -1

/*
 * This is a trade-off memory vs. performance.
 * We can easily preCompute the value for the first N positions for mult235(N).
 * Right now, I've precomputed the first N elements. We can increase or
 * decrease the size of this list based on memory constraints.
 * We could also precompute values from n = x to x+length.
 */
#define PRE_COMPUTED_VALUES 201
const int preComputed[] = {
	0,1,2,3,4,5,6,8,9,10,12,15,16,18,20,24,25,27,30,32,36,40,45,48,50,54,60,
	64,72,75,80,81,90,96,100,108,120,125,128,135,144,150,160,162,180,192,200,
	216,225,240,243,250,256,270,288,300,320,324,360,375,384,400,405,432,450,480,
	486,500,512,540,576,600,625,640,648,675,720,729,750,768,800,810,864,900,960,
	972,1000,1024,1080,1125,1152,1200,1215,1250,1280,1296,1350,1440,1458,1500,
	1536,1600,1620,1728,1800,1875,1920,1944,2000,2025,2048,2160,2187,2250,2304,
	2400,2430,2500,2560,2592,2700,2880,2916,3000,3072,3125,3200,3240,3375,3456,
	3600,3645,3750,3840,3888,4000,4050,4096,4320,4374,4500,4608,4800,4860,5000,
	5120,5184,5400,5625,5760,5832,6000,6075,6144,6250,6400,6480,6561,6750,6912,
	7200,7290,7500,7680,7776,8000,8100,8192,8640,8748,9000,9216,9375,9600,9720,
	10000,10125,10240,10368,10800,10935,11250,11520,11664,12000,12150,12288,12500,
	12800,12960,13122,13500,13824,14400,14580,15000,15360,15552,15625,16000,16200};

/* Keep track of wrong results to save some computing time (memoization) */
#define MAX_LIST_LENGTH 140

/* Binary search the precomputed list: */
int bSearchPrecomputed(int key, int min, int max) {
	if (max < min)
		return NOT_FOUND;
	else {
		int mid = (min + max) / 2;
		if (preComputed[mid] > key)
			return bSearchPrecomputed(key, min, mid-1);
		else if (preComputed[mid] < key)
			return bSearchPrecomputed(key, mid+1, max);
		else
			return mid;
	}
}

/* True if the param key is an element of the sorted list a: */
bool wasPrecomputed(int n) {
	if (n > preComputed[PRE_COMPUTED_VALUES-1]) return false;
	return (bSearchPrecomputed(n, 0, PRE_COMPUTED_VALUES-1) != NOT_FOUND);
}

/* For unsorted lists: */
bool isInList(int *a, int key, int length) {
    length = (length < MAX_LIST_LENGTH) ? length:(MAX_LIST_LENGTH-1);
	for (int i = 0; i < length; i++)
		if (a[i] == key) return true;
	return false;
}

/* True if number n is a combination of 2s, 3s, and 5s only: */
bool isMult235(int n, int *list, int &listSize) {
	if (n <= 6) return true;

	const bool divBy2 = (n % 2 == 0);
	const bool divBy3 = (n % 3 == 0);
	const bool divBy5 = (n % 5 == 0);

	if ((!divBy2) && (!divBy3) && (!divBy5)) return false;

	if (n <= preComputed[PRE_COMPUTED_VALUES-1]) return wasPrecomputed(n);

	/*
	 * If it's in the list of bad numbers, it can't be that one.
	 * This is only to improve performance.
	 */
	if (isInList(list, n, listSize)) return false;


	if ( ((divBy2) && (isMult235(n/2, list, listSize))) ||
	     ((divBy3) && (isMult235(n/3, list, listSize))) ||
	     ((divBy5) && (isMult235(n/5, list, listSize))) )
		return true;

	/* Save that this number doesn't go anywhere: */
	list[listSize % MAX_LIST_LENGTH] = n;
	listSize++;

	return false;
}

/* Return the value at position N: */
int mult235(int n) {
	if (n < PRE_COMPUTED_VALUES)
		return preComputed[n];

	int list[MAX_LIST_LENGTH]; //keep track of ignored values for a specific num.
	int pos = PRE_COMPUTED_VALUES - 1;
	int listCount;
	int count = preComputed[PRE_COMPUTED_VALUES-1] + 1;

	while (pos < n) {
		listCount = 0;
		if (isMult235(count, list, listCount))
			pos++;
		count++;
	}
	return count - 1;
}

int main() {
	const int test = 1000;
	cout << "Val at position " << test << ": " << mult235(test) << endl;
	return 0;
}


