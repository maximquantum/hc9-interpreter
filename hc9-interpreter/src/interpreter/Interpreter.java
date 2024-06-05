package interpreter;

abstract class Value {}

class IntValue extends Value {
	int value;
	IntValue(int value) { this.value = value; }
}

class BooleanValue extends Value {
	boolean value;
	BooleanValue(Boolean value) { this.value = value; }
}

class StringValue extends Value {
	String value;
	StringValue(String value) { this.value = value; }
}

public class Interpreter {
	
	static Value evaluate(Value value1, char operator, Value value2) {
		switch (operator) {
		case '+' -> {
			if (value1 instanceof IntValue i)
				return new IntValue(i.value + ((IntValue)value2).value);
			else if (value1 instanceof StringValue s)
				return new StringValue(s.value + ((StringValue)value2).value);
			else
				throw new RuntimeException("Type error");
		}
		case '&' -> {
			if (value1 instanceof BooleanValue b)
				return new BooleanValue(b.value && ((BooleanValue)value2).value);
			else if (value1 instanceof IntValue i)
				return new IntValue(i.value & ((IntValue)value2).value);
			else
				throw new RuntimeException("Type erorr");
		}
		default -> throw new RuntimeException("Operator not supported");
		}
	}
}
