package com.example.trainingcode.algorithm.topologicalsort;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * 基于邻接表、DFS的拓扑排序
 */
public class TopologicalSort_AdjacencyList_Dfs {

    private int mVertexNum; // 顶点数
    private int[] mInDegree; // 顶点入度数
    private List<Integer> mAdjacencyList[]; // 邻接表

    private boolean[] isVisited; // 用来避免顶点被重复访问
    private Stack<Integer> mReversePost; // 用栈来保存排序结果

    public TopologicalSort_AdjacencyList_Dfs(int vertexNum) {
        this.mVertexNum = vertexNum;
        this.mInDegree = new int[vertexNum];
        this.mAdjacencyList = new LinkedList[vertexNum];
        for (int i = 0; i < vertexNum; ++i) {
            mAdjacencyList[i] = new LinkedList<>();
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
        mAdjacencyList[s].add(t);
        mInDegree[t]++;
    }

    /**
     * 拓扑排序
     */
    public void sort() {
        for (int i = 0; i < mVertexNum; i++) {
            if (mInDegree[i] == 0) {
                recurDfs(i);
            }
        }

        System.out.println(mReversePost);
    }

    /**
     * 递归方法
     *
     * @param s
     */
    private void recurDfs(int s) {
        if (isVisited[s] || mReversePost.size() == mVertexNum) {
            return;
        }

        isVisited[s] = true;
        mReversePost.push(s);

        for (int i = 0; i < mAdjacencyList[s].size(); i++) {
            int w = mAdjacencyList[s].get(i);
            if (isVisited[w]) {
                return;
            }

            mInDegree[w]--;
            if (mInDegree[w] == 0) {
                recurDfs(w);
            }
        }
    }

    public static void main(String[] args) {
        TopologicalSort_AdjacencyList_Dfs graph = new TopologicalSort_AdjacencyList_Dfs(8);
        graph.addEdge(0, 1);
        graph.addEdge(0, 3);
        // graph.addEdge(1, 2);
        graph.addEdge(1, 4);
        graph.addEdge(2, 5);
        graph.addEdge(4, 5);
        graph.addEdge(4, 6);
        graph.addEdge(5, 7);
        graph.addEdge(6, 7);
        graph.sort();
    }
}
