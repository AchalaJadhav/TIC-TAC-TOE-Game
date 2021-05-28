/*  
 
 Assignment No: 6
 Name: Achala Jadhav
 Roll No: 3301
 Problem Statement: Implement Tic Tac Toe using MinMax Algorithm
 
 */

import java.util.*;
class board
{
	char arr[];
	int ep_value=0;
	board()
	{
		this.arr=new char[9];
		for(int i=0;i<9;i++)
		{
			//char b = (char)(i + '0'); convert to character
			this.arr[i]='-';
		}
	}
	board(board b)
	{
		this.arr=new char[9];
		for(int i=0;i<9;i++)
		{
			this.arr[i]=b.arr[i];
		}
	}
}
class game
{
	Scanner s=new Scanner(System.in);
	board b=new board();
	void accept()
	{
		int turn=0,ans=1;
		while(ans==1)
		{
			if(turn%2==0)
			{
				System.out.println("Player 1 \n Enter position for x : ");
				int pos=s.nextInt();
				if(place('x',pos)==1)
					turn=turn+1;
				else
					System.out.println("Invalid position");
				//calc_ep(b);
				if(check(b,'x')==1)
				{
					System.out.println("Player 1 Wins ");
					break;
				}
			}
			else
			{
				System.out.println("Computer ");
				b=move(b);
				turn++;
				/*System.out.println("Player 2 \n Enter positon for o : ");
				int pos=s.nextInt();
				if(place('o',pos)==1)
					turn=turn+1;
				else
					System.out.println("Invalid position");*/
				if(check(b,'o')==1)
				{
					System.out.println("Computer Wins ");
					break;
				}
			}
			display(b);
			if(turn==9)
			{
				System.out.println("Draw");
				break;
			}
			System.out.println("Do you want to continue 1.yes 2.no : ");
			ans=s.nextInt();
		}
	}
	int place(char symbol,int pos)//placing symbol if position is empyty
	{
		if(b.arr[pos]=='-' )
		{
			b.arr[pos]=symbol;
			return 1;
		}
		else
			return 0;
	}
	void display(board b)
	{
		for(int i=0;i<9;i++)
		{
			if(i==3 || i==6 || i==9)
				System.out.println();
			System.out.print(" "+b.arr[i]);
		}
		System.out.println();
	}
	int check(board b,char symbol)//checking if the player has won
	{
		int flag=0;
		for(int i=0;i<9;i=i+3)//checking rows
			if(b.arr[i]==symbol && b.arr[i+1]==symbol && b.arr[i+2]==symbol)
			{
				flag=1;
				break;
			}
				
		for(int i=0;i<3;i++)//checking columns
			if(b.arr[i]==symbol && b.arr[i+3]==symbol && b.arr[i+6]==symbol)
			{
				flag=1;
				break;
			}
		if(b.arr[0]==symbol && b.arr[4]==symbol && b.arr[8]==symbol)//checking diagonal
			flag=1;
		if(b.arr[2]==symbol && b.arr[4]==symbol && b.arr[6]==symbol)//checking diagonal
			flag=1;
		return flag;
	}
	int calc_ep(board b)//calculating ep_value
	{
		int ep_x=8,ep_o=8;//initializing ep values to 8 i.e. max
		for(int i=0;i<9;i=i+3)
			if(b.arr[i]=='o' || b.arr[i+1]=='o' || b.arr[i+2]=='o')
			{
				ep_x--;
			}				
		for(int i=0;i<3;i++)
			if(b.arr[i]=='o' || b.arr[i+3]=='o' || b.arr[i+6]=='o')
			{
				ep_x--;
			}
		if(b.arr[0]=='o' || b.arr[4]=='o' || b.arr[8]=='o')
			ep_x--;
		if(b.arr[2]=='o' || b.arr[4]=='o' || b.arr[6]=='o')
			ep_x--;
		
		for(int i=0;i<9;i=i+3)
			if(b.arr[i]=='x' || b.arr[i+1]=='x' || b.arr[i+2]=='x')
			{
				ep_o--;
			}				
		for(int i=0;i<3;i++)
			if(b.arr[i]=='x' || b.arr[i+3]=='x' || b.arr[i+6]=='x')
			{
				ep_o--;
			}
		if(b.arr[0]=='x' || b.arr[4]=='x' || b.arr[8]=='x')
			ep_o--;
		if(b.arr[2]=='x' || b.arr[4]=='x' || b.arr[6]=='x')
			ep_o--;
		
		int ep=ep_x-ep_o;
		//System.out.println(ep);
		return ep;
	}
	board move(board bo)//checking possible moves of computer
	{
		int index=-1;
		ArrayList<board> states=new ArrayList<board>();
		System.out.println("_______________________________________________________");
		System.out.println("Possible moves : ");
		for(int i=0;i<9;i++)
		{
			board nb=new board(bo);
			if(bo.arr[i]=='-')
			{
				nb.arr[i]='o';
				if(check(nb,'o')==1)
				{
					nb.arr[i]='o';
					states.add(nb);
					display(nb);
					return nb;
				}
			}
			if(bo.arr[i]=='-')
			{
				nb.arr[i]='x';
				if(check(nb,'x')==1)//if winning position of x if found, return the board and break, do not generate further boards 
				{
					nb.arr[i]='o';
					states.add(nb);
					return nb;
				}
				nb.arr[i]='o';
				nb.ep_value=calc_ep(nb);
				states.add(nb);
				display(nb);
				System.out.println("H value "+nb.ep_value);
			}
		}
		System.out.println("_______________________________________________________");
			index=minimum(states);//finding board with minimum ep_value
			return states.get(index);
		
	}
	int minimum(ArrayList<board> list)//finding board with minimum ep_value
	{
		int min,p=0;
		min=list.get(0).ep_value;
		for(int i=0;i<list.size();i++)
		{
			if(min>list.get(i).ep_value)
			{
				min=list.get(i).ep_value;
				p=i;
			}
		}
		return p;
	}
}
public class TicTacToe 
{

	public static void main(String[] args) 
	{
		
		game g=new game();
		g.accept();
	}

}



/*

=================================================================
						OUTPUT
=================================================================


 Player 1 
 Enter position for x : 
0
 x - -
 - - -
 - - -
Do you want to continue 1.yes 2.no : 
1
Computer 
_______________________________________________________
Possible moves : 
 x o -
 - - -
 - - -
H value 1
 x - o
 - - -
 - - -
H value 0
 x - -
 o - -
 - - -
H value 1
 x - -
 - o -
 - - -
H value -1
 x - -
 - - o
 - - -
H value 1
 x - -
 - - -
 o - -
H value 0
 x - -
 - - -
 - o -
H value 1
 x - -
 - - -
 - - o
H value 0
_______________________________________________________
 x - -
 - o -
 - - -
Do you want to continue 1.yes 2.no : 
1
Player 1 
 Enter position for x : 
6
 x - -
 - o -
 x - -
Do you want to continue 1.yes 2.no : 
1
Computer 
_______________________________________________________
Possible moves : 
 x o -
 - o -
 x - -
H value 0
 x - o
 - o -
 x - -
H value -1
 x - -
 o o -
 x - -
Do you want to continue 1.yes 2.no : 
1
Player 1 
 Enter position for x : 
5
 x - -
 o o x
 x - -
Do you want to continue 1.yes 2.no : 
1
Computer 
_______________________________________________________
Possible moves : 
 x o -
 o o x
 x - -
H value 1
 x - o
 o o x
 x - -
H value 0
 x - -
 o o x
 x o -
H value 1
 x - -
 o o x
 x - o
H value 0
_______________________________________________________
 x - o
 o o x
 x - -
Do you want to continue 1.yes 2.no : 
1
Player 1 
 Enter position for x : 
8
 x - o
 o o x
 x - x
Do you want to continue 1.yes 2.no : 
1
Computer 
_______________________________________________________
Possible moves : 
 x o o
 o o x
 x - x
H value 0
 x - o
 o o x
 x o x
Do you want to continue 1.yes 2.no : 
1
Player 1 
 Enter position for x : 
1
 x x o
 o o x
 x o x
Draw


***************************************************************************

Player 1 
 Enter position for x : 
7
 - - -
 - - -
 - x -
Do you want to continue 1.yes 2.no : 
1
Computer 
_______________________________________________________
Possible moves : 
 o - -
 - - -
 - x -
H value -1
 - o -
 - - -
 - x -
H value 0
 - - o
 - - -
 - x -
H value -1
 - - -
 o - -
 - x -
H value 0
 - - -
 - o -
 - x -
H value -2
 - - -
 - - o
 - x -
H value 0
 - - -
 - - -
 o x -
H value -1
 - - -
 - - -
 - x o
H value -1
_______________________________________________________
 - - -
 - o -
 - x -
Do you want to continue 1.yes 2.no : 
1
Player 1 
 Enter position for x : 
0
 x - -
 - o -
 - x -
Do you want to continue 1.yes 2.no : 
1
Computer 
_______________________________________________________
Possible moves : 
 x o -
 - o -
 - x -
H value 0
 x - o
 - o -
 - x -
H value -1
 x - -
 o o -
 - x -
H value 0
 x - -
 - o o
 - x -
H value 0
 x - -
 - o -
 o x -
H value -1
 x - -
 - o -
 - x o
H value -1
_______________________________________________________
 x - o
 - o -
 - x -
Do you want to continue 1.yes 2.no : 
1
Player 1 
 Enter position for x : 
6
 x - o
 - o -
 x x -
Do you want to continue 1.yes 2.no : 
1
Computer 
_______________________________________________________
Possible moves : 
 x o o
 - o -
 x x -
H value 0
 x - o
 o o -
 x x -
Do you want to continue 1.yes 2.no : 
1
Player 1 
 Enter position for x : 
1
 x x o
 o o -
 x x -
Do you want to continue 1.yes 2.no : 
1
Computer 
_______________________________________________________
Possible moves : 
 x x o
 o o o
 x x -
Computer Wins 


*/
 