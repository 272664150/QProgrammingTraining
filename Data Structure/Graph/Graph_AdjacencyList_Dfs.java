package com.example.trainingcode.datastructure.graph;

import java.util.LinkedList;
import java.util.List;

/**
 * 基于邻接表的深度优先搜索
 */
public class Graph_AdjacencyList_Dfs {

    private boolean isFound; // 是否找到终止顶点

    private int mVertexNum; // 顶点数
    private List<Integer> mAdjacencyList[]; // 邻接表

    public Graph_AdjacencyList_Dfs(int vertexNum) {
        this.mVertexNum = vertexNum;
        this.mAdjacencyList = new LinkedList[vertexNum];
        for (int i = 0; i < vertexNum; ++i) {
            mAdjacencyList[i] = new LinkedList<>();
        }
    }

    /**
     * 添加图的边
     *
     * @param s 起始顶点
     * @param t 终止顶点
     */
    public void addEdge(int s, int t) {
        mAdjacencyList[s].add(t);
        mAdjacencyList[t].add(s); // 无向图一条边存两次
    }

    /**
     * 深度优先搜索
     *
     * @param s
     * @param t
     */
    public void dfs(int s, int t) {
        if (s >= mVertexNum || t >= mVertexNum || s >= t) {
            return;
        }

        // visited是用来记录已经被访问的顶点，用来避免顶点被重复访问
        boolean[] visited = new boolean[mVertexNum];

        // prev用来记录搜索路径，prev[i]存储的是：顶点i是从哪个前驱顶点遍历过来的
        int[] prev = new int[mVertexNum];
        for (int i = 0; i < mVertexNum; ++i) {
            prev[i] = -1;
        }

        recurDfs(s, t, visited, prev);
        print(prev, s, t);
    }

    /**
     * 递归方法
     *
     * @param w
     * @param t
     * @param visited
     * @param prev
     */
    private void recurDfs(int w, int t, boolean[] visited, int[] prev) {
        if (isFound) {
            return;
        }

        visited[w] = true;
        if (w == t) {
            isFound = true;
            return;
        }

        for (int i = 0; i < mAdjacencyList[w].size(); ++i) {
            int q = mAdjacencyList[w].get(i);
            if (!visited[q]) {
                prev[q] = w;
                recurDfs(q, t, visited, prev);
            }
        }
    }

    /**
     * 递归打印 s->t 的路径
     *
     * @param prev
     * @param s
     * @param t
     */
    private void print(int[] prev, int s, int t) {
        if (prev[t] != -1 && t != s) {
            print(prev, s, prev[t]);
        }
        System.out.print(t + " ");
    }

    public static void main(String[] args) {
        Graph_AdjacencyList_Dfs graph = new Graph_AdjacencyList_Dfs(8);
        graph.addEdge(0, 1);
        graph.addEdge(0, 3);
        graph.addEdge(1, 2);
        graph.addEdge(1, 4);
        graph.addEdge(2, 5);
        graph.addEdge(4, 5);
        graph.addEdge(4, 6);
        graph.addEdge(5, 7);
        graph.addEdge(6, 7);
        graph.dfs(0, 6);
    }
}