package edu.osu.cse6341.lispInterpreter.program.nodes.functions;

import edu.osu.cse6341.lispInterpreter.program.ExpressionKind;
import edu.osu.cse6341.lispInterpreter.program.nodes.ListNode;

public class MinusFunction implements IFunction{

	private int length;
	private ListNode leftSide, rightSide;

	public MinusFunction(){}

	private MinusFunction(ListNode listNode){
		length = listNode.getLength();
		leftSide = listNode.getListNode();
		if(leftSide != null) rightSide = leftSide.getListNode();
	}

	@Override
	public boolean isDefinedCorrectly(){
		leftSide.evaluate();
		rightSide.evaluate();
		return length == 3 
			&& leftSide.getExpressionKind() == ExpressionKind.NUMERIC_EXPRESSION  
			&& rightSide.getExpressionKind() == ExpressionKind.NUMERIC_EXPRESSION;
	}  

	@Override
	public String evaluate(){
		int left = Integer.parseInt(leftSide.getValue());
		int right = Integer.parseInt(rightSide.getValue());
		return Integer.toString(left - right);
	}

	@Override
	public IFunction newInstance(ListNode listNode){
		return new MinusFunction(listNode);
	}

	@Override
	public ExpressionKind getExpressionKind(){
		return ExpressionKind.NUMERIC_EXPRESSION;
	}
}