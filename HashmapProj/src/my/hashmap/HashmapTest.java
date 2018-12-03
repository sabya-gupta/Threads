package my.hashmap;

import java.util.HashMap;

public class HashmapTest {

	public static void main(String[] args) {
		HashMap<Object, ValueObjectToStore> hm = new HashMap<>();

		// 1st Object
		KeysObjectWithHashCodeAndEqualsOverriden koo = new KeysObjectWithHashCodeAndEqualsOverriden();
		koo.key = "2345";
		koo.arbitraryhashCode = 5678;

		ValueObjectToStore vos2 = new ValueObjectToStore();
		vos2.value = 2345;

		hm.put(koo, vos2);

		// 2nd Object
		KeysObjectWithHashCodeAndEqualsOverriden koo2 = new KeysObjectWithHashCodeAndEqualsOverriden();
		koo2.key = "23456";
		koo2.arbitraryhashCode = 56789;
		ValueObjectToStore vos3 = new ValueObjectToStore();
		vos3.value = 23456;
		hm.put(koo2, vos3);

		// I will get the second value object with second key object
		System.out.println(hm.get(koo2).value);

		// I will get the first value object with the second key object!!
		koo2.key = "2345";
		koo2.arbitraryhashCode = 5678;
		System.out.println(hm.get(koo2).value);
		
		//So it all depends on the implementation of hashcode and equals method...

	}

}

class KeysObjectWithHashCodeAndEquals {
	public String key;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((key == null) ? 0 : key.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		KeysObjectWithHashCodeAndEquals other = (KeysObjectWithHashCodeAndEquals) obj;
		if (key == null) {
			if (other.key != null)
				return false;
		} else if (!key.equals(other.key))
			return false;
		return true;
	}
}

class KeysObjectWithHashCodeAndEqualsOverriden {
	public String key;

	public int arbitraryhashCode;

	@Override
	public int hashCode() {
		return arbitraryhashCode;
	}

	@Override
	public boolean equals(Object obj) {
		return (this.key).equalsIgnoreCase(((KeysObjectWithHashCodeAndEqualsOverriden) obj).key);
	}
}

class ValueObjectToStore {
	public int value;
}
