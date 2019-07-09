## 问题描述

[8-puzzle problem](https://en.wikipedia.org/wiki/Fifteen_puzzle)

## 问题分析

这次的编程实验最终的目的是编写一种拼图游戏的AI。在搜索解法的过程中，将每一步的棋盘情况看作一个**状态**, 每个状态都有其相邻的下一个状态(仅移动一次拼图)，这些状态连起来就会用生成一个状态空间，是一个图的结构。生成游戏树后，在运用相应的搜索算法，搜索起点到目标的一条最短路径，就可以找到最佳的解法。

## 实现
### Board类建模

### API描述

<details>

```java
public class Board {

    // create a board from an n-by-n array of tiles,
    // where tiles[row][col] = tile at (row, col)
    public Board(int[][] tiles)
    // string representation of this board
    public String toString()
    // board dimension n
    public int dimension()
    // number of tiles out of place
    public int hamming()
    // sum of Manhattan distances between tiles and goal
    public int manhattan()
    // is this board the goal board?
    public boolean isGoal()
    // does this board equal y?
    public boolean equals(Object y)
    // all neighboring boards
    public Iterable<Board> neighbors()
    // a board that is obtained by exchanging any pair of tiles
    public Board twin()
    // unit testing (not graded)
    public static void main(String[] args)
}
```
以下只挑几个有意思的api实现来分析
</details>


#### 状态存储

作业要求实现的API的入参是二维数组，实际上可以改用一维数组来进行优化。

#### 移动成本的衡量

在衡量每个节点的移动成本时，有许多不同的衡量方法，以下是两种常见的方法

- hamming distance：非目标位置的图块数量
- manhattan distance：错配图块与目标位置的水平和垂直距离之和

获取搜索节点的曼哈顿距离的关键是根据数组下标获取相应的垂直和水平距离

```
horizontal = Math.abs((board[i][j] - 1) % this.dimension() - j);
vertical = Math.abs((board[i][j] - 1) / this.dimension() - i);
```
#### 孪生节点

孪生节点是指**某个状态任意交换两个==非空图块==所产生的新状态**。

我在实现这个API的时候，对他的作用疑惑了很久。这个API实际上是用来检测某个初始状态是否一定有解的。有解性的检测基于这样一个结论：**某个状态与其孪生状态有且仅有一个有解**(证明见证明2)

```java
int p1_r = blankRow == 0 ? 1 : 0
int p1_c = 0, p2_r = blankRow == 0 ? 1 : 0, p2_c = 1;//tick off the blank block
exch(twinBlocks, p1_r, p1_c, p2_r, p2_c);
```

目前的实现比较丑陋，需要预先记录空白块的位置，然后固定交换两个位置的图块，若某个位置是白块，则再换一个位置交换。

#### 邻居节点

实现非常简单，一个状态最多有四个邻接状态，判断是否有条件产生新状态即可

```java
if (blankRow > 0) neighbors.add(new Board(exch(makeBoardCopy(), blankRow, blankCol, blankRow - 1, blankCol)));
if (blankCol > 0) neighbors.add(new Board(exch(makeBoardCopy(), blankRow, blankCol, blankRow, blankCol - 1)));
if (blankRow < this.dimension()-1) neighbors.add(new Board(exch(makeBoardCopy(), blankRow, blankCol, blankRow + 1, blankCol)));
if (blankCol < this.dimension()-1) neighbors.add(new Board(exch(makeBoardCopy(), blankRow, blankCol, blankRow, blankCol + 1)));
```
---
### Solver 类实现

### API描述

<details>

```java
public class Solver {

    // find a solution to the initial board (using the A* algorithm)
    public Solver(Board initial)

    // is the initial board solvable? (see below)
    public boolean isSolvable()

    // min number of moves to solve initial board
    public int moves()

    // sequence of boards in a shortest solution
    public Iterable<Board> solution()

    // test client (see below) 
    public static void main(String[] args)

}
```

</details>

#### 搜索节点实现

由于Board类没有实现Comparable接口，而且缺失前驱和优先度等信息，需要一个私有类将Board加一层封装。

根据任务书中的提醒，搜索节点应该包含有一下三个信息

> We define a search node of the game to be ==a board==, the ==number of moves== made to reach the board, and the ==previous search node==. First, insert the initial search node (the initial board, 0 moves, and a null previous search node) into a priority queue. 

初步的seachNode 设计

```java
class SNode implements Comparable<SNode> {
    private final Board board;
    private SNode prev;
    private int moves;
    private int priority;//manhatan distance plus moves, caching for avoiding repeatedly calculation
    
    public SNode(Board board) {
        
    }
}
```
#### 初始状态是否有解？

> There is a elegant algorithm for determining whether a board is solvable that relies on a parity argument (and occasionally we change our API to require this solution). However, the current API requires you to detect infeasiblity in Solver by ==using two synchronized A* searches== (e.g., using two priority queues).

这个问题的解决基于一个结论：一个状态与其孪生节点(经过一次非空交换所产生的节点)有且仅有一个节点有解。

##### 思路一：将初始状态和twins状态入队

将两个状态都加入到优先队列中，A*算法执行结束后必定可以找到目标节点，这是我们需要一个信息，判断这条路径是来自哪个初始节点的(原始节点还是孪生节点？)

根据这个想法，为了方便判断可解性，搜索节点中加入初始起点的信息.

```
private boolean fromOrigin;
```
然后将原始初始状态以及对应的twins状态入队。A*算法执行结束后，检查目标节点是否来源于原始的状态。

#### A*搜索算法实现

任务书中也给A*算法的具体流程

> First, ==insert== the initial search node ==(the initial board, 0 moves, and a null previous search node)== into a priority queue. 

> Then, ==delete== from the priority queue the search node with the ==minimum priority==, and ==insert== onto the priority queue ==all neighboring== search nodes (those that can be reached in ==one move== from the dequeued search node). 

> Repeat this procedure until the search node dequeued ==corresponds to the goal board==.

A_star 算法作为一种图搜索算法，与Dijkstra算法类似，采用的是**贪心**的搜索策略，每次选择距离目标节点最近的节点进行搜索，不同的只是评价成本的函数不同。(A* 算法多了启发函数的部分，在本次实验中就是指节点距离目标节点的曼哈顿距离)

算法流程

- 向优先队列中插入初始状态及其孪生状态(初始的状态节点，无前驱，移动次数为0, 是否为原始节点)，
- 将优先度最小的元素出队，然后将该元素的所有邻接节点入队(优化点1：禁止当前节点的前驱节点入队，防止重复访问)
- 重复上述过程，直到目标状态出队

```java
MinPQ<SNode> pq = new MinPQ<>();

        //insert botj origin search node and its twins into pq for checking out is solvable
        pq.insert(new SNode(initial, true));
        pq.insert(new SNode(initial.twin(), false));
        SNode curr = pq.delMin();

        //use A* algorithm to search the goal status node
        while (!curr.board.isGoal()) {
            for (Board b : curr.board.neighbors()) {// add all current neighbors
                if (curr.prev == null || !b.equals(curr.prev.board)) {// avoid add prev node into pq
                    pq.insert(new SNode(b, curr));
                }
            }
            curr = pq.delMin();
        }
        
        this.isSolvable = curr.fromOrignal;
```
#### 输出游戏最优解法过程

A*算法的执行过程中会产生一个游戏状态树，其中会包含一条初始状态指向目标节点的最短路径(最优路径见证明1)。

每个节点的有前驱的信息，不断访问其前驱即可得到解法路径。



```java
Stack<SNode> path = new Stack<>();

while (curr != null) {//the initial current node is the goal node
    this.path.push(curr.board);
    this.moves++;
    curr = curr.prev;
}
```

---
### 相关证明

<details> 

<summary>点击展开</summary>

#### 证明1

> when the goal board is dequeued, we have discovered not only a sequence of moves from the initial board to the goal board, but one that makes the fewest moves.

这个算法找到的是一条最短路径。


---


> To detect such situations, use the fact that boards are divided into two equivalence classes with respect to reachability:

> Those that can lead to the goal board

> Those that can lead to the goal board if we modify the initial board by swapping any pair of tiles (the blank square is not a tile).

可解决性的性质：

- 有解的状态通过一次交换后(交换非空格子)，变换为无解的状态
- 无解的状态经过一次交换后会变成有解的状态

</details>

### 总结

这次实验有意思的地方在于让我第一次窥探到一些AI算法的运行机制，我以前一直对各类游戏AI的实现，特别是棋牌类的AI都非常好奇。这次实验后理解到状态空间这一概念，以后有机会会自己写更多的AI算法。

### 完整代码

[https://github.com/chrolum/algs/tree/master/ProgrammingAssign/Puzzle](https://github.com/chrolum/algs/tree/master/ProgrammingAssign/Puzzle)