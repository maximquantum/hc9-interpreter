package interpreter;

interface Value {} // interface methodes by default abstract & public

interface AddableValue extends Value {
	Value add(Value other);
}

interface AndableValue extends Value {
	Value and(Value other);
}

class IntValue implements AddableValue, AndableValue {
	int value;
	IntValue(int value) { this.value = value; }
	@Override
	public Value add(Value other) {
		return new IntValue(value + ((IntValue)other).value);
	}
	public Value and(Value other) {
		return new IntValue(value & ((IntValue)other).value);
	}
	
	
}

class BooleanValue implements AndableValue {
	boolean value;
	BooleanValue(Boolean value) { this.value = value; }
	@Override
	public Value and(Value other) {
		return new BooleanValue(value && ((BooleanValue)other).value);
	}
	
}

class StringValue implements AddableValue {
	String value;
	StringValue(String value) { this.value = value; }
	@Override
	public Value add(Value other) {
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
			if (value1 instanceof AndableValue a)
				return a.and(value2);
			else
				throw new RuntimeException("Type error");
		}
		default -> throw new RuntimeException("Operator not supported");
		}
	}
}
