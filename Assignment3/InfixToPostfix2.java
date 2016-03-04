package Assignment3;

public class InfixToPostfix2 {

	public static String infixToPostfix2(String line){
		ArrayStack<Character> arrayStack = new ArrayStack<>();
		String outputString = "";
		
		//checks all the conditions
		for(int i = 0; i < line.length(); i++){
			if(!isOperator(line.charAt(i))){
				outputString += line.charAt(i);
			} else{
				//check if the stack is empty
				if(arrayStack.isEmpty()){
					arrayStack.push(line.charAt(i));
				} else if(higherPrecedence(arrayStack.top()) && higherPrecedence(line.charAt(i))){
					if(!arrayStack.isEmpty()){
						if(higherPrecedence(arrayStack.top()) == higherPrecedence(line.charAt(i))){
							if(arrayStack.top() == '('){
								arrayStack.push(line.charAt(i));
							}
							else if(line.charAt(i) == ')'){
								outputString += arrayStack.pop();
								while(!arrayStack.isEmpty()){
									if(arrayStack.top() == '('){
										arrayStack.pop();
										break;
									} else{
										outputString += arrayStack.pop();
									}
								}

							}else{
								outputString += arrayStack.pop();
								arrayStack.push(line.charAt(i));
							}
						}
					} else{
						arrayStack.push(line.charAt(i));
					}
				} else if(higherPrecedence(arrayStack.top()) && lowerPrecedence(line.charAt(i))){
					if(arrayStack.top() == '('){
						arrayStack.push(line.charAt(i));
					} 
					else{
						outputString += arrayStack.pop();
						if(!arrayStack.isEmpty()){
							if(lowerPrecedence(arrayStack.top()) == lowerPrecedence(line.charAt(i))){
								outputString += arrayStack.pop();
								arrayStack.push(line.charAt(i));
							} 
						} else{
							arrayStack.push(line.charAt(i));
						}
					}
				} else if(lowerPrecedence(arrayStack.top()) && lowerPrecedence(line.charAt(i))){
					outputString += arrayStack.pop();
					arrayStack.push(line.charAt(i));
				} else if(lowerPrecedence(arrayStack.top()) && higherPrecedence(line.charAt(i))){
					if(arrayStack.top() == '('){
						arrayStack.push(line.charAt(i));
					}
					else if(line.charAt(i) == ')'){
						outputString += arrayStack.pop();
						while(!arrayStack.isEmpty()){
							if(arrayStack.top() == '('){
								arrayStack.pop();
								break;
							} else{
								outputString += arrayStack.pop();
							}
						}
					}else{
						arrayStack.push(line.charAt(i));
					}					

				} else if(arrayStack.top() == ')'){
					arrayStack.pop();
					while(!arrayStack.isEmpty()){
						if(arrayStack.top() == '('){
							arrayStack.pop();
						} else{
							outputString += arrayStack.pop();
						}
					}
				}	
				else{
					arrayStack.push(line.charAt(i));
				}

			} 

		}
		while(!arrayStack.isEmpty()){
			outputString += arrayStack.pop();
		}
		return outputString;
	}

	//low precedence for + and -
	public static boolean lowerPrecedence(char x){
		switch(x){
		case '+':
			return true;
		case '-':
			return true;
		default:
			return false;
		}
	} 

	//high precedence for * and /
	public static boolean higherPrecedence(char x){
		switch(x){
		case '(':
			return true;
		case ')':
			return true;
		case '*':
			return true;
		case '/':
			return true;
		default:
			return false;
		}
	}

	public static boolean isOperator(char c){
		return c == '+' || c == '-' || c == '*' || c == '/' || c == '^'|| c == '(' || c == ')';
	}
}
