package interpreter;

abstract class Value {}

abstract class AddableValue extends Value {
	abstract Value add(Value other);
}

abstract class AndableValue extends Value {
	abstract Value and(Value other);
}

class IntValue extends AddableValue { // , AndableValue <— solution (convert to interface instead of class)
	int value;
	IntValue(int value) { this.value = value; }
	@Override
	Value add(Value other) {
		return new IntValue(value + ((IntValue)other).value);
	}
	
	
}

class BooleanValue extends AndableValue {
	boolean value;
	BooleanValue(Boolean value) { this.value = value; }
	@Override
	Value and(Value other) {
		return new BooleanValue(value & ((BooleanValue)other).value);
	}
	
}

class StringValue extends AddableValue {
	String value;
	StringValue(String value) { this.value = value; }
	@Override
	Value add(Value other) {
		return new StringValue(value + ((StringValue)other).value);
	}
}

public class Interpreter {
	
	static Value evaluate(Value value1, char operator, Value value2) {
		switch (operator) {
		case '+' -> {
			if (value1 instanceof AddableValue a)
				return a.add(value2);
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
