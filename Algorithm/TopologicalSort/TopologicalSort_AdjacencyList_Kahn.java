package com.example.trainingcode.algorithm.topologicalsort;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * 基于邻接表、Kahn的拓扑排序
 */
public class TopologicalSort_AdjacencyList_Kahn {

    private int mVertexNum; // 顶点数
    private int[] mInDegree; // 顶点入度数
    private List<Integer> mAdjacencyList[]; // 邻接表
    private Deque<Integer> mZeroDegreeDeque; // 入度数为0的顶点

    public TopologicalSort_AdjacencyList_Kahn(int vertexNum) {
        this.mVertexNum = vertexNum;
        this.mInDegree = new int[vertexNum];
        this.mAdjacencyList = new LinkedList[vertexNum];
        for (int i = 0; i < vertexNum; ++i) {
            mAdjacencyList[i] = new LinkedList<>();
        }
        this.mZeroDegreeDeque = new ArrayDeque<>();
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
                mZeroDegreeDeque.offer(i);
            }
        }

        List<Integer> result = new ArrayList<>();

        while (!mZeroDegreeDeque.isEmpty()) {
            int s = mZeroDegreeDeque.poll();
            for (int j = 0; j < mAdjacencyList[s].size(); j++) {
                int w = mAdjacencyList[s].get(j);
                mInDegree[w]--;
                if (mInDegree[w] == 0) {
                    mZeroDegreeDeque.offer(w);
                }
            }
            result.add(s);
        }

        System.out.println(result);
    }

    public static void main(String[] args) {
        TopologicalSort_AdjacencyList_Kahn graph = new TopologicalSort_AdjacencyList_Kahn(8);
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