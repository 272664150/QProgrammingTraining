package com.example.trainingcode.algorithm.topologicalsort;

import java.util.Stack;

/**
 * 基于邻接矩阵、DFS的拓扑排序
 */
public class TopologicalSort_AdjacencyMatrix_Dfs {

    private int mVertexNum; // 顶点数
    private int[] mInDegree; // 顶点入度数
    private int[][] mAdjacencyMatrix; // 邻接矩阵

    private boolean[] isVisited; // 用来避免顶点被重复访问
    private Stack<Integer> mReversePost; // 用栈来保存排序结果

    public TopologicalSort_AdjacencyMatrix_Dfs(int vertexNum) {
        this.mVertexNum = vertexNum;
        this.mInDegree = new int[vertexNum];
        this.mAdjacencyMatrix = new int[vertexNum][vertexNum];
        for (int i = 0; i < vertexNum; i++) {
            for (int j = 0; j < vertexNum; j++) {
                mAdjacencyMatrix[i][j] = 0;
            }
        }
        this.isVisited = new boolean[vertexNum];
        this.mReversePost = new Stack<>();
    }

    /**
     * 添加图的边，累计终止顶点入度
     *
     * @param s 起始顶点
     * @param t 终止顶点
     */
    public void addEdge(int s, int t) {
        mAdjacencyMatrix[s][t] = 1;
        mInDegree[t]++;
    }

    /**
     * 拓扑排序
     */
    public void sort() {
        for (int i = 0; i < mVertexNum; i++) {
            if (mInDegree[i] == 0) {
                recurDfs(i);
                break;
            }
        }

        System.out.print(mReversePost);
    }

    /**
     * 递归方法
     *
     * @param s
     */
    private void recurDfs(int s) {
        if (mReversePost.size() == mVertexNum) {
            return;
        }

        isVisited[s] = true;
        mReversePost.push(s);

        for (int i = 0; i < mVertexNum; i++) {
            if (mAdjacencyMatrix[s][i] == 1 && !isVisited[i]) {
                mInDegree[i]--;
                if (mInDegree[i] == 0) {
                    recurDfs(i);
                }
            }
        }
    }

    public static void main(String[] args) {
        TopologicalSort_AdjacencyMatrix_Dfs graph = new TopologicalSort_AdjacencyMatrix_Dfs(8);
        graph.addEdge(0, 1);
        graph.addEdge(0, 3);
        graph.addEdge(1, 2);
        graph.addEdge(1, 4);
        graph.addEdge(2, 5);
        graph.addEdge(4, 5);
        graph.addEdge(4, 6);
        graph.addEdge(5, 7);
        graph.addEdge(6, 7);
        graph.sort();
    }
}
