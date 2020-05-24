package com.example.trainingcode.datastructure.graph;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 基于邻接矩阵的广度优先搜索
 */
public class Graph_AdjacencyMatrix_Bfs {

    private int mVertexNum; // 顶点数
    private int[][] mAdjacencyMatrix; // 邻接矩阵

    public Graph_AdjacencyMatrix_Bfs(int vertexNum) {
        this.mVertexNum = vertexNum;
        this.mAdjacencyMatrix = new int[vertexNum][vertexNum];
        for (int i = 0; i < vertexNum; ++i) {
            for (int j = 0; j < vertexNum; ++j) {
                mAdjacencyMatrix[i][j] = 0;
            }
        }
    }

    /**
     * 添加图的边
     *
     * @param s 起始顶点
     * @param t 终止顶点
     */
    public void addEdge(int s, int t) {
        mAdjacencyMatrix[s][t] = 1;
        mAdjacencyMatrix[t][s] = 1; // 无向图一条边存两次
    }

    /**
     * 广度优先搜索(最短路径)
     *
     * @param s
     * @param t
     */
    public void bfs(int s, int t) {
        if (s >= mVertexNum || t >= mVertexNum || s == t) {
            return;
        }

        // visited是用来记录已经被访问的顶点，用来避免顶点被重复访问
        boolean[] visited = new boolean[mVertexNum];
        visited[s] = true;

        // prev用来记录搜索路径，prev[i]存储的是：顶点i是从哪个前驱顶点遍历过来的
        int[] prev = new int[mVertexNum];
        for (int i = 0; i < mVertexNum; ++i) {
            prev[i] = -1;
        }

        // queue是一个队列，用来存储已经被访问，但相连的顶点还没有被访问的顶点
        Queue<Integer> queue = new LinkedList<>();
        queue.add(s);

        while (queue.size() != 0) {
            int w = queue.poll();
            for (int i = 0; i < mVertexNum; ++i) {
                int v = mAdjacencyMatrix[w][i];
                if (v == 1 && !visited[i]) {
                    prev[i] = w;
                    if (i == t) {
                        print(prev, s, t);
                        return;
                    }
                    visited[i] = true;
                    queue.add(i);
                }
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
        Graph_AdjacencyMatrix_Bfs graph = new Graph_AdjacencyMatrix_Bfs(8);
        graph.addEdge(0, 1);
        graph.addEdge(0, 3);
        graph.addEdge(1, 2);
        graph.addEdge(1, 4);
        graph.addEdge(2, 5);
        graph.addEdge(4, 5);
        graph.addEdge(4, 6);
        graph.addEdge(5, 7);
        graph.addEdge(6, 7);
        graph.bfs(0, 6);
    }
}