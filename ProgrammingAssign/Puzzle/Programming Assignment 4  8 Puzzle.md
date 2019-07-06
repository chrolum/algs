## 问题描述

[8-puzzle problem](https://en.wikipedia.org/wiki/Fifteen_puzzle)

## 问题分析

这次的编程实验最终的目的是编写一种拼图游戏的AI。在搜索解法的过程中，将每一步的棋盘情况成为一个**状态**, 每个状态都有其相邻的下一个状态(仅移动一次拼图)，这些状态连起来就会用生成一个状态空间，是一个图的结构。生成游戏树后，在运用相应的搜索算法，搜索起点到目标的一条最短路径，就可以找到最佳的解法。

## 实现

### Board类建模



### 搜索算法

- 将初始节点加入优先队列
- 从优先队列中选择最小的节点
- 将改节点的所有邻接节点加入优先队列
- 重复第2~4步

#### 移动成本的衡量

在计算各个点之间的距离时，有多种算法可以选择

- hamming distance
- manhattan distance


### Solver 类实现

- searchNode：有序课程所给的Board类没有实现Comparable的接口，需要额外定义一个私有类`seachNode`来承担搜索节点的功能。

#### 搜索节点实现

根据任务书中的提醒，搜索节点应该包含有一下三个信息

> We define a search node of the game to be ==a board==, the ==number of moves== made to reach the board, and the ==previous search node==. First, insert the initial search node (the initial board, 0 moves, and a null previous search node) into a priority queue. 

初步的类设计

```java
class SNode implements Comparable<SNode> {
    private final Board board;
    private SNode prev;
    private int moves;
    
    public SNode(Board board) {
        
    }
}
```

#### 初始状态是否有解？

> There is a elegant algorithm for determining whether a board is solvable that relies on a parity argument (and occasionally we change our API to require this solution). However, the current API requires you to detect infeasiblity in Solver by ==using two synchronized A* searches== (e.g., using two priority queues).

这个问题的解决基于一个结论：一个初始状态经过一次交换后(不交换空白块)，其产生的对应状态的可解性与原始状态是相反的。

##### 思路一：将初始状态和twins状态入队

两个初始状态之间必定有一个解。在得到这个解时，我需要知道这条路径是否来自原始的状态的，是的话就有解，不是的话就无解。

根据这个想法，在搜索节点中加入初始起点的信息.

```
private boolean fromOrigin;
```
然后将原始初始状态以及对应的twins状态入队。A*算法执行结束后，检查目标节点是否来源与原始的状态。

#### A*搜索算法实现

任务书中也给A*算法的具体流程

> First, ==insert== the initial search node ==(the initial board, 0 moves, and a null previous search node)== into a priority queue. 

> Then, ==delete== from the priority queue the search node with the ==minimum priority==, and ==insert== onto the priority queue ==all neighboring== search nodes (those that can be reached in ==one move== from the dequeued search node). 

> Repeat this procedure until the search node dequeued ==corresponds to the goal board==.

A* 算法作为一种图搜索算法，采用的是贪心的搜索策略，每次选择距离目标节点最近的节点进行搜索，Dijkstra算法也是采用同样的搜索策略，不同的只是评价成本的函数不同。

- 向优先队列中插入初始搜索起点(初始的状态节点，无前驱，移动次数为0)
- 将优先度最小的元素出队，将该元素的所有邻接节点入队(优化点1：禁止当前节点的前驱节点入队，防止重复访问)
- 重复上述过程，直到目标状态出队


执行完上述流程后，从目标状态节点不断访问其前驱节点，即可找到一条最短路径。

```java

```
---
### 相关证明

> when the goal board is dequeued, we have discovered not only a sequence of moves from the initial board to the goal board, but one that makes the fewest moves.

这个算法找到的是一条最短路径。


---


> To detect such situations, use the fact that boards are divided into two equivalence classes with respect to reachability:

> Those that can lead to the goal board

> Those that can lead to the goal board if we modify the initial board by swapping any pair of tiles (the blank square is not a tile).

可解决性的性质：

- 有解的状态通过一次交换后(交换非空格子)，变换为无解的状态
- 无解的状态经过一次交换后会变成有解的状态

