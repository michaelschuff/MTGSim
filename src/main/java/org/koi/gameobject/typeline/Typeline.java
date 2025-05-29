package org.koi.gameobject.typeline;


import org.koi.util.Constants;

import java.math.BigInteger;
import java.util.ArrayList;

import static org.koi.util.Constants.typeToIndex;

public class Typeline {
	private final BigInteger value;

    Typeline(BigInteger value) {
        this.value = value;
    }

	public boolean isType(String type) {
		return value.testBit(typeToIndex(type));
	}
	public boolean isPermanent() {
		return !(value.testBit(typeToIndex("Instant")) || value.testBit(typeToIndex("Sorcery")));
	}

	public Typeline and(Typeline o) {
		return new Typeline(this.value.and(o.value));
	}
	public Typeline minus(Typeline o) {
		return new Typeline(this.value.andNot(o.value));
	}
	public Typeline or(Typeline o) {
		return new Typeline(this.value.or(o.value));
	}

	public String toString() {
		BigInteger v = value;
		ArrayList<String> Supertypes = new ArrayList<>();
		ArrayList<String> Cardtypes = new ArrayList<>();
		ArrayList<String> Subtypes = new ArrayList<>();
		while (v.signum() != 0) {
			int i = v.lowestSetBit;
			String type = Constants.allTypes[i];
			int superIndex = Constants.superTypes.binarySearch(type);
			int cardIndex = Constants.cardTypes.binarySearch(type);
			if (superIndex >= 0) {
				Supertypes.add(type);
			} else if (cardIndex >= 0) {
				Cardtypes.add(type);
			} else {
				Subtypes.add(type);
			}
			v = v.clearBit(i);
		}

		StringBuilder result = new StringBuilder();
		for (String s : Supertypes) {
			result.append(s).append(" ");
		}
		for (String s : Cardtypes) {
			result.append(s).append(" ");
		}
		if (!Subtypes.isEmpty())
			result.append("-- ");
		for (String s : Subtypes) {
			result.append(s).append(" ");
		}
		return result.toString();
	}
}
