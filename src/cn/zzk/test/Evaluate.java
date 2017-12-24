package cn.zzk.test;

import edu.princeton.cs.algs4.Stack;

/**
 * 双栈算术表达式求值算法,简单的没有运算符号优先权，只有括号
 * @author Administrator
 *
 */
public class Evaluate {
	public static double Evluate(String[] str){
		Stack<String> ops = new Stack<String>();
		Stack<Double> vals = new Stack<Double>();
		int i=0;
		while(i>=str.length){
			if(str[i].equals("(")){
				
			}else if(str[i].equals("+")){
				ops.push(str[i]);
			}else if(str[i].equals("-")){
				ops.push(str[i]);
			}else if(str[i].equals("*")){
				ops.push(str[i]);
			}else if(str[i].equals("/")){
				ops.push(str[i]);
			}else if(str[i].equals(")")){
				//如果字符是")"，弹出运算符合操作数，计算结果并压入栈
				String op = ops.pop();
				double v = vals.pop();
				if(op.equals("+")){
					v = vals.pop() + v;
				}else if(op.equals("-")){
					v = vals.pop() - v;
				}else if(op.equals("*")){
					v = vals.pop() * v;
				}else if(ops.equals("/")){
					v = vals.pop() / v;
				}
				vals.push(v);
			}else{
				vals.push(Double.parseDouble(str[i]));
			}
			i++;
		}
		return vals.pop();
	}
}
