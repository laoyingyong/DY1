package demo;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author laoyingyong
 * @date: 2020-02-28 20:34
 */
public class Demo
{
    public static void main(String[] args)
    {
        //多叉树的初始化
        ArrayList<Node> oneList=new ArrayList<>();//oneList存放根节点的所有孩子

        ArrayList<Node> twoList=new ArrayList<>();//twoList存放结点2的所有孩子
        twoList.add(new Node(5,null));//给结点2添加孩子5
        twoList.add(new Node(6,null));//给结点2添加孩子6
        twoList.add(new Node(7,null));//给结点2添加孩子7

        oneList.add(new Node(2,twoList));//给根节点添加孩子2

        oneList.add(new Node(3,null));//给根节点添加孩子3

        ArrayList<Node> fourList=new ArrayList<>();//用于存放结点4的所有孩子
        fourList.add(new Node(8,null));
        fourList.add(new Node(9,null));
        oneList.add(new Node(4,fourList));//给根节点添加孩子4

        List<List<Integer>> aList= Solution.levelOrder(new Node(1,  oneList));
        for (List<Integer> integers : aList)//遍历输出
        {

            for (int i = 0; i < integers.size(); i++)
            {
                if(i==integers.size()-1)//如果是集合的最后一个元素
                {
                    System.out.print(integers.get(i));
                }
                else
                {
                    System.out.print(integers.get(i)+",");
                }
            }
            System.out.println();//遍历完一层之后就换行

        }

    }
}

class Node
{
    private int value;//当前结点的值
    private List<Node> childrenList;//当前结点的所有孩子的集合


    public Node(int value,List<Node> childrenList)
    {
        this.value =value;
        this.childrenList =childrenList;
    }

    public int getValue()
    {
        return value;
    }

    public void setValue(int value)
    {
        this.value = value;
    }

    public List<Node> getChildrenList()
    {
        return childrenList;
    }

    public void setChildrenList(List<Node> childrenList)
    {
        this.childrenList = childrenList;
    }
}

class Solution
{

    public static List<List<Integer>> levelOrder(Node rootNode)
    {
        List<List<Integer>> totalList = new ArrayList<List<Integer>>();//存放的是整棵树的所有数据
        if (rootNode == null)
        {
            return totalList;
        }
        LinkedList<Node> levelList = new LinkedList<Node>();//一层中的数据，把levelList当做队列使用，实现层序遍历

        levelList.offer(rootNode);//先把跟结点添加到队列中
        Node firstNode= null;//队首元素

        while (! levelList.isEmpty())
        {
            List<Integer> list = new ArrayList<Integer>();//用于存放队列中出队的元素的值

            int size = levelList.size();//该层的结点个数
            for (int i = 0; i < size; i++)//遍历该层的所有结点
            {
                firstNode = levelList.poll();//获取并移除队列的第一个元素
                list.add(firstNode.getValue());
                if(firstNode.getChildrenList()!=null)//如果队首元素存在孩子结点
                {
                    for (Node node : firstNode.getChildrenList())
                    {
                        levelList.offer(node);//将队首元素所有的孩子添加到队列中
                    }
                }
            }
            totalList.add(list);//添加一层数据
        }
        return totalList;//返回整棵多叉树
    }
}