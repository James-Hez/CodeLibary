/*
 * *
 *  * Licensed to the Apache Software Foundation (ASF) under one or more
 *  * contributor license agreements.  See the NOTICE file distributed with
 *  * this work for additional information regarding copyright ownership.
 *  * The ASF licenses this file to You under the Apache License, Version 2.0
 *  * (the "License"); you may not use this file except in compliance with
 *  * the License.  You may obtain a copy of the License at
 *  *
 *  *    http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  * See the License for the specific language governing permissions and
 *  * limitations under the License.
 *
 */

package com.james.codelib.algorithms.graph.undirected.symbol;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 有符号图
 * Created by James.Hez on 2015/10/10.
 */
public class SymbolGraph {
    //边的数目
    private int edgeNumber;

    /**
     * 顶点名称数组
     */
    private Set<String> vertices;
    //邻接表
    private Map<String, List<String>> adj;

    /**
     * 创建一个含有v个顶点，但是不含有边的图
     *
     * @param vs
     */
    public SymbolGraph(List<String> vs) {
        vertices = Sets.newHashSet(vs);

        edgeNumber = 0;
        adj = Maps.newHashMap();
        for (String vertice : vertices) {
            adj.put(vertice, Lists.newArrayList());
        }
    }

    /**
     * 获取顶点数
     *
     * @return
     */
    public int verticesNumber() {
        return vertices.size();
    }

    /**
     * 获取边数
     *
     * @return
     */
    public int edgeNumber() {
        return edgeNumber;
    }

    /**
     * 向图中添加一条边，v-w
     *
     * @return
     */
    public void addEdge(String v, String w) {
        //将V添加到W的邻接表中
        adj.get(v).add(w);
        //将W添加到v的邻接表中
        adj.get(w).add(v);
        edgeNumber++;
    }


    /**
     * 和V相邻的所有顶点
     *
     * @return
     */
    public List<String> adj(String v) {
        return adj.get(v);
    }

    /**
     * 计算v的度数
     *
     * @param v
     * @return
     */
    public int degree(String v) {
        int degree = 0;
        for (String w : adj(v)) {
            degree++;
        }
        return degree;
    }

    /**
     * 计算所有顶点的最大度数
     *
     * @return
     */
    public int maxDegree() {
        int max = 0;
        for (String vertice : vertices) {
            if (degree(vertice) > max) {
                max = degree(vertice);
            }
        }
        return max;
    }

    /**
     * 计算所有节点的平均度数
     *
     * @return
     */
    public double avgDegree() {
        return 2 * edgeNumber() / verticesNumber();
    }

    /**
     * 计算自环个数
     * 自环指的是从一个边，从自己到自己，比如0-0，这样就是自环
     * 和检查DAG是否闭环无关。
     *
     * @return
     */
    public int numberOfSelfLoops() {
        int count = 0;
        for (String vertice : vertices) {
            for (String w : adj(vertice)) {
                if (vertice.equals(w)) {
                    count++;
                }
            }
        }
        //每条边都被标记两次
        return count / 2;
    }


    public String toString() {
        String s = vertices + " vertices, " + edgeNumber + " edges\n";
        for (String v : vertices) {
            s += v + ": ";
            for (String w : adj(v)) {
                s += w + " ";
            }
            s += "\n";
        }
        return s;
    }

}
