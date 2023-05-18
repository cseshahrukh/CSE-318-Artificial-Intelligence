#include <bits/stdc++.h>
using namespace std;
#define pb push_back
#define ff first
#define ss second
struct Node
{
    Node* parent; //print korar jonno
    int k, x, y, cost, move; //len, *x, *y, f=g+h, root theke move
    int** current_board; //2d array mane grid ta
};

struct Comparator
{
    bool operator()(const Node* node1, const Node* node2) const
    {
        return (node1->cost) > (node2->cost); //min priority queue te eivabe
    }
};

Node* makeNode(int k, int x, int y, int move, Node* parent, int** current_board)
{
    Node* node = new Node;
    node->move = move;
    node->k = k;
    node->x = x;
    node->y = y;

    node->parent = parent;
    // new node board memory allocation
    node->current_board = new int*[k];
    for (int i=0; i<k; i++)
    {
        node->current_board[i] = new int[k];
    }

    // new node board create korchi
    for(int i=0; i<k; i++)
    {
        for (int j=0; j<k; j++)
        {
            node->current_board[i][j] = current_board[i][j];
        }
    }
    if(node->parent != nullptr)
    {
        //swap er bayper ta
        swap(node->current_board[parent->x][parent->y], node->current_board[x][y]);
    }
    return node;
}

int count_inversion(int k, int** initial_board)
{
    int counter=0, inversionCount=0;
    int num_arr[k*k];

    //copy matrix in 1d array
    for(int i=0; i<k; i++)
    {
        for(int j=0; j<k; j++)
        {
            num_arr[counter]=initial_board[i][j];
            counter=counter+1;
        }
    }
        //count inversion
    for(int i=0; i<counter; i++)
    {
        if(num_arr[i]==0)
        {
            //do nothing
        }
        else if(num_arr[i] != 0)
        {
            for(int j=i+1; j<counter; j++)
            {
                if(num_arr[j] != 0 && num_arr[j] < num_arr[i])
                    inversionCount=inversionCount+1;
            }
        }
    }
    return inversionCount;

}

bool check_solvability(int k, int**initial_board)
{
    int blank=0;
    int inversion = count_inversion(k, initial_board);
    // blank position find korchi
    for(int i=0; i<k; i++)
    {
        for(int j=0; j<k; j++)
        {
            if(initial_board[i][j] == 0)
                blank = i;
        }
    }
    // solvability check
    if(inversion%2==1 &&k%2==1)   // k odd, inversion odd
        return false;
    if( inversion%2==0 && k%2==1) // k odd, inversion even
        return true;
    if(k%2==0 && ((blank%2==0 && inversion%2==1) || (blank%2==1 && inversion%2==0)))  //k even, blank even + inversion odd or blank odd + inversion even
        return true;
    return false;
}


int Hamming_Distance(int k, int** maxi, int** goal)
{
    int hamming_distance = 0;
    for(int i=0; i<k; i++)
    {
        for(int j=0; j<k; j++)
        {
            if(maxi[i][j] !=0 && maxi[i][j] != goal[i][j])
                hamming_distance=hamming_distance+1;
        }
    }
    return hamming_distance;
}

int Manhattan_Distance(int k, int** matrix)
{
    vector <pair<int,int>> goal_position;
    int manhattan_distance = 0;

    for(int i=0; i<k; i++)
    {
        for(int j=0; j<k; j++)
        {
            goal_position.pb(make_pair(i,j));
        }
    }

    for(int i=0; i<k; i++)
    {
        for(int j=0; j<k; j++)
        {
            if(matrix[i][j] != 0)
            {
                manhattan_distance += abs(i - goal_position[matrix[i][j]-1].ff);
                manhattan_distance += abs(j - goal_position[matrix[i][j]-1].ss);
            }
        }
    }
    return manhattan_distance;
}

//Nicher function ta final cost return korbe
int CalculateCost(int func, int k, int** initial, int** goal, int move_cost)
{
    int returnTotalCost=0;
    if(func == 1)
    {
        int heu_cost_hamming = Hamming_Distance(k, initial, goal);

        returnTotalCost= move_cost + heu_cost_hamming;
    }
    else if(func == 2)
    {
        int heu_cost_manhattan = Manhattan_Distance(k,initial);

        returnTotalCost= move_cost + heu_cost_manhattan;
    }

    return returnTotalCost;


}

bool isValid(int k, int x, int y)
{
    return (x >= 0 && x < k && y >= 0 && y < k);
}

bool isExist(vector<Node*> puzzleboardclosed, Node* child, int k)
{
    int len=puzzleboardclosed.size();
    for (int i = 0; i < len; i++)
    {
        int issame = 1;

        // compare matrix
        for(int j = 0; j < k; j++)
        {
            for(int m = 0; m < k; m++)
            {
                if(child->current_board[j][m] != puzzleboardclosed.at(i)->current_board[j][m])
                {
                    issame = 0;
                    break;
                }
            }
            if(issame == 0)
            {
                break;
            }
        }
        if(issame == 1)
        {
            return true; //already ache amader vector tay
        }
    }
    return false;
}

/*
void printBoard(int k, Node* current)
{
    for(int i = 0; i < k; i++)
    {
        for(int j = 0; j < k; j++)
        {
            if(current->current_board[i][j] == 0)
                cout << "*\t";
            else if(current->current_board[i][j] != 0)
                cout << current->current_board[i][j] << "\t";
        }
        cout << endl;
    }
} */

void printSolve(Node* current, int k)
{
    if(current == nullptr)
        return;
    printSolve(current->parent,k); //parent track kore kore age parent ta print kora lagbe recursion kore
    //printBoard(k, current);

    //printing the board
    for(int i=0; i<k; i++)
    {
        for(int j=0; j<k; j++)
        {
            if(current->current_board[i][j] == 0)
                cout << "*\t";
            else if(current->current_board[i][j] != 0)
                cout << current->current_board[i][j] << "\t";
        }
        cout << endl;
    }
    cout << endl;
}

void puzzleSolve(int method, int **initial, int k, int x, int y, int **goal)
{
    Node *root = makeNode(k, x, y, 0, nullptr, initial); //root er to kono parent nai...len *row *col cost parent grid

    // min hip
    priority_queue<Node *, vector<Node *>, Comparator > puzzleboardopen; //built in pq
    vector<Node *> puzzleboardclosed; //Eita linked list er moto

    root->cost = CalculateCost(method, k, initial, goal, 0);
    puzzleboardopen.push(root);

    while (!puzzleboardopen.empty())
    {

        Node *current = puzzleboardopen.top();
        puzzleboardopen.pop();
        if (!isExist(puzzleboardclosed, current, k)) //alreaedy ache kina
        {
            //close board e na thakle push kore dicchi close board e
            puzzleboardclosed.push_back(current);  // move to closed list

            // check goal
            int flag = 1;
            for (int i = 0; i < k; i++)
            {
                for (int j = 0; j < k; j++)
                {
                    if (current->current_board[i][j] != goal[i][j])
                    {
                        flag = 0;
                        break;
                    }
                }
                if (flag == 0)
                    break;
            }
            if (flag == 1)
            {
                cout << "Goal Reached"<<endl;
                cout << "Optimal Cost: " << current->move << endl;
                if(method == 1) cout << "Hamming:"<<endl;
                else if(method == 2) cout << "Manhattan:"<<endl;
                printSolve(current,k);

                //closedta hocche array list
                //last e solution ta just enter korechi explore to hoy ni tai -1
                cout << "explored node: " << puzzleboardclosed.size() - 1;

                //arraylist+priority queue
                cout << ", expanded node: " << puzzleboardclosed.size() + puzzleboardopen.size() << endl;
                return;
            }

            //total 4 ta move dite pare
            int dr[4] = {-1, 1, 0, 0};
            int dc[4] = {0, 0, -1, 1};
            // generate children
            for (int i=0; i<4; i++)    // a parent can have at best 4 children
            {
                //grid er baire jawa jabe na
                if (isValid(k, current->x + dr[i], current->y + dc[i]))
                {
                    Node *child = makeNode(current->k, current->x + dr[i], current->y + dc[i], current->move + 1, current,
                                           current->current_board);
                    //len, *row, *col, rootthekemove, parent, grid
                    //makeNodeFunction e jeye swap er bayper ta hobe

                    child->cost = CalculateCost(method, k, child->current_board, goal, child->move); //total cost ber korchi
                    if (!isExist(puzzleboardclosed, child, k))
                    {
                        //closeboard e na thakle open board e push kore dicchi
                        puzzleboardopen.push(child);
                    }
                }
            }
        }
    }
    cout << "Goal Not Reached"<<endl;
}

int main()
{
    int x, y, k; ////x and y hocche star er initial position. 0 base index
    cin >> k; //k*k size er grid
    int **initial = new int *[k]; //input char ke int e convert

    int **goal = new int *[k];
    int count = 1; //final grid initialize korar jonno

    // allocate memory
    for (int i=0; i<k; i++)
    {
        //dynamic memory allocation// ekek ta row allocate korchi
        initial[i] = new int[k];
        goal[i] = new int[k];

    }
    // final ba goal board initialization korchi
    for (int i=0; i<k; i++)
    {
        for (int j=0; j<k; j++)
        {
            goal[i][j] = count;
            count=count+1;
        }
    }
    goal[k-1][k-1] = 0; //last value ta * ba 0; uporer for loop e pore 10 hoye chilo


    // initial board input nicchi
    for (int i=0; i<k; i++)
    {
        for (int j=0; j<k; j++)
        {
            cin >> initial[i][j];    // zero mane blank
        }
    }

    for (int i=0; i<k; i++)
    {
        for (int j=0; j<k; j++)
        {
            if(initial[i][j] == 0)      // zero mane star
            {
                y = j; //star er col 0 base
                x = i; //star er row 0 base

            }
        }
    }

    //check solvability
    if (!check_solvability(k, initial)) //length and full grid
    {
        cout << "Not Solvable"<<endl;
    }
    else if (check_solvability(k, initial))
    {
        cout << "Solvable"<<endl;

        //1 is for Hamming
        puzzleSolve(1,initial,k,x,y,goal);

        //2 is for Manhattan
        puzzleSolve(2,initial,k,x,y,goal);
    }

    return 0;
}

