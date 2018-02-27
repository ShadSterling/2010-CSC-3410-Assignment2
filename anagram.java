/*
 *  Shad Sterling
 *  CSC 3410 CRN 80332 (2010 Fall)
 *  Assignment 2
 *  Assignment URL: http://cs.gsu.edu/jbhola/csc3410/fall10/assign2_ana.html
 *  Due Thursday, 2010-September-16
 *
 *  ----  Summary & Usage:
 *  This program reads words from a file, and outputs the same words in anagram groups
 *  Output goes both to stdout and an output file; some additional info goes to stdout.
 *  Case and punctuation are preserved; duplicates are preserved.
 *
 *  This program takes two arguments: an input file name and an output file name.
 *  Additional arguments are ignored without halting.
 *  If no input file name is given, the user is prompted.
 *  If no output file name is given, the name "output.txt" is used.
 *
 *  The code provides infrastructure for some options for which there is no user interface.
 *
 *  ----  Assignment:
 * 
 *  Note: (1) For all assignments, always use comments to explain your program.
 *        (2) No copying allowed. If it is found that students copy from each other
 *            (in the opinion of the instructor), all  of these programs will get 0.
 *        (3) You must use the names given to name your program.
 *            Should you use different names, you would lose 10% of what each program is worth.
 * 
 *  Objectives:
 *  1. To gain experience with string objects.
 *  2. To gain experience with vectors.
 *  3. To gain experience with generic algorithms.
 *  4. To gain experience with files - opening for input and output.
 * 
 *  Documentation: (44%)
 *  13% - Explain the purpose of the program as detail as possible.
 *  16% - Develop a solution for the problem and mention algorithms to be used.
 *   5% - List data structures to be used in solution.
 *   5% - Give a description of how to use the program and expected input/output.
 *   5% - Explain the purpose of each class you develop in the program.
 * 
 *  Programming: (56%)
 *  10% - For each method, give the pre and post conditions and invariant, if any.
 *  36% - Program execution according to the requirements given.
 *   5% - Naming of program as required.
 *   5% - Print out of source code.
 * 
 *  Description of program:
 *  You are to write a program name anagram.java that prompt the user for a user input file, reads
 *  the input words and computes anagram of words. An anagram of a word is a permutation of the
 *  letters in that word; for example, “stop” is an anagram of “tops”.
 *  As indicated, the input to the program is a list of words from a user input file. The output is
 *  a list containing the same words, but with anagrams displayed on the same line both on the screen
 *  and the output file. The output file must be named output.txt.
 *  You must prompt the user for the name of the input file.
 *  The input file, may look like the following:
 *  	
 *  	Pans naps
 *  	
 *  	Pots
 *  	
 *  	opt this that
 *  	
 *  	Sit
 *  	
 *  	it’s
 *  	
 *  	snap
 *  	
 *  and so on.
 *  
 *  Execute the program, example:
 *  	C:\>java anagram OR C:\java -jar anagram.jar [Enter]
 *  	
 *  	Enter the name of the input file:  input.dat [Enter]
 *  	
 *  	Pans snap
 *  	
 *  	Pots
 *  	
 *  	Sit it’s
 *  	
 *  	opt
 *  	
 *  
 *  Here are some requirements for the program:
 *  1. When determining if two words are anagrams, the program must treat upper and lower case
 *     letters as equivalent (thus “Pans” and “snap” are anagrams) and ignore punctuation marks
 *     (“it’s” and “Sit” are anagrams). However, the program must display words with their original
 *     capitalization and punctuation – as shown above.
 *  2. The “word” is assumed to be any series of nonblank characters; words may be separated by any
 *     number of white-space characters. Any number of words may appear on a line, including none.
 *     You may assume that no word is more than 12 characters long. And maximum number of words would
 *     be 50.
 *  3. The program must work even if the input file is empty. If this is the case print a message
 *     saying that “the input file is empty” and then terminate the program.
 *  4. The program must test the number of characters per word. If a word consist of more than 12
 *     characters, the program should ignore that word and continue. That word would also be ignored
 *     in the total number of words of 50.
 *  5. The program must also test the number of words. If there are more than 50 words, print a
 *     message saying that “there are more than 50 words in the input file” and then terminate the
 *     program
 *  
 *  Algorithm:
 *  Use efficient algorithms. The first insight is to create a “signature” for each word by sorting
 *  the letters in the word after removing punctuations..
 *  	Original Word     Signature
 *  	 Pans              anps
 *  	 Pots              opts
 *  	 opt               opt
 *  	 Sit               ist
 *  	 it’s              ist
 *  	 snap              anps
 *  Upper case are converted to lower case and non-alphabetic characters (eg. /<>[{) are removed
 *  before the signature is computed. Punctuation marks are also ignored when computing signatures.
 *  Creating signatures for words makes it easy to check whether two words are anagrams. However, we
 *  still have the problem of (apparently) having to compare every input word against every other
 *  input word. Actually, all we need to do is sort the lines by their signatures.
 *  	Original Word     Signature
 *  	 Pans              anps
 *  	 snap              anps
 *  	 Sit               ist
 *  	 it’s              ist
 *  	 Pots              opts
 *  	 opt               opt
 *  We next make a pass over from top to bottom, printing the original words. The words that have the
 *  same signature are anagrams, so we print them on the same line:
 *  	Pans snap
 *  	Pots
 *  	Sit it’s
 *  	opt
 *  
 *  Implementation:
 *  Use as many generic algorithm as possible so that the size of the program can be reduced.
 *
 *  ----  Algorithm:
 *  I identify anagrams by generating a number to represent each combination of letters; words or
 *  phrases which are anagrams of eachother are represented by the same number.  To generate numbers,
 *  each of the 26 letters of our alphabet is assigned a unique prime number; the number representing
 *  a word or phrase is the product of those prime numbers raised to the power of the number of
 *  occurrences of the corresponding letter in the word or phrase.  If I were to take this further,
 *  generating phrases which are anagrams of a given phrase is reduced to a factoring problem, which
 *  is well understood and I suspect more efficient than string searching.
 *  I sort the list of numbers alphabetically by finding the power of the "a" factor, then the "b"
 *  factor, etc, until a difference is found.  This is not very time-efficient, but it avoids having
 *  to store an additional representation.
 *  I do use multiple representations of each word (not it's anagram number) in order to both
 *  preserve original case & punctuation and ignore duplicates, and to use the numbers to perform an
 *  alphabetical sort.  These forms are generally used within only one method, not saved with the
 *  more persistent data.
 *  
 *  Here is an outline of the operation:
 *  generate word list:
 *  	read bytes from input
 *  	break bytes into words delimited by whitespace, word-separating punctuation, and non-ascii
 *  	for each word:
 *  		generate an anagram number
 *  		if the anagram number is new, add this word to the list
 *  		if the number is not new, check for duplicates; add to list if not a duplicate
 *  sort word list:
 *  	sort by increasing "a" count, then "b" count, etc
 *  output anagram list:
 *  	for each anagram number:
 *  		generate a line containing each associated word
 *
 *  There are several data structures involved, the only persistent structure is the Map relating
 *  anagram numbers to lists of matching words.  This is done with a HashMap of BigInteger keys and
 *  Vector<String> values.  A few places, most notably the sort routines, use additional structures,
 *  including a reverse map, an int[] of counts for each letter, Vector<BigInteger> of anagram
 *  numbers, and Vector<Vector<String>> of same-anagram-number word lists.  Much of this could be
 *  simplified, made less redundant, and made more efficient, by implementing an Anagram class
 *  for each number & corresponding list of words.
 *
 *  ----  Basis:
 *  All code is mine, no additional copyright notices are required.
 *  Some code is copied or derived from prior homework in other classes.
 *
 *  ----  Checklist from Assignment:
 *  ? Use comments to explain your program.
 *  	Overview is OK, but detail is lacking, and in some places that's bad.
 *  ✓ Write original code.  (Duh.)
 *  ✓ Use assigned filename.
 *  ? Explain the purpose of the program as detail as possible.
 *  	Probably not as much detail as is POSSIBLE...
 *  ✓ Develop a solution for the problem and mention algorithms to be used.
 *  ✓ List data structures to be used in solution.
 *  ✓ Give a description of how to use the program and expected input/output.
 *  ? Explain the purpose of each class you develop in the program.
 *  	Only one class that does everything
 *  ? For each method, give the pre and post conditions and invariant, if any.
 *  	No states, it's all take arguments & generate output.
 *  ✓ Print out of source code.
 *  ✓ Prompt for input file
 *  ✓ Decode input file
 *  ✓ Output anagrams to stdout and output file
 *  ✓ Ignore case & punctuation
 *  ✓ Reject words longer than 12 characters
 *  ✓ Show informative message when word list is empty
 *  ✓ Bail when word list exceeds 50
 *  	Disable this usability nightmare by setting CRAZY_BAIL = false
 *  ✗ Use sorted-letters "signature"
 *  	Numbers are better - but I can sort equivalently (inefficiently)
 *  ✗ Maintain parallel lists of signatures and original words
 *  	Maps are better
 *  ✓ Sort "signature" by sorted-letter representation
 *
 *  ----  My Checklist:
 *  ✓ Accept input and output filenames as arguments, prompt/default as fallback
 *  ✓ Ignore repeated words
 *  	Enable with KEEP_DUPLICATES = false
 *  ✓ Ignore words after 50 (don't bail)
 *  	Enable with CRAZY_BAIL = false
 *  ✓ Don't preserve word-separating punctuation marks
 *
 *  ----  Implementation Notes:
 *  • Not for real use; should have controls on maximum word length
 *  • Not for real use; should have controls on maximum word list length
 *  • Not for real use; should detect pipe or have option to use only stdin and stdout
 *  • Not for real use; should have option to anagram arguments
 *  • Not for real use; should handle UTF-8 punctuation & accented characters (or arbitrary encoding)
 *  • Not for real use; insufficient error handling
 *  • Inefficient; preserving original case & punctuation requires an additional standard list for
 *    duplicate detection
 *  • Using BigInteger for anagram numbers because the maximum expected number is too large for an
 *    Integer: ⌈log₂(P(26)^12)⌉ = 80; it would take an 80-bit unsigned int to represent.
 *  • Not really OO; should be making specialized anagram list objects.
 *  • The primes are assigned to letters so that the smaller primes are assigned to more frequently
 *    used letters, according to http://en.wikipedia.org/wiki/English_alphabet#Letter_frequencies
 *  • There is some unused code, which I used to find anagrams for the test input file.
 *    searching /usr/share/dict/words found 196184 words in 178264 groups (including groups of one)
 *  • Lots of inefficiencies that could be very much improved
 *  • Sorry the comments are sparse, I spent too much time on the code & finding nice anagrams for
 *    the test input file.
 *
 *  ----  Cases to Test:
 *  ✓ Filenames as arguments
 *
 */

//Everything is in this class.
//All methods are static methods.
public class anagram {

	//---- Constants
	private static final int MAX_LENGTH = 12; // ignore words longer than this
	private static final int MAX_COUNT = 50; // ignore words after this many
	private static final String DEFAULT_OUTFILE = "output.txt"; //default output file name
	private static final Boolean SHOW_NUMBERS = false; //adds numerical "signature" to output
	private static final Boolean INCLUDE_NON = true; //include non-anagrams in output
	private static final Boolean CRAZY_BAIL = true; //ridiculous requirement
	private static final Boolean KEEP_DUPLICATES = true; //slightly less ridiculous requirement
	private static final java.util.regex.Pattern DELIMITERS =
	                                              java.util.regex.Pattern.compile( "[^a-zA-Z-_']+" );
	private static final int[] LETTERPRIMES = { 5, 71, 37, 29, 2, 53, 59, 19, 11, 83, 79, 31, 43,
	                                            13, 7, 67, 97, 23, 17, 3, 41, 73, 47, 89, 61, 101 };


	//---- Entrypoint
	public static void main( String[] args ) {
		// initiallization:
		String infile = null;
		String outfile = "output.txt";
		java.util.HashMap<java.math.BigInteger, java.util.Vector<String>> wordlist;
		java.util.Vector<java.math.BigInteger> sorted;
		java.io.FileOutputStream outstream;
		java.io.OutputStreamWriter outwriter;
		// process arguments
		if( 3 <= args.length ) {
			System.out.println( "Too many arguments!  All but the first two are ignored." );
		}
		if( 2 <= args.length ) { outfile = args[1]; }
		if( 1 <= args.length ) { infile = args[0]; }
		// prompt for filenames (as needed)
		infile = promptFileCheck( infile, //argument from command line (null if absent)
			                      "input file" //prompt string
			                      ); //TODO: test accessibility in promptor
		if( "".equals(infile) ) { System.out.println( "Perhaps another time." ); return; }
		// generate anagram list
		try {
			wordlist = LoadWords( infile, MAX_LENGTH, MAX_COUNT, KEEP_DUPLICATES, CRAZY_BAIL );
		} catch( Throwable t ) {
			System.out.println( "Error loading words from " + infile
			                    + ":  " + t.getClass().getName() + " - " + t.getMessage() );
			return;
		}
		if( 0 == wordlist.size() ) { System.out.println( "the input file is empty." ); return; }
		// generate sorted list of anagram numbers
		sorted = SortWords( wordlist );
		//sorted = SortWordsCount( wordlist, sorted );
		//sorted = SortWordsLength( wordlist, sorted );
		// output to console
		System.out.println();
		System.out.println( PrintList( wordlist, sorted, INCLUDE_NON, SHOW_NUMBERS ) );
		// output to file
		try {
			outstream = new java.io.FileOutputStream( outfile );
			outwriter = new java.io.OutputStreamWriter( outstream, "US-ASCII" );
			outwriter.write( PrintList( wordlist, sorted ) );
			outwriter.flush();
			outstream.close();
		} catch( Throwable t ) {
			System.out.println( "Error writing output to " + outfile
			                    + ":  " + t.getClass().getName() + " - " + t.getMessage() );
		}
	}


	//---- Data Handling Methods

	//takes a wordlist and generates an array of member anagram numbers sorted alphabetically
	// - sort by count of "a"s, then by count of "b"s, etc
	public static java.util.Vector<java.math.BigInteger>
	 SortWords( java.util.HashMap<java.math.BigInteger, java.util.Vector<String>> wordlist ) {
		return SortWordsAlpha( wordlist, null );
	}
	public static java.util.Vector<java.math.BigInteger>
	 SortWordsAlpha( java.util.HashMap<java.math.BigInteger, java.util.Vector<String>> wordlist ) {
		return SortWordsAlpha( wordlist, null );
	}
	public static java.util.Vector<java.math.BigInteger>
	 SortWordsAlpha( java.util.HashMap<java.math.BigInteger, java.util.Vector<String>> wordlist,
	            java.util.Vector<java.math.BigInteger> numbers ) {
		if( null == numbers ) {
			numbers = new java.util.Vector<java.math.BigInteger>( wordlist.keySet() );
		}
		java.util.Collections.sort( numbers, new java.util.Comparator<java.math.BigInteger>() {
			public int compare( java.math.BigInteger a, java.math.BigInteger b ) {
				int[] al = LetterCounts( a );
				int[] bl = LetterCounts( b );
				int c=0;
				for( int i=0; 26>i; i++ ){
					c = bl[i] - al[i];
					if( 0 != c ) { break; }
				}
				return c;
			}
		});
		return numbers;
	}
	
	//takes a wordlist and generates an array of member anagram numbers sorted by word length
	public static java.util.Vector<java.math.BigInteger>
	 SortWordsLength( java.util.HashMap<java.math.BigInteger, java.util.Vector<String>> wordlist ) {
		return SortWordsLength( wordlist, null );
	}
	public static java.util.Vector<java.math.BigInteger>
	 SortWordsLength( java.util.HashMap<java.math.BigInteger, java.util.Vector<String>> wordlist,
	            java.util.Vector<java.math.BigInteger> numbers ) {
		if( null == numbers ) {
			numbers = new java.util.Vector<java.math.BigInteger>( wordlist.keySet() );
		}
		java.util.Collections.sort( numbers, new java.util.Comparator<java.math.BigInteger>() {
			public int compare( java.math.BigInteger a, java.math.BigInteger b ) {
				int[] al = LetterCounts( a );
				int[] bl = LetterCounts( b );
				int ac=0, bc=0;
				for( int i=0; 26>i; i++ ){
					ac += al[i];
					bc += bl[i];
				}
				return ac - bc;
			}
		});
		return numbers;
	}
	
	//takes a wordlist and generates an array of member anagram numbers sorted by anagram count
	public static java.util.Vector<java.math.BigInteger>
	 SortWordsCount( java.util.HashMap<java.math.BigInteger, java.util.Vector<String>> wordlist ) {
		return SortWordsCount( wordlist, null );
	}
	public static java.util.Vector<java.math.BigInteger>
	 SortWordsCount( java.util.HashMap<java.math.BigInteger, java.util.Vector<String>> wordlist,
	                java.util.Vector<java.math.BigInteger> numbers ) {
		java.util.HashMap<java.util.Vector<String>, java.math.BigInteger> listword;  //ugh!
		java.util.Vector<java.util.Vector<String>> anagrams;
		int i;
		if( null == numbers ) {
			anagrams = new java.util.Vector<java.util.Vector<String>>( wordlist.values() );
		} else {
			anagrams = new java.util.Vector<java.util.Vector<String>>( wordlist.size() );
			for( i=0; wordlist.size() > i; i++ ) {
				anagrams.add( wordlist.get( numbers.get(i) ) );
			}
		}
		listword =
		    new java.util.HashMap<java.util.Vector<String>, java.math.BigInteger>( wordlist.size() );
		for( java.math.BigInteger k : wordlist.keySet() ) {
			listword.put( wordlist.get(k), k );
		}
		java.util.Collections.sort( anagrams, new java.util.Comparator<java.util.Vector<String>>() {
			public int compare( java.util.Vector<String> a, java.util.Vector<String> b ) {
				return a.size() - b.size();
			}
		});
		numbers.clear();
		for( i=0; wordlist.size() > i; i++ ) {
			numbers.add( listword.get( anagrams.get(i) ) );
		}
		return numbers;
	}
	
	//Generates a printable string representation of a wordlist
	//(doesn't actually print the list)
	public static String
	 PrintList( java.util.HashMap<java.math.BigInteger, java.util.Vector<String>> wordlist,
	            java.util.Vector<java.math.BigInteger> sorted ) {
		return PrintList( wordlist, sorted, false );
	}
	public static String
	 PrintList( java.util.HashMap<java.math.BigInteger, java.util.Vector<String>> wordlist,
	            java.util.Vector<java.math.BigInteger> sorted,
	            Boolean printall ) {
		return PrintList( wordlist, sorted, printall, false );
	}
	public static String
	 PrintList( java.util.HashMap<java.math.BigInteger, java.util.Vector<String>> wordlist,
	            java.util.Vector<java.math.BigInteger> sorted,
	            Boolean printall,
	            Boolean showkeys ) {
		int keywidth=1;
		int i, j, k;
		StringBuffer r = new StringBuffer();
		String fmt = "%%0%dd:\t";
		java.math.BigInteger key;
		java.util.Vector<String> words;
		String newline = System.getProperty("line.separator");
		if( null == sorted ) { sorted = SortWords( wordlist ); }
		if( showkeys ) {
			for( i=0; sorted.size() > i; i++ ) {
				j = sorted.get(i).toString().length(); //icky
				if( j > keywidth ) { keywidth = j; }
			}
			fmt = String.format( fmt, keywidth );
		}
		for( i=0; sorted.size() > i; i++ ) {
			key = sorted.get(i);
			words = wordlist.get( key );
			if( 1 >= words.size() ) { continue; }
			//r.append( ""+arrayToString(LetterCounts( key ))+" " );
			if( showkeys ) { r.append( String.format( fmt, key ) ); }
			k = words.size();
			r.append( words.get(0) );
			for( j=1; k > j; j++ ) { r.append( " "+words.get(j) ); }
			r.append( newline );
		}
		return r.toString();
	}
	
	//takes a normalized word and generates it's anagram number
	public static java.math.BigInteger WordNumber( String word ) {
		java.math.BigInteger r = java.math.BigInteger.ONE;
		int l;
		//System.out.println( " # "+word+"\t("+word.length()+")" );
		for( int i=0; word.length() > i; i++ ) {
			l = word.charAt(i) - 'a';
			if( 0 > l || 25 < l ) {
				throw new RuntimeException( "Bad character \"" + word.substring(i,i+1)
				                            + "\" generating anagram number" );
			}
			r = r.multiply( java.math.BigInteger.valueOf( LETTERPRIMES[l] ) );
			//System.out.println( "  @ "+i+": "+word.charAt(i)+" = "+l+"\t-> "+LETTERPRIMES[l]+"\t-> "+r);
		}
		return r;
	}
	
	//takes a word number and returns an array of letter counts
	public static int[] LetterCounts( java.math.BigInteger number ) {
		int[] r = new int[26];
		java.math.BigInteger prime;
		java.math.BigInteger[] divmod;
		for( int i=0; 26>i; i++ ) {
			prime = java.math.BigInteger.valueOf( LETTERPRIMES[i] );
			while(true) {
				divmod = number.divideAndRemainder( prime );
				if( 0 == divmod[1].intValue() ) {
					r[i]++;
					number = divmod[0];
				} else { break; }
			}
		}
		return r;
	}
	
	//reduces a string to lowercase letters by changing case and removing nonletters
	public static String NormalizeWord( String word ) {
		return word.replaceAll( "[^a-zA-Z]", "" ).toLowerCase();
	}
	
	//takes an array and makes a nice string
	public static String arrayToString( int[] ary ) {
		StringBuffer r = new StringBuffer("[ ");
		int stop = ary.length - 1;
		for( int i=0;  i < stop; i++ ) { r.append( ""+ary[i]+", " ); }
		r.append( ""+ary[stop]+" ]" );
		return r.toString();
	}


	//---- Supporting Math Methods

	//determine the number of characters required for the string representation in a particular base
	public static int intLength( int num ) { return intLength( num, 10 ); }
	public static int intLength( int num, int base ) {
		boolean neg = false; if( 0 > num ) { neg = true; num = -num; }
		return 1+intLogBase( num, base ) + (neg ? 1 : 0); //10^n through 10^(n+1)-1 should return n+1
	}
	//find the nearest integer at or below the log of num in base
	public static int intLog10( int num ) { return intLogBase( num, 10 ); }
	public static int intLog2( int num ) { return intLogBase( num, 2 ); }
	public static int intLogBase( int num, int base ) {
		return (int) Math.floor( Math.log( num ) / Math.log( base ) );
	}
	public static int intLogNatural( int num ) {
		return (int) Math.floor( Math.log( num ) );
	}


	//---- IO Methods

	//prompt if necessary and re-prompt until a usable filename or empty string is entered
	public static String promptFileCheck( String filename, String prompt/*, access_mode*/ ) {
		if( "" == prompt || null == prompt ) { prompt = "filename"; } //default prompt string
		while( true ) {
			if( null != filename ) {
				try {
					// TODO: test accessibility
					break;
				} catch( Throwable t ) {
					System.out.println( "Error checking accessibility of " + filename + ":  "
					                    + t.getClass().getName() + " - " + t.getMessage() );
					filename = null; continue; //re-loop and go directly to prompt
				}
			}
			System.out.print( "Enter " + prompt + " (blank to abort): " );
			filename = System.console().readLine();
			if( "".equals(filename) ) { break; }
		}
		return filename;
	}

	//generate an anagram list from a file
	//may write to System.out
	public static java.util.HashMap<java.math.BigInteger, java.util.Vector<String>>
	 LoadWords( String filename, int maxlength, int maxcount ) throws java.io.FileNotFoundException {
		return LoadWords( new java.io.FileInputStream( filename ),
		                  maxlength, maxcount, false, false );
	}
	public static java.util.HashMap<java.math.BigInteger, java.util.Vector<String>>
	 LoadWords( String filename, int maxlength, int maxcount, Boolean dups )
	 throws java.io.FileNotFoundException {
		return LoadWords( new java.io.FileInputStream( filename ),
		                  maxlength, maxcount, dups, false );
	}
	public static java.util.HashMap<java.math.BigInteger, java.util.Vector<String>>
	 LoadWords( String filename, int maxlength, int maxcount, Boolean dups, Boolean bail )
	 throws java.io.FileNotFoundException {
		return LoadWords( new java.io.FileInputStream( filename ), maxlength, maxcount, dups, bail );
	}
	public static java.util.HashMap<java.math.BigInteger, java.util.Vector<String>>
	 LoadWords( java.io.InputStream source, int maxlength, int maxcount ) {
		return LoadWords( source, maxlength, maxcount, false, false );
	}
	public static java.util.HashMap<java.math.BigInteger, java.util.Vector<String>>
	 LoadWords( java.io.InputStream source, int maxlength, int maxcount, Boolean dups ) {
		return LoadWords( source, maxlength, maxcount, dups, false );
	}
	public static java.util.HashMap<java.math.BigInteger, java.util.Vector<String>>
	 LoadWords( java.io.InputStream source, int maxlength, int maxcount,
	            Boolean dups, Boolean bail ) {
		java.util.HashMap<java.math.BigInteger, java.util.Vector<String>> list;
		java.util.Scanner input;
		String word;
		String normal;
		java.math.BigInteger number;
		java.util.Vector<String> anagrams;
		int words = 0;
		int overlong = 0;
		int dupcount = 0;
		java.util.Vector<String> dupcheck = null;
		input = new java.util.Scanner( source, "US-ASCII" );
		input.useDelimiter( DELIMITERS );
		list = new java.util.HashMap<java.math.BigInteger, java.util.Vector<String>>();
		if( !dups ) { dupcheck = new java.util.Vector<String>(); }
		while(true) {
			if( !input.hasNext() ) { break; }
			word = input.next();
			normal = NormalizeWord( word );
			if( normal.length() < 2 ) { continue; }
			if( normal.length() > maxlength ) { overlong++; continue; }
			if( words >= maxcount ) {
				System.out.println( "there are more than 50 words in the input file" );
				if( bail ) {
					throw new RuntimeException( "there are more than 50 words in the input file" );
				}
				break;
			}
			number = WordNumber( normal );
			//System.out.println( ""+word.length()+":\t"+word+"\t -> "+normal+"\t -> "+number );
			if( list.containsKey( number ) ) {
				if( !dups ) {
					if( dupcheck.contains( normal ) ) {
						//System.out.println("DUP");
						dupcount++;
						continue;
					}
					dupcheck.add( normal );
				}
				anagrams = list.get( number );
			} else {
				//System.out.println("NEW");
				anagrams = new java.util.Vector<String>();
				list.put( number, anagrams );
				if( !dups ) { dupcheck.add( normal ); }
			}
			anagrams.add( word );
			//System.out.println("ADD -> "+anagrams.size()+" -> "+list.get(number).size() );
			words++;
		}
		if( 0 < overlong ) {
			System.out.println( "" + overlong + " words were skipped because they had more than "
			                    + maxlength + " letters." );
		}
		if( 0 < dupcount ) {
			System.out.println( "" + dupcount + " words were skipped because they were duplicates" );
		}
		System.out.println( "" + words + " words loaded in "+list.size()+" anagrams." );
		return list;
	}


}
